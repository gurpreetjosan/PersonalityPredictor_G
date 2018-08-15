import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gurpreet on 2/1/18.
 */
public class feature_extractor {

    double features[]=new double[31];
    double mean_of_features[]={2.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,2.0,1.0,0.0,1.0,0.0,0.0,1.0,13.0,0.0,0.0,1.0,12.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

    List <String> frst_per_snglr_pron=Arrays.asList("ਮੈਂ","ਮੈ","ਮੈਨੂੰ","ਮੈਥੋਂ", "ਮੇਰੇ","ਮੇਰਾ","ਸਾਡੀ","ਮੇਰੀ","ਅਸਾਂ","ਮੇਰੀਆਂ");
    List <String> self_ref=Arrays.asList("ਮੈਂ","ਮੈ","ਮੈਨੂੰ","ਮੈਥੋਂ", "ਮੇਰੇ","ਮੇਰਾ","ਮੇਰੀ","ਅਸਾਂ","ਸਾਡੀ","ਸਾਡੀਆਂ","ਅਸੀਂ","ਆਪਾਂ","ਸਾਡਾ","ਮੇਰੀਆਂ", "ਸਾਡੇ");
    List <String> frst_per_plrl_pron=Arrays.asList( "ਮੇਰੇ","ਸਾਡੀਆਂ","ਅਸੀਂ","ਆਪਾਂ","ਮੇਰੀਆਂ", "ਸਾਡੇ");
    List <String> scnd_per_snglr_pron=Arrays.asList( "ਤੂੰ","ਤੈਨੂੰ","ਤੈਥੋਂ","ਤੇਰਾ","ਤੁਹਾਡਾ","ਤੇਰੀ","ਤੁਹਾਡੀ");
    List <String> pronoun=Arrays.asList("ਮੈ","ਮੈਂ","ਤੂੰ","ਉਹ","ਅਸੀਂ","ਤੁਸੀਂ","ਆਪਾਂ","ਮੈਨੂੰ","ਤੈਨੂੰ","ਉਸਨੂੰ","ਸਾਨੂੰ","ਤੁਹਾਨੂੰ","ਉਹਨਾਂ","ਮੈਥੋਂ","ਸਾਥੋਂ","ਤੈਥੋਂ","ਤੁਹਾਥੋਂ","ਮੇਰੇ","ਉਸ","ਉਹਦੇ","ਤੁਹਾਡੇ","ਸਾਡੇ","ਮੇਰਾ","ਮੇਰੀ","ਸਾਡੀ","ਸਾਡੀਆਂ","ਸਾਡਿਆਂ","ਤੇਰੀ","ਤੁਹਾਡੇ","ਤੁਹਾਡੀ","ਤੁਹਾਡੀਆਂ","ਤੁਹਾਡਿਆਂ","ਵੇ","ਨੀ","ਉਏ","ਇਹ","ਅਸਾਂ","ਤੁਸਾਂ","ਇਹਨੇ","ਉਹਨੇ","ਤੇਰੀਆਂ","ਮੇਰੀਆਂ","ਇਹਦੀਆਂ","ਉਹਦੀਆਂ","ਇਹਦੀ","ਉਹਦੀ","ਉਹਦਾ","ਇਹਦਾ","ਇਹਦੇ","ਉਹਦੇ","ਆਪਣਾ","ਆਪ","ਖੁਦ","ਆਪਣੇ","ਆਪਣੀਆਂ","ਆਪੋ-ਆਪਣੇ","ਆਪਣੇ-ਆਪਣੇ","ਆਪਣੀ","ਆਪੋ-ਆਪਣਾ","ਆਪਣਾ-ਆਪਣਾ","ਆਪਣੇ ਆਪ","ਆਪੋ","ਆਪਸ","ਆਪੇ","ਆਪਣਿਆਂ","ਆਪਣੀਆਂ","ਜੋ","ਜਿਸ","ਜਿਹੜਾ","ਜਦੋਂ","ਜਿਹੜੀ","ਸੋ","ਆਪਸ","ਕੌਣ","ਕਦੋਂ","ਕਿੱਥੇ","ਕੋਈ","ਕਿਸ","ਕੁਝ","ਕਿਹੜਾ","ਕੀ","ਕਿਹੜੀ","ਕਿਹੜੇ","ਕਿਹੜੀਆਂ","ਕਿਨ੍ਹਾਂ","ਕਿਨ੍ਹੇ","ਕਿਨ","ਕੀਹਨੂੰ","ਕਿਸਦਾ","ਕੀਹਦਾ","ਕਿਸਦੇ","ਕੀਹਦੇ","ਕਾਸਦਾ","ਕਾਹਦਾ","ਕਿਸਦੀ","ਕੀਹਦੀ","ਕਾਸਦੀ","ਕਾਹਦੀ","ਕੀਹਦੀਆਂ","ਕਿਸਦੀਆਂ","ਕਿਸਦਿਆਂ","ਕੀਹਦਿਆਂ");
    List <String> swear=Arrays.asList( "ਲੰਡ","ਲੰਨ","ਫੁੱਦੀ","ਮੰਮੇ","ਮੰਮਿਆਂ","ਲੁੱਲ","ਬੁੰਡ","ਬੁੰਢ","ਭੈਣਚੌ","ਚਿਤੜਾ","ਫੁਦੀ","ਘੂਸਾ","ਮੱਮੇ","ਮੁੰਮੇ","ਟੱਟਾ","ਟੱਟੇ","ਸਾਲੇਓ","ਲੁੱਲੀ", "ਗਸਤੀ","ਹਰਾਮਖੋਰ","ਹਰਾਮੀ","ਹਰਾਮਜ਼ਾਦਾ", "ਹਰਾਮਜ਼ਾਦੇ","ਹਰਾਮਦੇ", "ਹਰਾਮ", "ਫੁੱਦੂ", "ਫੁਦੂ", "ਸਾਲੇ", "ਸਾਲਾ");
    List <String> preposition=Arrays.asList("ਅਤੇ","ਉੱਤੇ","ਹੇਠ","ਪਹਿਲਾਂ","ਬਾਅਦ","ਦੇ","ਸਾਹਮਣੇ","ਪਿੱਛੇ","ਪਰੇ","ਨੇਡ਼ੇ","ਵੱਲ","ਵਾਂਗ","ਵਿਖੇ","ਨੇੜੇ","ਤਕ","ਵਲੋਂ","ਬਿਨਾਂ","ਸੰਬੰਧੀ","ਸਬੰਧੀ","ਅੰਦਰ","ਪਾਸੇ","ਬਾਹਰ","ਨਾਲ","ਬਗੈਰ","ਬਾਰੇ","ਦਰਮਆਿਨ","ਪਰ","ਲਈ","ਵੱਲੋਂ","ਵਾਲੀ","ਉਤੇ","ਅਨੁਸਾਰ","ਨੂੰ","ਨੇ","ਦਾ","ਤੋਂ","ਵਿੱਚ","ਉੱਪਰ","ਨਾਲ਼","ਰਾਹੀਂ","ਦੁਆਰਾ","ਵਾਸਤੇ","ਉੱਤੋਂ","ਉੱਪਰੋਂ","ਦੀ","ਦੀਆਂ","ਵਜੋਂ","ਵਾਲਿਆਂ","ਇਲਾਵਾ","ਵਾਲੀਆਂ","ਹਿਤ","ਕੋਲ","ਲਾਗੇ","ਵਿਚ","'ਚ","ਵਾਲੇ","ਵਾਲਾ","ਵਿਚੋਂ","ਵਿੱਚੋਂ");
    List <String> ordinal=Arrays.asList("ਇੱਕੀਵਾਂ","ਬਾਹੀਵਾਂ","ਤੇਵੀਵਾਂ","ਚੌਵੀਵਾਂ","ਪੰਝੀਵਾਂ","ਛੱਬੀਵਾਂ","ਸਤਾਈਵਾਂ","ਅਠਾਈਵਾਂ","ਉਨੱਤੀਵਾਂ","ਤੀਹਵਾਂ","ਇਕੱਤੀਵਾਂ","ਬੱਤੀਵਾਂ","ਤੇਤੀਵਾਂ","ਚੌਂਤੀਵਾਂ","ਪੈਂਤੀਵਾਂ","ਛੱਤੀਵਾਂ","ਸੈਂਤੀਵਾਂ","ਅਠੱਤੀਵਾਂ","ਉਣਤਾਲੀਵਾਂ","ਚਾਲੀਵਾਂ","ਇਕਤਾਲੀਵਾਂ","ਬਤਾਲੀਵਾਂ","ਤਰਤਾਲੀਵਾਂ","ਚੌਤਾਲੀਵਾਂ","ਪੰਜਤਾਲੀਵਾਂ","ਛਿਆਲੀਵਾਂ","ਸੰਤਾਲੀਵਾਂ","ਅੱਠਤਾਲੀਵਾਂ","ਉਣਿੰਜਵਾਂ","ਪੰਜਾਹਵਾਂ","ਇਕਵਿੰਜਵਾਂ","ਬਵਿੰਜਵਾਂ","ਤਰਵਿੰਜਵਾਂ","ਚਰਿੰਜਵਾਂ","ਪਚਵਿੰਜਵਾਂ","ਛਪਿੰਜਵਾਂ","ਸਤਵਿੰਜਵਾਂ","ਅੱਠਵਿੰਜਵਾਂ","ਉਣਾਠਵਾਂ","ਸੱਠਵਾਂ","ਇਕਾਠਵਾਂ","ਬਾਠਵਾਂ","ਤਰੇਠਵਾਂ","ਚੌਠਵਾਂ","ਪੈਂਠਵਾਂ","ਛਆਠਵਾਂ","ਸਤਾਠਵਾਂ","ਅੱਠਾਠਵਾਂ","ਉਣੱਤਰਵਾਂ","ਸੱਤਰਵਾਂ","ਇਕ੍ਹੱਤਰਵਾਂ","ਬਹੱਤਰਵਾਂ","ਤਹੱਤਰਵਾਂ","ਚੌਹੱਤਰਵਾਂ","ਪੰਜੱਤਰਵਾਂ","ਛਿਹੱਤਰਵਾਂ","ਸਤੱਤਰਵਾਂ","ਅਠੱਤਰਵਾਂ","ਉਣਾਸੀਵਾਂ","ਅੱਸੀਵਾਂ","ਇਕਾਸੀਵਾਂ","ਬਿਆਸੀਵਾਂ","ਤਰਆਸੀਵਾਂ","ਚਰਾਸੀਵਾਂ","ਪੰਜਾਸੀਵਾਂ","ਛਿਆਸੀਵਾਂ","ਸਤਾਸੀਵਾਂ","ਅਠਾਸੀਵਾਂ","ਉਣਾਂਨਵਿਆਂ","ਨੱਬਵਿਆਂ","ਇਕਆਨਵੇਂ","ਬਿਆਨਵੇਂ","ਤਰਆਨਵੇਂ","ਚਰਾਨਵੇਂ","ਪਚਾਨਵੇਂ","ਛਿਆਨਵੇਂ","ਸਤਾਨਵੇਂ","ਅਠਾਨਵੇਂ","ਨਿੜੱਨਵੇਂ","ਸੌਆਂ","ਗਿਆਰਵਾਂ","ਬਾਹਰਵਾਂ","ਤੇਹਰਵਾਂ","ਚੌਦਵਾਂ","ਪੰਦਰਵਾਂ","ਸੋਲ਼ਵਾਂ","ਸਤਾਰਵਾਂ","ਅਠਾਰਵਾਂ","ਉਨੀਵਾਂ","ਵੀਹਵਾਂ","ਪਹਿਲਾ","ਦੂਜਾ","ਤੀਜਾ","ਚੌਥਾ","ਪੰਜਵਾਂ","ਛੇਵਾਂ","ਸੱਤਵਾਂ","ਅੱਠਵਾਂ","ਨੌਂਵਾਂ","ਦਸਵਾਂ","ਹਜਾਰਵਾਂ","ਲੱਖਵਾਂ","ਕਰੋੜਵਾਂ","ਅਰਬਵਾਂ","ਖਰਬਵਾਂ");
    List <String> quantifiers=Arrays.asList("ਕੁੱਝ","ਬਹੁਤ","ਥੋੜ੍ਹਾ","ਥੋੜਾ","ਬਹੁਤਾ","ਕਾਫੀ","ਕਈ","ਸਭ ","ਸਾਰੇ","ਵਿਰਲਾ","ਅਨੇਕ ","ਬਹੁਤੇ ","ਥੋੜੇ","ਥੋੜ੍ਹੇ","ਵਿਰਲੇ","ਸਾਰਾ","ਅਨੇਕਾਂ","ਵਿਰਲਿਆਂ","ਬਹੁਤਿਆਂ","ਥੋੜ੍ਹਿਆਂ","ਥੋੜਿਆਂ");



    private FileInputStream fis;
    private InputStreamReader in;
    private BufferedReader br;
    List<String> msgs;   //List of all messages of a person
    List <String> pos_msgs; //List of all messages tagged with Part of speech
     Pattern emoticon =     //pattren to find emojis in text
            Pattern.compile(
                    "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|[\u20a0-\u32ff]|[\u0023-\u0039]|[\ud800-\udbff][\udc00-\udfff]}",
                    Pattern.UNICODE_CASE |
                            Pattern.CANON_EQ |
                            Pattern.CASE_INSENSITIVE
            );
     Matcher unicodeOutlierMatcher;
    List<String> pos_emoji,neg_emoji;  //list of positive and negative emojis


    private char punct[]= {'.',',','?','!',';',':','…','।','॥','|'};

    int punct_cnt[];  // Array to hold count of punctuation corresponding to above list

    HashMap<String,String> sent_lex; //hold sentiment lexicon

    //Load positive and negative emojis
    public void load_emoji()
    {
        pos_emoji=new ArrayList();
        neg_emoji=new ArrayList<>();
        String line,tokens[];
        try {
            fis = new FileInputStream("/home/gurpreet/Projects/personality/src/pos-emoji.csv");
            in = new InputStreamReader(fis);//, Charset.forName("UTF-8"));
            br = new BufferedReader(in);
            line = br.readLine();
            while (line != null) {
                tokens=line.split(",");
                pos_emoji.add(tokens[0]);
                line = br.readLine();
            }
            fis.close();
            br.close();

            fis = new FileInputStream("/home/gurpreet/Projects/personality/src/neg-emojis.csv");
            in = new InputStreamReader(fis);//, Charset.forName("UTF-8"));
            br = new BufferedReader(in);
            line = br.readLine();
            while (line != null) {
                tokens=line.split(",");
                neg_emoji.add(tokens[0]);
                line = br.readLine();
            }
            fis.close();
            br.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    //count type/token ratio
    public double type_token_ratio(String line){
        String tokens[]=line.split(" ");
        int tot_wrd=tokens.length;
        Set<String> uniqueWords = new HashSet<String>();

        for (String word : tokens) {
            uniqueWords.add(word);
        }
        return (double)uniqueWords.size()/tot_wrd;
    }

    //count mean wrd freq ratio
    public double mean_wrd_freq(String line){
        String tokens[]=line.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String w : tokens) {
            Integer n = map.get(w);
            n = (n == null) ? 1 : ++n;
            map.put(w, n);
        }
        int sum = 0;
        for (int f : map.values()) {
            sum += f;
        }

        return (double)sum/map.size();
    }

    //load sentiment lexicon
    public void load_sentlex(){
        sent_lex=new HashMap<>();
        String line;
        String tokens[];
        try {
            fis = new FileInputStream("/home/gurpreet/Projects/personality/src/senti-lex");
            in = new InputStreamReader(fis);//, Charset.forName("UTF-8"));
            br = new BufferedReader(in);
            line = br.readLine();
            while (line != null) {
                tokens=line.split(" ");
                sent_lex.put(tokens[0],tokens[1]+"/"+tokens[2]);
                line = br.readLine();
            }
            fis.close();
            br.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    //count various punctuation in a given message
    public void punc_cnt(String line){
        punct_cnt=new int[9];
        for (int i = 0; i < line.length(); i++) {
            for (int j = 0; j < 9; j++) {
                if (line.charAt(i) == punct[j])
                    punct_cnt[j]++;
            }
        }
    }

    public void readmsg(String filename) {
        String msg="",line="";
        msgs=new ArrayList<>();

        try {
            fis = new FileInputStream("/home/gurpreet/Projects/personality/src/test/"+filename);
            in = new InputStreamReader(fis, Charset.forName("UTF-8"));
            br = new BufferedReader(in);
            line = br.readLine();
            while (line != null) {
                if(line.equals("<msg>")) {
                    msgs.add(msg+"\n");
                    msg="";
                    line = br.readLine();
                    continue;
                }
                msg=msg+line+"\n";
                line = br.readLine();

            }

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    // Tag with pos every message in the msgs list
    public void tag_pos(){
        extract_pos pos=new extract_pos();
        String temp;
        for(int i=0;i<msgs.size();i++) {
            temp = msgs.get(i);
            unicodeOutlierMatcher =emoticon.matcher(temp);
            temp=unicodeOutlierMatcher.replaceAll(" $0 ");
            temp=temp.replaceAll("([\\p{P}]+)", " $1 ");
            temp=temp.replaceAll("|", " $1 ");
            temp=temp.replaceAll("  ", " ");
            temp=temp.replaceAll("  ", " ");

            pos_msgs.add(pos.pos_frm_pup(temp));
        }
    }
    //count and return words per sentence
    public int wrd_per_sntnce(String temp){  //count words per sentence
        unicodeOutlierMatcher =emoticon.matcher(temp);
        temp=unicodeOutlierMatcher.replaceAll(" ");

        temp=temp.replaceAll("([\\p{P}&&[^!.?॥।]]+)", " ");
        temp=temp.replaceAll("([!.?॥।]+)", "$0");
        //temp=temp.replaceAll("\n", " ");
        temp=temp.replaceAll("\\s\\s+", " ").trim();
        String sentences[]=temp.split("[!.?॥।]");
        String tokens[];
        int totwrds=0;
        for(String sentence:sentences) {
            tokens = sentence.split(" ");
            totwrds = totwrds + tokens.length;
        }
        return totwrds/sentences.length;
    }

    //counts number of words in message
    public int wrdcnt(String temp)
    {
        unicodeOutlierMatcher =emoticon.matcher(temp);
        temp=unicodeOutlierMatcher.replaceAll(" ");
        temp=temp.replaceAll("([\\p{P}]+)", " ");
        temp=temp.replaceAll("\n", " ");
        temp=temp.replaceAll("\\s\\s+", " ").trim();
        return temp.split("[ \n]").length;
    }
    //count number of words more than six letters in a message
    public int wrds_morethan_sixltr(String temp)
    {
        int cnt=0;
        unicodeOutlierMatcher =emoticon.matcher(temp);
        temp=unicodeOutlierMatcher.replaceAll(" ");
        temp=temp.replaceAll("([\\p{P}]+)", " ");
        temp=temp.replaceAll("\n", " ");
        temp=temp.replaceAll("\\s\\s+", " ").trim();
        String tokens[]=temp.split("[ \n]");
        for(int i=0;i<tokens.length;i++)
        {
            if(tokens[i].length()>=6)
                cnt++;
        }
        return cnt;
    }
    public int cnt_frst_per_snglr_pron(String msg){
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(frst_per_snglr_pron.contains(token))
                cnt++;
        return cnt;
    }
    public int neg_partical(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(token.equals("ਨਹੀਂ")||token.equals("ਨਾ")||token.equals("ਨਾਹ")||token.equals("ਨਾਂਹ")||token.equals("ਬਿਨਾਂ")||token.equals("ਵਗੈਰ")||token.equals("ਬਗੈਰ")||token.equals("ਬਿਨ")||token.equals("ਬਿਨਾ"))
                cnt++;
        return cnt;
    }
    public int cnt_number(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(token.matches(".*\\d.*"))
                cnt++;
        return cnt;
    }
    public int cnt_pos_emoji(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(pos_emoji.contains(token))
                cnt++;
        return cnt;
    }
    public int cnt_neg_emoji(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(neg_emoji.contains(token))
                cnt++;
        return cnt;
    }

    public int preposition_cnt(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(preposition.contains(token))
                cnt++;
        return cnt;
    }

    public int pron_cnt(String msg)
    {

        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(pronoun.contains(token))
                cnt++;
        return cnt;
    }

    public int self_ref_cnt(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(self_ref.contains(token))
                cnt++;
        return cnt;
    }

    public int swear_cnt(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(swear.contains(token))
                cnt++;
        return cnt;
    }

    public int cnt_1_pers_plrl_pron(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(frst_per_plrl_pron.contains(token))
                cnt++;
        return cnt;
    }

    public int cnt_2_pers_snglr_pron(String msg)
    {
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(scnd_per_snglr_pron.contains(token))
                cnt++;
        return cnt;
    }

    public int quanitifiers_cnt(String msg) { //e.g. few many much
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(quantifiers.contains(token))
                cnt++;
        return cnt;
    }
    public int ordinal_cnt(String msg) { //e.g. first, second hundredth etc
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(ordinal.contains(token))
                cnt++;
        return cnt;
    }
    List <String> fmlywrd=Arrays.asList("ਮਾਂ","ਪਿਤਾ","ਮਾਤਾ","ਨਾਨਾ","ਦਾਦਾ","ਨਾਨੀ","ਦਾਦੀ","ਪਤੀ","ਪਤਨੀ","ਪੁੱਤਰੀ","ਪੁੱਤਰ","ਪੁੱਤ","ਭਾਈ","ਵੀਰ","ਭਰਾ","ਭੈਣ","ਪਿਓ","ਮਾਮਾ","ਤਾਇਆ","ਚਾਚਾ","ਮਾਮੀ","ਤਾਈ","ਚਾਚੀ","ਸਹੁਰਾ","ਸੱਸ","ਸਾਲਾ","ਸਾਲੀ","ਜੇਠ","ਭਤੀਜਾ","ਭਤੀਜੀ","ਭਣੇਵਾਂ","ਭਣੇਵੀਂ","ਦਿਓਰ","ਨਨਦ","ਪੋਤਾ","ਪੋਤੀ","ਦੋਹਤਾ","ਦੋਹਤੀ","ਫੁੱਫੜ","ਭੂਆ","ਮਾਸੜ","ਮਾਸੀ","ਜੁਆਈ","ਨੂੰਹ","ਸਾਲ਼ਾ","ਕੁੜਮ","ਕੁੜਮਣੀ","ਭਾਣਜਾ","ਭਾਣਜੀ");
    public int family_wrd_cnt(String msg) { //e.g. daughter, husband etc
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(fmlywrd.contains(token))
                cnt++;
        return cnt;
    }
    List <String> frndwrd=Arrays.asList("ਜੁੱਟ","ਸੰਗੀ","ਰਫ਼ੀਕ","ਭੁਜਾ","ਆਸ਼ਨਾ","ਭੁਜ","ਸਾਜਣ","ਸਾਜਨ","ਦੋਸਤ","ਮਿੱਤਰ","ਸਾਥੀ","ਯਾਰ","ਫ੍ਰੈਂਡ","ਸੱਜਣ","ਬੇਲੀ","ਆੜੀ","ਮਿੱਤਰਤਾ","ਹਿਤ","ਮਿੱਤਰਤਾ","ਮਿੱਤਰਤਾ","ਦੋਸਤਾਂ","ਮਿੱਤਰਤਾ","ਦੋਸਤੀ","ਯਾਰੀ","ਮਿੱਤਰਤਾ","ਮੇਲ","ਦੋਸਤਾਨਾ","ਸਨੇਹ","ਇਖਲਾਸ","ਯਾਰਾਨਾ","ਮਿੱਤਰਤਾਪੂਰਨ","ਦੋਸਤਾਨਾ","ਹਿਤਕਾਰੀ","ਮੁਹੱਬਤੀ","ਸਨੇਹੀ");

    public int frnd_wrd_cnt(String msg) { //e.g. buddy, dear etc.
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(fmlywrd.contains(token))
                cnt++;
        return cnt;
    }

    public int postv_wrd_cnt(String msg) {
        String tokens[]=msg.split(" ");
        int cnt=0;
        String temp[];
        for(String token:tokens) {
            if(sent_lex.containsKey(token)) {
                temp = sent_lex.get(token).split("/");
                if (Double.parseDouble(temp[0]) > 0.5)
                    cnt++;
            }
        }
        return cnt;
    }
    public int negtv_wrd_cnt(String msg) {
        String tokens[]=msg.split(" ");
        int cnt=0;
        String temp[];
        for(String token:tokens) {
            if(sent_lex.containsKey(token)) {
                temp = sent_lex.get(token).split("/");
                if (Double.parseDouble(temp[1]) > 0.5)
                    cnt++;
            }
        }
        return cnt;
    }

    List <String> tentativewrd=Arrays.asList("ਸ਼ਾਇਦ","ਅੰਦਾਜ਼ਾ", "ਅੰਦਾਜ਼ੇ","ਖਬਰੇ","ਖੌਰੇ੍ਹ","ਖਵਰੇ","ਭਾਵੇਂ","ਭਾਮੇਂ","ਸੰਭਾਵੀ","ਕਿਧਰੇ","ਕਿਤੇ","ਕੁਦਰਤੀ","ਸਬੱਬੀਂ","ਇਤਫ਼ਾਕੀਆ","ਇਤਫ਼ਾਕ","ਇਤਫ਼ਾਕਨ","ਕੁਦਰਤਨ","ਤਕਦੀਰੀਂ");
    public int tentative_wrd_cnt(String msg) { //e.g. maybe perhaps guess
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(tentativewrd.contains(token))
                cnt++;
        return cnt;
    }

    List <String> certainitywrd=Arrays.asList("ਹਮੇਸ਼ਾ","ਸਦਾ","ਨਿੱਤ","ਹਰਦਮ","ਸਦੀਵ","ਹਮੇਸ਼","ਨਿਸਦਿਨ","ਹਮੇਸ਼ਾਂ","ਪਰਮ","ਅਨਾਦਿ","ਕਦੇ","ਲਗਾਤਾਰ","ਸਦਾ","ਮਤਵਾਤਰ","ਮੁਤਵਾਤਰ");
    public int certainity_wrd_cnt(String msg) { //e.g. always, never
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(certainitywrd.contains(token))
                cnt++;
        return cnt;
    }
    List <String> mnywrd=Arrays.asList("ਪੈਸਾ","ਧਨ","ਚਾਂਦੀ","ਮਾਇਆ","ਮਾਲ","ਪੂੰਜੀ","ਰਕਮ","ਦੌਲਤ","ਮੁੱਦਰਾ","ਰੁਪਈਆ","ਦਾਮ","ਮਣੀ","ਰੁਪਇਆ","ਸਾਈ","ਲੇਖਾ","ਨਾਵਾਂ","ਗੱਲਾ","ਗੋਲਕ","ਸਰਾਫ਼ਾਂ","ਮਹਾਜਨ","ਚਾਹਵੇ","ਸੇਠ","ਕਮਾਈ","ਧਨੀ","ਧਨਵਾਨ","ਧਨਾਢ","ਦੌਲਤਮੰਦ","ਪੂੰਜੀਪਤੀ","ਰੋਕੜ","ਪੈਸਾ","ਰਕਮ","ਕੈਸ਼","ਦਾਮ","ਲੇਖਾ","ਨਾਵਾਂ","ਨਾਮਾ","ਨਕਦ","ਧਨ-ਰਾਸ਼ੀ");
    public int money_wrd_cnt(String msg) { //e.g. cash, paise, inaam
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(mnywrd.contains(token))
                cnt++;
        return cnt;

    }
    List <String> rlgnwrd=Arrays.asList("ਰੱਬ","ਰੱਬਾ","ੴ","ਗਣੇਸ਼","ਗਨੇਸ਼","ਲੱਛਮੀ","ਹਨੂਮਾਨ","ਸ਼ਨੀ","ਦੁਰਗਾ","ਭੈਰੋਂ","ਸ਼ਿਵਜੀ","ਬ੍ਰਹਮਾ","ਵਿਸ਼ਨੂੰ","ਵਾਹਿਗੁਰੂ","ਸਤਿਨਾਮ","ਭੋਲੇ","ਸ਼ੰਕਰ","ਸਾਈਂ","ਸਾਂਈ","ਰਾਮ","ਕ੍ਰਿਸ਼ਨ","ਕ੍ਰਿਸ਼ਨਾ","ਧਰਮ","ਮਾਰਗ","ਮਤ","ਇਮਾਨ","ਮਜ਼ਹਬ","ਮਜ਼੍ਹਬ","ਪਰਤੀਤ","ਆਸਥਾ","ਮਤ","ਸ਼ਰਧਾ","ਈਮਾਨ","ਨਿਸ਼ਠਾ","ਇਤਬਾਰ","ਅਕੀਦਾ","ਨਿਹਚਾ","ਸ਼ਰਧਾਹੀਣਤਾ","ਮੰਦਰ","ਸ਼ਰਧਾ","ਸਭਾ-ਘਰ","ਪ੍ਰਾਰਥਨਾ","ਡੇਰਾ","ਡੇਹਰਾ","ਚਰਚ","ਗਿਰਜਾ","ਗੁਰਦੁਆਰਾ","ਗੁਰਦਵਾਰਾ","ਗੁਰਦੁਆਰੇ","ਗੁਰਦਵਾਰੇ","ਮਸਜਿਦ","ਮਸੀਤ","ਨਮਾਜ਼","ਨਮਾਜ","ਅਰਦਾਸ","ਆਰਤੀ","ਪਾਠ","ਪੂਜਾ","ਭਗਤੀ");
    public int religion_wrd_cnt(String msg) { //e.g. gurdwara, mandir
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(rlgnwrd.contains(token))
                cnt++;
        return cnt;

    }
    List <String> assentwrd=Arrays.asList("ਸਹਿਮਤੀ","ਸਹਿਮਤ","ਮਰਜ਼ੀ","ਮਰਜ਼ੀਆਂ","ਅਨੁਮਤੀ","ਅਨੁਮਤੀਆਂ","ਮਨਜ਼ੂਰੀ","ਮਨਜ਼ੂਰੀਆਂ","ਰਾਜ਼ੀ","ਰਜਾਮੰਦੀ","ਰਜ਼ਾਮੰਦੀ");

    public int assent_wrd_cnt(String msg) { //e.g. agree ok
        String tokens[]=msg.split(" ");
        int cnt=0;
        for(String token:tokens)
            if(assentwrd.contains(token))
                cnt++;
        return cnt;
    }


}
