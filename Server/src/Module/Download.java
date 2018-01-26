/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

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
public class Download {
    Socket socket;
    int buffersize;

    public Download(Socket socket, int buffersize) {
        this.socket = socket;
        this.buffersize = buffersize;
    }
    
    public void down(String filename,int size) {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(socket.getInputStream());
            FileOutputStream fos = new FileOutputStream("media/Out"+filename);
            byte[] buffer = new byte[1024*1024*buffersize];
            int filesize = size; // Send file size in separate msg
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;
            while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                //System.out.println("read " + totalRead + " bytes.");
                fos.write(buffer, 0, read);
            }
            fos.close();
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dis.close();
            } catch (IOException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
