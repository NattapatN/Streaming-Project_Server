/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class StreamReceive extends Thread {

    Socket socket;
    int filename;
    Player player;

    public StreamReceive(Socket socket, int filename, Player player) {
        this.player = player;
        this.socket = socket;
        this.filename = filename;

    }

    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            long size = dis.readLong();

            FileOutputStream fos = new FileOutputStream("media/out" + filename + ".mp4");
            byte[] buffer = new byte[4096];

            int filesize = (int) size;
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;
            while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                fos.write(buffer, 0, read);
            }
            fos.close();
            dis.close();
            System.out.println(filename + "\t\t" + System.currentTimeMillis() + "\t" + socket.getRemoteSocketAddress());
            if (filename == 2) {
                sleep(4000);
                player.start();
            } else if (filename > 2) {
                player.add(filename - 1);
            }

        } catch (IOException ex) {
            Logger.getLogger(StreamReceive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(StreamReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
