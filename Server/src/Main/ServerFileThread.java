/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Module.*;

/**
 *
 * @author NattapatN
 */
public class ServerFileThread extends Thread {

    Socket socket;

    public ServerFileThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            ServerSocket sSocket = new ServerSocket(0);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int port = sSocket.getLocalPort();
            out.writeInt(port);
            int link = in.readInt();
            int lenght =in.readInt();
            byte[] data = new byte[lenght];
            in.readFully(data);
            String name = new String (data,"UTF-8");
            FileReceive []fileReceive=new FileReceive[link];
            for(int i=0;i<link;i++){
                Socket socket = sSocket.accept();
                fileReceive[i] = new FileReceive(socket);
                fileReceive[i].start();
            }
            //merge file
            while(fileReceive[0].isAlive()||fileReceive[1].isAlive()){
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerFileThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            MergeFile merge = new MergeFile(name,link);
            merge.merge();
            
            System.out.println("Receive : "+name+" Complete!!");
            
        } catch (IOException ex) {
            Logger.getLogger(ServerFileThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
