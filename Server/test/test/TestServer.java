/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class TestServer {

    public static void main(String[] args) {
        try {
            System.out.println("--[ Server Start ]--");
            for (int i = 0; i < 2; i++) {
                ServerSocket sSocket = new ServerSocket(9000 + i);
                Socket socket = sSocket.accept();
                System.out.println("Connect from : " + socket.getRemoteSocketAddress());
            }

        } catch (IOException ex) {
            Logger.getLogger(TestServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}