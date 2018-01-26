/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

import Module.Download;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ThreadReceive extends Thread{
    Socket socket;
    int count;
    int bufferSize;
    String fileName;
    public ThreadReceive(Socket socket,int count,int bufferSize,String fileName){
        this.socket=socket;
        this.count = count;
        this.bufferSize =bufferSize;
        this.fileName=fileName;
    }
    
    public void run(){
        try {
            Download down = new Download(socket,bufferSize);
            down.down(fileName, bufferSize);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
