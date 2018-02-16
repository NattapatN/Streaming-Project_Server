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

/**
 *
 * @author NattapatN
 */
public class ServerLiveThread extends Thread {

    Socket socket;
    Player player = new Player();

    public ServerLiveThread(Socket socket) {
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
            boolean live = false;
            int fileCount = 0;
            Player player = new Player();
            StreamReceive[] sr = new StreamReceive[link];
            while (!live) {
                //First Round Robin
                Socket socket = sSocket.accept();
                sr[fileCount%link] = new StreamReceive(socket,fileCount,player);
                sr[fileCount%link].start();
                fileCount++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerLiveThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
