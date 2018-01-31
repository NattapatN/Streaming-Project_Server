/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ThreadReceive extends Thread{
    DataInputStream dis;
    int bufferSize;
    String fileName;
    public ThreadReceive(DataInputStream dis,int bufferSize,String fileName){
        this.dis =dis;
        this.bufferSize =bufferSize;
        this.fileName=fileName;
    }
    
    public void run(){
        System.out.print("starr");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            byte[] buffer = new byte[4096];
            int filesize = bufferSize; // Send file size in separate msg
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;
            while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                fos.write(buffer, 0, read);
            }
            fos.close();
            dis.close();
            System.out.println("stop");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadReceive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThreadReceive.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
