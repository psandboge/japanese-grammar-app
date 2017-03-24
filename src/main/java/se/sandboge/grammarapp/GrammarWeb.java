package se.sandboge.grammarapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.sandboge.japanese.conjugation.Adjective;
import se.sandboge.japanese.conjugation.AdjectiveType;
import se.sandboge.japanese.conjugation.Verb;

@Controller
public class GrammarWeb {
    private static String[][] verbs = {
            { "あう", "会う", "möta" },
            { "あう", "合う", "matcha" },
            { "あきらめる", "諦める", "ge upp" },
            { "あく", "開く", "öppna sig" },
            { "あく", "開く", "öppna" },
            { "あげる", "あげる", "ge" },
            { "あつまる", "集る", "samla ihop" },
            { "あつめる", "集める", "samla" },
            { "あやまる", "謝る", "be om ursäkt" },
            { "あらう", "洗う", "tvätta" },
            { "あるく", "歩く", "gå" },
            { "あんないする", "案内する", "guida" },
            { "いそぐ", "急ぐ", "skynda sig" },
            { "いちにちじゅう", "一日中", "hela dagen" },
            { "いらっしゃいます", "いらっしゃいます", "är här (artigt)" },
            { "いれる", "入れる", "stoppa in" },
            { "いれる", "淹れる", "brygga (t ex kaffe" },
            { "うごく", "動く", "flytta" },
            { "うまれる", "生まれる", "födas" },
            { "うる", "売る", "sälja" },
            { "うんてんする", "運転する", "köra" },
            { "うんどうする", "運動する", "motionera" },
            { "えらぶ", "選ぶ", "välja" },
            { "おいのりする", "お祈りする", "be" },
            { "おくる", "送る", "skicka" },
            { "おこす", "起す", "väcka" },
            { "おごる", "おごる", "bjuda" },
            { "おしえる", "教える", "berätta" },
            { "おしえる", "教える", "lära ut" },
            { "おす", "押す", "stämpla" },
            { "おす", "押す", "trycka" },
            { "おちこむ", "落ち込む", "vara nedstämd" },
            { "おちる", "落ちる", "trilla ner" },
            { "おとす", "落す", "tappa" },
            { "おもいだす", "思い出す", "komma ihåg" },
            { "おもう", "思う", "tänka" },
            { "おゆをわかす", "お湯を沸かす", "koka vatten" },
            { "おろす", "下ろす", "ta ut (pengar)" },
            { "おわる", "終わる", "avsluta" },
            { "かぎをかける", "鍵をかける", "låsa" },
            { "かく", "描く", "rita" },
            { "かす", "貸す", "låna ut" },
            { "かたづける", "片付ける", "städa upp" },
            { "かなしむ", "悲しむ", "vara ledsen" },
            { "かみをとかす", "髪をとかす", "borsta håret" },
            { "かよう", "通う", "pendla" },
            { "かりる", "借りる", "låna" },
            { "かんがえる", "考える", "överväga" },
            { "かんこうする", "観光する", "gå på sightseeing" },
            { "かんどうする", "感動する", "bli berörd" },
            { "がんばる", "頑張る", "göra sitt bästa" },
            { "きえる", "消える", "försvinna" },
            { "くらべる", "比べる", "jämföra" },
            { "くる", "来る", "komma (använd hiragana)" },
            { "けいかくする", "計画する", "planera" },
            { "けしょうする", "化粧する", "sminka sig" },
            { "けど", "けど", "dock" },
            { "こたえる", "答える", "svara" },
            { "こまる", "困る", "vara orolig" },
            { "こむ", "込む", "inkludera" },
            { "ころぶ", "転ぶ", "falla" },
            { "こわす", "壊す", "göra sönder" },
            { "こわれる", "壊れる", "vara trasig" },
            { "さいきん", "最近", "nyligen" },
            { "さがす", "探す", "titta efter" },
            { "さく", "咲く", "blomma" },
            { "さそう", "誘う", "bjuda in" },
            { "しぬ", "死ぬ", "dö" },
            { "しまる", "閉まる", "stängas" },
            { "しまる", "閉まる", "vara stängd" },
            { "しめる", "閉める", "stänga" },
            { "しゃべる", "しゃべる", "småprata" },
            { "しゅうしょく", "就職", "söka jobb" },
            { "しゅうしょくする", "就職する", "ta anställning" },
            { "しゅっぱつする", "出発する", "avresa" },
            { "しょくじする", "食事する", "äta ett mål" },
            { "しらせる", "知らせる", "notifiera" },
            { "しらべる", "調べる", "undersöka" },
            { "しる", "知る", "veta" },
            { "すすむ", "進む", "göra framsteg" },
            { "すむ", "住む", "bo" },
            { "せいさんする", "生産する", "producera" },
            { "せわする", "世話する", "sköta om" },
            { "そうだん", "相談", "konsultation" },
            { "そうだんする", "相談する", "konsultera" },
            { "そつぎょうする", "卒業する", "ta examen" },
            { "たからくじにあたる", "宝くじに当たる", "vinna på lotteri" },
            { "たすかる", "助かる", "bli räddad" },
            { "たてる", "建てる", "bygga" },
            { "たのむ", "頼む", "fråga om en tjänst" },
            { "たりる", "足りる", "vara tillräckligt" },
            { "だす", "出す", "ta ut" },
            { "ちゅういする", "注意する", "varna" },
            { "ちゅうもんする", "注文する", "ge order" },
            { "つかう", "使う", "använda" },
            { "つきあう", "付き合う", "träffa (någon)" },
            { "つく", "点く", "sättas på" },
            { "つく", "着く", "anlända" },
            { "つれていく", "連れて行く", "ta med" },
            { "できる", "できる", "kunna göra" },
            { "とおる", "通る", "gå igenom" },
            { "とまる", "止まる", "stoppa" },
            { "とめる", "止める", "stoppa" },
            { "ともうします", "と申します", "jag heter" },
            { "なおす", "直す", "reparera" },
            { "なく", "泣く", "gråta" },
            { "ならう", "習う", "lära sig" },
            { "なれる", "慣れる", "vänja sig" },
            { "にあう", "似合う", "passa" },
            { "ぬぐ", "脱ぐ", "ta av sig" },
            { "のりおくれる", "乗り遅れる", "missa (bussen; tåget;...)" },
            { "はこぶ", "運ぶ", "transportera" },
            { "はじまる", "始まる", "börja" },
            { "はじめる", "始める", "börja" },
            { "はたらく", "働く", "arbeta" },
            { "ひかる", "光る", "skina" },
            { "ひく", "引く", "dra" },
            { "ひげをそる", "髭を剃る", "raka skägget" },
            { "ひらく", "開く", "öppna" },
            { "ふとる", "太る", "bli fet" },
            { "ほけんにはいる", "保険に入る", "teckna försäkring" },
            { "まにあう", "間に合う", "vara i tid" },
            { "まわる", "回る", "gå runt" },
            { "みえる", "見える", "synas" },
            { "みがく", "磨く", "borsta tänderna" },
            { "みがく", "磨く", "polera" },
            { "みせる", "見せる", "visa" },
            { "みちにまよう", "道に迷う", "gå vilse" },
            { "みつかる", "見つかる", "upptäckas" },
            { "むかえにいく", "迎えに行く", "åka och hämta" },
            { "むかえにくる", "迎えに来る", "komma och hämta" },
            { "やくす", "訳す", "översätta" },
            { "やくそくをまもる", "約束を守る", "hålla ett löfte" },
            { "よういする", "用意する", "förbereda" },
            { "よごす", "汚す", "smutsa ner" },
            { "よごれる", "汚れる", "bli smutsig" },
            { "よやくする", "予約する", "reservera" },
            { "りこんする", "離婚する", "skilja sig" },
            { "りゅうがくする", "留学する", "studera utomlands" },
            { "わかれる", "別れる", "separera" },
            { "わらう", "笑う", "le" },
            { "アイロンをかける", "アイロンをかける", "stryka" },
            { "プロポーズする", "プロポーズする", "fria" },
            { "気をつける", "気をつける", "vara försiktig" },
    };
    private static String[][] adjectives = {
            { "あおい", "青い", "blå", "i" },
            { "あかい", "赤い", "röd", "i" },
            { "あかるい", "明るい", "ljus", "i" },
            { "あつい", "暑い", "het", "i" },
            { "あぶない", "危ない", "farlig", "i" },
            { "いろいろ", "いろいろ", "blandade", "na" },
            { "うらやましい", "羨ましい", "avundsjuk", "i" },
            { "うれしい", "嬉しい", "glad", "i" },
            { "おもい", "重い", "tung", "i" },
            { "かなしい", "悲しい", "ledsen", "i" },
            { "からい", "辛い", "kryddstark", "i" },
            { "かるい", "軽い", "lätt", "i" },
            { "きたない", "汚い", "smutsig", "i" },
            { "きびしい", "厳しい", "sträng", "i" },
            { "くらい", "暗い", "dystert", "i" },
            { "さむい", "寒い", "kall", "i" },
            { "しあわせ", "幸せ", "lycklig", "na" },
            { "すくない", "少ない", "några", "i" },
            { "すごい", "凄い", "fantastisk", "i" },
            { "だめ", "駄目", "värdelös", "na" },
            { "ちかい", "近い", "nära", "i" },
            { "つよい", "強い", "kraftfull", "i" },
            { "とおい", "遠い", "långt", "i" },
            { "はずかしい", "恥ずかしい", "generad", "i" },
            { "ひくい", "低い", "kort", "i" },
            { "ひろい", "広い", "vid", "i" },
            { "ふとい", "太い", "fet", "i" },
            { "ほしい", "欲しい", "önskad", "i" },
            { "みじかい", "短い", "kort", "i" },
            { "ゆうめい", "有名", "berömd", "na" },
            { "よわい", "弱い", "svag", "i" },
            { "わるい", "悪い", "dålig", "i" },
    };

