/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ServerFile extends Thread {

    public ServerFile() {
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(9000);
            while (true) {
                Socket socket = server.accept();
                System.out.print("Connected to send File form client :");
                System.out.println(socket.getRemoteSocketAddress());
                ServerFileThread thread = new ServerFileThread(socket);
                thread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
