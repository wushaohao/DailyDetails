**Executor、Executors、ExecutorService**
  Executor, ExecutorService, 和 Executors 最主要的区别是 Executor 是一个抽象层面的核心接口(大致代码如下):
    
    public interface Executor {
        void execute(Runnable command);
    }
   
   **ExecutorService**
       ExecutorService 接口 对 Executor 接口进行了扩展，提供了返回 Future 对象，终止，关闭线程池等方法。当调用 shutDown 方法时，
   线程池会停止接受新的任务，但会完成正在 pending 中的任务.
       Future 对象提供了异步执行，这意味着无需等待任务执行的完成，只要提交需要执行的任务，然后在需要时检查 Future 是否已经有了结果，如果任务已经执行完成，
   就可以通过 Future.get() 方法获得执行结果。需要注意的是，Future.get()方法是一个阻塞式的方法，如果调用时任务还没有完成，会等待直到任务执行结束。
   通过ExecutorService.submit() 方法返回的 Future 对象，还可以取消任务的执行。Future 提供了cancel()方法用来取消执行pending[pending:未发生的、行将发生的]中的任务
   ExecutorService 部分代码如下:
   
       public interface ExecutorService extends Executor {
           void shutdown();
           <T> Future<T> submit(Callable<T> task);
           <T> Future<T> submit(Runnable task, T result);
           <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;
       }
   
   **Executors**
   Executors 是一个工具类，类似于 Collections。提供工厂方法来创建不同类型的线程池，比如 FixedThreadPool 或 CachedThreadPool。
   
       public class Executors {
           public static ExecutorService newFixedThreadPool(int nThreads) {
               return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
               }
                
            public static ExecutorService newCachedThreadPool() {
               return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
               }
       }
       
  **Executor vs ExecutorService vs Executors**     
   Executor 和 ExecutorService 这两个接口主要的区别是:ExecutorService接口继承了Executor接口,是Executor的子接口
   Executor 和 ExecutorService 第二个区别是:Executor接口定义了execute()方法用来接收一个Runnable接口的对象,而ExecutorService 接口中的submit()方法可以接受Runnable和Callable接口的对象。
   Executor 和 ExecutorService 接口第三个区别是Executor中的execute()方法不返回任何结果,而ExecutorService 中的submit()方法可以通过一个Future对象返回运算结果。
   Executor 和 ExecutorService 接口第四个区别是除了允许客户端提交一个任务,ExecutorService 还提供用来控制线程池的方法.比如:调用shutDown()方法终止线程池
   Executors类提供工厂方法用来创建不同类型的线程池.比如:newSingleThreadExecutor() 创建一个只有一个线程的线程池,newFixedThreadPool(int numOfThreads)来创建固定线程数的线程池，newCachedThreadPool()可以根据需要创建新的线程，但如果已有线程是空闲的会重用已有线程

  **Executor与ExecutorService比较**
                  Executor                                                   ExecutorService
   Executor是java线程池核心接口,用来并发执行提交的任务               ExecutorService是Executor接口的扩展,提供了异步执行和关闭线程池的方法
   提供void execute()方法来执行提交任务                            提供void execute(Runnable command)、Future<T> submit(Callable<T> task)和Future<?>submit(Runnable task)方法获取执行结果[参数是Callable可以get到结果、但是Runnable执行get获取到的只能是null]
   不能取消任务                                                  Future.cancel()取消还未执行(pending)的任务future.get()阻塞获取执行结果
   没有提供关闭线程池的方法                                        提供了关闭线程池的方法shutdown()、shutdownNow()
  
  
  **总结**
      利用 Executors 类提供的工厂方法来创建一个线程池是很方便，但对于需要根据实际情况自定义线程池某些参数的场景，就不太适用了,当线程池中的线程均处于工作状态，并且线程数已达线程池允许的最大线程数时，
   就会采取指定的饱和策略来处理新提交的任务
      如果使用 Executors 的工厂方法创建的线程池，那么饱和策略都是采用默认的 AbortPolicy，所以如果我们想当线程池已满的情况，使用调用者的线程来运行任务，就要自己创建线程池，指定想要的饱和策略，而不是使用 Executors 了
   所以我们可以根据需要创建 ThreadPoolExecutor(ExecutorService接口的实现类) 对象，自定义一些参数，而不是调用 Executors 的工厂方法创建。
   当然，在使用 Spring 框架的项目中，也可以使用 Spring 提供的 ThreadPoolTaskExecutor 类来创建线程池。ThreadPoolTaskExecutor 与 ThreadPoolExecutor 类似，也提供了许多参数用来自定义线程池，比如：核心线程池大小，线程池最大数量，饱和策略，线程活动保持时间等等

 **线程池关闭**
    我们可以通过调用线程池的shutdown或shutdownNow 方法来关闭线程池,它们的原理是遍历线程池中的工作线程，然后逐个调用线程的interrupt方法来中断线程,所以无法响应中断的任务可 能永远无法终止。
 但是它们存在一定的的区别,shutdownNow首先将线程池的状态设置成STOP,然后尝试停止所有的正在执行或暂停任务的线程,并返回等待执行任务的列表,而shutdown只是将线程池的状态设置成 SHUTDOWN 状态，
 然后中断所有没有正在执行任务的线程.只要调用了这两个关闭方法的其中一个,isShutdown 方法就会返回true.当所有的任务都已关闭后,才表示线程池关闭成功,这时调用isTerminaed 方法会返回 true。
    至于我们应该调用哪一种方 法来关闭线程池,应该由提交到线程池的任务特性决定，通常调用 shutdown 来关闭线程池,如果任务不一定要执行完,则可以调用shutdownNow。
 

