/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class FileReceive extends Thread {

    Socket socket;

    public FileReceive(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int length = dis.readInt();
            byte[] data = new byte[length];
            dis.readFully(data);
            String name = new String(data, "UTF-8");
            long size = dis.readLong();

            FileOutputStream fos = new FileOutputStream("media/"+name);
            byte[] buffer = new byte[4096];

            int filesize = (int) size; // Send file size in separate msg
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;
            while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                fos.write(buffer, 0, read);
            }
            fos.close();
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(FileReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
