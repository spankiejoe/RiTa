package rita.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static rita.support.QUnitStubs.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.junit.Test;

import rita.RiTa;
import rita.RiWordNet;
import rita.wordnet.WordnetUtil;

/*
 * Compare results to: http://wordnetweb.princeton.edu/perl/webwn
 * 
 * KENNY:
 * TODO: Make others like combinatoric case below: testGetSynonymsXXX() and testGetAllSynonymsXXX
 * TODO: Add negative cases (where there is no match in db)
 * TODO: Make sure all methods return non-deterministic arrays
 */
public class RiWordNetTest
{
	////////////////////////////////// Example-tests ///////////////////////////////////////
	// Each of these tests all 4 permutations of ignoreUpperCaseWords and ignoreCompundWords
	//   and verifies the original search term is not present in the result.
	/////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testGetSynonymsInt()
	{
		String[] expected = { "scour","grub","antique","comparison-shop","hunt","drag","shop","dowse","browse","seek","scrabble","quest after","search","fish","pursue","angle","shell","want","surf","seek out","window-shop","look for","divine","grope","leave no stone unturned","go after","gather","grope for","quest for","feel","fumble","dredge","finger" };
		//printArr( w.getSynonyms(81318273));
		setEqualMulti(expected, "getSynonyms", 81318273); 

		String[] expected2 = { "paterfamilias", "old man", "clotheshorse", "widower", "father-figure", "greybeard", "fellow", "Esq", "shaver", "stiff", "stud", "he-man", "divorced man", "ironside", "unmarried man", "old boy", "beau", "gallant", "iron man", "bull", "ponce", "boyfriend", "bachelor", "Samson", "signor", "ex-husband", "bey", "young buck", "Methuselah", "Esquire", "sir", "ex-boyfriend", "strapper", "dandy", "galoot", "posseman", "boy", "buster", "Herr", "ex", "signore", "sheik", "sod", "Peter Pan", "philanderer", "wonder boy", "ironman", "bozo", "grass widower", "dude", "eunuch", "Monsieur", "gentleman", "father surrogate", "hunk", "signior", "ejaculator", "swell", "Tarzan", "babu", "bruiser", "geezer", "golden boy", "father figure", "middle-aged man", "womaniser", "womanizer", "fop", "Senhor", "patriarch", "macho-man", "widowman", "swain", "inamorato", "graybeard", "hombre", "cat", "fashion plate", "housefather", "adonis", "guy", "Hooray Henry", "baboo", "young man", "castrate", "white man" };
		//printArr( w.getSynonyms(910172934));
		setEqualMulti(expected2, "getSynonyms", 910172934); 

		String[] expected3 = {};
		printArr( w.getSynonyms(9101729));
		setEqualMulti(expected3, "getSynonyms", 9101729); 

	}

	@Test
	public void testGetSynonymsIntInt()
	{
		String[] expected = { "scour","grub","antique","comparison-shop","hunt","drag","shop","dowse","browse","seek","scrabble","quest after","search","fish","pursue","angle","shell","want","surf","seek out","window-shop","look for","divine","grope","leave no stone unturned","go after","gather","grope for","quest for","feel","fumble","dredge","finger" };
		setContainsMulti(expected, "getSynonyms", 81318273, 4);

		String[] expected2 = { "paterfamilias", "old man", "clotheshorse", "widower", "father-figure", "greybeard", "fellow", "Esq", "shaver", "stiff", "stud", "he-man", "divorced man", "ironside", "unmarried man", "old boy", "beau", "gallant", "iron man", "bull", "ponce", "boyfriend", "bachelor", "Samson", "signor", "ex-husband", "bey", "young buck", "Methuselah", "Esquire", "sir", "ex-boyfriend", "strapper", "dandy", "galoot", "posseman", "boy", "buster", "Herr", "ex", "signore", "sheik", "sod", "Peter Pan", "philanderer", "wonder boy", "ironman", "bozo", "grass widower", "dude", "eunuch", "Monsieur", "gentleman", "father surrogate", "hunk", "signior", "ejaculator", "swell", "Tarzan", "babu", "bruiser", "geezer", "golden boy", "father figure", "middle-aged man", "womaniser", "womanizer", "fop", "Senhor", "patriarch", "macho-man", "widowman", "swain", "inamorato", "graybeard", "hombre", "cat", "fashion plate", "housefather", "adonis", "guy", "Hooray Henry", "baboo", "young man", "castrate", "white man" };
		setContainsMulti(expected2, "getSynonyms", 910172934, 4); 

		String[] expected3 = {};
		printArr( w.getSynonyms(9101729));
		setContainsMulti(expected3, "getSynonyms", 9101729,2); 
	}