    @RequestMapping("/grammar")
    public String grammar(
            @RequestParam(value="class", required=false, defaultValue="Verb") String clazz,
            @RequestParam(value="type", required=false, defaultValue="i") String type,
            @RequestParam(value="method", required=false, defaultValue="asPoliteForm") String method,
            @RequestParam(value="message", required=false, defaultValue="") String message,
            @RequestParam(value="word", required=false) String word,
            @RequestParam(value="reading", required=false, defaultValue = "") String reading,
            @RequestParam(value="svenska", required=false) String svenska,
            @RequestParam(value="answer", required=false) String answer,
            Model model) {
        if (answer != null) {
            if (clazz.equals("Verb")) {
                Verb v = new Verb(word);
                String s = v.asPoliteForm();
                switch (method) {
                    case "asPoliteForm":
                        s = v.asPoliteForm();
                        break;
                    case "asPotentialForm":
                        s = v.asPotentialForm();
                        break;
                }
                if (s.equals(answer)) {
                    model.addAttribute("message", "Correct: " + word + " = " + s);
                } else {
                    model.addAttribute("message", "Wrong: " + word + " should be " + s + " NOT " + answer);
                }
            } else if (clazz.equals("Adjective")) {
                Adjective adjective = new Adjective(word, AdjectiveType.valueOf(type));
                String s = adjective.asPoliteForm();
                switch (method) {
                    case "asPoliteForm":
                        s = adjective.asPoliteForm();
                        break;
                    case "asShortForm":
                        s = adjective.asShortForm();
                        break;
                    case "asLooksLikeForm":
                        s = adjective.asLooksLikeForm();
                        break;
                    case "asLooksLikeNegForm":
                        s = adjective.asLooksLikeNegForm();
                        break;
                }
                if (s.equals(answer)) {
                    model.addAttribute("message", "Correct: " + word + " = " + s);
                } else {
                    model.addAttribute("message", "Wrong: " + word + " should be " + s + " NOT " + answer);
                }
            }
        }
        if (clazz.equals("Verb")) {
            int index = (int)(Math.random()*verbs.length);
            word = verbs[index][1];
            reading = verbs[index][0];
            svenska = verbs[index][2];
        } else if (clazz.endsWith("Adjective")) {
            int index = (int)(Math.random()*adjectives.length);
            word = adjectives[index][1];
            reading = adjectives[index][0];
            svenska = adjectives[index][2];
            model.addAttribute("type", adjectives[index][3]);
        }
        model.addAttribute("clazz", clazz);
        model.addAttribute("method", method);
        model.addAttribute("word", word);
        model.addAttribute("reading", reading.equals(word) ? "" : reading);
        model.addAttribute("svenska", svenska);
        return "grammar";
    }
}
