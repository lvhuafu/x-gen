package cn.javass.xgen.util.file;

import java.io.*;

/**
 * Created by HASEE on 2017/7/10.
 */
public class FileHelper {
    //工具类写法private
    private FileHelper(){

    }

    public static void genDir(String dirPath){
        File f = new File(dirPath);
        f.mkdirs();
    }

    public static String readFile(String path){
        String content = "";
        DataInputStream din = null;
        try {
            din = new DataInputStream(new BufferedInputStream(FileHelper.class.getClassLoader().getResourceAsStream(path)));
            byte bs[] = new byte[din.available()];
            din.read(bs);
            content = new String(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                din.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public static void writeFile(String pathName,String content){
        File f = new File(pathName);
        if(f.exists()){
            f.delete();
        }
        DataOutputStream dout = null;
        try {
            f.createNewFile();
            dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            dout.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dout.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
