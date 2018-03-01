/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author NattapatN
 */
public class Player extends Thread {

    long start = System.currentTimeMillis();
    MediaListPlayer player;
    MediaList playlist;

    public static void main(String[] args) {
        Player player = new Player();
        player.start();
    }

    public Player() {
    }

    public void run() {
        JFrame f = new JFrame();
        f.setLocation(100, 50);
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Canvas c = new Canvas();
        c.setBackground(Color.black);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        p.add(c);
        f.add(p);

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        MediaPlayerFactory mpf = new MediaPlayerFactory();

        EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));

        emp.setVideoSurface(mpf.newVideoSurface(c));

        player = mpf.newMediaListPlayer();
        player.setMediaPlayer(emp);
//        //full Screen
//        emp.toggleFullScreen();
        //hide cursor
        emp.setEnableMouseInputHandling(false);
        //disablc keyboard
        emp.setEnableKeyInputHandling(false);

//        String file = "media/test.mp4";
        //prepare media file
        playlist = mpf.newMediaList();
        playlist.addMedia("media/out0.mp4");
        playlist.addMedia("media/out1.mp4");
        

        player.setMediaList(playlist);
        player.setMode(MediaListPlayerMode.DEFAULT);
        player.play();
        start = System.currentTimeMillis();
//        long start = System.currentTimeMillis();
//        int count = 1;
//        while (true) {
//            if (new File("media/out" + count + ".mp4").exists()) {
//                playlist.addMedia("media/out" + count + ".mp4");
//                if (System.currentTimeMillis() - start > 5000) {
//                    player.playItem(count);
//                }
//                start = System.currentTimeMillis();
//                count++;
//            }
//        }
    }

    public void add(int filename) {
        playlist.addMedia("media/out" + filename + ".mp4");
        if (System.currentTimeMillis() - start > 7000) {
            player.playItem(filename);
        } else {
            start = System.currentTimeMillis();
        }
    }

}
