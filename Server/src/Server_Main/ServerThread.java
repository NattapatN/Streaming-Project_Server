/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

import java.io.DataInputStream;
<<<<<<< HEAD
import java.io.DataOutputStream;
=======
>>>>>>> 74854bccdbf98d37679ff83656cde4798694c85f
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
<<<<<<< HEAD

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
            
            bufferSize = in.readInt();
            long size = in.readLong();
=======
    int port;

    public ServerThread(int port) {
        this.port = port;
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
>>>>>>> 74854bccdbf98d37679ff83656cde4798694c85f
            int length = in.readInt();
            byte[] data = new byte[length];
            in.readFully(data);
            String name = new String(data,"UTF-8");
<<<<<<< HEAD
            System.out.println("Name : "+name);
            System.out.println("Size : "+size);
            
            int count=0;
            while (true) {
                Socket socket = sSocket.accept();
                System.out.println("Client connected : "+count);
                sleep(5000);
                socket.close();
//                ThreadReceive tc = new ThreadReceive(socket,count,bufferSize,name+".cache"+count);
//                tc.start();
                count++;
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
=======
            System.out.println("file name : "+name);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
>>>>>>> 74854bccdbf98d37679ff83656cde4798694c85f
    }

}
