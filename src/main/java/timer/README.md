**定时任务调度**

 基于给定的时间点给定的时间间隔或者给定的执行次数自动执行的任务
 
 
**Timer**
   一种工具,线程用前排以后在后台线程中执行的任务.安排任务执行一次,或者定期重复执行(有且仅有一个后台线程对多个业务线程进行定时频率的调度)
   Timer-------定时调用-------->TimerTask
 
  **函数**

  **schedule** 
  
   `1.`schedule(task,time):在时间等于或超过time的时候执行且仅执行一次task
       task--所安排的任务
       time--执行任务的时间
   `2.`schedule(task,time,period):时间等于或超过time的时候首次执行task,之后每隔period毫秒后重复执行task
       task--所安排的任务
       time--首次执行任务的时间
       period--执行一次task的时间间隔,单位是毫秒
   `3.`schedule(task,delay):等到delay毫秒后执行且仅执行一次task
       task--所安排的任务
       delay--执行任务前的延时时间,单位是毫秒
   `4.`schedule(task,delay,period):等待delay毫秒后首次执行task,每隔period毫秒后重复执行task
       task--所安排的任务
       delay--执行任务前的延时时间,单位是毫秒
       period--执行一次task的时间间隔，单位是毫秒
       
  **scheduleAtFixedRate**
  
    `1.`scheduleAtFixedRate(task,time,period):时间等于或超过time时首次执行 之后间隔period之后重复执行task
       task--所安排的任务
       time--首次执行任务的时间
       period--执行一次task的时间间隔，单位是毫秒
    `2.`scheduleAtFixedRate(task,delay,period):等待delay毫秒后首次执行task 之后每隔period毫秒后重复执行
       task--所安排的任务
       delay--执行任务前的延时时间,单位是毫秒
       period--执行一次task的时间间隔，单位是毫秒
       
  **cancel()**:
  
     取消当前TimerTask里的任务
  
  
  **scheduledExecutionTime()**
  
      返回此任务最近实际执行的以安排执行的时间
      返回值:最近发生此任务执行安排的时间,为long型
      
  **purge()**
  
     从此计时器的任务队列中移除所有的 已取消 的任务(返回值是从队列中移除的任务数)
  
  **schedule与scheduleAtFixedRate区别**
  
      `1.`首次计划执行的时间遭遇当前的时间
         schedule:"fixed-delay";如果第一次执行时间被delay了,随后的执行时间按照上一次实际执行完成时间点进行计算
         scheduleAtFixedRate:"fixed-rate";如果第一次执行时间被delay了,随后的执行时间按照上一次开始的时间点进行计算,并且
       为了赶上进度会多次执行任务,因此TimerTask中的执行体需要考虑同步.
      `2.`任务执行所需时间超出任务的执行周期间隔
         schedule:下一次执行时间相对于上一次实际执行完成的时间点,因此执行时间会不断延后
         scheduleAtFixedRate:下一次执行时间相对于上一次开始的时间点,因此知心时间一般不会延后,因此存在并发行
**缺陷**
  管理并发任务的缺陷:Timer有且仅有一个线程去执行定时任务,如果存在镀铬任务,且任务时间过长,会导致执行效果与预期不符
  当任务抛出异常时的缺陷:如果TimerTask执行时抛出RuntimeException,Timer会停止所有任务的运行
  
**Timer使用禁区**
  对时效性要求较高的多任务并发作业
  对复杂的任务的调度
  
  
**Quartz**
