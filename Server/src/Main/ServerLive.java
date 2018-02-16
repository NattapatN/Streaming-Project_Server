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
public class ServerLive extends Thread {

    public ServerLive() {}

    public void run() {
        try {
            ServerSocket server = new ServerSocket(9090);
            while (true) {
                Socket socket = server.accept();
                System.out.print("Connected to Live Stream form client :");
                System.out.println(socket.getRemoteSocketAddress());
                ServerLiveThread slt = new ServerLiveThread(socket);
                slt.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerLive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
