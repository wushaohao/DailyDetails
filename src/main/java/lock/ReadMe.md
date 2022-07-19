ReentrantLock与Synchronize:
synchronized 的一些限制： 
     1.1 ：无法中断正在等候获取一个锁的线程 
     1.2 ：无法通过投票得到一个锁 
     1.3 ：释放锁的操作只能与获得锁所在的代码块中进行，无法在别的代码块中释放锁 。
 
ReentrantLock类实现了Lock,它拥有与 synchronized 相同的并发性和内存语义，但是添加了类似锁投票、定时锁等候和可中断锁等候的一些特性。此外，它还提供了在激烈争用情况下更佳的性能。
定时锁等候：设置定时等候之后，在这个等候时间内如果没有获得这个锁，这个线程就会自己中断。
可中断锁等候：就是线程等候可以自己中断也可以别人中断。

* 线程中断:
    每个线程都有一个线程中断标志位来标志该线程是否被中断。我们可以通过检测该标志位是否为true来判断该线程是否被中断。调用线程的thread.interrupt()方法，将会设置该线程为中断状态，
即设置为true。线程中断后的结果是死亡、还是等待新的任务或是继续运行至下一步，
取决于这个程序本身。线程会不时地检测这个中断标识位，以判断线程是否应该被中断（中断标识值是否为true）。它并不像stop方法那样会中断一个正在运行的线程。
当我们调用thread.interrupt()方法时，会将thread的中断标志位设为true。通过isInterrupted()方法可以判断标志位是否为true。此后程序是该继续还是结束就取决于程序本身了。
    注意：如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、1.5中的condition.await、以及可中断的通道上的 I/O 操作方法后可进入阻塞状态），
则在线程在检查中断标识时如果发现中断标示为true，则会在这些阻塞方法（sleep、join、wait、1.5中的condition.await及可中断的通道上的 I/O 操作方法）调用处抛出InterruptedException异常，
并且在抛出异常后立即将线程的中断标别位清除，即重新设置为false。抛出异常是为了线程从阻塞状态醒过来，并在结束线程前让程序员有足够的时间来处理中断请求。一个抛出了InterruptedException的线程的状态马上就会被置为非中断状态，
如果catch语句没有处理异常，则下一 次循环中isInterrupted()为false，线程会继续执行，可能你N次抛出异常，也无法让线程停止.

   * synchronized在获锁的过程中是不能被中断的，意思是说如果产生了死锁，则不可能被中断（请参考后面的测试例子）。与synchronized功能相似的reentrantLock.lock()方法也是一样，它也不可中断的，即如果发生死锁，
那么reentrantLock.lock()方法无法终止，如果调用时被阻塞，则它一直阻塞到它获取到锁为止。但是如果调用带超时的tryLock方法reentrantLock.tryLock(long timeout, TimeUnit unit)，那么如果线程在等待时被中断，将抛出一个InterruptedException异常，
这是一个非常有用的特性，因为它允许程序打破死锁。你也可以调用reentrantLock.lockInterruptibly()方法，它就相当于一个超时设为无限的tryLock方法.

* 设置中断信号量。
中断线程最好的，最受推荐的方式是，使用共享变量（shared variable）发出信号，告诉线程必须停止正在运行的任务。线程必须周期性的核查这一变量，然后有秩序地中止任务。