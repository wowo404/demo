package org.liu.exe;

import java.io.*;

public class EXEToJar {

    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(args[0]); // 可以将整个exe文件解码
        FileOutputStream fout = new FileOutputStream(args[1]);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(fout);
        int in = 0;
        do {
            in = bin.read();
            if (in == -1)
                break;
            in ^= 0x88;
            bout.write(in);
        } while (true);
        bin.close();
        fin.close();
        bout.close();
        fout.close();
    }

}
