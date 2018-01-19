/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

import Module.Download;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
class ServerThread extends Thread{
    private Socket socket;
    int buffersize;
    
    public ServerThread(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try {
            ServerSocket sSocket = new ServerSocket(0);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            int port = sSocket.getLocalPort();
            out.writeInt(port);
            
            Socket socket = sSocket.accept();
            //read Meta
            DataInputStream in = new DataInputStream(socket.getInputStream());
            buffersize = in.readInt();
            long size = in.readLong();
            int length = in.readInt();
            byte[] data = new byte[length];
            in.readFully(data);
            String name = new String(data,"UTF-8");
            System.out.println("Name : "+name);
            System.out.println("Size : "+size);
            
            Download down = new Download(socket,buffersize);
            down.down(name,(int)size);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){}
}