	@Test
	public void testGetSynonymsStringString()
	{
		String[] expected = { "shop","grope","seek","want","fumble","scour","grub","gather","seek out","leave no stone unturned","divine","hunt","quest after","feel","angle","go after","fish","browse","quest for","finger","dredge","look for","surf","drag","pursue", };
		//println(w.getSynonyms("search", "v"), true);
		setEqualMulti(expected, "getSynonyms", "search", "v");

		String[] expected2 = { "endeavor","variation","concealing","protection","utilisation","mourning","works","wastefulness","ceremony","seeking","instruction","continuance","buzz","provision","control","misconduct","practice","measuring","acting","variance","market","space walk","deeds","playacting","didactics","wrongdoing","precedency","forage","line","occupation","assist","creative activity","demand","pedagogy","animation","help","disassembly","use","ransacking","burst","market place","supporting","laughter","standardization","organization","leading","playing","procedure","wrongful conduct","activation","puncture","leadership","measurement","recreation","frisking","conduct","music","perturbation","grouping","activating","line of work","calibration","operation","attempt","try","measure","update","job","dish","education","representation","sensory activity","concealment","foraging","locating","training","service","teaching","process","performing","military operation","exploration","turn","cup of tea","standardisation","disturbance","release","outlet","last","assistance","looking for","negotiation","supply","support","emplacement","scouring","marketplace","liveliness","hunt","readying","energizing","work","enjoyment","supplying","doings","solo","employment","mystification","play","followup","mensuration","quest","precedence","placement","location","fun","manhunt","organisation","continuation","dismantlement","fit","obfuscation","pattern","preparation","verbalization","delectation","utilization","timekeeping","politics","dismantling","diversion","position","vent","bag","aid","creation","hiding","positioning","looking","pleasure","behaviour","effort","grooming","exercise","committal to writing","worship","game","precession","shakedown","writing","domesticity","rummage","endeavour","follow-up","role","hunting","lamentation","frisk","actus reus","business","usage","waste","behavior","verbalisation","educational activity","dissipation", };
		println(w.getSynonyms("search", "n"), true);
		setEqualMulti(expected2, "getSynonyms", "search", "n");

		String[] expected3 = {};
		println(w.getSynonyms("search", "r"), true);
		setEqualMulti(expected3, "getSynonyms", "search", "r");

		String[] expected4 = {};
		println(w.getSynonyms("search", "a"), true);
		setEqualMulti(expected4, "getSynonyms", "search", "a");

		String[] expected5 = {"contented","content","bright","riant","elated","blissful","joyful","euphoric","cheerful","laughing","golden","joyous","felicitous","halcyon","glad","prosperous","blessed", };
		println(w.getSynonyms("happy", "a"), true);
		setEqualMulti(expected5, "getSynonyms", "happy", "a");

		String[] expected6 = { };
		println(w.getSynonyms("happyyyyyyyy", "a"), true);
		setEqualMulti(expected6, "getSynonyms", "happyyyyyyyy", "a");

		try{
			println(w.getSynonyms("search", "j"), true);
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetSynonymsStringStringInt()
	{

		String[] expected = { "shop","grope","seek","want","fumble","scour","grub","gather","seek out","leave no stone unturned","divine","hunt","quest after","feel","angle","go after","fish","browse","quest for","finger","dredge","look for","surf","drag","pursue", };
		//println(w.getSynonyms("search", "v"), true);
		setContainsMulti(expected, "getSynonyms", "search", "v",5);

		String[] expected2 = { "endeavor","variation","concealing","protection","utilisation","mourning","works","wastefulness","ceremony","seeking","instruction","continuance","buzz","provision","control","misconduct","practice","measuring","acting","variance","market","space walk","deeds","playacting","didactics","wrongdoing","precedency","forage","line","occupation","assist","creative activity","demand","pedagogy","animation","help","disassembly","use","ransacking","burst","market place","supporting","laughter","standardization","organization","leading","playing","procedure","wrongful conduct","activation","puncture","leadership","measurement","recreation","frisking","conduct","music","perturbation","grouping","activating","line of work","calibration","operation","attempt","try","measure","update","job","dish","education","representation","sensory activity","concealment","foraging","locating","training","service","teaching","process","performing","military operation","exploration","turn","cup of tea","standardisation","disturbance","release","outlet","last","assistance","looking for","negotiation","supply","support","emplacement","scouring","marketplace","liveliness","hunt","readying","energizing","work","enjoyment","supplying","doings","solo","employment","mystification","play","followup","mensuration","quest","precedence","placement","location","fun","manhunt","organisation","continuation","dismantlement","fit","obfuscation","pattern","preparation","verbalization","delectation","utilization","timekeeping","politics","dismantling","diversion","position","vent","bag","aid","creation","hiding","positioning","looking","pleasure","behaviour","effort","grooming","exercise","committal to writing","worship","game","precession","shakedown","writing","domesticity","rummage","endeavour","follow-up","role","hunting","lamentation","frisk","actus reus","business","usage","waste","behavior","verbalisation","educational activity","dissipation", };
		setContainsMulti(expected2, "getSynonyms", "search", "n",20);

		String[] expected3 = {};
		setContainsMulti(expected3, "getSynonyms", "search", "r",1);

		String[] expected4 = {};
		setContainsMulti(expected4, "getSynonyms", "search", "a",5);

		String[] expected5 = {"contented","content","bright","riant","elated","blissful","joyful","euphoric","cheerful","laughing","golden","joyous","felicitous","halcyon","glad","prosperous","blessed", };
		setContainsMulti(expected5, "getSynonyms", "happy", "a",3);

		String[] expected6 = { };
		setContainsMulti(expected6, "getSynonyms", "happyyyyyyyy", "a",2);

		try{
			println(w.getSynonyms("search", "j",4), true);
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetAllSynonymsStringString()
	{
		String[] expected = { "check","pursue","experiment","re-explore","grub","research","peruse","prospect","mapquest","look for","comb","skim","nose","explore","glance over","look","cruise","poke","hunt","scan","candle","drag","seek","angle","browse","take stock", "x-ray","autopsy","fumble","want","cast around","quest after","rake","size up","examine","strip-search","divine","frisk","inspect","gather","horn in","beat about","run down","rifle","cast about","fish","google","dredge","raid","intrude","go","grope","rummage","scour","ransack","probe","scrutinise","survey","pry","scrutinize","shop","seek out","auscultate","finger","surf","go after","quest for","feel","leave no stone unturned", };
		//println(w.getAllSynonyms("search", "v"), true);
		setEqualMulti(expected, "getAllSynonyms", "search", "v");

		String[] expected2 = { "pleasure","liveliness","fit","space walk","readying","checkup","post-mortem examination","pursuit","creation","practice","thought","off-line operation","rummage","frisking","procedure","locating","vent","precedency","deciding","worship","count","lookup","sort","bank examination","mensuration","analysis","activating","binary operation","study","tabulation","use","misconduct","follow-up","supporting","recreation","last","actus reus","unary operation","politics","comparison","educational activity","utilization","forage","assistance","supplying","reexamination","support","tally","hiding","pedagogy","grouping","writing","sorting","business","shakedown","foraging","dyadic operation","verbalisation","necropsy","intellection","puncture","audit","fun","attempt","scrutiny","activation","wiretap","medical","disassembly","computer operation","empiricism","scouring","time and motion study","printing operation","variance","concurrent operation","timekeeping","dissipation","creative activity","solo","manhunt","turn","endeavour","variation","once-over","enjoyment","memory access","thought process","boolean operation","conduct","performing","police investigation","survey","waste","perturbation","looking","role","testing","burst","aid","disturbance","committal to writing","rhinoscopy","buzz","control function","delectation","positioning","going-over","knowing","research","effort","postmortem examination","market","instruction","deeds","exercise","asynchronous operation","synchronous operation","post-mortem","hunt","logic operation","assist","grooming","dish","examination","didactics","animation","utilisation","linguistic process","control operation","representation","police work","simultaneous operation","music","hunting","time study","ophthalmoscopy","parallel operation","standardisation","organisation","placement","process","behaviour","usage","tactual exploration","numeration","behavior","training","standardization","obfuscation","employment","looking for","ransacking","precedence","supply","teaching","works","cup of tea","bag","doings","mourning","time-and-motion study","quest","inquiry","palpation","enumeration","leading","medical examination","counting","multiplex operation","reckoning","job","line of work","organization","lamentation","machine operation","fine-toothed comb","threshold operation","medical checkup","enquiry","look-over","play","operation","wastefulness","PM","decision making","binary arithmetic operation","endoscopy","diversion","seeking","access","ceremony","line","mentation","playing","autopsy","exploration","game","precession","continuation","help","keratoscopy","preparation","concealment","laughter","serial operation","emplacement","market place","sensory activity","measuring","tap","scan","consecutive operation","release","pattern","domesticity","sequential operation","comparing","logical operation","dismantlement","language","cerebration","demand","occupation","verbalization","continuance","wrongful conduct","leadership","auxiliary operation","measurement","location","wrongdoing","inspection","endeavor","suggestion","work","motion study","review","calibration","outlet","military operation","mystification","medical exam","monadic operation","frisk","control","health check","update","provision","thinking","concealing","education","negotiation","protection","position","try","fixed-cycle operation","gonioscopy","acting","followup","measure","dismantling","playacting","fine-tooth comb","pursuance","energizing","time-motion study","work study","marketplace","postmortem","service", };
		println(w.getAllSynonyms("search", "n"), true);
		setEqualMulti(expected2, "getAllSynonyms", "search", "n");

		String[] expected3 = {};
		println(w.getAllSynonyms("search", "r"), true);
		setEqualMulti(expected3, "getAllSynonyms", "search", "r");

		String[] expected4 = {};
		println(w.getAllSynonyms("search", "a"), true);
		setEqualMulti(expected4, "getAllSynonyms", "search", "a");

		String[] expected5 = {"contented","euphoric","joyous","elated","cheerful","laughing","felicitous","content","fortunate","willing","golden","glad","halcyon","bright","blissful","joyful","blessed","riant","prosperous","well-chosen", };
		println(w.getAllSynonyms("happy", "a"), true);
		setEqualMulti(expected5, "getAllSynonyms", "happy", "a");

		String[] expected6 = { };
		println(w.getAllSynonyms("happyyyyyyyy", "a"), true);
		setEqualMulti(expected6, "getAllSynonyms", "happyyyyyyyy", "a");

		try{
			println(w.getAllSynonyms("search", "j"), true);
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}


	}

	@Test
	public void testGetAllSynonymsStringStringInt()
	{
		String[] expected = { "check","pursue","experiment","re-explore","grub","research","peruse","prospect","mapquest","look for","comb","skim","nose","explore","glance over","look","cruise","poke","hunt","scan","candle","drag","seek","angle","browse","take stock", "x-ray","autopsy","fumble","want","cast around","quest after","rake","size up","examine","strip-search","divine","frisk","inspect","gather","horn in","beat about","run down","rifle","cast about","fish","google","dredge","raid","intrude","go","grope","rummage","scour","ransack","probe","scrutinise","survey","pry","scrutinize","shop","seek out","auscultate","finger","surf","go after","quest for","feel","leave no stone unturned", };
		setContainsMulti(expected, "getAllSynonyms", "search", "v", 10);

		String[] expected2 = { "pleasure","liveliness","fit","space walk","readying","checkup","post-mortem examination","pursuit","creation","practice","thought","off-line operation","rummage","frisking","procedure","locating","vent","precedency","deciding","worship","count","lookup","sort","bank examination","mensuration","analysis","activating","binary operation","study","tabulation","use","misconduct","follow-up","supporting","recreation","last","actus reus","unary operation","politics","comparison","educational activity","utilization","forage","assistance","supplying","reexamination","support","tally","hiding","pedagogy","grouping","writing","sorting","business","shakedown","foraging","dyadic operation","verbalisation","necropsy","intellection","puncture","audit","fun","attempt","scrutiny","activation","wiretap","medical","disassembly","computer operation","empiricism","scouring","time and motion study","printing operation","variance","concurrent operation","timekeeping","dissipation","creative activity","solo","manhunt","turn","endeavour","variation","once-over","enjoyment","memory access","thought process","boolean operation","conduct","performing","police investigation","survey","waste","perturbation","looking","role","testing","burst","aid","disturbance","committal to writing","rhinoscopy","buzz","control function","delectation","positioning","going-over","knowing","research","effort","postmortem examination","market","instruction","deeds","exercise","asynchronous operation","synchronous operation","post-mortem","hunt","logic operation","assist","grooming","dish","examination","didactics","animation","utilisation","linguistic process","control operation","representation","police work","simultaneous operation","music","hunting","time study","ophthalmoscopy","parallel operation","standardisation","organisation","placement","process","behaviour","usage","tactual exploration","numeration","behavior","training","standardization","obfuscation","employment","looking for","ransacking","precedence","supply","teaching","works","cup of tea","bag","doings","mourning","time-and-motion study","quest","inquiry","palpation","enumeration","leading","medical examination","counting","multiplex operation","reckoning","job","line of work","organization","lamentation","machine operation","fine-toothed comb","threshold operation","medical checkup","enquiry","look-over","play","operation","wastefulness","PM","decision making","binary arithmetic operation","endoscopy","diversion","seeking","access","ceremony","line","mentation","playing","autopsy","exploration","game","precession","continuation","help","keratoscopy","preparation","concealment","laughter","serial operation","emplacement","market place","sensory activity","measuring","tap","scan","consecutive operation","release","pattern","domesticity","sequential operation","comparing","logical operation","dismantlement","language","cerebration","demand","occupation","verbalization","continuance","wrongful conduct","leadership","auxiliary operation","measurement","location","wrongdoing","inspection","endeavor","suggestion","work","motion study","review","calibration","outlet","military operation","mystification","medical exam","monadic operation","frisk","control","health check","update","provision","thinking","concealing","education","negotiation","protection","position","try","fixed-cycle operation","gonioscopy","acting","followup","measure","dismantling","playacting","fine-tooth comb","pursuance","energizing","time-motion study","work study","marketplace","postmortem","service", };
		println(w.getAllSynonyms("search", "n"), true);
		setContainsMulti(expected2, "getAllSynonyms", "search", "n",5);

		String[] expected3 = {};
		println(w.getAllSynonyms("search", "r"), true);
		setContainsMulti(expected3, "getAllSynonyms", "search", "r",5);

		String[] expected4 = {};
		println(w.getAllSynonyms("search", "a"), true);
		setContainsMulti(expected4, "getAllSynonyms", "search", "a",9);

		String[] expected5 = {"contented","euphoric","joyous","elated","cheerful","laughing","felicitous","content","fortunate","willing","golden","glad","halcyon","bright","blissful","joyful","blessed","riant","prosperous","well-chosen", };
		println(w.getAllSynonyms("happy", "a"), true);
		setContainsMulti(expected5, "getAllSynonyms", "happy", "a",1);

		String[] expected6 = { };
		println(w.getAllSynonyms("happyyyyyyyy", "a"), true);
		setContainsMulti(expected6, "getAllSynonyms", "happyyyyyyyy", "a",4);

		try{
			println(w.getAllSynonyms("search", "j",6), true);
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			println(w.getAllSynonyms("search", "v",-1), true);
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testRiWordNetString()
	{
		RiWordNet.useMorphologicalProcessor = false;

		w.ignoreCompoundWords(false);
		w.ignoreUpperCaseWords(false);
		ok(!w.exists("healthXXX"));
		ok(w.exists("health"));

		ok(w.exists("medicare"));
		ok(w.exists("Medicare")); //TODO these pass on online wordnet search
		ok(w.exists("MedIcare"));
		ok(w.exists("medicare "));
		ok(w.exists(" medicare"));
		ok(w.exists(" medicare "));
		ok(w.exists("medicare	"));
		ok(w.exists("	medicare"));
		ok(w.exists("health insurance"));
		ok(!w.exists("health ignorance"));
		ok(!w.exists("health XXX"));

		ok(w); // see above
		ok(w.exists("hello"));
		ok(!w.exists("caz"));

		ok(!w.exists(""));
		ok(!w.exists("||"));
		ok(!w.exists("!@#$%^&*("));

		w.ignoreCompoundWords(true);
		w.ignoreUpperCaseWords(true);

		ok(!w.exists("healthXXX"));
		ok(w.exists("health"));

		ok(w.exists("medicare"));
		ok(w.exists("health insurance"));
		ok(!w.exists("health ignorance"));
		ok(!w.exists("health XXX"));

		ok(w); // see above
		ok(w.exists("hello"));
		ok(!w.exists("caz"));

		ok(!w.exists(""));
		ok(!w.exists("||"));
		ok(!w.exists("!@#$%^&*("));

	}

	@Test
	public void testGetSenseIdsStringString() 
	{

		//TODO cannot use setEqualMulti as it is int[]
		w.ignoreCompoundWords(false);
		w.ignoreUpperCaseWords(false);
		int[] expected = { 92124272, 910172934, 99919605, 93614083, 92989061, 92986962, 92130460, 9903174 };
		int[] result = w.getSenseIds("cat", "n");
		deepEqual(expected, result);

		int[] expected1 = { 913367788 };
		int[] result1 = w.getSenseIds("health insurance", "n");
		//		println(result1);
		deepEqual(expected1, result1);

		int[] expected13 = { 81414524, 876153};
		int[] result13 = w.getSenseIds("cat", "v");
		//		println(result13);
		deepEqual(expected13, result13);

		int[] expected3 = {  };
		int[] result3 = w.getSenseIds("health insurance", "v");
		//		println(result3);
		deepEqual(expected3, result3);

		int[] expected4 = {  };
		int[] result4 = w.getSenseIds("health insurance", "a");
		//		println(result4);
		deepEqual(expected3, result4);

		int[] expected5 = {  };
		int[] result5 = w.getSenseIds("health insurance", "r");
		//		println(result5);
		deepEqual(expected5, result5);

		int[] expected2 = { };
		int[] result2 = w.getSenseIds("caz", "n");
		deepEqual(expected2, result2);

		try{
			w.getSenseIds("cat", "u");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		w.ignoreCompoundWords(true);
		w.ignoreUpperCaseWords(true);

		int[] expected6 = { 92124272, 910172934, 99919605, 93614083, 92989061, 92986962, 92130460, 9903174 };
		int[] result6 = w.getSenseIds("cat", "n");
		deepEqual(expected6, result6);

		int[] expected7 = { 913367788 };
		int[] result7 = w.getSenseIds("health insurance", "n");
		deepEqual(expected7, result7);

		int[] expected12 = { 81414524, 876153};
		int[] result12 = w.getSenseIds("cat", "v");
		//		println(result12);
		deepEqual(expected12, result12);

		int[] expected9 = {  };
		int[] result9 = w.getSenseIds("health insurance", "v");
		//		println(result9);
		deepEqual(expected9, result9);

		int[] expected10 = {  };
		int[] result10 = w.getSenseIds("health insurance", "a");
		//		println(result10);
		deepEqual(expected10, result10);

		int[] expected11 = {  };
		int[] result11 = w.getSenseIds("health insurance", "r");
		//		println(result11);
		deepEqual(expected11, result11);

		int[] expected8 = { };
		int[] result8 = w.getSenseIds("caz", "n");
		deepEqual(expected8, result8);

		try{
			w.getSenseIds("cat", "u");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetHypernymsStringString()
	{

		String[] expected = { "root" };
		setEqualMulti(expected,"getHypernyms","carrot","n");

		String[] expected2 = { "domestic animal", "domesticated animal", "canid", "canine" };
		setEqualMulti(expected2,"getHypernyms","dog","n");

		String[] expected3 = { "bush" ,"shrub"};
		setEqualMulti(expected3,"getHypernyms","rose","n");

		String[] expected4 = { "plant organ"};
		setEqualMulti(expected4,"getHypernyms","root","n");

		String[] expected7 = {"grow" };
		setEqualMulti(expected7,"getHypernyms","root","v");

		String[] expected8 = {};
		setEqualMulti(expected8,"getHypernyms","root","r");

		String[] expected9 = {};
		setEqualMulti(expected9,"getHypernyms","root","a");

		String[] expected5 = { };
		setEqualMulti(expected5,"getHypernyms","rootttt","n");

		try{
			String[] result6 = w.getHypernyms("root", "j");
			//println(result6);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetContainsStringStringInt()
	{
		w.ignoreCompoundWords(true);
		String[] expected = { "activeness", "activewear", "attractiveness" };
		setContainsMulti(expected,"getContains","active","n",3);

		String[] expected2 = { "avalokiteshvara", "avalokitesvara" };
		//printArr(result2);
		setContainsMulti(expected2,"getContains","kite","n",2);

		w.ignoreCompoundWords(false);
		String[] expected3 = { "active agent", "active air defense", "active application" };
		setContainsMulti(expected3,"getContains","active","n",3);

		String[] expected4 = { "avalokiteshvara", "avalokitesvara" };
		setContainsMulti(expected4,"getContains","kite","n",2);

		String[] expected5 = { };
		setContainsMulti(expected5,"getContains","kittxx","n",2);

		String[] expected6 = { "hell-kite","avalokitesvara", "black kite", "blatherskite", "avalokiteshvara", "white-tailed kite", "sport kite", "stunt kite", "kite balloon", "kite tail", "box kite", "hell-kite", "samarskite", "kitembilla", "melkite", "greenockite", "swallow-tailed kite" };
		setContainsMulti(expected6,"getContains","kite","n",2000);

		try{
			//wrong result
			String[] result6 = w.getContains("kite", "j", 2);
			//println(result6);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			//wrong result
			String[] result8 = w.getContains("kitxx", "j", 2000);
			//println(result8);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetContainsStringString()
	{
		String[] expected = { "reactive depression", "highly active antiretroviral therapy", "active immunity", "primary subtractive colour for light", "attractive feature", "radioactive iodine excretion test", "c-reactive protein", "radioactive dust", "radioactive decay", "radioactive dating", "active agent", "reactive schizophrenia", "active transport", "radioactive iodine uptake test", "active birth", "surface-active agent", "unattractiveness", "inactiveness", "psychoactive substance", "interactive multimedia system", "psychoactive drug", "attractive force", "activewear", "interactive multimedia", "low-level radioactive waste", "active trust", "active citizen", "radioactive material", "active site", "radioactive waste", "refractive index", "activeness", "attractiveness", "active placebo", "refractiveness", "primary subtractive color for light", "radioactive iodine test", "active voice", "high-level radioactive waste", "active air defense", "active matrix screen", "active application", "attractive nuisance", "benefactive role" };
		setEqualMulti(expected,"getContains","active","n");

		String[] expected2 = { "hell-kite", "melkite", "kitembilla", "blatherskite", "box kite", "stunt kite", "sport kite", "kite balloon", "greenockite", "kite tail", "swallow-tailed kite", "black kite", "white-tailed kite", "avalokiteshvara", "avalokitesvara", "samarskite" };
		//printArr(result2);
		setEqualMulti(expected2,"getContains","kite","n");

		String[] expected4 = {};
		setEqualMulti(expected4,"getContains","kitxx","n");

		String[] expected5 = { "expostulate", "postpose", "apostatize", "compost", "postdate", "apostrophise", "apostatise", "apostrophize", "signpost", "postmark", "postulate", "hypostatize", "riposte", "postpone", "change posture", "hypostatise", "posture" };
		setEqualMulti(expected5,"getContains","post","v");
		//printArr(w.getContains("post","v"));

		String[] expected7 = {};
		setEqualMulti(expected7,"getContains","brutally","r");
		//		printArr(w.getContains("brutally","r"));


		String[] expected6 = { "indefinite" };;
		setEqualMulti(expected6,"getContains","definite","a");
		//		printArr(w.getContains("definite","a"));

		setEqualMulti(expected6,"getContains","Definite","a");

		setEqualMulti(expected6,"getContains","DefIniTe","a");

		setEqualMulti(expected6,"getContains","definite ","a");

		setEqualMulti(expected6,"getContains"," definite","a");

		setEqualMulti(expected6,"getContains"," definite ","a");


		try{
			String[] result3 = w.getContains("kite", "j");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			String[] result5 = w.getContains("kitxx", "j");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetEndsWithStringStringInt()  // TODO setContainsMulti not working	
	{
		String[] expected = { "actuarial table", "graduated table", "inevitable", "pool table", "writing table", "conference table", "livery stable", "timetable", "billiard table", "gaming table", "turntable", "drawing table", "round table", "communion table", "refectory table", "tilt-top table", "cruciferous vegetable", "statistical table", "portable", "leafy vegetable", "training table", "console table", "snooker table", "king arthur's round table", "dining-room table", "pingpong table", "gateleg table", "pin table", "drafting table", "john constable", "dining table", "water table", "operating table", "worktable", "high table", "card table", "tip table", "plane table", "stable", "tip-top table", "collectable", "raw vegetable", "lord's table", "pedestal table", "coffee table", "parsons table", "knight of the round table", "pier table", "dressing table", "dinner table", "toilet table", "periodic table", "mortality table", "notable", "solanaceous vegetable", "roundtable", "drop-leaf table", "chief constable", "table-tennis table", "ping-pong table", "correlation table", "root vegetable", "work table", "decision table", "tea table", "breakfast table", "police constable", "cocktail table", "trestle table", "potable", "file allocation table", "constable", "julienne vegetable", "eatable", "kitchen table", "council table", "vegetable" };
		//printArr(w.getEndsWith("table", "n"));
		setContainsMulti(expected,"getEndsWith","table","n",5);
		setContainsMulti(expected,"getEndsWith","table","n",1500);

		String[] expected2 = { "naris", "genus liparis", "vena maxillaris", "sutura intermaxillaris", "dracunculus vulgaris", "rubus flagellaris", "calluna vulgaris", "nervus ulnaris", "syringa vulgaris", "chorizagrotis auxiliaris", "aspalathus linearis", "genus baccharis", "arteria maxillaris", "genus phalaris", "lupus vulgaris", "hydrangea petiolaris", "certhia familiaris", "arteria ciliaris", "vena ulnaris", "carlina vulgaris", "iliamna ruvularis", "carassius vulgaris", "arteria angularis", "phaseolus angularis", "cenchrus ciliaris", "vena supratrochlearis", "homarus vulgaris", "vena testicularis", "genus agriocharis", "herb paris", "pyorrhea alveolaris", "genus capparis", "thymus vulgaris", "ascaris", "canis familiaris", "arteria ulnaris", "capparis", "sciurus vulgaris", "lysimachia vulgaris", "truncus atrioventricularis", "botaurus stellaris", "ratibida columnaris", "turdus pilaris", "keratosis follicularis", "primula vulgaris", "trochlearis", "prunella modularis", "petasites vulgaris", "genus paris", "alstonia scholaris", "nervus vestibulocochlearis", "university of paris", "lens culinaris", "posterior naris", "eleocharis", "genus eleocharis", "polistes annularis", "enterobius vermicularis", "silene vulgaris", "pylodictus olivaris", "keratosis pilaris", "vena auricularis", "vena angularis", "alectis ciliaris", "sistrurus miliaris", "genus hydrocharis", "passiflora ligularis", "idria columnaris", "vigna angularis", "berberis vulgaris", "arteria axillaris", "prunella vulgaris", "vena retromandibularis", "vena jugularis", "helianthus petiolaris", "ixodes scapularis", "liparis", "chamaecyparis", "petunia axillaris", "linosyris vulgaris", "barbarea vulgaris", "artemisia vulgaris", "articulatio temporomandibularis", "linaria vulgaris", "vena axillaris", "lepas fascicularis", "haastia pulvinaris", "acne vulgaris", "arteria basilaris", "genus ascaris", "polygala vulgaris", "phalaris", "arteria appendicularis", "merluccius bilinearis", "bambusa vulgaris", "passiflora quadrangularis", "baccharis pilularis", "senecio vulgaris", "agriocharis", "eleocharis acicularis", "vena appendicularis", "alnus vulgaris", "vena vestibularis", "saxifraga stellaris", "araucaria columnaris", "clematis verticillaris", "paris", "arteria auricularis", "lygus lineolaris", "chilopsis linearis", "triturus vulgaris", "arteria testicularis", "sabbatia stellaris", "cephalotus follicularis", "noctiluca miliaris", "colaptes caper collaris", "genus chamaecyparis", "nierembergia rivularis", "beta vulgaris vulgaris", "liparis liparis", "plaster of paris", "arctonyx collaris", "baccharis", "pulsatilla vulgaris", "pomoxis annularis", "ochotona collaris", "cyathea medullaris", "phaseolus vulgaris", "fouquieria columnaris", "senecio triangularis", "sabbatia angularis", "solea lascaris", "jacquinia armillaris", "vespula vulgaris", "beta vulgaris", "anterior naris", "polaris", "citrullus vulgaris", "aquilegia vulgaris", "sturnus vulgaris", "hydrocharis", "satureja vulgaris", "arteria alveolaris" };
		//printArr(w.getEndsWith("aris", "n"));
		setContainsMulti(expected2,"getEndsWith","aris","n",4);
		setContainsMulti(expected,"getEndsWith","aris","n",4); //TODO this should be failed

		String[] expected3 = { "bring", "get moving", "get rolling", "keep going", "sight-sing", "hamstring", "sing", "hit the ceiling", "string", "sling", "fling", "get going", "cling", "get weaving", "unstring", "sting", "ping", "swing", "ding", "get cracking", "come into being", "ting", "have it coming", "sightsing", "send packing", "practice bundling", "ring", "spring", "wring", "wing" };
		//printArr(w.getEndsWith("ing", "v"));
		setContainsMulti(expected3,"getEndsWith","ing","v",5);

		String[] expected4 = { "interspecies", "behind-the-scenes", "intra vires", "ultra vires", "offsides", "dressed to the nines", "isosceles", "in small stages", "in straitened circumstances", "in series", "intraspecies", "too big for one's breeches" };
		//printArr(w.getEndsWith("es", "a"));
		setContainsMulti(expected4,"getEndsWith","es","a",5);

		String[] expected5 = { "under the circumstances", "when the time comes", "four times", "thousand times", "at times", "three times", "betimes", "two times", "in large quantities", "unawares", "besides", "six times", "for all practical purposes", "a million times", "as the crow flies", "ofttimes", "ultra vires", "a hundred times", "in spades", "for all intents and purposes", "oftentimes", "by small degrees", "in stages", "to all intents and purposes", "sometimes", "nine times", "by inches", "in circles" };
		printArr(w.getEndsWith("es", "r"));
		setContainsMulti(expected5,"getEndsWith","es","r",5);

		String[] expected6 = {};
		setContainsMulti(expected6,"getEndsWith","qwes","r",10);

		try{
			String[] result8 = w.getEndsWith("razilll", "j", 40);
			println(result8);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testGetEndsWithStringString()
	{
		String[] expected = { "actuarial table", "graduated table", "inevitable", "pool table", "writing table", "conference table", "livery stable", "timetable", "billiard table", "gaming table", "turntable", "drawing table", "round table", "communion table", "refectory table", "tilt-top table", "cruciferous vegetable", "statistical table", "portable", "leafy vegetable", "training table", "console table", "snooker table", "king arthur's round table", "dining-room table", "pingpong table", "gateleg table", "pin table", "drafting table", "john constable", "dining table", "water table", "operating table", "worktable", "high table", "card table", "tip table", "plane table", "stable", "tip-top table", "collectable", "raw vegetable", "lord's table", "pedestal table", "coffee table", "parsons table", "knight of the round table", "pier table", "dressing table", "dinner table", "toilet table", "periodic table", "mortality table", "notable", "solanaceous vegetable", "roundtable", "drop-leaf table", "chief constable", "table-tennis table", "ping-pong table", "correlation table", "root vegetable", "work table", "decision table", "tea table", "breakfast table", "police constable", "cocktail table", "trestle table", "potable", "file allocation table", "constable", "julienne vegetable", "eatable", "kitchen table", "council table", "vegetable" };
		//printArr(w.getEndsWith("table", "n"));
		setEqualMulti(expected,"getEndsWith","table","n");

		String[] expected2 = { "naris", "genus liparis", "vena maxillaris", "sutura intermaxillaris", "dracunculus vulgaris", "rubus flagellaris", "calluna vulgaris", "nervus ulnaris", "syringa vulgaris", "chorizagrotis auxiliaris", "aspalathus linearis", "genus baccharis", "arteria maxillaris", "genus phalaris", "lupus vulgaris", "hydrangea petiolaris", "certhia familiaris", "arteria ciliaris", "vena ulnaris", "carlina vulgaris", "iliamna ruvularis", "carassius vulgaris", "arteria angularis", "phaseolus angularis", "cenchrus ciliaris", "vena supratrochlearis", "homarus vulgaris", "vena testicularis", "genus agriocharis", "herb paris", "pyorrhea alveolaris", "genus capparis", "thymus vulgaris", "ascaris", "canis familiaris", "arteria ulnaris", "capparis", "sciurus vulgaris", "lysimachia vulgaris", "truncus atrioventricularis", "botaurus stellaris", "ratibida columnaris", "turdus pilaris", "keratosis follicularis", "primula vulgaris", "trochlearis", "prunella modularis", "petasites vulgaris", "genus paris", "alstonia scholaris", "nervus vestibulocochlearis", "university of paris", "lens culinaris", "posterior naris", "eleocharis", "genus eleocharis", "polistes annularis", "enterobius vermicularis", "silene vulgaris", "pylodictus olivaris", "keratosis pilaris", "vena auricularis", "vena angularis", "alectis ciliaris", "sistrurus miliaris", "genus hydrocharis", "passiflora ligularis", "idria columnaris", "vigna angularis", "berberis vulgaris", "arteria axillaris", "prunella vulgaris", "vena retromandibularis", "vena jugularis", "helianthus petiolaris", "ixodes scapularis", "liparis", "chamaecyparis", "petunia axillaris", "linosyris vulgaris", "barbarea vulgaris", "artemisia vulgaris", "articulatio temporomandibularis", "linaria vulgaris", "vena axillaris", "lepas fascicularis", "haastia pulvinaris", "acne vulgaris", "arteria basilaris", "genus ascaris", "polygala vulgaris", "phalaris", "arteria appendicularis", "merluccius bilinearis", "bambusa vulgaris", "passiflora quadrangularis", "baccharis pilularis", "senecio vulgaris", "agriocharis", "eleocharis acicularis", "vena appendicularis", "alnus vulgaris", "vena vestibularis", "saxifraga stellaris", "araucaria columnaris", "clematis verticillaris", "paris", "arteria auricularis", "lygus lineolaris", "chilopsis linearis", "triturus vulgaris", "arteria testicularis", "sabbatia stellaris", "cephalotus follicularis", "noctiluca miliaris", "colaptes caper collaris", "genus chamaecyparis", "nierembergia rivularis", "beta vulgaris vulgaris", "liparis liparis", "plaster of paris", "arctonyx collaris", "baccharis", "pulsatilla vulgaris", "pomoxis annularis", "ochotona collaris", "cyathea medullaris", "phaseolus vulgaris", "fouquieria columnaris", "senecio triangularis", "sabbatia angularis", "solea lascaris", "jacquinia armillaris", "vespula vulgaris", "beta vulgaris", "anterior naris", "polaris", "citrullus vulgaris", "aquilegia vulgaris", "sturnus vulgaris", "hydrocharis", "satureja vulgaris", "arteria alveolaris" };
		//printArr(w.getEndsWith("aris", "n"));
		setEqualMulti(expected2,"getEndsWith","aris","n");

		String[] expected3 = { "bring", "get moving", "get rolling", "keep going", "sight-sing", "hamstring", "sing", "hit the ceiling", "string", "sling", "fling", "get going", "cling", "get weaving", "unstring", "sting", "ping", "swing", "ding", "get cracking", "come into being", "ting", "have it coming", "sightsing", "send packing", "practice bundling", "ring", "spring", "wring", "wing" };
		//printArr(w.getEndsWith("ing", "v"));
		setEqualMulti(expected3,"getEndsWith","ing","v");

		String[] expected4 = { "interspecies", "behind-the-scenes", "intra vires", "ultra vires", "offsides", "dressed to the nines", "isosceles", "in small stages", "in straitened circumstances", "in series", "intraspecies", "too big for one's breeches" };
		//printArr(w.getEndsWith("es", "a"));
		setEqualMulti(expected4,"getEndsWith","es","a");

		String[] expected5 = { "under the circumstances", "when the time comes", "four times", "thousand times", "at times", "three times", "betimes", "two times", "in large quantities", "unawares", "besides", "six times", "for all practical purposes", "a million times", "as the crow flies", "ofttimes", "ultra vires", "a hundred times", "in spades", "for all intents and purposes", "oftentimes", "by small degrees", "in stages", "to all intents and purposes", "sometimes", "nine times", "by inches", "in circles" };
		//printArr(w.getEndsWith("es", "r"));
		setEqualMulti(expected5,"getEndsWith","es","r");

		String[] expected6 = {};
		setEqualMulti(expected6,"getEndsWith","qwes","r");

		try{
			String[] result8 = w.getEndsWith("razilll", "j");
			//println(result8);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testGetStartsWithStringStringInt()  //TODO more tests 	w.ignoreCompoundWords() cases
	{
		String[] expected = { "turnabout", "turnaround", "turnbuckle", "turncoat", "turncock"};
		String[] result = w.getStartsWith("turn", "n", 5);
		//println(result);
		setEqual(expected, result);

		String[] expected2 = { "turner", "turnip", "turnbuckle", "turncock", "turnspit", "turnover", "turnicidae", "turnaround", "turnstone", "turning", "turncoat", "turnix", "turnstile", "turntable", "turnround", "turnkey", "turnout", "turndown", "turnpike", "turnup", "turnoff", "turnverein", "turnabout", "turnery" };
		String[] result2 = w.getStartsWith("turn", "n", 50);
		//printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 = { "alcoholise", "alchemize", "alcoholize", "alchemise", "alarm" };
		String[] result3 = w.getStartsWith("al", "v", 5);
		//printArr(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "turned" };
		String[] result4 = w.getStartsWith("turn", "a", 5);
		//printArr(result4);
		setEqual(expected4, result4);

		String[] expected5 = { "alfresco", "alee", "alarmingly", "alas", "alertly" };
		String[] result5 = w.getStartsWith("al", "r", 5);
		//printArr(result5);
		setEqual(expected5, result5);

		String[] expected6 = { };
		String[] result6 = w.getStartsWith("ttturn", "r", 5);
		//printArr(result6);
		setEqual(expected6, result6);

		try{
			String[] result7 = w.getStartsWith("turn", "j", 5);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			String[] result8 = w.getStartsWith("ttturn", "j", 5);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetStartsWithStringString()
	{
		String[] expected = { "wearing", "weary willie", "weariness", "wearing away", "wearer", "wearable", "wearing apparel", "wear and tear" };
		//printArr(w.getStartsWith("wear", "n"));
		setEqualMulti(expected, "getStartsWith","wear","n");

		String[] expected2 = { "young girl", "yolk", "young turk", "yob", "yosemite falls", "yogi", "yottabyte", "young man", "youthfulness", "yogacara", "yo-yo", "yosemite", "yobibyte", "youth", "youth subculture", "young carnivore", "yobo", "yoko ono", "yodeling", "yobibit", "young fish", "yorkshire pudding", "yowl", "yorktown", "yogurt", "youth movement", "yore", "yobbo", "york", "young buck", "youngstown", "yottabit", "youngness", "yorkshire fog", "yogi berra", "youngster", "yodel", "yokel", "youth culture", "young mammal", "yoke", "yom kippur war", "young lady", "youth-on-age", "yoghourt", "younker", "young person", "yorkshire", "yodh", "young woman", "yokohama", "yoruba", "yolk sac", "youth gang", "you-drive", "yom kippur", "young", "yosemite toad", "yoga", "yoghurt", "youth hostel", "yosemite national park", "yodeller", "young bird", "young's modulus", "yokuts", "youth crusade", "yorkshire terrier" };
		//printArr(w.getStartsWith("yo", "n"));
		setEqualMulti(expected2, "getStartsWith","yo","n");

		String[] expected3 = { "yodel", "yoke", "yowl" };
		//printArr(w.getStartsWith("yo", "v"));
		setEqualMulti(expected3, "getStartsWith","yo","v");

		String[] expected4 = { "young-begetting", "yogic", "youthful", "yokelish", "yon", "younger", "yonder", "young-bearing", "youngish", "yokel-like", "young", "yogistic" };
		//printArr(w.getStartsWith("yo", "a"));
		setEqualMulti(expected4, "getStartsWith","yo","a");

		String[] expected5 = { "you bet", "youthfully", "yon", "yonder", "you said it" };
		//printArr(w.getStartsWith("yo", "r"));
		setEqualMulti(expected5, "getStartsWith","yo","r");

		try{
			String[] result6 = w.getStartsWith("yo", "z");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetRegexMatchStringStringInt() //TODO more tests 	w.ignoreCompoundWords() cases
	{
		w.ignoreCompoundWords(true);
		String[] expected = { "collectable", "constable", "eatable", "inevitable"};
		setContainsMulti(expected,"getRegexMatch",".*table","n", 4);

		String[] expected2 = { "ayin", "cain", "chin", "asin" };
		setContainsMulti(expected2,"getRegexMatch",".[^A-Z]in","n",4);

		String[] expected3 = {  };
		setContainsMulti(expected3,"getRegexMatch","in[du]","n",40);

		String[] expected4 = { "coin", "gain", "grin", "chin" };
		setContainsMulti(expected4,"getRegexMatch",".[^A-Z]in","v",4);

		String[] expected5 = { "altogether", "further", "farther", "either" };
		setContainsMulti(expected5,"getRegexMatch",".*ther","r",4);

		String[] expected6 = { "akin", "main", "jain", "fain" };
		setContainsMulti(expected6,"getRegexMatch",".[^A-Z]in","a",4);

		try{
			String[] result7 = w.getRegexMatch("[^A-Z]in", "o",4);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			String[] result8 = w.getRegexMatch("*****ther","n",4);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			String[] result9 = w.getRegexMatch("*****ther","p",4);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testGetRegexMatchStringString()
	{
		String[] expected = { "collectable", "constable", "eatable", "inevitable", "notable", "portable", "potable", "roundtable", "stable", "table", "timetable", "turntable", "vegetable", "worktable" };
		setEqualMulti(expected,"getRegexMatch",".*table","n");

		String[] expected2 = { "fin", "tin", "pin", "lin", "yin", "sin", "win", "din", "min", "kin", "hin", "qin", "bin", "gin" };
		setEqualMulti(expected2,"getRegexMatch",".[^A-Z]in","n");

		String[] expected3 = {  };
		setEqualMulti(expected3,"getRegexMatch","in[du]","n");

		String[] expected4 = { "sin", "din", "win", "gin", "pin", "tin", "fin", "bin" };
		setEqualMulti(expected4,"getRegexMatch","[^A-Z]in","v");

		String[] expected5 = { "hell-for-leather", "either", "hither and thither", "one after the other", "altogether", "rather", "hither", "farther", "all together", "together", "thither", "further", "one after another" };
		setEqualMulti(expected5,"getRegexMatch",".*ther","r");

		String[] expected6 = { "ain", "kin" };
		setEqualMulti(expected6,"getRegexMatch","[^A-Z]in","a");

		try{
			String[] result7 = w.getRegexMatch("[^A-Z]in", "o");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			String[] result8 = w.getRegexMatch("*****ther","n");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		try{
			String[] result9 = w.getRegexMatch("*****ther","p");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testGetSoundsLikeStringStringInt() //TODO more tests 	w.ignoreCompoundWords() cases
	{
		String[] expected = { "tabbouleh", "tableau", "tabooli"};
		String[] result = w.getSoundsLike("table", "n", 3);
		//println(result);
		setEqual(expected, result);

		String[] expected2 ={ "paba", "pap", "papa" };
		String[] result2 = w.getSoundsLike("puppy", "n", 3);
		//printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 ={ "peep", "pave", "peeve" };
		String[] result3 = w.getSoundsLike("puppy", "v", 3);
		//printArr(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "pup", "peep", "pop", "pip", "pipe", "puff", "pave", "peeve" };
		String[] result4 = w.getSoundsLike("puppy", "v", 300);
		//printArr(result4);
		setEqual(expected4, result4);

		String[] expected5 = { "peppy", "pop", "puff" };
		String[] result5 = w.getSoundsLike("puppy", "a", 3);
		//printArr(result5);
		setEqual(expected5, result5);

		String[] expected6 = { "pop" };
		String[] result6 = w.getSoundsLike("puppy", "r", 3);
		//printArr(result6);
		setEqual(expected6, result6);

		String[] expected7 = { };
		String[] result7 = w.getSoundsLike("dacszff", "r", 3);
		//printArr(result7);
		setEqual(expected7, result7);

		try{
			String[] result8 = w.getSoundsLike("table", "o", 3);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
		try{
			String[] result9 = w.getSoundsLike("table", "o", 3);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetSoundsLikeStringString()
	{
		String[] expected = { "tubful", "tipple", "tiepolo", "t-bill", "tableau", "tabbouleh", "tea ball", "tube well", "tepal", "tivoli", "tubule", "tuvalu", "tabooli", "tupelo" };
		//		printArr(w.getSoundsLike("table", "n"));
		setEqualMulti(expected,"getSoundsLike","table","n");

		String[] expected2 ={ "pep", "pop", "pib", "pappa", "phobia", "pup", "pave", "pob", "pawpaw", "peba", "pupa", "papaya", "peavy", "poof", "peeve", "pub", "paba", "payoff", "papaia", "piaf", "puff", "pipe", "pope", "piaffe", "pouf", "poove", "pouffe", "poop", "poppy", "peep", "papua", "pap", "pooh-bah", "papaw", "pip", "papio", "pipa", "pavo", "peavey", "papa", "phoebe" };
		//		printArr(w.getSoundsLike("puppy", "n"));
		setEqualMulti(expected2,"getSoundsLike","puppy","n");

		String[] expected3 ={ "peeve", "peep", "pave", "pooh-pooh", "pay up", "pipe", "pip", "pee-pee", "pay off", "pop", "puff", "pup" };
		//		printArr(w.getSoundsLike("puppy", "v"));
		setEqualMulti(expected3,"getSoundsLike","puppy","v");

		String[] expected5 = { "peppy", "puffy", "pop", "puff" };
		//		printArr(w.getSoundsLike("puppy", "a"));
		setEqualMulti(expected5,"getSoundsLike","puppy","a");

		String[] expected6 = { "pop" };
		//		printArr(w.getSoundsLike("puppy", "r"));
		setEqualMulti(expected6,"getSoundsLike","puppy","r");

		String[] expected7 = { };
		//		printArr(w.getSoundsLike("dacszff", "r"));
		setEqualMulti(expected7,"getSoundsLike","dacszff","r");

		try{
			w.getSoundsLike("table", "o");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetWildcardMatchStringStringInt() //TODO more tests 	w.ignoreCompoundWords() cases
	{
		String[] expected = { "tale", "tile"};
		String[] result = w.getWildcardMatch("t?le", "n", 2); // single-letter
		//println(result);
		setEqual(expected, result);

		String[] expected2 = { "teasdale", "teakettle", }; 
		String[] result2 = w.getWildcardMatch("tea*le", "n", 2); // multiple-letter
		//println(result2);
		setEqual(expected2, result2);


		//TODO
	}

	@Test
	public void testGetWildcardMatchStringString()
	{
		String[] expected = { "tale", "tile", "tole"};
		//printArr(w.getWildcardMatch("t?le", "n")); // single-letter
		setEqualMulti(expected,"getWildcardMatch","t?le", "n");

		String[] expected2 = { "teasle", "tea table", "teakettle", "teasdale" }; 
		//printArr(w.getWildcardMatch("tea*le", "n")); // multiple-letter
		setEqualMulti(expected2,"getWildcardMatch","tea*le", "n");

		String[] expected3 = { "din", "min", "win", "yin", "fin", "kin", "sin", "pin", "gin", "tin", "qin", "bin", "lin", "hin" };
		//printArr(w.getWildcardMatch("?in", "n")); // single-letter
		setEqualMulti(expected3,"getWildcardMatch","?in", "n");

		String[] expected4 = { "safekeeping", "weather stripping", "stripping", "whipping", "scraping", "gulping", "strip cropping", "keeping", "beekeeping", "minesweeping", "landscaping", "table tipping", "shipping", "outcropping", "stovepiping", "griping", "showjumping", "blood typing", "double stopping", "limping", "cross-country jumping", "camping", "shopping", "overlapping", "dumping", "helping", "press clipping", "clopping", "looping", "stadium jumping", "chipping", "carping", "tapping", "tissue typing", "timekeeping", "piping", "coping", "stopping", "mapping", "leaping", "table rapping", "trapping", "double dipping", "trumping", "equipping", "peiping", "jumping", "hand clapping", "sleeping", "mopping", "thumping", "sweeping", "developing", "flapping", "snipping", "primping", "warping", "taping", "roping", "grouping", "weeping", "shaping", "yelping", "clapping", "pole jumping", "burping", "kidnapping", "bookkeeping", "teng hsiaoping", "ski jumping", "typing", "newspaper clipping", "wrapping", "peacekeeping", "creeping", "dripping", "calf roping", "rasping", "gift wrapping", "deng xiaoping", "grasping", "lapping", "chomping", "steer roping", "gossiping", "chromosome mapping", "supping", "single-entry bookkeeping", "double-entry bookkeeping", "horsewhipping", "weatherstripping", "table tapping", "cupping", "clipping", "teng hsiao-ping", "name-dropping", "stumping", "spirit rapping", "striping", "blacktopping", "touch typing", "topping", "housekeeping", "clumping", "popping", "walloping" };  
		//		printArr(w.getWildcardMatch("*ping", "n")); // multiple-letter
		setEqualMulti(expected4,"getWildcardMatch","*ping", "n");

		String[] expected5 = { "din", "fin", "sin", "bin", "pin", "gin", "tin", "win" };
		//		printArr(w.getWildcardMatch("?in", "v")); // single-letter
		setEqualMulti(expected5,"getWildcardMatch","?in", "v");

		String[] expected6 = {  };
		//		printArr(w.getWildcardMatch("*ping", "v")); // multiple-letter
		setEqualMulti(expected6,"getWildcardMatch","*ping", "v");

		String[] expected7 = { "kin", "ain" };
		//		printArr(w.getWildcardMatch("?in", "a")); // single-letter
		setEqualMulti(expected7,"getWildcardMatch","?in", "a");

		String[] expected8 = { "sweeping", "tripping", "thumping", "slipping", "dropping", "drooping", "high-stepping", "rasping", "sloping", "ripping", "whopping", "topping", "walloping", "unsleeping", "outward-developing", "grasping", "napping", "eye-popping", "gaping", "weeping", "enveloping", "groping", "nontelescoping", "developing", "shaping", "sleeping", "gripping", "plumping", "out or keeping", "strapping", "inward-developing", "peacekeeping", "downward-sloping", "whipping", "seeping", "nipping", "stooping" }; 
		//		printArr(w.getWildcardMatch("*ping", "a")); // multiple-letter
		setEqualMulti(expected8,"getWildcardMatch","*ping", "a");

		String[] expected9 = {};
		//		printArr(w.getWildcardMatch("?in", "r")); // single-letter
		setEqualMulti(expected9,"getWildcardMatch","?in", "r");

		String[] expected10 = { "dripping", "whopping", "piping", "sopping" };
		//		printArr(w.getWildcardMatch("*ping", "r")); // multiple-letter
		setEqualMulti(expected10,"getWildcardMatch","*ping", "r");

		try{
			String[] result = w.getWildcardMatch("*ping", "j");
			equal(1,2);
		}catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testFilterIntStringStringInt()//TODO more tests 	w.ignoreCompoundWords() cases
	{
		String[] expected10 = { "tablet" };
		String[] result10 = w.filter(RiWordNet.WILDCARD_MATCH, "table?", "n", 3);
		setEqual(expected10, result10);

		String[] expected11 = { "collectable", "charitableness", "acceptableness" };
		String[] result11 = w.filter(RiWordNet.WILDCARD_MATCH, "*table*", "n", 3);
		//printArr(result11);
		setEqual(expected11, result11);

		String[] expected = { "tabbouleh", "tableau", "tabooli"};
		String[] result = w.filter(RiWordNet.SOUNDS_LIKE, "table", "n", 3);
		//println(result);
		setEqual(expected, result);

		String[] expected2 = { "tablecloth", "tablefork", "tableau"};
		String[] result2 = w.filter(RiWordNet.STARTS_WITH, "table", "n", 3);
		//		printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 = { "bleat" };
		String[] result3 = w.filter(RiWordNet.ANAGRAMS, "table", "n", 3);
		//		printArr(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "collectable", "acceptableness", "charitableness" };
		String[] result4 = w.filter(RiWordNet.CONTAINS, "table", "n", 3);
		//		printArr(result4);
		setEqual(expected4, result4);

		String[] expected5 = {};
		String[] result5 = w.filter(RiWordNet.CONTAINS, "tableauu", "n", 3);
		//		printArr(result5);
		setEqual(expected5, result5);

		String[] expected6 = { "constable", "collectable", "eatable" };
		String[] result6 = w.filter(RiWordNet.ENDS_WITH, "table", "n", 3);
		//		printArr(result6);
		setEqual(expected6, result6);

		String[] expected8 = { "table" }; //TODO
		String[] result8 = w.filter(RiWordNet.REGEX_MATCH, "table", "n", 3);
		//printArr(result8);
		setEqual(expected8, result8);

		String[] expected9 = { ".22", "0", "'hood" }; //TODO is the result normal?
		String[] result9 = w.filter(RiWordNet.SIMILAR_TO, "table", "n", 3);
		//		printArr(result9);
		setEqual(expected9, result9);

		String[] expected12 = { };
		String[] result12 = w.filter(RiWordNet.CONTAINS, "nahsuchword", "n", 3);
		//		printArr(result12);
		setEqual(expected12, result12);

		String[] expected14 = { "unstableness", "tablecloth", "tractableness", "habitableness", "tabletop", "excitableness", "inevitable", "stable", "hospitableness", "disreputableness", "immutableness", "ratables", "tablespoonful", "suitableness", "worktable", "stableman", "notable", "mutableness", "tablet", "vegetable", "profitableness", "palatableness", "unacceptableness", "inhospitableness", "charitableness", "unpalatableness", "tableau", "stableness", "intractableness", "inevitableness", "uncomfortableness", "comfortableness", "collectable", "acceptableness", "roundtable", "tableland", "permutableness", "unprofitableness", "constable", "eatable", "tablespoon", "stableboy", "potable", "timetable", "tableware", "turntable", "tablefork", "tablemate", "portable", "stablemate", "unsuitableness" };
		String[] result14 = w.filter(RiWordNet.CONTAINS, "table", "n", 30000);
		//		printArr(result14);
		setEqual(expected14, result14);


		try{
			w.filter(RiWordNet.CONTAINS, "table", "e", 3);
			ok(false);
		}
		catch(Exception e)
		{
			ok(e);
		}

	}

	@Test
	public void testFilterIntStringString()
	{

		w.ignoreCompoundWords(false);
		w.ignoreUpperCaseWords(false);

		String[] expected10 = { "tablet" };
		String[] result10 = w.filter(RiWordNet.WILDCARD_MATCH, "table?", "n");
		//printArr(result10);
		setEqual(expected10, result10);

		String[] expected11 = { "table lifting", "constable", "eatable", "inevitable accident", "work table", "vegetable garden", "pool table", "julienne vegetable", "comfortableness", "cruciferous vegetable", "abatable nuisance", "table turning", "solanaceous vegetable", "uncomfortableness", "profitableness", "drawing table", "coffee table", "roundtable", "table salt", "lord's table", "acceptableness", "table-tennis table", "stable gear", "ping-pong table", "water table", "pier table", "stable companion", "inflatable cushion", "console table", "gateleg table", "actuarial table", "tablet-armed chair", "king arthur's round table", "vegetable marrow", "leafy vegetable", "root vegetable", "trestle table", "toilet table", "table mustard", "memorial tablet", "knight of the round table", "livery stable", "timetable", "vegetable sheep", "training table", "portable saw", "portable", "tablefork", "relocatable program", "plane table", "disreputableness", "card table", "charitableness", "raw vegetable", "tableau vivant", "parsons table", "tea table", "sleeping tablet", "vegetable wax", "potable", "vegetable oyster", "table linen", "table of contents", "unsuitableness", "snooker table", "drafting table", "notable", "mutableness", "coffee-table book", "inhospitableness", "conference table", "billiard table", "pedestal table", "italian vegetable marrow", "inevitable", "police constable", "vegetable sponge", "table-tennis bat", "table talk", "kitchen table", "augean stables", "adjustable wrench", "habitableness", "graduated table", "vegetable oil", "unacceptableness", "vegetable patch", "table d'hote", "tablet", "suitableness", "tip table", "collectable", "john constable", "pin table", "table knife", "mortality table", "dressing table", "intractableness", "round-table conference", "pingpong table", "chief constable", "communion table", "tablespoonful", "permutableness", "disreputable person", "tableau", "refectory table", "stablemate", "vegetable soup", "table game", "periodic table", "turntable", "immutableness", "file allocation table", "irritable bowel syndrome", "tableware", "ratables", "excitable area", "table service", "unprofitableness", "adjustable spanner", "stableman", "table napkin", "correlation table", "tablecloth", "operating table", "vegetable silk", "dining-room table", "unpalatableness", "stableness", "table wine", "table-mountain pine", "portable computer", "tip-top table", "tablemate", "cocktail table", "breakfast table", "japanese table pine", "dinner table", "drop-leaf table", "portable circular saw", "vegetable hummingbird", "stable", "table tapping", "stable factor", "council table", "tablespoon", "palatableness", "excitableness", "tilt-top table", "gaming table", "high table", "table-tennis racquet", "vegetable tallow", "worktable", "decision table", "table rapping", "hospitableness", "vegetable ivory", "stableboy", "table tipping", "table tennis", "tabletop", "inevitableness", "dining table", "vegetable", "writing table", "table tilting", "charitable trust", "unstableness", "round table", "vegetable matter", "statistical table", "table lamp", "tractableness", "tableland", "table mat", "table saw" };
		String[] result11 = w.filter(RiWordNet.WILDCARD_MATCH, "*table*", "n");
		//printArr(result11);
		setEqual(expected11, result11);

		String[] expected15 = { "drawing table", "stableman", "training table", "table game", "constable", "table lifting", "gaming table", "augean stables", "table tilting", "high table", "ping-pong table", "raw vegetable", "disreputableness", "dining table", "hospitableness", "adjustable spanner", "vegetable tallow", "worktable", "communion table", "charitableness", "table linen", "table of contents", "leafy vegetable", "portable computer", "tip table", "table-tennis bat", "root vegetable", "suitableness", "vegetable soup", "vegetable silk", "periodic table", "table service", "table turning", "stable", "vegetable", "police constable", "table tapping", "immutableness", "drafting table", "irritable bowel syndrome", "council table", "decision table", "table tennis", "uncomfortableness", "tableau", "graduated table", "tablefork", "tip-top table", "lord's table", "adjustable wrench", "inevitable accident", "palatableness", "potable", "vegetable sponge", "tilt-top table", "julienne vegetable", "table lamp", "tabletop", "round table", "breakfast table", "correlation table", "tea table", "stablemate", "tableware", "king arthur's round table", "charitable trust", "roundtable", "relocatable program", "pingpong table", "vegetable wax", "table mustard", "coffee-table book", "vegetable oyster", "vegetable hummingbird", "tableau vivant", "sleeping tablet", "statistical table", "profitableness", "collectable", "round-table conference", "mortality table", "coffee table", "permutableness", "pin table", "operating table", "stable companion", "portable", "eatable", "vegetable ivory", "gateleg table", "snooker table", "tablespoonful", "unacceptableness", "inflatable cushion", "cruciferous vegetable", "vegetable matter", "inhospitableness", "stable gear", "table-tennis table", "drop-leaf table", "knight of the round table", "refectory table", "plane table", "tablet", "table tipping", "intractableness", "unsuitableness", "vegetable oil", "table wine", "table-tennis racquet", "table saw", "dining-room table", "file allocation table", "tableland", "cocktail table", "trestle table", "tablemate", "portable circular saw", "stable factor", "actuarial table", "acceptableness", "writing table", "table napkin", "chief constable", "disreputable person", "vegetable patch", "mutableness", "toilet table", "tablet-armed chair", "portable saw", "tablespoon", "habitableness", "solanaceous vegetable", "excitable area", "table knife", "excitableness", "vegetable sheep", "vegetable marrow", "water table", "dinner table", "kitchen table", "work table", "japanese table pine", "table d'hote", "pedestal table", "unprofitableness", "stableness", "comfortableness", "dressing table", "conference table", "card table", "turntable", "table talk", "table rapping", "abatable nuisance", "stableboy", "pier table", "unpalatableness", "parsons table", "vegetable garden", "italian vegetable marrow", "table-mountain pine", "inevitable", "timetable", "tablecloth", "table salt", "pool table", "unstableness", "billiard table", "john constable", "inevitableness", "livery stable", "ratables", "console table", "tractableness", "notable", "memorial tablet", "table mat" };
		String[] result15 = w.filter(RiWordNet.WILDCARD_MATCH, "*table*", "n");
		//printArr(result15);
		setEqual(expected15, result15);

		String[] expected = { "tube well", "tubful", "tubule", "tea ball", "tipple", "tabbouleh", "tupelo", "t-bill", "tableau", "tabooli", "tivoli", "tiepolo", "tuvalu", "tepal" };
		String[] result = w.filter(RiWordNet.SOUNDS_LIKE, "table", "n");
		//		printArr(result);
		setEqual(expected, result);

		String[] expected2 = { "table wine", "tableland", "table linen", "tableware", "table mat", "tablet", "table-tennis racquet", "table talk", "table-tennis table", "tablespoon", "table-mountain pine", "table knife", "table game", "table mustard", "tablecloth", "table salt", "table lamp", "table napkin", "table-tennis bat", "table d'hote", "tableau vivant", "table turning", "table saw", "tabletop", "tablet-armed chair", "table tilting", "table service", "tablespoonful", "table tennis", "table rapping", "table lifting", "tablemate", "tablefork", "table of contents", "table tipping", "table tapping", "tableau" };
		String[] result2 = w.filter(RiWordNet.STARTS_WITH, "table", "n");
		//		printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 = { "bleat" };
		String[] result3 = w.filter(RiWordNet.ANAGRAMS, "table", "n");
		//		printArr(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "adjustable wrench", "round-table conference", "conference table", "periodic table", "coffee-table book", "table rapping", "trestle table", "portable", "turntable", "writing table", "vegetable matter", "stable companion", "adjustable spanner", "plane table", "table service", "vegetable wax", "charitable trust", "pin table", "dining-room table", "intractableness", "training table", "chief constable", "memorial tablet", "dressing table", "portable saw", "tablefork", "tablespoonful", "japanese table pine", "potable", "vegetable sponge", "worktable", "stable gear", "table of contents", "julienne vegetable", "tablecloth", "table-mountain pine", "knight of the round table", "vegetable tallow", "billiard table", "stable factor", "immutableness", "drawing table", "leafy vegetable", "hospitableness", "stablemate", "water table", "sleeping tablet", "king arthur's round table", "inhospitableness", "round table", "graduated table", "augean stables", "mortality table", "table-tennis table", "constable", "table mat", "work table", "solanaceous vegetable", "unstableness", "pool table", "decision table", "tablespoon", "pingpong table", "kitchen table", "table saw", "excitableness", "card table", "actuarial table", "table turning", "stable", "dining table", "unprofitableness", "operating table", "profitableness", "inflatable cushion", "permutableness", "roundtable", "pedestal table", "table tilting", "john constable", "table mustard", "tableland", "dinner table", "disreputable person", "communion table", "inevitableness", "high table", "table salt", "unpalatableness", "mutableness", "drafting table", "vegetable", "tip table", "tea table", "vegetable hummingbird", "eatable", "table d'hote", "cruciferous vegetable", "notable", "ping-pong table", "tablemate", "table talk", "vegetable marrow", "vegetable ivory", "parsons table", "habitableness", "drop-leaf table", "vegetable patch", "irritable bowel syndrome", "table game", "table linen", "vegetable sheep", "table-tennis racquet", "unsuitableness", "vegetable oyster", "tip-top table", "tablet-armed chair", "breakfast table", "suitableness", "refectory table", "table wine", "vegetable oil", "portable circular saw", "tableware", "uncomfortableness", "vegetable soup", "file allocation table", "timetable", "cocktail table", "tableau vivant", "table tipping", "tilt-top table", "coffee table", "collectable", "inevitable", "lord's table", "excitable area", "pier table", "console table", "table napkin", "relocatable program", "snooker table", "tablet", "police constable", "stableman", "root vegetable", "statistical table", "acceptableness", "gaming table", "stableness", "raw vegetable", "unacceptableness", "inevitable accident", "ratables", "disreputableness", "tractableness", "livery stable", "tabletop", "vegetable silk", "portable computer", "abatable nuisance", "comfortableness", "palatableness", "italian vegetable marrow", "table tapping", "charitableness", "toilet table", "council table", "correlation table", "stableboy", "table lifting", "table lamp", "table knife", "vegetable garden", "tableau", "table-tennis bat", "table tennis", "gateleg table" };
		String[] result4 = w.filter(RiWordNet.CONTAINS, "table", "n");
		//		printArr(result4);
		setEqual(expected4, result4);

		String[] expected5 = {};
		String[] result5 = w.filter(RiWordNet.CONTAINS, "tableauu", "n");
		//printArr(result5);
		setEqual(expected5, result5);

		String[] expected6 = {"collectable"};
		String[] result6 = w.filter(RiWordNet.ENDS_WITH, "ctable", "n");
		//printArr(result6);
		setEqual(expected6, result6);

		String[] expected8 = { "constable", "dinner table", "chief constable", "john constable", "notable", "breakfast table", "tip table", "parsons table", "trestle table", "drafting table", "council table", "plane table", "coffee table", "portable", "lord's table", "card table", "turntable", "raw vegetable", "file allocation table", "writing table", "cruciferous vegetable", "conference table", "work table", "communion table", "table", "dressing table", "tea table", "roundtable", "root vegetable", "pin table", "toilet table", "timetable", "cocktail table", "collectable", "periodic table", "knight of the round table", "kitchen table", "police constable", "pier table", "correlation table", "gateleg table", "dining table", "solanaceous vegetable", "graduated table", "training table", "dining-room table", "tilt-top table", "round table", "pool table", "gaming table", "operating table", "console table", "king arthur's round table", "billiard table", "decision table", "eatable", "table-tennis table", "leafy vegetable", "refectory table", "snooker table", "ping-pong table", "livery stable", "pingpong table", "water table", "statistical table", "julienne vegetable", "tip-top table", "vegetable", "actuarial table", "mortality table", "high table", "pedestal table", "inevitable", "worktable", "drop-leaf table", "drawing table", "potable", "stable" };
		String[] result8 = w.filter(RiWordNet.REGEX_MATCH, ".*table", "n");
		//		printArr(result8);
		setEqual(expected8, result8);

		String[] expected9 ={ "volleyball", "clientele", "comestible", "convertible", "collage", "cape sable", "corbie gable", "eatable", "folk tale", "clientage", "coffee table", "colpocele", "plane table", "roundtable", "folktale", "pool table", "college", "vocable", "conventicle", "conjecture", "colleague", "collembola", "work table", "college boy", "telltale", "console table", "collectivism", "portable", "collocalia", "vegetable", "collect call", "pollen tube", "follicle", "tall tale", "collywobbles", "mollycoddle", "notable", "syllable", "collect", "conductance", "decolletage", "reflectance", "toll call", "collet", "corrective", "collecting", "colette", "round table", "collectible", "collegian", "connective", "potable", "collapse", "color tube", "combustible", "coax cable", "toilet table", "sociable", "spectacle", "collectivist", "collotype", "collective", "collector", "roll call", "molecule", "collection", "timetable", "bell gable", "constable", "worktable", "card table" };
		String[] result9 = w.filter(RiWordNet.SIMILAR_TO, "collectable", "n");
		//		printArr(result9);
		setEqual(expected9, result9);

		String[] expected12 = {  };
		String[] result12 = w.filter(RiWordNet.CONTAINS, "nahsuchword", "n");
		//		printArr(result12);
		setEqual(expected12, result12);

		String[] expected14 = { "table rapping", "habitableness", "unstableness", "work table", "stableman", "tractableness", "tablespoon", "timetable", "table salt", "excitableness", "table mustard", "intractableness", "pingpong table", "drop-leaf table", "disreputable person", "table tilting", "vegetable matter", "pier table", "japanese table pine", "vegetable marrow", "portable saw", "graduated table", "table-tennis bat", "vegetable oyster", "breakfast table", "suitableness", "ratables", "drafting table", "periodic table", "correlation table", "sleeping tablet", "constable", "augean stables", "table-tennis racquet", "palatableness", "abatable nuisance", "actuarial table", "tableau", "tip table", "console table", "operating table", "unprofitableness", "round table", "table talk", "vegetable sponge", "disreputableness", "toilet table", "adjustable spanner", "plane table", "drawing table", "pool table", "billiard table", "inhospitableness", "notable", "irritable bowel syndrome", "vegetable soup", "table turning", "inevitable accident", "stable factor", "lord's table", "trestle table", "solanaceous vegetable", "coffee table", "portable", "tip-top table", "dinner table", "tablecloth", "unsuitableness", "writing table", "vegetable oil", "portable computer", "knight of the round table", "kitchen table", "stable", "council table", "tablemate", "tea table", "tableland", "tableware", "vegetable wax", "vegetable", "parsons table", "table saw", "gateleg table", "worktable", "vegetable sheep", "high table", "stable gear", "table linen", "turntable", "tablet-armed chair", "water table", "table d'hote", "table lamp", "dining table", "immutableness", "gaming table", "table wine", "collectable", "comfortableness", "vegetable tallow", "vegetable garden", "leafy vegetable", "pedestal table", "italian vegetable marrow", "police constable", "portable circular saw", "hospitableness", "table napkin", "unpalatableness", "table service", "refectory table", "relocatable program", "table knife", "mutableness", "mortality table", "cocktail table", "round-table conference", "tilt-top table", "statistical table", "training table", "tabletop", "conference table", "table mat", "charitableness", "stable companion", "vegetable hummingbird", "king arthur's round table", "tablefork", "tableau vivant", "cruciferous vegetable", "memorial tablet", "communion table", "eatable", "stableboy", "card table", "inflatable cushion", "table tipping", "table-tennis table", "dining-room table", "julienne vegetable", "livery stable", "root vegetable", "table tennis", "table game", "potable", "vegetable silk", "pin table", "stablemate", "tablespoonful", "acceptableness", "john constable", "table of contents", "permutableness", "vegetable ivory", "snooker table", "ping-pong table", "raw vegetable", "roundtable", "charitable trust", "uncomfortableness", "coffee-table book", "unacceptableness", "adjustable wrench", "inevitable", "table lifting", "profitableness", "dressing table", "vegetable patch", "inevitableness", "excitable area", "tablet", "file allocation table", "table tapping", "chief constable", "decision table", "stableness", "table-mountain pine" };
		String[] result14 = w.filter(RiWordNet.CONTAINS, "table", "n");
		//		printArr(result14);
		setEqual(expected14, result14);

		try{
			w.filter(RiWordNet.CONTAINS, "table", "e");
			ok(false);
		}
		catch(Exception e)
		{
			ok(e);
		}


		w.ignoreCompoundWords(true);
		w.ignoreUpperCaseWords(true);

		expected10 = new String[]{ "tablet" };
		result10 = w.filter(RiWordNet.WILDCARD_MATCH, "table?", "n");
		//printArr(result10);
		setEqual(expected10, result10);

		expected11 = new String[]{ "inevitable", "mutableness", "portable", "tablespoonful", "profitableness", "tableau", "potable", "tablespoon", "eatable", "tablefork", "tablemate", "disreputableness", "turntable", "unpalatableness", "stable", "uncomfortableness", "worktable", "tabletop", "immutableness", "acceptableness", "stableman", "constable", "unsuitableness", "hospitableness", "tablecloth", "roundtable", "stablemate", "tablet", "ratables", "timetable", "stableboy", "unacceptableness", "tableland", "unstableness", "tableware", "collectable", "suitableness", "notable", "unprofitableness", "stableness", "inhospitableness", "permutableness", "charitableness", "comfortableness", "intractableness", "habitableness", "inevitableness", "excitableness", "tractableness", "vegetable", "palatableness" };
		result11 = w.filter(RiWordNet.WILDCARD_MATCH, "*table*", "n");
		//		printArr(result11);
		setEqual(expected11, result11);

		expected15 = new String[]{ "vegetable", "acceptableness", "tablemate", "timetable", "potable", "portable", "permutableness", "stable", "worktable", "disreputableness", "tablespoon", "constable", "intractableness", "tablespoonful", "unpalatableness", "charitableness", "turntable", "excitableness", "tractableness", "unacceptableness", "inevitableness", "notable", "suitableness", "immutableness", "uncomfortableness", "habitableness", "hospitableness", "tableware", "unsuitableness", "tableland", "stableboy", "collectable", "stableman", "unstableness", "mutableness", "stableness", "profitableness", "unprofitableness", "tablet", "eatable", "comfortableness", "palatableness", "tablefork", "tablecloth", "stablemate", "inevitable", "ratables", "roundtable", "tabletop", "inhospitableness", "tableau" };
		result15 = w.filter(RiWordNet.WILDCARD_MATCH, "*table*", "n");
		//		printArr(result15);
		setEqual(expected15, result15);

		expected = new String[]{ "tubule", "tuvalu", "tabooli", "tiepolo", "tivoli", "t-bill", "tepal", "tipple", "tubful", "tabbouleh", "tableau", "tupelo" };
		result = w.filter(RiWordNet.SOUNDS_LIKE, "table", "n");
		//		printArr(result);
		setEqual(expected, result);

		expected2 = new String[]{ "tableware", "tableland", "tablemate", "tablespoon", "tableau", "tabletop", "tablecloth", "tablefork", "tablet", "tablespoonful" };
		result2 = w.filter(RiWordNet.STARTS_WITH, "table", "n");
		//		printArr(result2);
		setEqual(expected2, result2);

		expected3 = new String[]{ "bleat" };
		result3 = w.filter(RiWordNet.ANAGRAMS, "table", "n");
		//		printArr(result3);
		setEqual(expected3, result3);

		expected4 = new String[]{ "unstableness", "notable", "tablemate", "stableness", "tablespoonful", "tablespoon", "eatable", "timetable", "tractableness", "immutableness", "portable", "stablemate", "tabletop", "unprofitableness", "ratables", "inevitable", "tableau", "potable", "inevitableness", "hospitableness", "vegetable", "stableman", "tableland", "stableboy", "worktable", "stable", "tablet", "excitableness", "tablefork", "unpalatableness", "comfortableness", "unsuitableness", "intractableness", "habitableness", "uncomfortableness", "constable", "tableware", "turntable", "permutableness", "palatableness", "unacceptableness", "acceptableness", "mutableness", "tablecloth", "inhospitableness", "disreputableness", "profitableness", "suitableness", "charitableness", "collectable", "roundtable" };
		result4 = w.filter(RiWordNet.CONTAINS, "table", "n");
		//		printArr(result4);
		setEqual(expected4, result4);

		expected5 = new String[]{};
		result5 = w.filter(RiWordNet.CONTAINS, "tableauu", "n");
		//		printArr(result5);
		setEqual(expected5, result5);

		expected6 = new String[]{"collectable"};
		result6 = w.filter(RiWordNet.ENDS_WITH, "ctable", "n");
		//		printArr(result6);
		setEqual(expected6, result6);

		expected8 = new String[]{ "inevitable", "table", "potable", "stable", "eatable", "vegetable", "turntable", "constable", "notable", "portable", "collectable", "worktable", "timetable", "roundtable" };
		result8 = w.filter(RiWordNet.REGEX_MATCH, ".*table", "n");
		//		printArr(result8);
		setEqual(expected8, result8);

		expected9 = new String[]{ "collectible", "folktale", "syllable", "collecting", "colleague", "collage", "decolletage", "collective", "telltale", "reflectance", "collotype", "worktable", "follicle", "collapse", "eatable", "collembola", "constable", "colpocele", "colette", "collectivist", "portable", "clientele", "collector", "vocable", "combustible", "corrective", "connective", "conductance", "conventicle", "timetable", "collection", "clientage", "sociable", "conjecture", "volleyball", "spectacle", "collect", "molecule", "notable", "college", "potable", "convertible", "vegetable", "collectivism", "collywobbles", "collet", "collocalia", "comestible", "collegian", "roundtable", "mollycoddle" };
		result9 = w.filter(RiWordNet.SIMILAR_TO, "collectable", "n");
		//		printArr(result9);
		setEqual(expected9, result9);

		expected12 = new String[]{ };
		result12 = w.filter(RiWordNet.CONTAINS, "nahsuchword", "n");
		//		printArr(result12);
		setEqual(expected12, result12);

		expected14 = new String[]{ "tableau", "stableness", "unprofitableness", "stableboy", "habitableness", "stableman", "hospitableness", "charitableness", "unpalatableness", "tablemate", "tablecloth", "ratables", "suitableness", "stable", "inevitable", "collectable", "roundtable", "tableland", "mutableness", "constable", "tablefork", "worktable", "unacceptableness", "excitableness", "acceptableness", "intractableness", "potable", "eatable", "notable", "tableware", "unstableness", "immutableness", "comfortableness", "inhospitableness", "vegetable", "stablemate", "disreputableness", "tablet", "inevitableness", "portable", "uncomfortableness", "permutableness", "unsuitableness", "tablespoonful", "tabletop", "tractableness", "turntable", "tablespoon", "timetable", "palatableness", "profitableness" };
		result14 = w.filter(RiWordNet.CONTAINS, "table", "n");
		//		printArr(result14);
		setEqual(expected14, result14);

		try{
			w.filter(RiWordNet.CONTAINS, "table", "e");
			ok(false);
		}
		catch(Exception e)
		{
			ok(e);
		}


	}

	@Test
	public void testGetGlossStringString()
	{

		w.ignoreCompoundWords(true);
		w.ignoreUpperCaseWords(true);

		String expected = "impairment resulting from long use; "+'"'+"the tires showed uneven wear"+'"';
		String result = w.getGloss("wear", "n");
		//println(result);
		assertEquals(expected, result);

		String expected2 = "a sign posted in a public place as an advertisement; "+'"'+"a poster advertised the coming attractions"+'"';
		String result2 = w.getGloss("poster", "n");
		//println(result2);
		assertEquals(expected2, result2);

		String expected3 = "affix in a public place or for public notice; "+'"'+"post a warning"+'"';
		String result3 = w.getGloss("post", "v");
		//println(result3);
		assertEquals(expected3, result3);

		String expected4 = null;
		String result4 = w.getGloss("post", "a");
		//println(result4);
		assertEquals(expected4, result4);

		String expected5 = null;
		String result5 = w.getGloss("post", "r");
		//println(result5);
		assertEquals(expected5, result5);


		String expected6 = "engagingly stimulating or provocative; "+'"'+"a piquant wit"+'"'+"; "+'"'+"salty language"+'"';
		String result6 = w.getGloss("salty", "a");
		//println(result6);
		assertEquals(expected6, result6);

		String expected7 = "with resolute determination; "+'"'+"we firmly believed it"+'"'+"; "+'"'+"you must stand firm"+'"';
		String result7 = w.getGloss("firmly", "r");
		//println(result7);
		assertEquals(expected7, result7);

		try{
			w.getGloss("post", "u");
			ok(false);
		}
		catch(Exception e)
		{
			ok(e);
		}


		w.ignoreCompoundWords(false);
		w.ignoreUpperCaseWords(false);

		expected = "impairment resulting from long use; "+'"'+"the tires showed uneven wear"+'"';
		result = w.getGloss("wear", "n");
		//println(result);
		assertEquals(expected, result);

		expected2 = "a sign posted in a public place as an advertisement; "+'"'+"a poster advertised the coming attractions"+'"';
		result2 = w.getGloss("poster", "n");
		//println(result2);
		assertEquals(expected2, result2);

		expected3 = "affix in a public place or for public notice; "+'"'+"post a warning"+'"';
		result3 = w.getGloss("post", "v");
		//println(result3);
		assertEquals(expected3, result3);

		expected4 = null;
		result4 = w.getGloss("post", "a");
		//println(result4);
		assertEquals(expected4, result4);

		expected5 = null;
		result5 = w.getGloss("post", "r");
		//println(result5);
		assertEquals(expected5, result5);


		expected6 = "engagingly stimulating or provocative; "+'"'+"a piquant wit"+'"'+"; "+'"'+"salty language"+'"';
		result6 = w.getGloss("salty", "a");
		//println(result6);
		assertEquals(expected6, result6);

		expected7 = "with resolute determination; "+'"'+"we firmly believed it"+'"'+"; "+'"'+"you must stand firm"+'"';
		result7 = w.getGloss("firmly", "r");
		//println(result7);
		assertEquals(expected7, result7);

		try{
			w.getGloss("post", "u");
			ok(false);
		}
		catch(Exception e)
		{
			ok(e);
		}




	}

	@Test
	public void testGetAllGlosses()
	{
		w.ignoreUpperCaseWords(false);
		w.ignoreCompoundWords(false);

		String[] expected2 ={ "with sadness; in a sad manner; \"`She died last night,' he said sadly\"","in an unfortunate way; \"sadly he died before he could see his grandchild\"","in an unfortunate or deplorable manner; \"he was sadly neglected\"; \"it was woefully inadequate\"", }; 
		String[] result2 = w.getAllGlosses("sadly", "r");
		setEqual(expected2, result2);

		String[] expected = { "impairment resulting from long use; \"the tires showed uneven wear\"","the act of having on your person as a covering or adornment; \"she bought it for everyday wear\"","a covering designed to be worn on a person's body", };
		String[] result = w.getAllGlosses("wear", "n");
		//println(result,true);
		setEqual(expected, result);

		String[] expected3 = { "feeling happy appreciation; \"glad of the fire's warmth\"","cheerful and bright; \"a beaming smile\"; \"a glad May morning\"","eagerly disposed to act or to be of service; \"glad to help\"","showing or causing joy and pleasure; especially made happy; \"glad you are here\"; \"glad that they succeeded\"; \"gave a glad shout\"; \"a glad smile\"; \"heard the glad news\"; \"a glad occasion\"", };
		String[] result3 = w.getAllGlosses("glad", "a");
		//println(result3,true);
		setEqual(expected3, result3);

		String[] expected4 = { "cause to grow or develop; \"He grows vegetables in his backyard\"","come to have or undergo a change of (physical features and attributes); \"He grew a beard\"; \"The patient developed abdominal pains\"; \"I got funny spots all over my body\"; \"Well-developed breasts\"","pass into a condition gradually, take on a specific property or attribute; become; \"The weather turned nasty\"; \"She grew angry\"","cultivate by growing, often involving improvements by means of agricultural techniques; \"The Bordeaux region produces great red wines\"; \"They produce good ham in Parma\"; \"We grow wheat here\"; \"We raise hogs here\"","develop and reach maturity; undergo maturation; \"He matured fast\"; \"The child grew fast\"","become larger, greater, or bigger; expand or gain; \"The problem grew too large for me\"; \"Her business grew fast\"","come into existence; take on form or shape; \"A new religious movement originated in that country\"; \"a love that sprang up from friendship\"; \"the idea for the book grew out of a short story\"; \"An interesting phenomenon uprose\"","grow emotionally or mature; \"The child developed beautifully in her new kindergarten\"; \"When he spent a summer at camp, the boy grew noticeably and no longer showed some of his old adolescent behavior\"","become attached by or as if by the process of growth; \"The tree trunks had grown together\"","increase in size by natural process; \"Corn doesn't grow here\"; \"In these forests, mushrooms grow under the trees\"; \"her hair doesn't grow much anymore\"", };
		String[] result4 = w.getAllGlosses("grow", "v");
		//println(result4,true);
		setEqual(expected4, result4);

		String[] expected12 = { };
		String[] result12 = w.getAllGlosses("grow", "r");
		println(result12,true);
		setEqual(expected12, result12);


		String[] expected11 = { };
		String[] result11 = w.getAllGlosses("growwwwww", "v");
		//println(result9,true);
		setEqual(expected11, result11);

		// DO THE SAME TESTS AGAIN WITH BOTH TRUE (SHOULD BE SAME RESULT FOR GLOSSES AND EXAMPLES)

		w.ignoreUpperCaseWords(true);
		w.ignoreCompoundWords(true);

		String[] expected5 ={ "with sadness; in a sad manner; \"`She died last night,' he said sadly\"","in an unfortunate way; \"sadly he died before he could see his grandchild\"","in an unfortunate or deplorable manner; \"he was sadly neglected\"; \"it was woefully inadequate\"", }; 
		String[] result5 = w.getAllGlosses("sadly", "r");
		setEqual(expected5, result5);

		String[] expected6 = { "impairment resulting from long use; \"the tires showed uneven wear\"","the act of having on your person as a covering or adornment; \"she bought it for everyday wear\"","a covering designed to be worn on a person's body", };
		String[] result6 = w.getAllGlosses("wear", "n");
		//println(result6,true);
		setEqual(expected6, result6);

		String[] expected7 = { "feeling happy appreciation; \"glad of the fire's warmth\"","cheerful and bright; \"a beaming smile\"; \"a glad May morning\"","eagerly disposed to act or to be of service; \"glad to help\"","showing or causing joy and pleasure; especially made happy; \"glad you are here\"; \"glad that they succeeded\"; \"gave a glad shout\"; \"a glad smile\"; \"heard the glad news\"; \"a glad occasion\"", };
		String[] result7 = w.getAllGlosses("glad", "a");
		//println(result7,true);
		setEqual(expected7, result7);

		String[] expected8 = { "cause to grow or develop; \"He grows vegetables in his backyard\"","come to have or undergo a change of (physical features and attributes); \"He grew a beard\"; \"The patient developed abdominal pains\"; \"I got funny spots all over my body\"; \"Well-developed breasts\"","pass into a condition gradually, take on a specific property or attribute; become; \"The weather turned nasty\"; \"She grew angry\"","cultivate by growing, often involving improvements by means of agricultural techniques; \"The Bordeaux region produces great red wines\"; \"They produce good ham in Parma\"; \"We grow wheat here\"; \"We raise hogs here\"","develop and reach maturity; undergo maturation; \"He matured fast\"; \"The child grew fast\"","become larger, greater, or bigger; expand or gain; \"The problem grew too large for me\"; \"Her business grew fast\"","come into existence; take on form or shape; \"A new religious movement originated in that country\"; \"a love that sprang up from friendship\"; \"the idea for the book grew out of a short story\"; \"An interesting phenomenon uprose\"","grow emotionally or mature; \"The child developed beautifully in her new kindergarten\"; \"When he spent a summer at camp, the boy grew noticeably and no longer showed some of his old adolescent behavior\"","become attached by or as if by the process of growth; \"The tree trunks had grown together\"","increase in size by natural process; \"Corn doesn't grow here\"; \"In these forests, mushrooms grow under the trees\"; \"her hair doesn't grow much anymore\"", };
		String[] result8 = w.getAllGlosses("grow", "v");
		//println(result8,true);
		setEqual(expected8, result8);

		String[] expected9 = { };
		String[] result9 = w.getAllGlosses("growwwwww", "v");
		//println(result9,true);
		setEqual(expected9, result9);

		String[] expected13 = { };
		String[] result13 = w.getAllGlosses("grow", "r");
//		println(result13,true);
		setEqual(expected13, result13);

		try{
			String[] result10 = w.getAllGlosses("grow", "j");
		}
		catch(Exception e){
			ok(e);
		}

		try{
			String[] result14 = w.getAllGlosses("growwwwwww", "j");
		}
		catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testGetGlossInt()
	{
		w.ignoreUpperCaseWords(false);
		w.ignoreCompoundWords(false);
		
		String expected = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result = w.getGloss(92124272);
		//println(result);
		assertEquals(expected, result);

		String expected2 = "try to locate or discover, or try to establish the existence of; "+'"'+"The police are searching for clues"+'"'+"; "+'"'+"They are searching for the missing man in the entire county"+'"';
		String result2 = w.getGloss(81318273);
		//println(result2);
		assertEquals(expected2, result2);

		String expected3 = "a whip with nine knotted cords; "+'"'+"British sailors feared the cat"+'"';
		String result3 = w.getGloss(92989061);
		//println(result3);
		assertEquals(expected3, result3);

		try{
			String result7 = w.getGloss(123213123);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		w.ignoreUpperCaseWords(true);
		w.ignoreCompoundWords(true);
		
		 expected = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		 result = w.getGloss(92124272);
		//println(result);
		assertEquals(expected, result);

		 expected2 = "try to locate or discover, or try to establish the existence of; "+'"'+"The police are searching for clues"+'"'+"; "+'"'+"They are searching for the missing man in the entire county"+'"';
		 result2 = w.getGloss(81318273);
		//println(result2);
		assertEquals(expected2, result2);

		 expected3 = "a whip with nine knotted cords; "+'"'+"British sailors feared the cat"+'"';
		 result3 = w.getGloss(92989061);
		//println(result3);
		assertEquals(expected3, result3);

		try{
			String result7 = w.getGloss(123213123);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		
	}

	@Test
	public void testGetDescriptionInt()
	{
		w.ignoreUpperCaseWords(false);
		w.ignoreCompoundWords(false);
		String expected = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result = w.getDescription(92124272);
		//		println(result);
		assertEquals(expected, result);

		String expected2 = "try to locate or discover, or try to establish the existence of";
		String result2 = w.getDescription(81318273);
		//		println(result2);
		assertEquals(expected2, result2);

		String expected3 = "a whip with nine knotted cords";
		String result3 = w.getDescription(92989061);
		//		println(result3);
		assertEquals(expected3, result3);
		
		try{
			String result7 = w.getDescription(123213123);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}

		w.ignoreUpperCaseWords(true);
		w.ignoreCompoundWords(true);
		String expected4 = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result4 = w.getDescription(92124272);
		//		println(result);
		assertEquals(expected4, result4);

		String expected5 = "try to locate or discover, or try to establish the existence of";
		String result5 = w.getDescription(81318273);
		//		println(result2);
		assertEquals(expected5, result5);

		String expected6 = "a whip with nine knotted cords";
		String result6 = w.getDescription(92989061);
		//		println(result6);
		assertEquals(expected6, result6);

		try{
			String result7 = w.getDescription(123213123);
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetDescriptionStringString()
	{
		w.ignoreUpperCaseWords(false);
		w.ignoreCompoundWords(false);
		
		String expected = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result = w.getDescription("cat", "n");
		//		println(result);
		assertEquals(expected, result);

		String expected2 = "change location; move, travel, or proceed, also metaphorically";
		String result2 = w.getDescription("move", "v");
		//		println(result2);
		assertEquals(expected2, result2);

		String expected3 = "having an (over)abundance of flesh";
		String result3 = w.getDescription("fat", "a");
		//		println(result3);
		assertEquals(expected3, result3);

		String expected4 = "to a severe or serious degree";
		String result4 = w.getDescription("badly", "r");
		//		println(result4);
		assertEquals(expected4, result4);
		
		try{
			String result10 = w.getDescription("badly", "u");
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}
		
		w.ignoreUpperCaseWords(true);
		w.ignoreCompoundWords(true);

		String expected5 = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result5 = w.getDescription("cat", "n");
		//		println(result);
		assertEquals(expected5, result5);

		String expected6 = "change location; move, travel, or proceed, also metaphorically";
		String result6 = w.getDescription("move", "v");
		//		println(result2);
		assertEquals(expected6, result6);

		String expected7 = "having an (over)abundance of flesh";
		String result7 = w.getDescription("fat", "a");
		//		println(result3);
		assertEquals(expected7, result7);

		String expected8 = "to a severe or serious degree";
		String result8 = w.getDescription("badly", "r");
		//		println(result4);
		assertEquals(expected8, result8);

		String expected9 = null;
		String result9 = w.getDescription("badlyyyyy", "r");
		//println(result9);
		assertEquals(expected9, result9);

		try{
			String result10 = w.getDescription("badly", "u");
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetExamplesCharSequenceCharSequence()
	{
		w.ignoreUpperCaseWords(false);
		w.ignoreCompoundWords(false);

		String[] expected = {"the tires showed uneven wear"};
		setEqual(expected, w.getExamples("wear","n"));

		String[] expected2 = { "the visit was especially wearing", "an exhausting march" };
		//printArr(w.getExamples("wearing", "a"));
		setEqual(expected2,w.getExamples("wearing","a"));

		String[] expected6 = { };
		//printArr(w.getExamples("wearing", "r"));
		setEqual(expected6,w.getExamples("wearing","r"));

		String[] expected3 = {  "he had stupidly bought a one way ticket" };
		//printArr(w.getExamples("stupidly", "r"));
		setEqual(expected3,w.getExamples("stupidly","r"));

		String[] expected4 = { "Don't run--you'll be out of breath", "The children ran to the store" };
		//printArr(w.getExamples("run", "v"));
		setEqual(expected4, w.getExamples("run","v")); 

		String[] expected5 = { };
		//printArr(w.getExamples("run", "v"));
		setEqual(expected5,w.getExamples("runununun","v"));


		try{
			w.getExamples("run", "j");
			fail("no exception");
		}
		catch(Exception e){
			ok(e);
		}

		// DO THE SAME TESTS AGAIN WITH BOTH TRUE (SHOULD BE SAME RESULT FOR GLOSSES AND EXAMPLES)
		w.ignoreUpperCaseWords(true);
		w.ignoreCompoundWords(true);

		expected = new String[] {"the tires showed uneven wear"};
		setEqual(expected, w.getExamples("wear","n"));

		expected2 = new String[] { "the visit was especially wearing", "an exhausting march" };
		//printArr(w.getExamples("wearing", "a"));
		setEqual(expected2,w.getExamples("wearing","a"));

		expected6 = new String[]{ };
		//printArr(w.getExamples("wearing", "r"));
		setEqual(expected6,w.getExamples("wearing","r"));

		expected3 = new String[] {  "he had stupidly bought a one way ticket" };
		//printArr(w.getExamples("stupidly", "r"));
		setEqual(expected3,w.getExamples("stupidly","r"));

		expected4 = new String[] { "Don't run--you'll be out of breath", "The children ran to the store" };
		//printArr(w.getExamples("run", "v"));
		setEqual(expected4, w.getExamples("run","v")); 

		expected5 = new String[] { };
		//printArr(w.getExamples("run", "v"));
		setEqual(expected5,w.getExamples("runununun","v"));


		try{
			w.getExamples("run", "j");
			fail("no exception");
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetAnyExample()
	{
		w.ignoreCompoundWords(false);
		w.ignoreUpperCaseWords(false);
		
		String[] expected = {"The police are searching for clues", "They are searching for the missing man in the entire county",
				"the students had to research the history of the Second World War for their history project", "He searched for information on his relatives on the web",
				"The police searched the suspect", "We searched the whole house for the missing keys"};
		String result = w.getRandomExample("search", "v");
		//println(result);
		//ssertTrue(Arrays.asList(expected).contains(result));
		setContains(expected, result);
		//setEqual(expected, result);

		String[] expected2 = {};
		String result2 = w.getRandomExample("dude", "n");
		//println(result2);
		equal(null, result2);

		String[] expected3 = new String[] {  "he had stupidly bought a one way ticket" };
		//printArr(w.getRandomExample("stupidly", "r"));
		setContains(expected3,w.getRandomExample("stupidly","r"));


		String[] expected4 = { "Don't run--you'll be out of breath", "The children ran to the store" };  //TODO failed -- the examples are different from getExamples?????
		//println(w.getRandomExample("run","v"));
		setContains(expected4, w.getRandomExample("run","v")); 


		String[] expected5 = new String[] { "the visit was especially wearing", "an exhausting march" };
		//printArr(w.getRandomExample("wearing", "a"));
		setContains(expected5,w.getRandomExample("wearing","a"));


		String[] expected6 = {};
		String result6 = w.getRandomExample("wearing", "r");
		//println(w.getRandomExample("wearing", "a"));
		setContains(null,w.getRandomExample("wearing","r"));  //TODO failed -- should have NO examples

		try{
			w.getRandomExample("wearing", "j");
			equal(1,2);
		}catch(Exception e){
			ok(e);
		}
		
		
		w.ignoreCompoundWords(true);
		w.ignoreUpperCaseWords(true);
		
		expected = new String[]{"The police are searching for clues", "They are searching for the missing man in the entire county",
				"the students had to research the history of the Second World War for their history project", "He searched for information on his relatives on the web",
				"The police searched the suspect", "We searched the whole house for the missing keys"};
		result = w.getRandomExample("search", "v");
		//println(result);
		//ssertTrue(Arrays.asList(expected).contains(result));
		setContains(expected, result);
		//setEqual(expected, result);

		expected2 = new String[]{};
		result2 = w.getRandomExample("dude", "n");
		//println(result2);
		equal(null, result2);

		expected3 = new String[] {  "he had stupidly bought a one way ticket" };
		//printArr(w.getRandomExample("stupidly", "r"));
		setContains(expected3,w.getRandomExample("stupidly","r"));


		expected4 = new String[]{ "Don't run--you'll be out of breath", "The children ran to the store" };  //TODO failed -- the examples are different from getExamples?????
		//println(w.getRandomExample("run","v"));
		setContains(expected4, w.getRandomExample("run","v")); 


		expected5 = new String[] { "the visit was especially wearing", "an exhausting march" };
		//printArr(w.getRandomExample("wearing", "a"));
		setContains(expected5,w.getRandomExample("wearing","a"));


		expected6 = new String[]{};
		result6 = w.getRandomExample("wearing", "r");
		//println(w.getRandomExample("wearing", "a"));
		setContains(null,w.getRandomExample("wearing","r"));  //TODO failed -- should have NO examples

		try{
			w.getRandomExample("wearing", "j");
			equal(1,2);
		}catch(Exception e){
			ok(e);
		}


	}

	@Test
	public void testGetExamplesInt()  //TODO
	{
		String[] expected = {"the tires showed uneven wear"};
		String[] result = w.getExamples(914586275);
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllExamples()
	{
		w.ignoreCompoundWords(false);
		w.ignoreUpperCaseWords(false);
		
		String[] expected = {"The police are searching for clues", "They are searching for the missing man in the entire county",
				"the students had to research the history of the Second World War for their history project", "He searched for information on his relatives on the web",
				"The police searched the suspect", "We searched the whole house for the missing keys"};
		String[] result = w.getAllExamples("search", "v");
		//println(result);
		setEqual(expected, result);

		String[] expected2 = { "the visit was especially wearing" };
		String[] result2 = w.getAllExamples("wearing", "a");
		//		printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 = {};
		String[] result3 = w.getAllExamples("wearing", "r");
		//		printArr(result3);
		setEqual(expected3, result3);

		String[] expected4 = {};
		String[] result4 = w.getAllExamples("wearing", "n");
		//		printArr(result4);
		setEqual(expected4, result4);

		String[] expected5 = {};
		String[] result5 = w.getAllExamples("wearing", "v");
		//		printArr(result5);
		setEqual(expected5, result5);

		String[] expected6 = { "What should I wear today?", "He always wears a smile", "wear one's hair in a certain way", "She was wearing yellow that day" };
		String[] result6 = w.getAllExamples("wear", "v");
		//		printArr(result6);
		setEqual(expected6, result6);

		String[] expected7 = { "they shouted happily", "happily he was not injured" };
		String[] result7 = w.getAllExamples("happily", "r");
		//		printArr(result7);
		setEqual(expected7, result7);

		String[] expected8 = { "a fat land", "fatty food", "he hadn't remembered how fat she was", "fat tissue", "a nice fat job", "a fat rope" };
		String[] result8 = w.getAllExamples("fat", "a");
		//		printArr(result8);
		setEqual(expected8, result8);

		String[] expected9 = {};
		String[] result9 = w.getAllExamples("fatttttt", "a");
		//		printArr(result9);
		setEqual(expected9, result9);

		try{
			w.getAllExamples("fatttttt", "u");
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}
		
		w.ignoreCompoundWords(true);
		w.ignoreUpperCaseWords(true);
		
		expected = new String[]{"The police are searching for clues", "They are searching for the missing man in the entire county",
				"the students had to research the history of the Second World War for their history project", "He searched for information on his relatives on the web",
				"The police searched the suspect", "We searched the whole house for the missing keys"};
		result = w.getAllExamples("search", "v");
		//println(result);
		setEqual(expected, result);

		expected2 = new String[]{ "the visit was especially wearing" };
		result2 = w.getAllExamples("wearing", "a");
		//		printArr(result2);
		setEqual(expected2, result2);

		expected3 = new String[]{};
		result3 = w.getAllExamples("wearing", "r");
		//		printArr(result3);
		setEqual(expected3, result3);

		expected4 = new String[]{};
		result4 = w.getAllExamples("wearing", "n");
		//		printArr(result4);
		setEqual(expected4, result4);

		expected5 = new String[]{};
		result5 = w.getAllExamples("wearing", "v");
		//		printArr(result5);
		setEqual(expected5, result5);

		expected6 = new String[]{ "What should I wear today?", "He always wears a smile", "wear one's hair in a certain way", "She was wearing yellow that day" };
		result6 = w.getAllExamples("wear", "v");
		//		printArr(result6);
		setEqual(expected6, result6);

		expected7 = new String[]{ "they shouted happily", "happily he was not injured" };
		result7 = w.getAllExamples("happily", "r");
		//		printArr(result7);
		setEqual(expected7, result7);

		expected8 = new String[]{ "a fat land", "fatty food", "he hadn't remembered how fat she was", "fat tissue", "a nice fat job", "a fat rope" };
		result8 = w.getAllExamples("fat", "a");
		//		printArr(result8);
		setEqual(expected8, result8);

		expected9 = new String[]{};
		result9 = w.getAllExamples("fatttttt", "a");
		//		printArr(result9);
		setEqual(expected9, result9);

		try{
			w.getAllExamples("fatttttt", "u");
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testGetCommonParents() { //TODO stopped here

		String[] expected = { "wear", "habiliment", "vesture", "wearable", "article of clothing", "clothing" };
		String[] result = w.getCommonParents("activewear", "beachwear", "n");
		printArr(result);
		setEqual(expected, result);

		String[] expected2 = { "hymenopterous insect", "hymenopter", "hymenopteron", "hymenopteran" };
		String[] result2 = w.getCommonParents("bee", "ant", "n");
		printArr(result2);
		//setEqual(expected2, result2);

		String[] expected3 = { "physical entity" };
		String[] result3 = w.getCommonParents("bee", "wood", "n");
		printArr(result3);

		String[] expected4 = { "entity" };
		String[] result4 = w.getCommonParents("bee", "run", "n");
		printArr(result4);

		String[] expected5 = { };
		String[] result5 = w.getCommonParents("beeesdasd", "run", "n");
		printArr(result5);

		String[] expected6 = { };
		String[] result6 = w.getCommonParents("beeesdasd", "runasdasdasd", "n");
		printArr(result6);

		String[] expected7 = { };
		String[] result7 = w.getCommonParents("flower", "runasdasdasd", "n");
		printArr(result7);

		String[] expected8 = { };
		String[] result8 = w.getCommonParents("flower", "happily", "v");
		printArr(result8);

		String[] expected9 = { };
		String[] result9 = w.getCommonParents("flower", "happily", "r");
		printArr(result9);

		String[] expected10 = { };
		String[] result10 = w.getCommonParents("flower", "happily", "a");
		printArr(result10);

		String[] expected11 = { };
		String[] result11 = w.getCommonParents("sadly", "happily", "r");
		printArr(result11);

		String[] expected12 = { };
		String[] result12 = w.getCommonParents("fat", "thin", "a");
		printArr(result12);

		String[] expected13 = { };
		String[] result13 = w.getCommonParents("go", "run", "v");
		printArr(result13);

		try{
			w.getCommonParents("fatttttt", "sad","j");
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetCommonParent()
	{
		int expected = 93055525;
		int result = w.getCommonParent(94292941, 92817909);
		//println("getCommonParent: "+result);
		equal(expected, result); // DCH: changed from KENNY's version
	}

	@Test
	public void testGetSynsetStringString()
	{
		String[] result = w.getSynset("medicare", "n");
		//println(result);
		setEqual(new String[0], result);

		String[] expected = {};
		result = w.getSynset("health insurance", "n");
		//println("getSynset:"+result);
		setEqual(new String[0], result);

		// assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
		expected = new String[] { "sportswear" };
		result = w.getSynset("activewear", "n");

		//println("getSynset:"+Arrays.asList(result));

		setEqual(expected, result);
	}

	@Test
	public void testGetSynsetStringStringBoolean()
	{
		String[] expected = {"sportswear", "activewear"};
		String[] result = w.getSynset("activewear", "n", true);
		//println(w.getSenseIds("search", "v"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetSynsetInt()
	{
		String[] expected = {"sportswear", "activewear"};
		String[] result = w.getSynset(94292941);
		//println(w.getSenseIds("activewear", "n"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetAllSynsets()
	{
		String[] expected = new String[]{ "sportswear", "athletic wear" };
		String[] result = w.getAllSynsets("activewear", "n");
		setEqual(expected, result);
	}

	@Test
	public void testGetSenseCount()
	{
		int expected = 6;
		int result = w.getSenseCount("table", "n");
		//println(w.getSenseIds("activewear", "n"));
		//println(result);
		equal(expected, result);
	}

	@Test
	public void testGetAntonymsStringString()
	{
		setEqual(w.getAntonyms("day","n"), new String[]{}); // WHY?? Added to known-issues
		setEqual(w.getAntonyms("night","n"), new String[]{"day"});

		setEqual(w.getAntonyms("left", "a"), new String[]{"right"});
		setEqual(w.getAntonyms("right", "a"), new String[]{"left"});

		setEqual(w.getAntonyms("full", "a"), new String[]{"empty"});
		setEqual(w.getAntonyms("empty", "a"), new String[]{"full"});

		setEqual(w.getAntonyms("quickly", "r"), new String[]{"slowly"});
		setEqual(w.getAntonyms("slowly", "r"), new String[]{"quickly"});

		setEqual(w.getAntonyms("smoothly", "r"), new String[]{});
		setEqual(w.getAntonyms("", "r"), new String[]{});

		setEqual(w.getAntonyms("smoothlyyyyyyyyyyyy", "r"), new String[]{});
		try{
			w.getAntonyms("smoothlyyyyyyyyyyyy", "u");
			equal(1,2);
		}
		catch(Exception e){
			ok(e);
		}

	}

	@Test
	public void testGetAntonymsInt()
	{
		String[] expected = {"day"};
		String[] result = w.getAntonyms(915192074);
		//println(w.getSenseIds("night", "n"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetAllAntonyms()
	{
		String[] expected = {"day"};
		String[] result = w.getAllAntonyms("night", "n");
		//println(w.getSenseIds("night", "n"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetHypernymsInt()
	{
		String[] expected = {"period"};
		String[] result = w.getHypernyms(915192074);
		//println(w.getSenseIds("night", "n"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetAllHypernyms()
	{
		String[] expected = {"period", "dark", "darkness", "twilight", "dusk", "gloaming", "gloam", "nightfall", "evenfall", "fall", "crepuscule", "crepuscle"};
		String[] result = w.getAllHypernyms("night", "n");
		//println(w.getSenseIds("night", "n"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	/*
0) 'night:nighttime:dark'
1) 'measure:quantity:amount'
2) 'period'
3) 'entity'
4) 'abstraction'
	 */
	@Test
	public void testGetHypernymTree()
	{
		String[] expected = {"night:nighttime:dark", "measure:quantity:amount", "period", "abstraction", "entity"};
		String[] result = w.getHypernymTree(915192074);
		setEqual(expected, result);
	}

	@Test
	public void testGetHyponymsStringString()
	{
		String[] expected = { "weeknight" };
		String[] result = w.getHyponyms("night", "n");
		setEqual(expected, result);
	}

	@Test
	public void testGetHyponymsInt()
	{
		String[] expected = {"weeknight"};
		String[] result = w.getHyponyms(915192074);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllHyponyms()
	{
		String[] expected = {"weeknight"};
		String[] result = w.getAllHyponyms("night", "n");
		//println(w.getSenseIds("night", "n"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetHyponymTree()
	{
		String[] expected = {"weeknight"};
		String[] result = w.getHyponymTree(915192074);
		//println(w.getSenseIds("night", "n"));
		//println(result);
		deepEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testIsAdjective()
	{
		RiWordNet.useMorphologicalProcessor = true;
		equal(true, w.isAdjective("biggest"));
		RiWordNet.useMorphologicalProcessor = false;
		equal(!true, w.isAdjective("biggest"));

		equal(true, w.isAdjective("big"));
		equal(true, w.isAdjective("bigger"));
		equal(true, w.isAdjective("old"));
		equal(true, w.isAdjective("elder"));
		equal(true, w.isAdjective("eldest"));
		equal(false, w.isAdjective("slowly"));
		equal(false, w.isAdjective("quite"));
	}

	@Test
	public void testIsAdverb()
	{
		equal(false, w.isAdverb("mary"));
		equal(true, w.isAdverb("slowly"));
		equal(true, w.isAdverb("quite"));
		equal(true, w.isAdverb("together"));
	}

	@Test
	public void testIsVerb()
	{
		equal(false, w.isVerb("mary"));
		equal(true, w.isVerb("run"));
		equal(true, w.isVerb("walk"));
		equal(true, w.isVerb("sing"));
	}

	@Test
	public void testIsNoun()
	{
		equal(true, w.isNoun("mary"));
		equal(true, w.isNoun("run"));
		equal(false, w.isNoun("slowly"));
		equal(false, w.isNoun("together"));
	}

	@Test
	public void testGetStems()
	{
		String[] expected = {"produce"};
		String[] result = w.getStems("produced", "v");
		//println(w.getSenseIds("night", "n"));
		//println(result);
		setEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testIsStem()
	{
		equal(false, w.isStem("waiting", "v"));
		equal(true, w.isStem("wait", "n"));
	}

	@Test
	public void testExists()
	{
		equal(true, w.exists("abc"));
		equal(true, w.exists("wait"));
		equal(false, w.exists("tesxx"));

		equal(false, w.exists("123"));
		equal(false, w.exists("#$%^&*()"));
		equal(false, w.exists("tes$%^"));

	}

	@Test
	public void testRemoveNonExistent()
	{
		String[] testArray = {"abc", "wait", "tesxx"};
		String[] expected = {"abc", "wait"};
		List<String> testlist = new ArrayList<String>(Arrays.asList(testArray));
		List<String> expectedlist = new ArrayList<String>(Arrays.asList(expected));
		w.removeNonExistent(testlist);
		deepEqual(testlist, expectedlist);
	}

	@Test
	public void testGetPosString()
	{
		String[] expected = {"v", "n"};
		String[] result = w.getPos("wait");
		//println(w.getSenseIds("night", "n"));
		//println(result);
		deepEqual(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetPosInt()
	{
		equal("n", w.getPos(915297015));
		equal("n", w.getPos(91065863));
		equal("v", w.getPos(82644022));
		equal("v", w.getPos(82647547));
		equal("v", w.getPos(8721987));
		equal("v", w.getPos(82418420));
	}

	@Test
	public void testGetPosStr()
	{
		String expected = "vn";
		String result = w.getPosStr("wait");
		//println(w.getSenseIds("night", "n"));
		//println(result);
		equal(expected, result);
		//assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	}

	@Test
	public void testGetBestPos()
	{
		equal("v", w.getBestPos("wait"));
		equal("n", w.getBestPos("dog"));
	}

	@Test
	public void testGetRandomExample()
	{
		ok(w.getRandomExample("v"));
	}

	@Test
	public void testGetRandomExamples()
	{
		String[] result = w.getRandomExamples("v", 5);
		//println(result);
		equal(result.length, 5);
	}

	@Test
	public void testGetRandomWords()
	{
		String[] result = w.getRandomWords("v", 5);
		//println(result);
		equal(result.length, 5);
	}

	@Test
	public void testGetRandomWordCharSequence()
	{
		String result = w.getRandomWord("v");
		ok(result);
	}

	@Test
	public void testGetRandomWordCharSequenceBooleanInt()
	{
		String result = w.getRandomWord("v", true, 10);
		ok(w.isStem(result, "v"));
		equal(true, w.isStem(result, "v"));
	}

	@Test
	public void testGetDistance()
	{
		float expected = (float) 0.2;
		float result = w.getDistance("table", "chair", "n");
		equal(expected, result);
	}

	@Test
	public void testGetMeronymsStringString()
	{
		String[] expected = {"row", "column"};
		String[] result = w.getMeronyms("table", "n");
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetMeronymsInt()
	{
		String[] expected = {"row", "column"};
		String[] result = w.getMeronyms(98283156);
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllMeronyms()
	{
		String[] expected = {"row", "column", "leg", "tabletop", "tableware"};
		String[] result = w.getAllMeronyms("table", "n");
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetHolonymsStringString()
	{
		String[] expected = {"body", "homo", "man", "human"};
		String[] result = w.getHolonyms("arm", "n");
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetHolonymsInt()
	{
		String[] expected = {"body", "homo", "man", "human"};
		String[] result = w.getHolonyms(95571403);
		//println(w.getSenseIds("arm", "n"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllHolonyms()
	{
		String[] expected = {"body", "homo", "man", "human", "weaponry", "arms", "munition", "armchair", "garment"};
		String[] result = w.getAllHolonyms("arm", "n");
		//println(w.getSenseIds("arm", "n"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetCoordinatesStringString()
	{
		String[] expected = {"hindlimb", "forelimb", "flipper", "leg", "crus", "thigh", "cubitus", "forearm"};
		String[] result = w.getCoordinates("arm", "n");
		//println(w.getSenseIds("arm", "n"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetCoordinatesInt()
	{
		String[] expected = {"hindlimb", "forelimb", "flipper", "leg", "crus", "thigh", "arm", "cubitus", "forearm"};
		String[] result = w.getCoordinates(95571403);
		//println(w.getSenseIds("arm", "n"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllCoordinates()
	{
		String[] expected = { "biquadrate","root","divisor","record","subtrahend","multiplicand","biquadratic","arity","prime","constant","quartic","radix","remainder","score","lineage","square","cardinality","augend","base","cube","factor","paging","co-ordinate","difference","no.","integer","multiplier","minuend","ordinal","decimal","folio","addend","cardinal","pagination","linage","dividend","imaginary","quotient","count","quota", };
		String[] result = w.getAllCoordinates("coordinate", "n");
		setEqual(expected, result);

		String[] expected2 = {"stretch", "attend", "occupy", "fill", "populate", "dwell", "inhabit", "reach", "touch", "run", "go",
				"pass", "lead", "extend", "cover", "continue", "lie", "sit", "face", "straddle", "follow", "rest", "belong", "come", "know", "experience", "suffer",
				"endure", "meet", "feel", "enjoy", "witness", "find", "see"};
		String[] result2 = w.getAllCoordinates("live", "v");
		setEqual(expected2, result2);
	}

	@Test
	public void testGetVerbGroupStringString()
	{
		String[] expected = {"populate", "dwell", "inhabit"};
		String[] result = w.getVerbGroup("live", "v");
		setEqual(expected, result);

		// TODO: >=3
	}

	@Test
	public void testGetVerbGroupInt()
	{
		String[] expected = {"populate", "dwell", "live", "inhabit"};
		String[] result = w.getVerbGroup(82655932);
		//println(w.getSenseIds("live", "v"));
		setEqual(expected, result);
	}

	@Test
	public void testGetAllVerbGroups()
	{
		String[] expected = {"populate", "dwell", "inhabit", "survive", "last", "go", "endure", "be", "exist", "subsist", "know", "experience"};
		String[] result = w.getAllVerbGroups("live", "v");
		//println(result,true);
		setEqual(expected, result);
	}

	@Test
	public void testGetDerivedTermsStringString()
	{
		String[] expected = {"jubilant", "blithe", "gay", "mirthful", "merry", "happy"};
		String[] result = w.getDerivedTerms("happily", "r");
		//println(w.getSenseIds("arm", "n"));
		//println(result);
		setEqual(expected, result);

	}

	@Test
	public void testGetDerivedTermsInt()
	{
		String[] expected = {"jubilant", "blithe", "gay", "mirthful", "merry", "happy"};
		String[] result = w.getDerivedTerms(650835);
		//println(w.getSenseIds("happily", "r"));
		//println(result);
		setEqual(expected, result);

	}

	@Test
	public void testGetAllDerivedTerms()
	{
		String[] expected = {"jubilant", "blithe", "gay", "mirthful", "merry", "happy"};
		String[] result = w.getAllDerivedTerms("happily", "r");
		//println(w.getSenseIds("arm", "n"));
		//println(result);
		setEqual(expected, result);

	}

	@Test
	public void testGetAlsoSeesStringString()
	{
		String[] expected = {"cheerful", "contented", "content", "glad", "elated", "euphoric", "felicitous", "joyful", "joyous"};
		String[] result = w.getAlsoSees("happy", "a");
		//println(w.getSenseIds("arm", "n"));
		//println(result);
		setEqual(expected, result);

	}

	@Test
	public void testGetAlsoSeesInt()
	{
		String[] expected = {"cheerful", "contented", "content", "glad", "elated", "euphoric", "felicitous", "joyful", "joyous"};
		String[] result = w.getAlsoSees(71151786);
		//println(w.getSenseIds("happy", "a"));
		//println(result);
		setEqual(expected, result);

	}

	@Test
	public void testGetAllAlsoSees()
	{
		String[] expected = {"cheerful", "contented", "content", "glad", "elated", "euphoric", "felicitous", "joyful", "joyous"};
		String[] result = w.getAllAlsoSees("happy", "a");
		//println(w.getSenseIds("happy", "a"));
		//println(result);
		setEqual(expected, result);

	}

	@Test
	public void testGetNominalizationsStringString()
	{
		String[] expected = {"happiness"};
		String[] result = w.getNominalizations("happy", "a");
		//println(w.getSenseIds("happy", "a"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetNominalizationsInt()
	{
		String[] expected = {"happiness"};
		String[] result = w.getNominalizations(71151786);
		//println(w.getSenseIds("happy", "a"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllNominalizations()
	{
		String[] expected = {"happiness", "felicitousness", "felicity"};
		String[] result = w.getAllNominalizations("happy", "a");
		//println(w.getSenseIds("happy", "a"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetSimilarStringString()
	{
		String[] expected = {"blessed", "blissful", "bright", "golden", "halcyon", "prosperous", "laughing", "riant"};
		String[] result = w.getSimilar("happy", "a");
		//println(w.getSenseIds("happy", "a"));
		println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetSimilarInt()
	{
		String[] expected = {"blessed", "blissful", "bright", "golden", "halcyon", "prosperous", "laughing", "riant"};
		String[] result = w.getSimilar(71151786);
		//println(w.getSenseIds("happy", "a"));
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllSimilar()
	{
		String[] expected = {"blessed", "blissful", "bright", "golden", "halcyon", "prosperous", "laughing", "riant", "fortunate", "willing", "felicitous"};
		String[] result = w.getAllSimilar("happy", "a");
		//println(w.getSenseIds("happy", "a"));
		//println(result);
		setEqual(expected, result);

	}


	@Test
	public void testGetAnagramsStringStringInt()
	{
		String[] expected = { "thing" };
		String[] result = w.getAnagrams("night", "n", 2);
		//println(result);
		setEqual(expected, result);
		String[] expected2 = { "thing" };
		String[] result2 = w.getAnagrams("night", "n", 1);
		//println(result2);
		setEqual(expected2, result2);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAnagramsStringString()
	{
		String[] expected = { "thing" };
		String[] result = w.getAnagrams("night", "n");
		//println(result);

		setEqual(expected, result);
		String[] expected2 = {  "god" };
		String[] result2 = w.getAnagrams("dog", "n");
		//println(result2);
		setEqual(expected2, result2);

		String[] expected3 = { "bleat", };
		String[] result3 = w.getAnagrams("table", "n");
		//println(result2);
		setEqual(expected3, result3);
	}

	@Test
	public void testIgnoreCompoundWordsBoolean()
	{

		String[] expected = { "collectable", "constable", "eatable", "inevitable"};
		w.ignoreCompoundWords(true);
		String[] result = w.getEndsWith("table", "n", 4);
		setEqual(expected, result);

		String[] expected2 = { "actuarial table", "card table", "billiard table", "breakfast table"};
		w.ignoreCompoundWords(false);
		String[] result2 = w.getEndsWith("table", "n", 4);
		setEqual(expected2, result2);

	}

	@Test
	public void testIgnoreCompoundWords()
	{
		w.ignoreCompoundWords(true);
		ok(w.ignoreCompoundWords());

		w.ignoreCompoundWords(false);
		ok(!w.ignoreCompoundWords());

		// TODO: add tests here like in testIgnoreUpperCaseWords();
	}

	@Test
	public void testIgnoreUpperCaseWordsBoolean() 
	{
		w.ignoreUpperCaseWords(false);
		w.ignoreCompoundWords(true);

		String[] expected = { "Dulles"};
		String[] result = w.getSynset("dulles", "n");
		//System.out.println("testIgnoreUpperCaseWordsBoolean: ");
		RiTa.out(result);
		setEqual(expected, result);

		expected = new String[] { "Fungi"};
		result = w.getSynset("fungi", "n");
		setEqual(expected, result);

		expected = new String[] { "Fungi","Prokayotae", "Monera", "Protoctista", "Animalia", "Plantae" };
		result = w.getAllSynonyms("fungi", "n");
		setEqual(expected, result);
	}

	@Test
	public void testIgnoreUpperCaseWords()
	{
		w.ignoreUpperCaseWords(true);
		w.ignoreCompoundWords(true);
		ok(w.ignoreUpperCaseWords());

		String[] expected = new String[] { };
		String[] result = w.getSynset("fungi", "n");
		setEqual(expected, result);

		w.ignoreUpperCaseWords(false);
		ok(!w.ignoreUpperCaseWords());

		expected = new String[] { "Fungi"};
		result = w.getSynset("fungi", "n");
		setEqual(expected, result);
	}

	@Test
	public void testIteratorString()
	{
		String[] pos = {"n","a","r","v",};
		Set s = new HashSet();
		for (int i = 0; i < pos.length; i++)
		{
			Iterator it = w.iterator(pos[i]);
			int numOfItems = 0;
			while (it.hasNext())
			{
				String word = (String) it.next();
				String wpos = w.getPosStr(word);
				s.add(wpos);
				ok(word);
				if (!wpos.contains(pos[i]))
					System.err.println("FAIL: "+word+"("+wpos+") does not contain: "+pos[i]);
				//ok(wpos.contains(pos[i]));
				numOfItems++;
			}
			//System.out.println(numOfItems+" of "+pos[i]);
			ok(numOfItems > 50);
		}
	}

	@Test
	public void testOrFilterIntArrayStringArrayStringInt()
	{/*
	  String[] word = {"arity", "pagination", "folio", "paging", "decimal", "constant", "cardinality", "count", "factor", "prime", "score",
			  "record", "ordinal", "no.", "cardinal", "base", "radix", "quota", "linage", "lineage", "integer", "addend", "augend", "minuend", "subtrahend", "remainder",
			  "difference", "imaginary", "square", "cube", "biquadrate", "biquadratic", "quartic", "root", "dividend", "divisor", "quotient", "multiplier", "multiplicand"};
	  String[] result = w.orFilter(RiWordNet.ALL_FILTERS,word , "n");

	  for(int i=0; i< result.length ; i++){
		  System.out.print(result[i] +"\n");
	  }*/

		fail("Not yet implemented");
	}

	@Test
	public void testOrFilterIntArrayStringArrayString()
	{
		int[] filters = { RiWordNet.STARTS_WITH, RiWordNet.ENDS_WITH };
		String[] patterns = { "ax", "ax" };
		String[] result = w.orFilter(filters, patterns , "n");
		RiTa.out(result);
		fail("Not yet implemented");
	}

	@Test
	public void testIsCompound()  // DCH: ok now...
	{

		String[] input3 = {"back", "space", "back space", "back_space","","#$%^&"};
		boolean[] expected3 = {false, false, true, true,false,false};
		for(int i=0; i< input3.length; i++){
			//println(w.isCompound(input3[i]));
			deepEqual(w.isCompound(input3[i]),expected3[i]);
		}
	}



	/////////////////////////////  Dynamics ///////////////////////////////

	void setContainsMulti(String[] expected, String methodNm, int id, int count) {
		setContainsMulti(expected, methodNm, 
				new Class[] { int.class, int.class }, new Object[]{ id, count } );
	}
	void setContainsMulti(String[] expected, String methodNm, String word, String pos, int count) {
		setContainsMulti(expected, methodNm, 
				new Class[] { String.class, String.class, int.class }, new Object[]{ word, pos, count } );
	}
	void setContainsMulti(String[] expected, String methodNm, Class[] argTypes, Object[] args)
	{
		boolean ignoreCompoundsOrig = w.ignoreCompoundWords();
		boolean ignoreUppersOrig = w.ignoreUpperCaseWords();
		String[] result;
		Method m = RiTa._findMethod(w, methodNm, argTypes );
		try
		{
			w.ignoreCompoundWords(false);
			w.ignoreUpperCaseWords(false);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setContains(expected, result);


			w.ignoreCompoundWords(true);
			w.ignoreUpperCaseWords(false);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setContains(removeCompoundWords(expected), result);


			w.ignoreCompoundWords(false);
			w.ignoreUpperCaseWords(true);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setContains(removeUpperCaseWords(expected), result);


			w.ignoreCompoundWords(true);
			w.ignoreUpperCaseWords(true);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setContains(removeCompoundWords(removeUpperCaseWords(expected)), result);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		w.ignoreCompoundWords(ignoreCompoundsOrig);
		w.ignoreUpperCaseWords(ignoreUppersOrig);
	}

	void setEqualMulti(String[] expected, String methodNm, int id)
	{
		setEqualMulti(expected, methodNm, new Class[] { int.class }, new Object[]{ id });
	}
	void setEqualMulti(String[] expected, String methodNm, String word, String pos)
	{
		setEqualMulti(expected, methodNm, new Class[] { String.class, String.class }, new Object[]{ word, pos });
	}

	void setEqualMulti(String[] expected, String methodNm, Class[] argTypes, Object[] args)
	{
		boolean ignoreCompoundsOrig = w.ignoreCompoundWords();
		boolean ignoreUppersOrig = w.ignoreUpperCaseWords();
		String[] result;
		Method m = RiTa._findMethod(w, methodNm, argTypes );
		try
		{
			w.ignoreCompoundWords(false);
			w.ignoreUpperCaseWords(false);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setEqual(expected, result);

			w.ignoreCompoundWords(true);
			w.ignoreUpperCaseWords(false);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setEqual(removeCompoundWords(expected), result);

			w.ignoreCompoundWords(false);
			w.ignoreUpperCaseWords(true);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setEqual(removeUpperCaseWords(expected), result);

			w.ignoreCompoundWords(true);
			w.ignoreUpperCaseWords(true);
			result = (String[]) m.invoke(w, args);
			if (args[0] instanceof String) // make sure we don't have the search term
				ok(!Arrays.asList(result).contains(args[0]));
			setEqual(removeCompoundWords(removeUpperCaseWords(expected)), result);
		}
		catch (Throwable e)
		{
			throw new RuntimeException(e);
		}
		w.ignoreCompoundWords(ignoreCompoundsOrig);
		w.ignoreUpperCaseWords(ignoreUppersOrig);
	}

	//////////////////////////////// Helpers ///////////////////////////////////

	private static String[] removeUpperCaseWords(String[] s)
	{
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < s.length; i++)
		{
			if (!WordnetUtil.startsWithUppercase(s[i]))
				al.add(s[i]);
		}
		return al.toArray(new String[0]);
	}

	private static String[] removeCompoundWords(String[] s)
	{
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < s.length; i++)
		{
			if (!w.isCompound(s[i]))
				al.add(s[i]);
		}
		return al.toArray(new String[0]);
	}

	static RiWordNet w;
	static boolean preloadFilters;
	static { 

		SILENT = false;
		long ts = System.currentTimeMillis();
		w = new RiWordNet("/WordNet-3.1");
		if (preloadFilters) {
			String[] pos = {"n","a","r","v",};
			for (int i = 0; i < pos.length; i++)
				w.iterator(pos[i]); // force load filters, so slow (TODO: optimize!)
		}
		System.out.println("[INFO] Loaded in "+(System.currentTimeMillis()-ts)+"ms");
	}

	public static void main(String[] args)
	{  
		println(new RiWordNet("/WordNet-3.1").ignoreCompoundWords(false).getSynset("medicare", "n"));
	}
}
