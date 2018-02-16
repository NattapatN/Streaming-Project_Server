/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NattapatN
 */
public class MergeFile {
    String filename;
    int link;
    public MergeFile(String filename,int link){
        this.filename=filename;
        this.link=link;
    }
    
    public void merge(){
        FileOutputStream fos;
        FileInputStream fis;
        byte[] fileBytes;
        int bytesRead = 0;
        List<File> list = new ArrayList<File>();
        for (int i = 0; i < link; i++) {
            list.add(new File("media/"+filename+i));
        }

        try {
            fos = new FileOutputStream("media/"+filename, true);
            for (File file : list) {
                fis = new FileInputStream(file);
                fileBytes = new byte[(int) file.length()];
                bytesRead = fis.read(fileBytes, 0, (int) file.length());
                assert (bytesRead == fileBytes.length);
                assert (bytesRead == (int) file.length());
                fos.write(fileBytes);
                fos.flush();
                fileBytes = null;
                fis.close();
                fis = null;
            }
            fos.close();
            fos = null;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
}
