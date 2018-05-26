创建默认线程池:
* newSingleThreadExecutor：一个单线程的线程池，可以用于需要保证顺序执行的场景，并且只有一个线程在执行。
* newFixedThreadPool：一个固定大小的线程池，可以用于已知并发压力的情况下，对线程数做限制。
* newCachedThreadPool：一个可以无限扩大的线程池，比较适合处理执行时间比较小的任务。
* newScheduledThreadPool：可以延时启动，定时启动的线程池，适用于需要多个后台线程执行周期任务的场景。
* newWorkStealingPool：一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行。

拒绝策略:
AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作
CallerRunsPolicy 策略：只要线程池未关闭，该策略直接在调用者线程中，运行当前的被丢弃的任务
DiscardOleddestPolicy策略： 该策略将丢弃最老的一个请求，也就是即将被执行的任务，并尝试再次提交当前任务
DiscardPolicy策略：该策略默默的丢弃无法处理的任务，不予任何处理


ThreadPoolExecutor构造函数的定义(可自定义线程池)：
*corePoolSize
 线程池大小，决定着新提交的任务是新开线程去执行还是放到任务队列中，也是线程池的最最核心的参数。一般线程池开始时是没有线程的，只有当任务来了并且线程数量小于corePoolSize才会创建线程。
*maximumPoolSize
 最大线程数，线程池能创建的最大线程数量。
*keepAliveTime
 在线程数量超过corePoolSize后，多余空闲线程的最大存活时间。
*unit
 时间单位
*workQueue
 存放来不及处理的任务的队列，是一个BlockingQueue。
*threadFactory
 生产线程的工厂类，可以定义线程名，优先级等。
*handler
 拒绝策略，当任务来不及处理的时候，如何处理, 前面有讲解。