**创建默认线程池**

   newSingleThreadExecutor：一个单线程的线程池，可以用于需要保证顺序执行的场景，并且只有一个线程在执行。
   newFixedThreadPool：一个固定大小的线程池，可以用于已知并发压力的情况下，对线程数做限制。
   newCachedThreadPool：一个可以无限扩大的线程池，比较适合处理执行时间比较小的任务。
   newScheduledThreadPool：可以延时启动，定时启动的线程池，适用于需要多个后台线程执行周期任务的场景。
   newWorkStealingPool：一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行。

**拒绝策略**

   AbortPolicy策略:该策略会直接抛出异常，阻止系统正常工作
   CallerRunsPolicy 策略:只要线程池未关闭，该策略直接在调用者线程中，运行当前的被丢弃的任务
   DiscardOldestPolicy策略:该策略将丢弃最老的一个请求，也就是即将被执行的任务，并尝试再次提交当前任务
   DiscardPolicy策略:该策略默默的丢弃无法处理的任务，不予任何处理


**ThreadPoolExecutor构造函数的定义(可自定义线程池)**

   *corePoolSize*:线程池大小，决定着新提交的任务是新开线程去执行还是放到任务队列中，也是线程池的最最核心的参数。一般线程池开始时是没有线程的，只有当任务来了并且线程数量小于corePoolSize才会创建线程。
   
   *maximumPoolSize*:最大线程数，线程池能创建的最大线程数量。
  
   *keepAliveTime*:在线程数量超过corePoolSize后，多余空闲线程的最大存活时间。
   
   *unit*:时间单位
   
   *workQueue*:存放来不及处理的任务的队列，是一个BlockingQueue。
   
   *threadFactory*:生产线程的工厂类，可以定义线程名，优先级等。
   
   *handler*:拒绝策略，当任务来不及处理的时候，如何处理, 前面有讲解。
   
 **线程池工作顺序:**
   (corePoolSize -> 任务队列 -> maximumPoolSize -> 拒绝策略)
   `1.`如果运行的线程少于corepoolol大小，执行程序总是喜欢添加一个新线程，而不是排队。
   `2.`如果corePoolSize或更多的线程正在运行，执行程序总是希望对请求进行排队，而不是添加新的线程。
   `3.`如果一个请求不能排队，就会创建一个新的线程，除非这个线程的大小超过最大值，在这种情况下，任务将被拒绝
   
  **可以向线程池提交的任务有两种**
   Runnable和Callable，二者的区别如下：
   `a.`方法签名不同，void Runnable.run(), V Callable.call() throws Exception
   `b.`是否允许有返回值，Callable允许有返回值
   `c.`是否允许抛出异常，Callable允许抛出异常
  
 **三种提交方式**
  Future<T> submit(Callable<T> task)   关心返回结果
  Future<?> submit(Runnable task)      不关心返回结果,虽然返回Future但是get()方法结果总是返回null
  void execute(Runnable command)       不关心返回结果

