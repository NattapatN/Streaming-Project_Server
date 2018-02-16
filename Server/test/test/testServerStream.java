/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Main.StreamReceive;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class testServerStream {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9000);
            int c=0;
            while (true) {
                Socket socket = server.accept();
                ReceiveStream r = new ReceiveStream(socket,""+c);
                r.start();
                c++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(testServerStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ReceiveStream extends Thread{
    Socket socket;
    String filename;
    
    public ReceiveStream(Socket socket,String filename){
        this.socket = socket;
        this.filename = filename;
        System.out.println("receive"+filename);
    }
    
    public void run(){
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            long size = dis.readLong();
            
            FileOutputStream fos = new FileOutputStream("media/out"+filename+".mp4");
            byte[] buffer = new byte[4096];
            
            int filesize = (int) size;
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
            Logger.getLogger(ReceiveStream.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}