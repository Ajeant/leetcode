package leecode;

import java.util.*;

/**
 * 天啊，900+ms，不过还是解决了，下次再看看怎么减掉循环吧
 */
public class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        //判断是否已经匹配数组
        boolean jud[] = new boolean[words.length];
        List<Integer> res = new ArrayList<>();
        if(words.length == 0){
            return res;
        }
        //遍历字符串s，一旦发现与words中任意word相等，继续直到末尾
        for(int i=0; i < s.length(); i++){
            for(int j=0; j < jud.length; j++){
                //Boolean全部置为false
                jud[j] = false;
            }
            judNext(s, i, jud, words, res);
        }
        return res;
    }
    void judNext(String s, int i, boolean[] jud, String[] words, List<Integer> res){
        //如何解决中断呢？？前一个有符合，后一个没有，必须回到findSubString
        //解决回退问题，如果中途发现不符合，要回退比较
        int k = 0;
        //如果有不匹配的，jud数组全部置为false
        for(; i <= s.length()-words[0].length()*words.length; i++){
            for(int j=0; j < words.length; j++){
                //如果还未匹配，则匹配
                if(jud[j] == false && i < s.length() && s.charAt(i) == words[j].charAt(k)){
                    while(i < s.length()) {
                        if(s.charAt(i) == words[j].charAt(k)) {
                            i++;
                            k++;
                            if (k == words[j].length()) {
                                //已经匹配，存为true
                                jud[j] = true;
                                //已经全部匹配
                                if(judIsAllTrue(jud)){
                                    //开始位置为
                                    int start = i-words[0].length()*words.length;
                                    if(!res.contains(start)) {
                                        res.add(start);
                                    }
                                    return;
                                }
                                //继续判断另一个
                                j=-1;
                                break;
//                                judNext(s, i, jud, words, res);
//                                return;
                            }
                        }else {
                            //回退
                            i = i-k;
                            break;
                        }
                    }
                }
                //发现有不匹配的，直接跳出本单词
                k = 0;
            }
            for(int j=0; j < jud.length; j++){
                if(jud[j] == true){
                    return;
                }
            }
        }
    }
    boolean judIsAllTrue(boolean[] jud){
        for(int i=0; i < jud.length; i++){
            if(jud[i] == false){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        FindSubstring find = new FindSubstring();
//        String s = "bacfoothefoobarman";
//        String[] words = {"bar", "foo"};
//        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
//        String[] words = {"fooo","barr","wing","ding","wing"};
//        String s = "bccbcc";
//        String[] words = {"bc", "cc", "cb"};
//        String s = "ttqupktjvq\"wxnazzgwzlincurnioleblays\",\"txwrtqcaciunqdaskqbxjgqsa\",\"vdhzkkflociynhpowycqmrnsv\",\"sondwiizlapibmfyyfyoaymzo\",\"advmnlciojurgbfdohaworgir\",\"gbwpprhetfmjddaqxqwmeshas\",\"vmjcjlzasgtnrazmvfbnrfkuf\",\"zjxvnmfxayxlgnzssgayidibw\",\"xmsdwclozzurtesskdrofufkc\",\"gglmhnnrhwaetgrelkyjrlwbx\",\"galldybmwzrsozbnxyvfniqyl\",\"ofrbxnxgnefbaueghcbqldetf\",\"ltaczrtrtvigvpnqrncazoacp\",\"psacpkyhfsazmgkkadygnmnio\",\"mztmeoukmtmsdohlugclrjhgs\",\"gjdaqjsxphojgoihlowfxoyih\",\"gpcztxizzwsfvjdmhthsdvlbb\",\"bksjgvvglkdtuxhlnhkymtgto\",\"mewbjatnnylmlamrjrumfzkpx\",\"orsqpyotjlhlskcricbwveqec\",\"wsrdidpuytslzhtiheegjpskp\",\"zehikwbrcaqagjrahnbgozsve\",\"lzjoyehxbbttftvpqifviafad\",\"phyyfungiyxvignkjkboavlgu\",\"zdadsqaumdqnykozmbaerbiac\",\"zxyoruvlioevfbtvjzsdwugri\",\"xtvotgutpmetmoiwcxidgwwkx\",\"cpvxtcnnvmaisoucjvehwdxst\",\"jfgzacxstacwrwxnrkmagvhkz\",\"nubrhcnrqxizwdiropfpmrmpt\",\"rwahikxnbanqyveeafgxloepq\",\"zncxvbeiaxcombypgriszdywt\",\"ottznlgmljvbcwzxqvouezeop\",\"rqguzqyfuclhgvoixbotkylmp\",\"zdsvpffvubvrhlrjkrgecxbjk\",\"kqbvgarsjtduvdobyduyfhumk\",\"azhkdexxiqgkgzcfjccmmtdcy\",\"igcudsdkouxvqfdhinzvzphbd\",\"igylpaqhxlavtemfrkfbcgtzz\",\"ezdfanpirjnmnxcjxxbccimpj\",\"hwydjosbxvsuqcijkiovbfvcf\",\"lzgwaiijmogbcrtpwmmbffzdt\",\"fsqgolcvbncttmriivgagppbp\",\"hsqbiaauxzqeogwrarpfuipmp\",\"mxonxghsmrqazldhfhuakvdba\",\"legajnsmaqdhfrsednjrlrhch\",\"eebwbgifcrjdyunprrhgzamjz\",\"suxzpxiruizscmubdapkvgoen\",\"uvnekcbtcfreqywtrjiwymeqb\",\"pquuuvmknvsafawymztykfphw\",\"bhxfwbssquhcmpkwupzpuetih\",\"pshdpvvkotfzcjrrzbarpljus\",\"edgdulxyofngciphxznwqnghi\",\"pwzrivzxmexawavglqkwgprxw\",\"wimiohxvhtlhnzvmxdzokohbx\",\"sjcqalwynbjaksrcjnvccwgry\",\"qwfkyjeabdcrihatdbwhmzodh\",\"thmqsvaywlqwdxuozpjddrvpw\",\"jdpuawgqycxavrjqxflavpzyj\",\"xphftvkfkvtdnskbvdaauujow\",\"mwkcaowmafwecxdrpcwdkoxez\",\"rrlbbvietzvkhbeonpsqpiogh\",\"gyglgplsphtjuarwldnhnkrlc\",\"sqshgljhutkbtjjjrvrtzxcdb\",\"ihvnmwectywomjdibfnddnsfh\",\"dymvirpvsqarzwiqnymiodjpn\",\"evrocfhbeuleghbkdsobdbjdm\",\"sbbfnsxujdnkeyqziptzkpemt\",\"gmlucsfhiscphrwbvforsvnur\",\"nfbceicqizfufcwfzjlmidhrf\",\"tbqxanffjiubddidfrgbyeemr\",\"fszmebvmvdsuiiprxkpbemcuf\",\"ziwqcqzawkinvbcewnawezkhm\",\"dmolmtryfobswfrujsckykhva\",\"ewcflmhhprotrtnzcnbxyufad\",\"qcebmfvorruiawoqyqrotlgnb\",\"qdnwpemljzmudmyiqpqxpjcsf\",\"xhaewbjgsdrmpencismzrqlyt\",\"iyrrcbxvwgjwcdfagcqybywjm\",\"rhezwydtrswwbzavkfdriqwbe\",\"kljmfvnkyseotzcyrpwcfyoiy\",\"xtygncsqeramomcgwupanqmng\",\"uzoegsotzycktizwzswrvmles\",\"fzlpchdikhotlgbllxsotxnci\",\"rrwrxwqqzahgwhhjcvmlswdne\",\"xtckpftazltocgvisvhihjmsu\",\"aovdhambtnkvpguoyyeewwhql\",\"qenewxnpzfhdbamfwkybxatbu\",\"wqvursrgkyrrmgklgtaykmpgq\",\"ezbwxkjxqbphoromxfepappwa\",\"mzeqqrmcewepsrvkyvjgfhehb\",\"ulzbezgwrroowtoraaifaaucf\",\"rdxzzkdbnzyodndqvjhwmqyds\",\"qtryvjjcrnoiirnlqdewcgvkp\",\"slemeueamiapngfbuskzbfaxc\",\"ssejwngvdgucntdadqdxhqgwd\",\"lhgdmptbdbyptezaxgjshsnxn\",\"qheoqszaxbvfnshrsjtqkcwhq\",\"twwkkamuxcwpbylngylhcpgps\",\"fszrvadibvxnynvkccjgftmxq\",\"hftqnhwfgpdislmnkzmxwybbj\",\"iimszlgolmfhhdabisrfcenzf\",\"mztizykktjbpnsqgjagyhnldz\",\"eacvxzcmnnigozbjzrazvauct\",\"hkhkjjteoarrapbouggbahhiw\",\"uknkzjtldsgyygjwyctxqaqwh\",\"lduheaglipveqogteizobzxoc\",\"ggvzcgkbngexqplfvmhpizlul\",\"omjeoridfthannguvvhntdvom\",\"uulivjajzfhhnhunyeqkmojrn\",\"npofvbdqwtjfnewcjpozzmbwx\",\"altzpuboytrkxphfnumxhxfan\",\"scbdcfpqodxmqikioadndchsy\",\"jfontgnoftejlunfuzvidcezt\",\"pvohffrkmslopfhpigigfvzpi\",\"kkhjbtzpfdgyljfgrftgnqbjz\",\"sllgiexbwpsxfmgqttldvcnzg\",\"exyfjlrkcioxywnauwfsumpbh\",\"hkuzttgzjwssxxmftggubxeol\",\"gftdpsshhaciduhwdmljinuae\",\"uzktepjafqccyqbsqtoyvnnad\",\"fnzrunpwibfrtkhsfvqkjowpzgpvtlmokhpjxwvzxkvfhyfgrnhgamrezqxpyovlkoqvezgcrljqslmbbunhudqnahuqqcgylrohotchmbxeounavgfrkuzqkbqexlcczifvmosisjerymaqvpieoqljvtceodrqrktbendtdstinosjlkjnzrhaphrxeimartpxxphyjudhldzyjmfdqfsbfhonpqihclxrogqekurflpjkwqdfodttcufirujuknynbfkrwblhdhvuhnncnnletsdehinmsyugthnuuyrmstqcybdfqomliditbuvhvavqyckzjmubfhcgdmmwoijxjplxedttiemxdgkpaoxpdnzefudcxkojiuqtnpnhgmyxnmcejjdzbafaeumtsmovwqhgxsuzdvmkkdjihirbvapivpnwigpeeykzpxphmtczeqbkexwiyleknlgtnfwgwaeeklxclicwwcbfbeiuxpquficmxatbwbssbbcnapobehzzlfcsmheoqtjzetiuszbuokloowaqnvvbdhkekufkwkqdqumhhzynawtfjrqhknearpcukfaclsahmrswxlxxweposbstyfasishkbdbrfqfrftyzjgtkzrrtwcacaqmxrdljcofosioagvqgyjxyhwabkdkecygwfqaaqqayzasmchpcciyglwnzokqggkdeavfdelbmsmopavzvzzohlabnugucaztiqpjnuzhmpsvjlqrpdoujykjpjaszbygqecltqwdxhhtgwymcyzoegfjbkkzpnmdohmpfwcaidqvvidhilyorjkfvmajqogbazntfdcsxjwozmjrlijrcavpdquxgxsspdckpbmnhemixfnnrxyehtvrgmcgjhmjnhmsbeyqrclsrqnkcgvnbbgnmjttbrssaqrxexvetvbbitlmjeeunmjsnervdsryyohbaqwjdbdqqvbuxumyyquawxqhtwmzwpqlvjmbsdtcnigpzxsukzwmkokhfcvgkvzcacbvyjgxljcnsefgttinaushwdwqszgvgnmyrrnajawziwsmzxpemhvrehhmrpdrtjraqsdwrhbuwtencsbrrdaocdwcumhjpeoxtnyfmyjgidwdygzzsupwrrbmfqxhznqvgzwncnucisxoessjaxelwczdrfgzvpzufmkvcyneoihyivzdjjyarvpptttblcltdkrtbnonydtvkwofhnzbvdiheadshixavlsgzhljyvrhwgyfxnvpdglythhclicnaspmkjczrsbedwzgcolugcgagmpliuhbtaozfiyuncpbxttubdjuawlysesrelasduqdvmqlivkemjwvdvegpzylwacuitqstkwhexmfmqpohxkseifqgjvzhtpvcudgbsxnqfjgnptzygmrpkrtezkklwiloagputwqufsnuiliwzkkswbmqmaygqwpoluqgnluswyiqtkiikfkgujfjnceqgnrfrhruhjbythhuiezfqtimvwcuxzntfhlrnefppfynqctklvcjofupfpppbgfzhnjfvuqxiwlcgzdmvvvtgutpwfojhsgkxnucxqjhotzsluesddebcenekfzbmgwhhrapdtanmhyjyndatsvodjcqstzrpwtohoteahsjdyjpjmnkyjmrcarfpqfnneawoysnozwgnpfntrgescdnaamyvbulegalkpvjwiwqiwupsrorniwvnuhieayasqajpythnstckaaadehgciomnrgzwevjvnccsyvymkadfzwiolfaoovcixofddssksvddztfkrdasocdhthqicvcivwcpuxpghqjprhzgteihsdlcfuiasytbjayvaklpqmprckwycjvozfarxyakorbrhvchhowtfpvstyicqmnyykqyqxrxhlxljpuofavimwtasugazodnbbblkynhqeihlxsygethktxbwlvnbjaxbstdokilljvqkkrjauydntpweewxnazzgwzlincurnioleblaystxwrtqcaciunqdavdhzkkflociynhpowycqmrnsvsondwiizlapibmfyyfyoaymzoadvmnlciojurgbfdohaworgirgbwpprhetfmjddaqxqwmeshasvmjcjlzasgtnrazmvfbnrfkufzjxvnmfxayxlgnzssgayidibwskqbxjgqsaylzkyirjnnjuhyrmrhwkaoepyzavshbquaasltdyimtpmmddtzotnsknsnkkumcooeizxmyfyrwlfbqyaetpzdetsodmahcpllqizopzhnmmodkqhdfijjbbxgqansryfhiewzgfmsscvcsfizntkpukvkkjfbjmkutitzoirgmpkfuughnrykbuwnfbqqqckjlgizqbhcqtjkosudlunookbvntodvymggwhyaodqkodygzbrtpfkbifodtszughcdpdffgvpwapdzrwtiefjomxsirrcyygdpjixrfmogctfvpafvfqksncchtgftmklysipxudfyynvoncjvsvpixrpsxumlexjyfbxbgdcfqzvfcgcuctczmtsjngxtdgtkjrnqwrxorsvvyaratwcgpurfaoyfxurgneylyvzrpwmowdqqzsyropwqewvbqojfvkqtfrkxowlmbdcdycumssdwoazhpkmkufdcwpzmnmzopdtngpcyhfwyfxhuhvecajexyfjlrkcioxywnauwfsumpbhwxnazzgwzlincurnioleblaysssejwngvdgucntdadqdxhqgwdmxonxghsmrqazldhfhuakvdbabksjgvvglkdtuxhlnhkymtgtogglmhnnrhwaetgrelkyjrlwbxxnqfjgnptzygmrpkrtezkklwiwqvursrgkyrrmgklgtaykmpgqpsacpkyhfsazmgkkadygnmnioltaczrtrtvigvpnqrncazoacpirbvapivpnwigpeeykzpxphmtjlkjnzrhaphrxeimartpxxphyheoqtjzetiuszbuokloowaqnvhkuzttgzjwssxxmftggubxeoluknkzjtldsgyygjwyctxqaqwhmzeqqrmcewepsrvkyvjgfhehbezbwxkjxqbphoromxfepappwavdhzkkflociynhpowycqmrnsveumtsmovwqhgxsuzdvmkkdjihzxyoruvlioevfbtvjzsdwugritblcltdkrtbnonydtvkwofhnzljvtceodrqrktbendtdstinosjczrsbedwzgcolugcgagmpliudbdqqvbuxumyyquawxqhtwmzwsondwiizlapibmfyyfyoaymzouulivjajzfhhnhunyeqkmojrnztnmuciopqkyqtxxbgkkczihrttiemxdgkpaoxpdnzefudcxkohmpsvjlqrpdoujykjpjaszbygycjvozfarxyakorbrhvchhowtorsqpyotjlhlskcricbwveqecsllgiexbwpsxfmgqttldvcnzgmwkcaowmafwecxdrpcwdkoxezczeqbkexwiyleknlgtnfwgwaealtzpuboytrkxphfnumxhxfanmztizykktjbpnsqgjagyhnldzkkzpnmdohmpfwcaidqvvidhilfszrvadibvxnynvkccjgftmxqhftqnhwfgpdislmnkzmxwybbjigcudsdkouxvqfdhinzvzphbdezdfanpirjnmnxcjxxbccimpjjraqsdwrhbuwtencsbrrdaocdqenewxnpzfhdbamfwkybxatbumewbjatnnylmlamrjrumfzkpxloagputwqufsnuiliwzkkswbmiimszlgolmfhhdabisrfcenzfzjxvnmfxayxlgnzssgayidibwnfbceicqizfufcwfzjlmidhrffzlpchdikhotlgbllxsotxncicnnletsdehinmsyugthnuuyrmomjeoridfthannguvvhntdvomkkhjbtzpfdgyljfgrftgnqbjzqecltqwdxhhtgwymcyzoegfjblxrogqekurflpjkwqdfodttcuhgamrezqxpyovlkoqvezgcrljgpcztxizzwsfvjdmhthsdvlbbmjeeunmjsnervdsryyohbaqwjlzgwaiijmogbcrtpwmmbffzdtggvzcgkbngexqplfvmhpizlulmztmeoukmtmsdohlugclrjhgsyorjkfvmajqogbazntfdcsxjwlcfuiasytbjayvaklpqmprckwfppfynqctklvcjofupfpppbgfpwzrivzxmexawavglqkwgprxwgnmjttbrssaqrxexvetvbbitleacvxzcmnnigozbjzrazvauctrdxzzkdbnzyodndqvjhwmqydsxbstdokilljvqkkrjauydntpwpbmnhemixfnnrxyehtvrgmcgjamyvbulegalkpvjwiwqiwupsrgyglgplsphtjuarwldnhnkrlcziwqcqzawkinvbcewnawezkhmtwwkkamuxcwpbylngylhcpgpsevrocfhbeuleghbkdsobdbjdmrhezwydtrswwbzavkfdriqwbezehikwbrcaqagjrahnbgozsvelduheaglipveqogteizobzxoceebwbgifcrjdyunprrhgzamjzckaaadehgciomnrgzwevjvnccvbdhkekufkwkqdqumhhzynawtwfojhsgkxnucxqjhotzsluesdfsqgolcvbncttmriivgagppbphmjnhmsbeyqrclsrqnkcgvnbbnhqeihlxsygethktxbwlvnbjafpvstyicqmnyykqyqxrxhlxljigylpaqhxlavtemfrkfbcgtzzthmqsvaywlqwdxuozpjddrvpwysesrelasduqdvmqlivkemjwvozmjrlijrcavpdquxgxsspdckphyyfungiyxvignkjkboavlgugalldybmwzrsozbnxyvfniqyleahsjdyjpjmnkyjmrcarfpqfnxtvotgutpmetmoiwcxidgwwkxnpofvbdqwtjfnewcjpozzmbwxgftdpsshhaciduhwdmljinuaetbqxanffjiubddidfrgbyeemrqwfkyjeabdcrihatdbwhmzodhqdnwpemljzmudmyiqpqxpjcsflegajnsmaqdhfrsednjrlrhchgttinaushwdwqszgvgnmyrrnaiyrrcbxvwgjwcdfagcqybywjmkhfcvgkvzcacbvyjgxljcnsefqheoqszaxbvfnshrsjtqkcwhqgjdaqjsxphojgoihlowfxoyihqslmbbunhudqnahuqqcgylrohxlxxweposbstyfasishkbdbrfhsqbiaauxzqeogwrarpfuipmpaovdhambtnkvpguoyyeewwhqlmkvcyneoihyivzdjjyarvppttwsrdidpuytslzhtiheegjpskpjfgzacxstacwrwxnrkmagvhkzpvohffrkmslopfhpigigfvzpibvdiheadshixavlsgzhljyvrhewcflmhhprotrtnzcnbxyufadsjcqalwynbjaksrcjnvccwgrydssksvddztfkrdasocdhthqiculzbezgwrroowtoraaifaaucfdymvirpvsqarzwiqnymiodjpnorniwvnuhieayasqajpythnstwgyfxnvpdglythhclicnaspmkofrbxnxgnefbaueghcbqldetfrwahikxnbanqyveeafgxloepqxatbwbssbbcnapobehzzlfcsmzdadsqaumdqnykozmbaerbiackljmfvnkyseotzcyrpwcfyoiyscbdcfpqodxmqikioadndchsyjawziwsmzxpemhvrehhmrpdrtsuxzpxiruizscmubdapkvgoendvegpzylwacuitqstkwhexmfmwcumhjpeoxtnyfmyjgidwdygzfnzrunpwibfrtkhsfvqkjowpzzncxvbeiaxcombypgriszdywtkfkgujfjnceqgnrfrhruhjbyttxwrtqcaciunqdaskqbxjgqsalzjoyehxbbttftvpqifviafadeklxclicwwcbfbeiuxpquficmdmolmtryfobswfrujsckykhvaxphftvkfkvtdnskbvdaauujowvmjcjlzasgtnrazmvfbnrfkufkqbvgarsjtduvdobyduyfhumkjdpuawgqycxavrjqxflavpzyjzdsvpffvubvrhlrjkrgecxbjkdebcenekfzbmgwhhrapdtanmhrqguzqyfuclhgvoixbotkylmpqfrftyzjgtkzrrtwcacaqmxrdsbbfnsxujdnkeyqziptzkpemtedgdulxyofngciphxznwqnghiisxoessjaxelwczdrfgzvpzufxmsdwclozzurtesskdrofufkchhuiezfqtimvwcuxzntfhlrnefszmebvmvdsuiiprxkpbemcufhbtaozfiyuncpbxttubdjuawlecygwfqaaqqayzasmchpcciygljcofosioagvqgyjxyhwabkdkzhnjfvuqxiwlcgzdmvvvtgutpstqcybdfqomliditbuvhvavqyslemeueamiapngfbuskzbfaxcjudhldzyjmfdqfsbfhonpqihchkhkjjteoarrapbouggbahhiwuzoegsotzycktizwzswrvmlesneawoysnozwgnpfntrgescdnawimiohxvhtlhnzvmxdzokohbxnubrhcnrqxizwdiropfpmrmptazhkdexxiqgkgzcfjccmmtdcysqshgljhutkbtjjjrvrtzxcdbpquuuvmknvsafawymztykfphwihvnmwectywomjdibfnddnsfhqpohxkseifqgjvzhtpvcudgbslhgdmptbdbyptezaxgjshsnxnqtryvjjcrnoiirnlqdewcgvkppshdpvvkotfzcjrrzbarpljushwydjosbxvsuqcijkiovbfvcfottznlgmljvbcwzxqvouezeopcpvxtcnnvmaisoucjvehwdxstsyvymkadfzwiolfaoovcixofdvcivwcpuxpghqjprhzgteihsdlcczifvmosisjerymaqvpieoqxtygncsqeramomcgwupanqmngjfontgnoftejlunfuzvidceztgmlucsfhiscphrwbvforsvnurvzvzzohlabnugucaztiqpjnuzgpvtlmokhpjxwvzxkvfhyfgrnyjyndatsvodjcqstzrpwtohotqmaygqwpoluqgnluswyiqtkiigbwpprhetfmjddaqxqwmeshasrrlbbvietzvkhbeonpsqpioghckzjmubfhcgdmmwoijxjplxedpuofavimwtasugazodnbbblkyuzktepjafqccyqbsqtoyvnnadqcebmfvorruiawoqyqrotlgnbfirujuknynbfkrwblhdhvuhnnxhaewbjgsdrmpencismzrqlytuvnekcbtcfreqywtrjiwymeqbfjrqhknearpcukfaclsahmrswpqlvjmbsdtcnigpzxsukzwmkootchmbxeounavgfrkuzqkbqexadvmnlciojurgbfdohaworgirzsupwrrbmfqxhznqvgzwncnucjiuqtnpnhgmyxnmcejjdzbafaxtckpftazltocgvisvhihjmsurrwrxwqqzahgwhhjcvmlswdnelwnzokqggkdeavfdelbmsmopabhxfwbssquhcmpkwupzpuetihhctysuzzrgwubqmvxpjoziiohxlntvucxpcpjcrckpynflifautbccqjbdywasbxhdpthixuxwhehqljkfkojcxmhvlhpmhwryrzgptflajusvfecpsnurbdomldaucsstrykmdvmorhlwnpogcizelosyrtuvlmrembqrxeiqntnbjbjnlitywmxqhrlugeyugqmfwuvkpinylwowghvdvuveniyvnyqtocylytxnekopicoolltjfonuzmqmkghbkhoialvgxlyhpmvdxpmefzyenosawzbggrojcmrbzpipddscueicfhivxbvwcyclprutfnuedevijnngtvibeqwcttyytcjivfdaszhxdcimqvsjqakvtizobxmubxexchxvgeulhcyzxqkotxjofkejjrfkokhttfoclknunntkggazdlpkvttemzpdlwwgdmirwyljlhwrpwssihcddykrzzukxssdgbwdbjomqmghjyztiewnciedhbphvhmckihlrxykkgrvetooxmbbvieejtleaxinhryzpylzecwlvkzuwdvrlbwuwjaxjlmqckrrjcspfoacaaxxduqqwmksymrhxdzcortztooaepffrccwwsmuhynfkgokknmaghbcvpcagjfzljvkdjgkhvrucfgovsqbbrdxii";
//        s = s.replace(",","");
//        s = s.replace("\"", "");
//        String[] words = {"wxnazzgwzlincurnioleblays","txwrtqcaciunqdaskqbxjgqsa","vdhzkkflociynhpowycqmrnsv","sondwiizlapibmfyyfyoaymzo","advmnlciojurgbfdohaworgir","gbwpprhetfmjddaqxqwmeshas","vmjcjlzasgtnrazmvfbnrfkuf","zjxvnmfxayxlgnzssgayidibw","xmsdwclozzurtesskdrofufkc","gglmhnnrhwaetgrelkyjrlwbx","galldybmwzrsozbnxyvfniqyl","ofrbxnxgnefbaueghcbqldetf","ltaczrtrtvigvpnqrncazoacp","psacpkyhfsazmgkkadygnmnio","mztmeoukmtmsdohlugclrjhgs","gjdaqjsxphojgoihlowfxoyih","gpcztxizzwsfvjdmhthsdvlbb","bksjgvvglkdtuxhlnhkymtgto","mewbjatnnylmlamrjrumfzkpx","orsqpyotjlhlskcricbwveqec","wsrdidpuytslzhtiheegjpskp","zehikwbrcaqagjrahnbgozsve","lzjoyehxbbttftvpqifviafad","phyyfungiyxvignkjkboavlgu","zdadsqaumdqnykozmbaerbiac","zxyoruvlioevfbtvjzsdwugri","xtvotgutpmetmoiwcxidgwwkx","cpvxtcnnvmaisoucjvehwdxst","jfgzacxstacwrwxnrkmagvhkz","nubrhcnrqxizwdiropfpmrmpt","rwahikxnbanqyveeafgxloepq","zncxvbeiaxcombypgriszdywt","ottznlgmljvbcwzxqvouezeop","rqguzqyfuclhgvoixbotkylmp","zdsvpffvubvrhlrjkrgecxbjk","kqbvgarsjtduvdobyduyfhumk","azhkdexxiqgkgzcfjccmmtdcy","igcudsdkouxvqfdhinzvzphbd","igylpaqhxlavtemfrkfbcgtzz","ezdfanpirjnmnxcjxxbccimpj","hwydjosbxvsuqcijkiovbfvcf","lzgwaiijmogbcrtpwmmbffzdt","fsqgolcvbncttmriivgagppbp","hsqbiaauxzqeogwrarpfuipmp","mxonxghsmrqazldhfhuakvdba","legajnsmaqdhfrsednjrlrhch","eebwbgifcrjdyunprrhgzamjz","suxzpxiruizscmubdapkvgoen","uvnekcbtcfreqywtrjiwymeqb","pquuuvmknvsafawymztykfphw","bhxfwbssquhcmpkwupzpuetih","pshdpvvkotfzcjrrzbarpljus","edgdulxyofngciphxznwqnghi","pwzrivzxmexawavglqkwgprxw","wimiohxvhtlhnzvmxdzokohbx","sjcqalwynbjaksrcjnvccwgry","qwfkyjeabdcrihatdbwhmzodh","thmqsvaywlqwdxuozpjddrvpw","jdpuawgqycxavrjqxflavpzyj","xphftvkfkvtdnskbvdaauujow","mwkcaowmafwecxdrpcwdkoxez","rrlbbvietzvkhbeonpsqpiogh","gyglgplsphtjuarwldnhnkrlc","sqshgljhutkbtjjjrvrtzxcdb","ihvnmwectywomjdibfnddnsfh","dymvirpvsqarzwiqnymiodjpn","evrocfhbeuleghbkdsobdbjdm","sbbfnsxujdnkeyqziptzkpemt","gmlucsfhiscphrwbvforsvnur","nfbceicqizfufcwfzjlmidhrf","tbqxanffjiubddidfrgbyeemr","fszmebvmvdsuiiprxkpbemcuf","ziwqcqzawkinvbcewnawezkhm","dmolmtryfobswfrujsckykhva","ewcflmhhprotrtnzcnbxyufad","qcebmfvorruiawoqyqrotlgnb","qdnwpemljzmudmyiqpqxpjcsf","xhaewbjgsdrmpencismzrqlyt","iyrrcbxvwgjwcdfagcqybywjm","rhezwydtrswwbzavkfdriqwbe","kljmfvnkyseotzcyrpwcfyoiy","xtygncsqeramomcgwupanqmng","uzoegsotzycktizwzswrvmles","fzlpchdikhotlgbllxsotxnci","rrwrxwqqzahgwhhjcvmlswdne","xtckpftazltocgvisvhihjmsu","aovdhambtnkvpguoyyeewwhql","qenewxnpzfhdbamfwkybxatbu","wqvursrgkyrrmgklgtaykmpgq","ezbwxkjxqbphoromxfepappwa","mzeqqrmcewepsrvkyvjgfhehb","ulzbezgwrroowtoraaifaaucf","rdxzzkdbnzyodndqvjhwmqyds","qtryvjjcrnoiirnlqdewcgvkp","slemeueamiapngfbuskzbfaxc","ssejwngvdgucntdadqdxhqgwd","lhgdmptbdbyptezaxgjshsnxn","qheoqszaxbvfnshrsjtqkcwhq","twwkkamuxcwpbylngylhcpgps","fszrvadibvxnynvkccjgftmxq","hftqnhwfgpdislmnkzmxwybbj","iimszlgolmfhhdabisrfcenzf","mztizykktjbpnsqgjagyhnldz","eacvxzcmnnigozbjzrazvauct","hkhkjjteoarrapbouggbahhiw","uknkzjtldsgyygjwyctxqaqwh","lduheaglipveqogteizobzxoc","ggvzcgkbngexqplfvmhpizlul","omjeoridfthannguvvhntdvom","uulivjajzfhhnhunyeqkmojrn","npofvbdqwtjfnewcjpozzmbwx","altzpuboytrkxphfnumxhxfan","scbdcfpqodxmqikioadndchsy","jfontgnoftejlunfuzvidcezt","pvohffrkmslopfhpigigfvzpi","kkhjbtzpfdgyljfgrftgnqbjz","sllgiexbwpsxfmgqttldvcnzg","exyfjlrkcioxywnauwfsumpbh","hkuzttgzjwssxxmftggubxeol","gftdpsshhaciduhwdmljinuae","uzktepjafqccyqbsqtoyvnnad","fnzrunpwibfrtkhsfvqkjowpz","gpvtlmokhpjxwvzxkvfhyfgrn","hgamrezqxpyovlkoqvezgcrlj","qslmbbunhudqnahuqqcgylroh","otchmbxeounavgfrkuzqkbqex","lcczifvmosisjerymaqvpieoq","ljvtceodrqrktbendtdstinos","jlkjnzrhaphrxeimartpxxphy","judhldzyjmfdqfsbfhonpqihc","lxrogqekurflpjkwqdfodttcu","firujuknynbfkrwblhdhvuhnn","cnnletsdehinmsyugthnuuyrm","stqcybdfqomliditbuvhvavqy","ckzjmubfhcgdmmwoijxjplxed","ttiemxdgkpaoxpdnzefudcxko","jiuqtnpnhgmyxnmcejjdzbafa","eumtsmovwqhgxsuzdvmkkdjih","irbvapivpnwigpeeykzpxphmt","czeqbkexwiyleknlgtnfwgwae","eklxclicwwcbfbeiuxpquficm","xatbwbssbbcnapobehzzlfcsm","ztnmuciopqkyqtxxbgkkczihr","heoqtjzetiuszbuokloowaqnv","vbdhkekufkwkqdqumhhzynawt","fjrqhknearpcukfaclsahmrsw","xlxxweposbstyfasishkbdbrf","qfrftyzjgtkzrrtwcacaqmxrd","ljcofosioagvqgyjxyhwabkdk","ecygwfqaaqqayzasmchpcciyg","lwnzokqggkdeavfdelbmsmopa","vzvzzohlabnugucaztiqpjnuz","hmpsvjlqrpdoujykjpjaszbyg","qecltqwdxhhtgwymcyzoegfjb","kkzpnmdohmpfwcaidqvvidhil","yorjkfvmajqogbazntfdcsxjw","ozmjrlijrcavpdquxgxsspdck","pbmnhemixfnnrxyehtvrgmcgj","hmjnhmsbeyqrclsrqnkcgvnbb","gnmjttbrssaqrxexvetvbbitl","mjeeunmjsnervdsryyohbaqwj","dbdqqvbuxumyyquawxqhtwmzw","pqlvjmbsdtcnigpzxsukzwmko","khfcvgkvzcacbvyjgxljcnsef","gttinaushwdwqszgvgnmyrrna","jawziwsmzxpemhvrehhmrpdrt","jraqsdwrhbuwtencsbrrdaocd","wcumhjpeoxtnyfmyjgidwdygz","zsupwrrbmfqxhznqvgzwncnuc","isxoessjaxelwczdrfgzvpzuf","mkvcyneoihyivzdjjyarvpptt","tblcltdkrtbnonydtvkwofhnz","bvdiheadshixavlsgzhljyvrh","wgyfxnvpdglythhclicnaspmk","jczrsbedwzgcolugcgagmpliu","hbtaozfiyuncpbxttubdjuawl","ysesrelasduqdvmqlivkemjwv","dvegpzylwacuitqstkwhexmfm","qpohxkseifqgjvzhtpvcudgbs","xnqfjgnptzygmrpkrtezkklwi","loagputwqufsnuiliwzkkswbm","qmaygqwpoluqgnluswyiqtkii","kfkgujfjnceqgnrfrhruhjbyt","hhuiezfqtimvwcuxzntfhlrne","fppfynqctklvcjofupfpppbgf","zhnjfvuqxiwlcgzdmvvvtgutp","wfojhsgkxnucxqjhotzsluesd","debcenekfzbmgwhhrapdtanmh","yjyndatsvodjcqstzrpwtohot","eahsjdyjpjmnkyjmrcarfpqfn","neawoysnozwgnpfntrgescdna","amyvbulegalkpvjwiwqiwupsr","orniwvnuhieayasqajpythnst","ckaaadehgciomnrgzwevjvncc","syvymkadfzwiolfaoovcixofd","dssksvddztfkrdasocdhthqic","vcivwcpuxpghqjprhzgteihsd","lcfuiasytbjayvaklpqmprckw","ycjvozfarxyakorbrhvchhowt","fpvstyicqmnyykqyqxrxhlxlj","puofavimwtasugazodnbbblky","nhqeihlxsygethktxbwlvnbja","xbstdokilljvqkkrjauydntpw"};
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        List<Integer> res = find.findSubstring(s, words);
        System.out.println(res);
    }
    //这是讨论区大神的10ms
    public List<Integer> findSubstring1(String s, String[] words) {
        //String长度
        int N = s.length();
        //结果集
        List<Integer> indexes = new ArrayList<>(s.length());
        if (words.length == 0) {
            return indexes;
        }
        int M = words[0].length();
        if (N < M * words.length) {
            return indexes;
        }
        int last = N - M + 1;

        //map each string in words array to some index and compute target counters
        Map<String, Integer> mapping = new HashMap<>(words.length);
        int [][] table = new int[2][words.length];
        int failures = 0, index = 0;
        for (int i = 0; i < words.length; ++i) {
            Integer mapped = mapping.get(words[i]);
            if (mapped == null) {
                ++failures;
                mapping.put(words[i], index);
                mapped = index++;
            }
            ++table[0][mapped];
        }

        //find all occurrences at string S and map them to their current integer, -1 means no such string is in words array
        int [] smapping = new int[last];
        for (int i = 0; i < last; ++i) {
            String section = s.substring(i, i + M);
            Integer mapped = mapping.get(section);
            if (mapped == null) {
                smapping[i] = -1;
            } else {
                smapping[i] = mapped;
            }
        }

        //fix the number of linear scans
        for (int i = 0; i < M; ++i) {
            //reset scan variables
            int currentFailures = failures; //number of current mismatches
            int left = i, right = i;
            Arrays.fill(table[1], 0);
            //here, simple solve the minimum-window-substring problem
            while (right < last) {
                while (currentFailures > 0 && right < last) {
                    int target = smapping[right];
                    if (target != -1 && ++table[1][target] == table[0][target]) {
                        --currentFailures;
                    }
                    right += M;
                }
                while (currentFailures == 0 && left < right) {
                    int target = smapping[left];
                    if (target != -1 && --table[1][target] == table[0][target] - 1) {
                        int length = right - left;
                        //instead of checking every window, we know exactly the length we want
                        if ((length / M) ==  words.length) {
                            indexes.add(left);
                        }
                        ++currentFailures;
                    }
                    left += M;
                }
            }

        }
        return indexes;
    }
}
