package client;

import ggTCalculator.LogPOA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogImpl extends LogPOA {

    File file;

    public LogImpl() {
        file = new File(new SimpleDateFormat("HHmmss").format(new Date()) + ".log");
    }

    @Override
    public synchronized void log(String user, String msg) {
        /* Print Log Entry and add to File List */
        BufferedWriter bw = null;
        try {
            System.out.println(user + " : " + msg);
            bw = new BufferedWriter(new FileWriter(file, true));            
            bw.write(user + ": " + msg);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    // ignore this
                }
            }

        }

    }
}
