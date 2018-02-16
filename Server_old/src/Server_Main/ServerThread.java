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
public class ServerThread extends Thread {

    Socket socket;
    int bufferSize;
    public ServerThread(Socket socket) {
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
            
            System.out.println("Client Network Conection : "+link);
            System.out.println();
            int count=0;
            while(true){
                Socket socket = sSocket.accept();
                System.out.println(socket.getRemoteSocketAddress());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                ThreadReceive tr = new ThreadReceive(dis,bufferSize,"media/fileOut"+count+".mp4");
                tr.start();
                count++;
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
