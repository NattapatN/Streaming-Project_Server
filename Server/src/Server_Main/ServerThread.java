/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Main;

import java.net.Socket;

/**
 *
 * @author NattapatN
 */
public class ServerThread extends Thread {

    Socket socket;
    int port;

    public ServerThread(Socket socket, int port) {
        this.socket = socket;
        this.port = port;
    }

    public void run(){
    }

}
