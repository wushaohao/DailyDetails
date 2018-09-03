CountDownLatch、CyclicBarrier、Semaphore

*CountDownLatch:
    CountDownLatch类只提供了一个构造器：
     * public CountDownLatch(int count) {};  //参数count为计数值
     * public void await() throws InterruptedException {};//调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行(哪个线程调用了await(),那么这个线程
       就会等待调用了countDown()方法的线程的计数为0时才会继续执行)
     * public boolean await(long timeout, TimeUnit unit) throws InterruptedException {};  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
     * public void countDown() {};  //将count值减1


*CyclicBarrier:
    //字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，
    CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了

    CyclicBarrier有2个构造函数:
    public CyclicBarrier(int parties, Runnable barrierAction) {
    }
    public CyclicBarrier(int parties) {
    }
    参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容

    CyclicBarrier中最重要的方法就是await方法：
    public int await() throws InterruptedException, BrokenBarrierException { };
    public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
    第一个版本比较常用，用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
    第二个版本是让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务

*Semaphore:
    Semaphore构造函数:
    public Semaphore(int permits) {          //参数permits表示许可数目，即同时可以允许多少线程进行访问
        sync = new NonfairSync(permits);
    }
    public Semaphore(int permits, boolean fair) {    //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
        sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
    }

    Semaphore方法(acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
                 release()用来释放许可。注意，在释放许可之前，必须先获获得许可。):
    public void acquire() throws InterruptedException {  }     //获取一个许可
    public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
    public void release() { }          //释放一个许可
    public void release(int permits) { }    //释放permits个许可
    这4个方法都会被阻塞，如果想立即得到执行结果(另外还可以通过availablePermits()方法得到可用的许可数目):
    public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
    public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { };

-------------------------------------------------
总结:
(1)CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：

CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；

而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；

另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。

2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。