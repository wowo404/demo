package org.liu.objectserver;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ObjectClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 4123);
        BufferedInputStream bis  = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos  = new BufferedOutputStream(socket.getOutputStream());

        Hello hello = (Hello) readObject(bis);
        System.out.println("receive hello," + hello.getServerName());

//        System.out.println(readInputStream(bis));

        DataTransport dataTransport = new DataTransport();
        dataTransport.setId(1);
        dataTransport.setName("liu");
        dataTransport.setBirthday(new Date());
        writeObject(dataTransport, bos);

        System.out.println("----------write object over-------");

        dataTransport = (DataTransport) readObject(bis);
        System.out.println(dataTransport.getCallback());
    }

    private static void writeObject(DataTransport dataTransport, BufferedOutputStream bos) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(dataTransport);
        oos.flush();
    }

    private static Object readObject(BufferedInputStream bis) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    private static String readInputStream(BufferedInputStream bin)
            throws IOException {
        if(bin==null) {
            return null;
        }
        byte data[] = null;
        int s = bin.read();
        if(s==-1) {
            return null; //Connection lost
        }
        int alength = bin.available();
        if(alength > 0) {
            data = new byte[alength+1];

            bin.read(data, 1, alength);
        } else {
            data = new byte[1];
        }
        data[0] = (byte)s;
        return new String(data);
    }

}
