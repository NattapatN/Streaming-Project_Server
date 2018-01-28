/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

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
public class ThreadNIC extends Thread {

    Socket socket;
    String filename;

    public ThreadNIC(Socket socket, String filename) {
        this.socket = socket;
        this.filename = filename;
    }

    public void run() {
        DataInputStream dis = null;
        try {
            System.out.println("Client : " + socket.getRemoteSocketAddress() + " Conected!");
            dis = new DataInputStream(socket.getInputStream());
            FileOutputStream fos = new FileOutputStream("media/out" + filename + ".cache");
            byte[] buffer = new byte[1024 * 1024];
            int filesize = 1024 * 1024; // Send file size in separate msg
            int read = 0;
            int remaining = filesize;
            read = dis.read(buffer, 0, Math.min(buffer.length, remaining));    
            fos.write(buffer, 0, read);
            
            System.out.println("Receive Complete.");
            fos.close();
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadNIC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dis.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadNIC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
