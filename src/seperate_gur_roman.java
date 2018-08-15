import jdk.nashorn.internal.runtime.regexp.RegExp;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by gurpreet on 8/1/18.
 */
public class seperate_gur_roman {
    public static void main(String[] args) {
        FileInputStream fis;
        InputStreamReader in;
        BufferedReader br;
        int count=0;
String line="",tokens[];
        try {
            fis = new FileInputStream("/home/gurpreet/Desktop/data-personality");
            in = new InputStreamReader(fis, Charset.forName("UTF-8"));//, Charset.forName("UTF-8"));
            br = new BufferedReader(in);
            FileOutputStream fos = new FileOutputStream("/home/gurpreet/Desktop/data-personality1");//folder.getAbsolutePath()+"/"+listOfFiles[i].getName());
            OutputStreamWriter out = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
            BufferedWriter bw = new BufferedWriter(out);
            line = br.readLine();
            while (line != null) {
                tokens=line.split(" ");
                count=0;
                for (String s : tokens)
                {
                  if(s.matches("\\A\\p{ASCII}*\\z")||s.matches("[\\u0900-\\u097F]*")) //if token is from ascii characters
                      count++;
                }
                if (count<tokens.length/4) {
                    System.out.println(line);
                    bw.write(line + "\nਗ਼ਗ਼ਗ਼ਗ਼ਗ਼\n");
                }
                line = br.readLine();
            }
            fis.close();
            br.close();
            bw.close();
            fos.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
