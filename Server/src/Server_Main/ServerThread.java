/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ServerThread extends Thread {
    int port;

    public ServerThread(int port) {
        this.port = port;
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int length = in.readInt();
            byte[] data = new byte[length];
            in.readFully(data);
            String name = new String(data,"UTF-8");
            System.out.println("file name : "+name);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
