import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by gurpreet on 1/1/18.
 */
public class start {

    public static void main(String[] args) {
        String temp;
        String prediction[]={
                "ਤੁਸੀਂ ਆਪਣੇ ਆਲੇ ਦੁਆਲੇ ਲੋਕਾਂ ਨੂੰ ਦੇਖ ਕੇ ਜੋਸ਼ ਮਹਿਸੂਸ ਕਰਦੇ ਹੋ। ਤੁਹਾਨੂੰ ਪਾਰਟੀਆਂ ਵਿਚ ਜਾਣਾ, ਜਲਸਿਆਂ ਇਕੱਠਾਂ ਮੇਲਿਆਂ (ਧਾਰਮਿਕ, ਰਾਜਨੀਤਿਕ, ਸਮਾਜਿਕ, ਜਾਂ ਵਪਾਰਿਕ) ਵਿੱਚ ਸ਼ਾਮਿਲ ਹੋਣਾ ਪਸੰਦ ਹੈ। ਤੁਸੀਂ ਗਰੁੱਪ ਵਿੱਚ ਕੰਮ ਕਰ ਕੇ ਖੁਸ਼ ਹੁੰਦੇ ਹੋ ਪਰ ਇੱਕਲੇ ਹੋਣ ਤੇ ਬੋਰੀਅਤ ਮਹਿਸੂਸ ਕਰਦੇ ਹੋ।",
                "ਤੁਸੀਂ ਆਪਣੇ ਆਪ ਵਿੱਚ ਮਸਤ ਰਹਿੰਦੇ ਹੋ ਅਤੇ ਆਪਣੇ ਆਲੇ ਦੁਆਲੇ ਲੋਕਾਂ ਦੇ ਇਕੱਠ ਨੂੰ ਪਸੰਦ ਨਹੀਂ ਕਰਦੇ। ਤੁਹਾਨੂੰ ਅਜਿਹੇ ਕਾਰਜਾਂ ਵਿੱਚ ਦਿਲਚਸਪੀ ਹੈ ਜੋ ਇੱਕਲੇ ਹੁੰਦੇ ਹੋਣ ਜਿਵੇਂ ਕਿ ਪੜੵਨਾ, ਲਿਖਣਾ, ਕੰਪਿੳੂਟਰ ਤੇ ਕੰਮ ਕਰਨਾ, ਗਾਣੇ ਕੰਪੋਜ਼ ਕਰਨੇ ਵਗੈਰਾ।  ਤੁਸੀਂ ਗਰੁੱਪ ਵਿੱਚ ਕੰਮ ਕਰ ਕੇ ਖੁਸ਼ ਨਹੀਂ ਹੁੰਦੇ ਹੋ। ਪਰ ਇੱਕਲੇ ਕੰਮ ਕਰ ਕੇ ਖੁਸ਼ ਹੁੰਦੇ ਹੋ। ਇੱਕ ਸਮੇਂ ਤੇ ਇੱਕ ਕੰਮ ਨੂੰ ਹੀ ਹੱਥ ਪਾਉਂਦੇ ਹੋ। ਬੋਲਣ ਤੋਂ ਪਹਿਲਾਂ ਸੋਚਣ ਵਿੱਚ ਵਿਸ਼ਵਾਸ ਰੱਖਦੇ ਹੋ।",
                "ਸ਼ਾਂਤ ਸੁਭਾਅ ਅਤੇ ਤਣਾਅ ਮੁਕਤ ਹੋ।",
                "ਚਿੰਤਾ, ਟੈਂਸ਼ਨ ਲੈਣ ਵਾਲੇ, ਤਣਾਅ ਵਿੱਚ ਰਹਿਣ ਵਾਲੇ, ਦੁਜਿਆਂ ਤੋਂ ਜਲਣ ਵਾਲੇ, ਈਰਖਾ ਕਰਨ ਵਾਲੇ, ਬਹੁਤ ਛੇਤੀ ਚਿੜਚਿੜੇ ਅਤੇ ਗੁੱਸੇ ਵਿੱਚ ਆ ਜਾਂਦੇ ਹੋ।",
                "ਦੋਸਤਾਨਾ ਜਾਂ ਮਿਲਾਪੜੇ ਸੁਭਾਅ ਦੇ ਮਾਲਕ ਹੋ। ਮਿਲਵਰਤਨ ਨਾਲ ਕੰਮ ਕਰਨ ਵਾਲੇ, ਦੁਜਿਆਂ ਲਈ ਆਪਣੇ ਕੰਮ ਪਿੱਛੇ ਛੱਡਣ ਵਾਲੇ ਹੋ। ਤੁਸੀਂ ਦੁਜਿਆਂ ਦਾ ਲਿਹਾਜ਼ ਕਰਦੇ ਹੋ ਅਤੇ ਉਦਾਰਵਾਦੀ ਹੋ।",
                "ਤੁਸੀਂ ਕੋਰੇ ਸੁਭਾਅ ਦੇ ਹੋ। ਅਾਪਣੇ ਕੰਮਾ ਨੂੰ ਪਹਿਲ ਦੇਣ ਵਾਲੇ, ਦੁਜਿਆਂ ਦਾ ਲਿਹਾਜ਼ ਨਾ ਕਰਨ ਵਾਲੇ ਹੋ। ਤੁਸੀਂ ਮਿਲਵਰਤਨ ਨਾਲੋ ਅਲੱਗ ਹੋ ਕੇ ਕੰਮ ਕਰਨ ਵਿੱਚ ਭਲਾਈ ਸਮਝਦੇ ਹੋ। ਦੁਜਿਆਂ ਦੀ ਮਦਦ ਘੱਟ ਹੀ ਕਰਦੇ ਹੋ।",
                "ਤੁਸੀਂ ਭਰੋਸੇਯੋਗ ਅਤੇ ਜ਼ਿੰਮੇਵਾਰ ਹੋ। ਹਰ ਕੰਮ ਪਲੈਨਿੰਗ ਨਾਲ ਕਰਨ ਵਾਲੇ, ਹਰ ਕੰਮ ਵਿੱਚ ਚੰਗੀ ਕਾਰਗੁਜ਼ਾਰੀ ਦਿਖਾਉਣ ਵਾਲੇ ਹੋ। ਦਿਆਨਤਦਾਰੀ, ਸਵੈ-ਅਨੁਸ਼ਾਸਨ ਅਤੇ ਸਾਵਧਾਨੀ ਤੁਹਾਡੀਆਂ ਖਾਸੀਅਤਾਂ ਹਨ। ",
                "ਮਸਤ, ਬੇਇਖਤਿਆਰ ਜਾਂ ਬਿਨਾਂ ਪਲੈਨਿੰਗ ਦੇ ਵਿੱਚ ਕੰਮ ਕਰਨ ਵਾਲੇ ਹੋ। ਲੋਕ ਤੁਹਾਨੂੰ ਘੱਟ ਭਰੋਸੇਯੋਗ ਸਮਝਦੇ ਹਨ।",
                "ਤੁਸੀਂ ਰਚਨਾਤਮਕ ਬਿਰਤੀ ਦੇ ਮਾਲਕ ਹੋ। ਤੁਹਾਨੂੰ ਨਵੇਂ ਵਿਚਾਰ ਕੁੱਝ ਨਵਾਂ ਸਿੱਖਣ ਦੀ ਚੇਸ਼ਟਾ ਰਹਿੰਦੀ ਹੈ। ਕੁਝ ਨਵਾਂ ਕਰਨਾ ਲੋਚਦੇ ਹੋ। ਤੁਸੀਂ ਵਿਹਾਰਕ (practical) ਨਾਲੋਂ ਜ਼ਿਆਦਾ ਕਲਪਨਾਸ਼ੀਲ ਹੋ।",
                "ਆਪਣੇ ਆਪ ਵਿੱਚ ਰਹਿਣ ਵਾਲੇ, ਹਰ ਗੱਲ ਵਿੱਚ ਵਿਸ਼ਲੇਸ਼ਣ ਕਰਨ ਵਾਲੇ ਅਤੇ ਤਬਦੀਲੀਆਂ ਤੋਂ ਬਚਣ ਵਾਲੇ ਹੋ। ਕੁਝ ਨਵਾਂ ਕਰਨ ਤੋਂ ਕਤਰਾਉਂਦੇ ਹੋ। ਤੁਸੀਂ ਵਿਹਾਰਕ(practical) ਸੁਭਾਅ ਦੇ ਹੋ। "
        };
        double correlation_table[][] = {
                {-0.08, -0.04, -0.01, -0.04, -10},
                {-0.02, 0.01, -0.02, -0.01, 0.1},
                {0, -0.05, 0.06, 0, -0.03},
                {0.05, -0.15, 0.05, 0.04, -0.14},
                {-0.08, 0.12, 0.11, -0.07, 0.01},
                {-0.03, -0.18, -0.11, -0.11, 0.04},
                {-0.03, 0.05, -0.03, -0.02, -0.06},
                {0.07, 0.07, 0.05, 0.02, 0.02},
                {0, 0.06, 0.04, 0.08, -0.04},
                {0.07, 0.12, 0.04, 0.02, -0.06},
                {-0.06, -0.05, -0.04, -0.06, 0.08},
                {-0.06, 0.06, -0.05, 0.02, 0.1},
                {0.07, -0.14, -0.06, -0.04, -0.14},
                {-0.01, 0, -0.14, -0.11, 0.08},
                {-0.05, 0.1, -0.04, -0.05, 0.09},
                {-0.01, 0.02, 0.02, -0.02, 0.06},
                {0.06, 0.07, 0.04, 0.01, 0.04},
                {-0.01, 0.03, -0.06, -0.04, 0.11},
                {0.05, -0.06, 0.03, 0.06, -0.07},
                {-0.01, 0.02, 0.02, -0.02, 0.06},
                {-0.03, 0.05, -0.03, -0.02, -0.06},
                {-0.03, 0.05, -0.03, -0.02, -0.06},
                {0.05, -0.05, 0.09, 0.04, -0.07},
                {0.06, -0.04, 0.02, 0.01, -0.12},
                {0.07, -0.01, 0.03, -0.02, 0.08},
                {0, 0, 0, 0, 0},
                {-0.06, -0.01, -0.03, -0.06, 0.05},
                {0.05, -0.01, 0.03, 0.04, 0.04},
                {-0.02, 0.24, -0.13, -0.24, 0.01},
                {0, 0.03, 0, -0.06, 0.07},
                {0.01, 0.02, 0, -0.04, 0.04}
        };
        File[] files = new File("/home/gurpreet/Projects/personality/src/test/").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                feature_extractor mc = new feature_extractor();
                mc.readmsg(file.getName()); //read all msgs and put in list
                mc.load_sentlex();
                mc.load_emoji();
                for (String msg : mc.msgs) {
                    mc.unicodeOutlierMatcher = mc.emoticon.matcher(msg);
                    msg = mc.unicodeOutlierMatcher.replaceAll(" $0 ");
                    msg = msg.replaceAll("([\\p{P}]+)", " $1 ");
                    msg = msg.replaceAll("  ", " ");
                    msg = msg.replaceAll("  ", " ");

                    mc.punc_cnt(msg); //count all the punctuation in msg
                    int sum = 0;
                    for (int num : mc.punct_cnt) {
                        sum = sum + num;
                    }
                    mc.features[0] = mc.features[0] + sum;//all punctuation
                    mc.features[1] = mc.features[1] + mc.punct_cnt[1]; //count of commas
                    mc.features[2] = mc.features[2] + mc.punct_cnt[3];  // count of exclamation
                    mc.features[3] = mc.features[3] + mc.cnt_frst_per_snglr_pron(msg);
                    mc.features[4] = mc.features[4] + mc.neg_partical(msg);
                    mc.features[5] = mc.features[5] + mc.cnt_neg_emoji(msg);
                    mc.features[6] = mc.features[6] + mc.cnt_number(msg);
                    mc.features[7] = mc.features[7] + mc.cnt_pos_emoji(msg);
                    mc.features[8] = mc.features[8] + mc.preposition_cnt(msg);
                    mc.features[9] = mc.features[9] + mc.pron_cnt(msg);
                    mc.features[10] = mc.features[10] + mc.punct_cnt[2];  // count of ?
                    mc.features[11] = mc.features[11] + mc.wrds_morethan_sixltr(msg);
                    mc.features[12] = mc.features[12] + mc.self_ref_cnt(msg);
                    mc.features[13] = mc.features[13] + mc.swear_cnt(msg);
                    mc.features[14] = mc.features[14] + mc.type_token_ratio(msg);
                    mc.features[15] = mc.features[15] + mc.wrdcnt(msg);
                    mc.features[16] = mc.features[16] + mc.cnt_1_pers_plrl_pron(msg);
                    mc.features[17] = mc.features[17] + mc.cnt_2_pers_snglr_pron(msg);
                    mc.features[18] = mc.features[18] + mc.mean_wrd_freq(msg);
                    mc.features[19] = mc.features[19] + mc.wrd_per_sntnce(msg);
                    mc.features[20] = mc.features[20] + mc.quanitifiers_cnt(msg);
                    mc.features[21] = mc.features[21] + mc.ordinal_cnt(msg);
                    mc.features[22] = mc.features[22] + mc.family_wrd_cnt(msg);
                    mc.features[23] = mc.features[23] + mc.frnd_wrd_cnt(msg);
                    mc.features[24] = mc.features[24] + mc.postv_wrd_cnt(msg);
                    mc.features[25] = mc.features[25] + mc.negtv_wrd_cnt(msg);
                    mc.features[26] = mc.features[26] + mc.tentative_wrd_cnt(msg);
                    mc.features[27] = mc.features[27] + mc.certainity_wrd_cnt(msg);
                    mc.features[28] = mc.features[28] + mc.money_wrd_cnt(msg);
                    mc.features[29] = mc.features[29] + mc.religion_wrd_cnt(msg);
                    mc.features[30] = mc.features[30] + mc.assent_wrd_cnt(msg);
                }
                int personality_score[] = new int[5];

                for (int i = 0; i < mc.features.length; i++) {
                    if (mc.features[i] > mc.mean_of_features[i]) {
                        for (int j = 0; j < 5; j++) {
                            if (correlation_table[i][j] > 0)
                                personality_score[j]++;
                            else if (correlation_table[i][j] < 0)
                                personality_score[j]--;
                        }
                    }
                }
                System.out.print("\n"+file.getName()+",");
                for (int j = 0; j < 5; j++) {
                    System.out.print((personality_score[j] > 0 ? "Y" : personality_score[j] < 0 ? "N" : "O")+",");
                    //System.out.print((personality_score[j]>0?prediction[2*j]:personality_score[j]<0?prediction[2*j+1]:" "));
                }

            }
        }
    }
}
