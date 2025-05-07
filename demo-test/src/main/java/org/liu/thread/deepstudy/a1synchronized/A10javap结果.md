```text
Classfile /D:/work/workspace-idea/demo/demo-test/target/classes/org/liu/thread/deepstudy/SyncUsingWay.class
  Last modified 2025年4月7日; size 764 bytes
  SHA-256 checksum 88f0fad6e58278bb443d51a66f7af80490cb64b5df48c139d611a1250ee98c53
  Compiled from "SyncUsingWay.java"
public class org.liu.thread.deepstudy.SyncUsingWay
  minor version: 0
  major version: 61
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #21                         // org/liu/thread/deepstudy/SyncUsingWay
  super_class: #2                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 4, attributes: 1
Constant pool:
   #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
   #2 = Class              #4             // java/lang/Object
   #3 = NameAndType        #5:#6          // "<init>":()V
   #4 = Utf8               java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Fieldref           #8.#9          // java/lang/System.out:Ljava/io/PrintStream;
   #8 = Class              #10            // java/lang/System
   #9 = NameAndType        #11:#12        // out:Ljava/io/PrintStream;
  #10 = Utf8               java/lang/System
  #11 = Utf8               out
  #12 = Utf8               Ljava/io/PrintStream;
  #13 = String             #14            // syncCodeBlock
  #14 = Utf8               syncCodeBlock
  #15 = Methodref          #16.#17        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #16 = Class              #18            // java/io/PrintStream
  #17 = NameAndType        #19:#20        // println:(Ljava/lang/String;)V
  #18 = Utf8               java/io/PrintStream
  #19 = Utf8               println
  #20 = Utf8               (Ljava/lang/String;)V
  #21 = Class              #22            // org/liu/thread/deepstudy/SyncUsingWay
  #22 = Utf8               org/liu/thread/deepstudy/SyncUsingWay
  #23 = Utf8               Code
  #24 = Utf8               LineNumberTable
  #25 = Utf8               LocalVariableTable
  #26 = Utf8               this
  #27 = Utf8               Lorg/liu/thread/deepstudy/SyncUsingWay;
  #28 = Utf8               syncMethod
  #29 = Utf8               syncStaticMethod
  #30 = Utf8               StackMapTable
  #31 = Class              #32            // java/lang/Throwable
  #32 = Utf8               java/lang/Throwable
  #33 = Utf8               SourceFile
  #34 = Utf8               SyncUsingWay.java
{
  public org.liu.thread.deepstudy.SyncUsingWay();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V //注意此init方法
         4: return
      LineNumberTable:
        line 6: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lorg/liu/thread/deepstudy/SyncUsingWay;

  public synchronized void syncMethod();
    descriptor: ()V
    flags: (0x0021) ACC_PUBLIC, ACC_SYNCHRONIZED //注意此处的ACC_SYNCHRONIZED
    Code:
      stack=0, locals=1, args_size=1
         0: return
      LineNumberTable:
        line 10: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       1     0  this   Lorg/liu/thread/deepstudy/SyncUsingWay;

  public static synchronized void syncStaticMethod();
    descriptor: ()V
    flags: (0x0029) ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED //注意此处的ACC_STATIC, ACC_SYNCHRONIZED
    Code:
      stack=0, locals=0, args_size=0
         0: return
      LineNumberTable:
        line 15: 0

  public void syncCodeBlock();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter //注意此处的monitorenter：开始进入同步代码块
         4: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
         7: ldc           #13                 // String syncCodeBlock
         9: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        12: aload_1
        13: monitorexit //注意此处的monitorexit：正常退出同步代码块
        14: goto          22
        17: astore_2
        18: aload_1
        19: monitorexit //注意此处的monitorexit：异常退出同步代码块
        20: aload_2
        21: athrow
        22: return
      Exception table: //注意此table，是配合异常退出同步块使用的
         from    to  target type
             4    14    17   any
            17    20    17   any
      LineNumberTable:
        line 18: 0
        line 19: 4
        line 20: 12
        line 21: 22
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      23     0  this   Lorg/liu/thread/deepstudy/SyncUsingWay;
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 17
          locals = [ class org/liu/thread/deepstudy/SyncUsingWay, class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
        frame_type = 250 /* chop */
          offset_delta = 4
}
SourceFile: "SyncUsingWay.java"
```