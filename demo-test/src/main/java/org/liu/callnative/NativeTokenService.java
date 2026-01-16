package org.liu.callnative;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

public class NativeTokenService {

    // 定义 JNA 接口
    public interface TokenLibrary extends Library {
        // 加载库
        // 在 macOS 上，它会寻找 libtokenlib.dylib
        // 在 Windows 上，它会寻找 tokenlib.dll
        // 在 Linux 上，它会寻找 libtokenlib.so
        // 如果您的文件名为 tokenlib.dll 但运行在 Mac 上，请重命名为 libtokenlib.dylib (且必须是 Mac 编译的)
        TokenLibrary INSTANCE = Native.load("tokenlib.dll", TokenLibrary.class);

        // 对应 Go 的 export GenerateToken
        // Go string return type is actually a struct { char* data; long len; } in C ABI usually,
        // but JNA maps String to char*.
        // If Go returns a Go string, it's incompatible with Java String directly unless Go uses C.CString.
        // Assuming Go function returns *C.char (C string).
        String GenerateToken(int seq, int val, int cmd, long deviceId, byte[] key, int keyLen);

        // For ParseToken, if it returns multiple values via pointers:
        // Go/C signature: char* ParseToken(char* token, long deviceId, char* key, int keyLen, int* outSeq, int* outVal, int* outCmd);
        String ParseToken(String token, long deviceId, byte[] key, int keyLen, IntByReference outSeq, IntByReference outVal, IntByReference outCmd);
    }

    public static void main(String[] args) {
        try {
            System.out.println("OS: " + System.getProperty("os.name"));
            System.out.println("Arch: " + System.getProperty("os.arch"));

            // 尝试设置 jna.library.path 指向项目根目录 (如果 dll/dylib 在根目录)
            // System.setProperty("jna.library.path", "."); 

            byte[] keyBytes = "MySuperSecretKeyMySuperSecretKey".getBytes("UTF-8");
            Long targetDeviceId = 100100126011008601L;

            // 构造参数
            int seq = 0;
            int val = 999;
            int cmd = 1;

            System.out.println("Calling Native Go Library...");

            // 调用 Go 函数
            String token = TokenLibrary.INSTANCE.GenerateToken(
                    seq, val, cmd, targetDeviceId, keyBytes, keyBytes.length
            );

            System.out.println("Generated Token: " + token);
            System.out.println("Formatted: " + token.replaceAll("(.{3})", "$1 ").trim());

            // 使用 IntByReference 来接收返回值
            IntByReference outSeq = new IntByReference();
            IntByReference outVal = new IntByReference();
            IntByReference outCmd = new IntByReference();

            System.out.println("Parsing Token...");
            // 注意：这里假设 ParseToken 返回的是 JSON 字符串或者错误信息，
            // 并且通过指针参数返回解析出的数值。
            // 如果 Go 函数签名不是这样，会导致崩溃。
            String parsedResult = TokenLibrary.INSTANCE.ParseToken(
                    token, targetDeviceId, keyBytes, keyBytes.length, outSeq, outVal, outCmd
            );

            System.out.println("Parse Result: " + parsedResult);
            System.out.println("Parsed Seq: " + outSeq.getValue());
            System.out.println("Parsed Val: " + outVal.getValue());
            System.out.println("Parsed Cmd: " + outCmd.getValue());

        } catch (Throwable e) {
            // 捕获 Throwable 以便捕获 UnsatisfiedLinkError
            System.err.println("Error loading library or executing native method:");
            e.printStackTrace();
        }
    }
}