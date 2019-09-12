# quickserver源码学习笔记
```
quickserver是一个tcp server，必然要使用ServerSocket来启动一个tcp服务，也必然要调用accept()方法接收客户端连接
代码调用路径是
org.quickserver.net.server.QuickServer
startServer()---458行，new Thread一个当前对象，因为QuickServer本身实现了Runnable，启动线程后就会运行run方法
run()---中间有调用initAllPools()方法初始化所有的对象池
makeServerSocket()---创建了ServerSocket，并配置了各种参数，然后调用了processServerHooks()处理各种服务器钩子，紧接着runNonBlocking()或runBlocking()
runBlocking()---在此方法中有一个while(true)，然后调用了server.accept()接收客户端连接，
    然后设置Socket的各种参数，并接着在2745行调用了ClientHandler的handleClient()方法，ClientHandler有个基础的实现类BasicClientHandler，
    在此基础类中将各种需要的属性设置了进去，真正使用的类型BlockingClientHandler和NonBlockingClientHandler都继承了它，
    然后在2754行调用了ClientPool的addClient()方法
addClient()---ClientPool持有一个list保存每一个待运行的ClientHandler，60行从pool拿到一个ClientThread，此处有一个比较绕的地方是，
    从pool中拿到的object是ClientThread类型，这是因为ObjectPool有一个实现类BasicObjectPool，BasicObjectPool实现了了方法borrowObject()，
    里面调用了addObject()方法，方法中调用了factory.makeObject()，这里的PoolableObjectFactory的实现类是ThreadObjectFactory，
    ThreadObjectFactory的makeObject()方法就返回了ClientThread，最后在89行调用了notify()，
ClientThread.run()---133行从pool中拿到一个client，137调用executeClient()，
executeClient()---107行调用run()
```

## 从stacktrace更容易看出调用轨迹
``
[StackTrace: java.net.SocketTimeoutException: Read timed out
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:170)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.ObjectInputStream$PeekInputStream.read(ObjectInputStream.java:2313)
	at java.io.ObjectInputStream$PeekInputStream.readFully(ObjectInputStream.java:2326)
	at java.io.ObjectInputStream$BlockDataInputStream.readShort(ObjectInputStream.java:2797)
	at java.io.ObjectInputStream.readStreamHeader(ObjectInputStream.java:802)
	at java.io.ObjectInputStream.<init>(ObjectInputStream.java:299)
	at org.quickserver.net.server.impl.BlockingClientHandler.setInputStream(BlockingClientHandler.java:57)
	at org.quickserver.net.server.impl.BlockingClientHandler.updateInputOutputStreams(BlockingClientHandler.java:506)
	at org.quickserver.net.server.impl.BasicClientHandler.prepareForRun(BasicClientHandler.java:644)
	at org.quickserver.net.server.impl.BlockingClientHandler.run(BlockingClientHandler.java:156)
	at org.quickserver.util.pool.thread.ClientThread.executeClient(ClientThread.java:107)
	at org.quickserver.util.pool.thread.ClientThread.run(ClientThread.java:137)
]
``