**Java 中 Synchronized 关键字**

     
   Synchronized 块是 Java 提供的一种原子性内置锁，Java 中每个对象都可以当做一个同步锁的功能来使用，这些 Java 内置的使用者看不到的锁被称为内部锁，也叫做监视器锁。
线程在进入 Synchronized 代码块前会自动尝试获取内部锁，如果这时候内部锁没有被其他线程占有，则当前线程就获取到了内部锁，这时候其它企图访问该代码块的线程会被阻塞挂起。
拿到内部锁的线程会在正常退出同步代码块或者异常抛出后或者同步块内调用了该内置锁资源的 wait 系列方法时候释放该内置锁；
 内置锁是排它锁，也就是当一个线程获取这个锁后，其它线程必须等待该线程释放锁才能获取该锁。
 上一节讲了多线程并发修改共享变量时候会存在内存不可见的问题，究其原因是因为 Java 内存模型中线程操作共享变量时候会从自己的工作内存中获取而不是从主内存获取或者线程写入到本地内存的变量没有被刷新会主内存。
 下面讲解下 Synchronized 的一个内存语义，这个内存语义就可以解决共享变量内存不可见性问题。
 线程进入 Synchronized 块的语义是会把在 Synchronized 块内使用到的变量从线程的工作内存中清除，在 Synchronized 块内使用该变量时候就不会从线程的工作内存中获取了，而是直接从主内存中获取；
 退出 Synchronized 块的内存语义是会把 Synchronized 块内对共享变量的修改刷新到主内存。
 对应上面一节讲解的假如线程在 Synchronized 块内获取变量 X 的值，那么线程首先会清空所在的 CPU 的缓存，然后从主内存获取变量 X 的值；
 当线程修改了变量的值后会把修改的值刷新回主内存。
 其实这也是加锁和释放锁的语义，当获取锁后会清空本地内存中后面将会用到的共享变量，在使用这些共享变量的时候会从主内存进行加载；
 在释放锁时候会刷新本地内存中修改的共享变量到主内存。
 除了可以解决共享变量内存可见性问题外，Synchronized 经常被用来实现原子性操作，另外注意，Synchronized 关键字会引起线程上下文切换和线程调度的开销


**Java 中  关键字**

 上面介绍了使用锁的方式可以解决共享变量内存可见性问题，但是使用锁太重，因为它会引起线程上下文的切换开销，对于解决内存可见性问题，Java 还提供了一种弱形式的同步，也就是使用了 volatile 关键字。
 一旦一个变量被 volatile 修饰了，当线程获取这个变量值的时候会首先清空线程工作内存中该变量的值，然后从主内存获取该变量的值.
 当线程写入被 volatile 修饰的变量的值的时候，首先会把修改后的值写入工作内存，然后会刷新到主内存。这就保证了对一个变量的更新对其它线程马上可见


**CyclicBarrier和 CountDownLatch区别**

`1.`CyclicBarrier的某个线程运行到某个点上之后，该线程即停止运行，直到所有的线程都到达了这个点，所有线程才重新运行；CountDownLatch则不是，某线程运行到某个点上之后，只是给某个数值-1而已，该线程继续运行

`2.`CyclicBarrier只能唤起一个任务，CountDownLatch可以唤起多个任务

`3.`CyclicBarrier可重用，CountDownLatch不可重用，计数值为0该CountDownLatch就不可再用了


**线程分类**

`1.`Java中的线程可以分为两类，即用户线程和守护线程。
   用户线程是为了完成任务，而守护线程是为其他线程服务
守护线程的唯一用途是为其他线程提供服务。守护线程会随时中断，因此不要在守护线程上使用需要释放资源的资源，如输入输出流，数据库连接等，所有的守护线程都是后台线程，如果虚拟机中只剩下守护线程，虚拟机就会退出

`2.`Thread类与守护线程相关方法：
   isDaemon():测试一个线程是否为守护线程
   setDaemon(boolean on):将一个线程标记为守护线程或用户线程
