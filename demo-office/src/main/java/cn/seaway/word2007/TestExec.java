package cn.seaway.word2007;

import java.io.IOException;

public class TestExec {
    public static void main(String[] args) {
        String command = "java -jar /opt/jodconverter-cli-2.2.2.jar 3.docx 04101011.pdf";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (IOException e) {
            System.err.println("IOException " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }
}
