/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author NattapatN
 */
public class Server {

    public static void main(String[] args) {
        System.out.println("[Server Start]");
        ServerFile file = new ServerFile();
        ServerLive live = new ServerLive();
        file.start();
        live.start();
    }

}
