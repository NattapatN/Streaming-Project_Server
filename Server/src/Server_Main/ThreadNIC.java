/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
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
    int size;
    
    public ThreadNIC(Socket socket,String filename,int size) {
        this.socket = socket;
        this.filename = filename;
        this.size = size;
    }

    public void run() {
        DataInputStream dis = null;
        try {
            System.out.println("Client : " + socket.getRemoteSocketAddress() + " Conected!");
            dis = new DataInputStream(socket.getInputStream());
            FileOutputStream fos = new FileOutputStream("media/out"+filename);
            byte[] buffer = new byte[4096];
            int filesize = 100000000; // Send file size in separate msg
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;
            while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                fos.write(buffer, 0, read);
            }
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
