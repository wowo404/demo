package org.liu.thread.deepstudy.a0thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class A1ThreadPrint {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
        //[1] main 主线程
        //[2] Reference Handler 引用处理的线程。强、软、弱、虚。-GC有不同表现，在jvm课程中深入分析
        //[3] Finalizer GC线程。1.只有当开始一轮垃圾收集的时候，才会开始调用finalize方法。
        //                     2.daemon prio=10 高优先级的守护线程
        //                     3.jvm在垃圾收集的时候，会将失去引用的对象封装到Finalizer对象（Reference），放入F-queue队列中。有Finalizer线程执行finalize方法
        //[4] Signal Dispatcher 信号分发器线程：收到其他进程和jvm进程的交互后，负责分发信号，如通过cmd发送jstack，传到了jvm进程，此时信号分发器起作用
        //[5] Attach Listener 附加监听器线程：jvm进程和其他进程的交互，如cmd、jqs、jmap、dump等。
        //                      开启此线程的两种方式：1.是通过jvm参数开启，-XX:StartAttachListener
        //                                        2.延迟开启，如通过cmd执行命令java -version后，jvm适时开启此线程
        //[13] Common-Cleaner
        //[14] Monitor Ctrl-Break idea开发工具的通过反射的方式开启的一个随着jvm进程开启和关闭的监听线程
        //[15] Notification Thread

    }

}
