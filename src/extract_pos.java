import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by gurpreet on 28/12/17.
 */

//program for extracting PUNJABI pos from university dictionary site.

public class extract_pos {


public void pos_frm_aglsoft(String line){
    try {
        URL u = new URL("http://punjabi.aglsoft.com/ajax.aspx");
        URLConnection urlConnection = u.openConnection();
        urlConnection.setDoOutput(true);
        //  urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        urlConnection.connect();
        OutputStream outputStream = urlConnection.getOutputStream();
        String values = "action=Tagging&inputText=" + line;
        outputStream.write((values).getBytes("UTF-8"));
        outputStream.flush();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        while ((inputLine = br.readLine()) != null)
            System.out.println(inputLine);
            br.close();
        }
        catch (Exception e){}
    }

    public String pos_frm_pup(String line) {
        HashMap<String,String> dataMap= new HashMap<>();
       System.setProperty("http.agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        String val="";
        Document document;
        String url ="http://punjabipos.learnpunjabi.org/default.aspx"; //"http://www.shabdkosh.com/pa/translate?e=" + line + "&l=pa";
        Connection.Response mainpage,response;
        Elements inputs;
        try {

//                line="ਬਹੁਤ ਹੀ ਗਿਆਨ ਭਰਪੂਰ ਲੇਖ ਹੈ ਸਰ। ਲੇਖ ਦੀਆਂ ਦੋਨੋਂ ਕਿਸਤਾਂ ਪੜ੍ਹ ਕੇ ਤਕਨਾਲੋਜੀ ਦੇ ਵਿਕਸਤ ਹੋਏ ਸਾਧਨਾਂ ਬਾਰੇ ਬਹੁਤ ਹੀ ਉਮਦਾ ਜਾਣਕਾਰੀ ਮਿਲੀ ਹੈ ਸਰ";
                mainpage = Jsoup.connect(url)
                                .timeout(20 * 1000)
                                .method(Connection.Method.POST)
                                .execute();

                document = mainpage.parse();

                inputs = document.select("textarea,input[type=hidden],input[id=RadioButton1_RB],input[name=Button1_Tag]");
                        for (Element inp : inputs) {
                            if (inp.attr("name").length() == 0)
                                dataMap.put(inp.attr("id"), inp.attr("value"));
                            else
                                dataMap.put(inp.attr("name"), inp.attr("value"));
                        }
                  dataMap.put("TextBox1_Input", line);
                    response = Jsoup.connect(url)
                                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                                    .data(dataMap)
                                    .cookies(mainpage.cookies())
                                    .method(Connection.Method.POST)
                                    .followRedirects(true)
                                    .ignoreContentType(true)
                                    .timeout(0)
                                    .execute();

                    document = response.parse();
                    val=document.select("span#Label1_output").text();
                    System.out.println(val);

        }
        catch (Exception e) {
            System.out.print(e);
            val="Some problem Occure";
        }
        return val;
    }
}



