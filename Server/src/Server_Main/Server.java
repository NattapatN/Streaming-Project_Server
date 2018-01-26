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
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("[ Server ]");
            while (true) {
                System.out.println();
                System.out.println("waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.print("connected form client : ");
                System.out.println(socket.getRemoteSocketAddress());
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                ServerThread2 serverThread = new ServerThread2(socket);
                serverThread.start();
=======

                ServerSocket sSocket = new ServerSocket(0);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                int port = sSocket.getLocalPort();
                out.writeInt(port);
                int link = in.readInt();
                System.out.println("Client have " + link + " Network Connection");
                sSocket.close();
                
                for (int i = 0; i < link; i++) {
                    ServerThread sThread = new ServerThread(port+i);
                    sThread.start();
                }
>>>>>>> 74854bccdbf98d37679ff83656cde4798694c85f
=======
=======
>>>>>>> parent of 9c7a4a6... v 0.0.6

                ServerSocket sSocket = new ServerSocket(0);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                int port = sSocket.getLocalPort();
                out.writeInt(port);
                sSocket.close();
//                socket = sSocket.accept();
//                
//                ServerThread sThread = new ServerThread(socket,port);
//                sThread.start();
<<<<<<< HEAD
>>>>>>> parent of 9c7a4a6... v 0.0.6
=======
>>>>>>> parent of 9c7a4a6... v 0.0.6
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
