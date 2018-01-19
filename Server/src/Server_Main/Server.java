/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

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
    
    public static void main(String []args){
        try {
            ServerSocket sSocket = new ServerSocket(9000);
            System.out.println("--[ Server Start ]--");
            
            while(true){
                System.out.println();
                System.out.println("waiting for connection...");
                Socket socket = sSocket.accept();
                System.out.println("connected form client");
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}