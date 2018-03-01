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
public class MPTCP {
    public static void main(String [] args){
        try {
            ServerSocket sSocket = new ServerSocket(9090);
            Socket socket = sSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(MPTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
