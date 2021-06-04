# java线程池创建的简单实例

线程池 
1、线程分为内核线程KLT和用户线程ULT     Android中：ULT（APP使用的线程）     KLT（系统使用的）

2、我们的jvm虚拟机大多数都是内核线程klt
这个可以编译一下我的Hello.java文件，将for循环数值调整到2000，运行，然后打开你的windows系统任务管理器，可以看到系统进程多了二千个，这就是klt。如果ult的话那么不会显示到系统的任务管理中去。

3、线程池的创建看TheadModel文件,Task是每个线程运行的任务。

4、线程池适合用于单个任务工作量少(耗时少)   且任务数量多的情况中。

5、关于KLT多线程的执行中，cpu会给每个线程分配不一致的执行时间，如果这个线程在执行时间内没有执行完，那么他当前的状态会被保存到内存中（一个用来保存在分配时间里未完成的线程任务中断时的状态 的容器），当cpu执行完所有的任务队列后，再从内存中取出未执行完的线程继续执行。

6、我在Android开发中还未接触到过，大致了解一下线程池原理。

7、线程池可以存入的任务数量，不可超过（maximumPoolSize+workQueu）的数量。

8、线程池运行时调用execute( )存入任务，存储任务后调用shutdown( )来让线程池执行完当前所有任务后不再接受新任务，并且变为TIDYING，接着走向线程池彻底终止

线程池的五种状态	
1、RUNNING
(1) 状态说明：线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理。
(2) 状态切换：线程池的初始化状态是RUNNING。换句话说，线程池被一旦被创建，就处于RUNNING状态，并且线程池中的任务数为0！

2、 SHUTDOWN
(1) 状态说明：线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。
(2) 状态切换：调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN。

3、STOP
(1) 状态说明：线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。
(2) 状态切换：调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP。

4、TIDYING
(1) 状态说明：当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。terminated()在ThreadPoolExecutor类中是空的，若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现。
(2) 状态切换：当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING。
当线程池在STOP状态下，线程池中执行的任务为空时，就会由STOP -> TIDYING。

5、 TERMINATED
(1) 状态说明：线程池彻底终止，就变成TERMINATED状态。
(2) 状态切换：线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED。
