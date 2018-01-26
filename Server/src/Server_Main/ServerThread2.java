/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

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
public class ServerThread2 extends Thread {

    Socket socket;
    int bufferSize;
    public ServerThread2(Socket socket) {
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
            
            bufferSize = in.readInt();
            long size = in.readLong();
            int length = in.readInt();
            byte[] data = new byte[length];
            in.readFully(data);
            String name = new String(data,"UTF-8");
            System.out.println("Name : "+name);
            System.out.println("Size : "+size);
            System.out.println("Client Network Conection : "+link);
            System.out.println();
            
            for(int i =0;i<2;i++){
                Socket socket = sSocket.accept();
                socket.close();
                ThreadNIC tnic = new ThreadNIC(socket,name,(int)size);
                tnic.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
