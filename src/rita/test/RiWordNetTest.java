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
		String[] expected = { "quest for", "divine", "quest after", "look for", "drag", "seek out", "window-shop", "scrabble", "feel", "gather", "grope for", "seek", "leave no stone unturned", "browse", "fish", "want", "angle", "scour", "grope", "finger", "shell", "dredge", "hunt", "antique", "grub", "fumble", "search", "pursue", "shop", "dowse", "comparison-shop", "surf", "go after" };		//println(w.getSynonyms(81318273), true);
		printArr( w.getSynonyms(81318273));
		setEqualMulti(expected, "getSynonyms", 81318273); 
		
		String[] expected2 = { "Methuselah", "beau", "baboo", "fashion plate", "ironman", "dandy", "graybeard", "macho-man", "babu", "fop", "divorced man", "signior", "grass widower", "Samson", "ponce", "young man", "young buck", "old man", "Tarzan", "widowman", "fellow", "old boy", "cat", "middle-aged man", "stiff", "wonder boy", "unmarried man", "father-figure", "he-man", "Peter Pan", "guy", "boy", "Herr", "boyfriend", "swell", "Esq", "gallant", "dude", "shaver", "hombre", "hunk", "golden boy", "Hooray Henry", "bozo", "signor", "iron man", "gentleman", "inamorato", "Senhor", "womanizer", "adonis", "widower", "Monsieur", "bull", "bey", "white man", "womaniser", "housefather", "buster", "sheik", "ejaculator", "sod", "greybeard", "ex", "castrate", "philanderer", "signore", "ex-husband", "sir", "posseman", "galoot", "clotheshorse", "eunuch", "father surrogate", "patriarch", "stud", "ironside", "geezer", "swain", "father figure", "strapper", "ex-boyfriend", "paterfamilias", "bruiser", "bachelor", "Esquire" };
		printArr( w.getSynonyms(910172934));
		setEqualMulti(expected, "getSynonyms", 910172934); 

	}

	@Test
	public void testGetSynonymsIntInt()
	{
		String[] expected = { "scour","grub","antique","comparison-shop","hunt","drag","shop","dowse","browse","seek","scrabble","quest after","search","fish","pursue","angle","shell","want","surf","seek out","window-shop","look for","divine","grope","leave no stone unturned","go after","gather","grope for","quest for","feel","fumble","dredge","finger" };
		setContainsMulti(expected, "getSynonyms", 81318273, 4);
	}

	@Test
	public void testGetSynonymsStringString()
	{
		String[] expected = { "shop","grope","seek","want","fumble","scour","grub","gather","seek out","leave no stone unturned","divine","hunt","quest after","feel","angle","go after","fish","browse","quest for","finger","dredge","look for","surf","drag","pursue", };
		//println(w.getSynonyms("search", "v"), true);
		setEqualMulti(expected, "getSynonyms", "search", "v");
	}

	@Test
	public void testGetSynonymsStringStringInt()
	{
		String[] expected = { "shop","grope","seek","want","fumble","scour","grub","gather","seek out","leave no stone unturned","divine","hunt","quest after","feel","angle","go after","fish","browse","quest for","finger","dredge","look for","surf","drag","pursue", };
		setContainsMulti(expected, "getSynonyms", "search", "v", 4);
	}

	@Test
	public void testGetAllSynonymsStringString()
	{
		String[] expected = { "check","pursue","experiment","re-explore","grub","research","peruse","prospect","mapquest","look for","comb","skim","nose","explore","glance over","look","cruise","poke","hunt","scan","candle","drag","seek","angle","browse","take stock", "x-ray","autopsy","fumble","want","cast around","quest after","rake","size up","examine","strip-search","divine","frisk","inspect","gather","horn in","beat about","run down","rifle","cast about","fish","google","dredge","raid","intrude","go","grope","rummage","scour","ransack","probe","scrutinise","survey","pry","scrutinize","shop","seek out","auscultate","finger","surf","go after","quest for","feel","leave no stone unturned", };
		//println(w.getAllSynonyms("search", "v"), true);
		setEqualMulti(expected, "getAllSynonyms", "search", "v");
	}

	@Test
	public void testGetAllSynonymsStringStringInt()
	{
		String[] expected = { "check","pursue","experiment","re-explore","grub","research","peruse","prospect","mapquest","look for","comb","skim","nose","explore","glance over","look","cruise","poke","hunt","scan","candle","drag","seek","angle","browse","take stock", "x-ray","autopsy","fumble","want","cast around","quest after","rake","size up","examine","strip-search","divine","frisk","inspect","gather","horn in","beat about","run down","rifle","cast about","fish","google","dredge","raid","intrude","go","grope","rummage","scour","ransack","probe","scrutinise","survey","pry","scrutinize","shop","seek out","auscultate","finger","surf","go after","quest for","feel","leave no stone unturned", };
		setContainsMulti(expected, "getAllSynonyms", "search", "v", 10);
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
	public void testGetSenseIdsStringString() //TODO More pos
	{

		//TODO cannot use setEqualMulti as it is int[]
		w.ignoreCompoundWords(false);
		w.ignoreUpperCaseWords(false);
		int[] expected = { 92124272, 910172934, 99919605, 93614083, 92989061, 92986962, 92130460, 9903174 };
		int[] result = w.getSenseIds("cat", "n");
		deepEqual(expected, result);

		int[] expected1 = { 913367788 };
		int[] result1 = w.getSenseIds("health insurance", "n");
		deepEqual(expected1, result1);

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
		
		int[] expected3 = { 92124272, 910172934, 99919605, 93614083, 92989061, 92986962, 92130460, 9903174 };
		int[] result3 = w.getSenseIds("cat", "n");
		deepEqual(expected3, result3);

		int[] expected4 = { 913367788 };
		int[] result4 = w.getSenseIds("health insurance", "n");
		deepEqual(expected4, result4);

		int[] expected5 = { };
		int[] result5 = w.getSenseIds("caz", "n");
		deepEqual(expected5, result5);

		try{
			w.getSenseIds("cat", "u");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetHypernymsStringString() //TODO More pos
	{

		String[] expected = { "root" };
		setEqualMulti(expected,"getHypernyms","carrot","n");

		String[] expected2 = { "canid" ,"canine"};    
		setEqualMulti(expected2,"getHypernyms","dog","n");

		String[] expected3 = { "bush" ,"shrub"};
		setEqualMulti(expected3,"getHypernyms","rose","n");


		String[] expected4 = { };
		String[] result4 = w.getHypernyms("root", "n");
		//println(result4);
		setEqual(expected4, result4);

		String[] expected5 = { };
		String[] result5 = w.getHypernyms("rootttt", "n");
		setEqual(expected5, result5);

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
		String[] expected = { "activeness", "activewear", "attractiveness", "inactiveness", "refractiveness", "unattractiveness" };
		String[] result = w.getContains("active", "n");
		//printArr(result);
		setEqual(expected, result);

		String[] expected2 = { "avalokiteshvara", "avalokitesvara", "blatherskite", "greenockite", "kitembilla", "melkite", "samarskite", "hell-kite" };
		String[] result2 = w.getContains("kite", "n");
		//println(result2);
		setEqual(expected2, result2);

		String[] expected4 = {};
		String[] result4 = w.getContains("kitxx", "n");
		//println(result4);
		setEqual(expected4, result4);

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
	public void testGetEndsWithStringStringInt()
	{
		String[] expected = { "collectable", "constable", "eatable", "inevitable"};
		String[] result = w.getEndsWith("table", "n", 4);
		//println(result);
		setEqual(expected, result);

		String[] expected2 = { "agriocharis", "capparis", "baccharis", "ascaris"};
		String[] result2 = w.getEndsWith("aris", "n", 4);
		//println(result2);
		setEqual(expected2, result2);

		String[] expected3 = { "cling", "fling", "ding", "bring"};
		String[] result3 = w.getEndsWith("ing", "v", 4);
		//println(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "isosceles", "intraspecies", "interspecies", "behind-the-scenes"};
		String[] result4 = w.getEndsWith("es", "a", 4);
		//println(result4);
		setEqual(expected4, result4);

		String[] expected5 = { "oftentimes", "betimes", "besides", "ofttimes"};
		String[] result5 = w.getEndsWith("es", "r", 4);
		//println(result5);
		setEqual(expected5, result5);

		String[] expected6 = {};
		String[] result6 = w.getEndsWith("qwes", "r", 4);
		//println(result5);
		setEqual(expected6, result6);

		String[] expected7 = {"brazil"};
		String[] result7 = w.getEndsWith("razil", "n", 40);
		//println(result7);
		setEqual(expected7, result7);

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
		String[] expected = { "collectable", "constable", "eatable", "inevitable", "notable", "portable", "potable", "roundtable", "stable", "timetable", "turntable", "vegetable", "worktable" };
		String[] result = w.getEndsWith("table", "n");
		//println(result);
		setEqual(expected, result);


		String[] expected2 = { "liparis", "baccharis", "trochlearis", "ascaris", "chamaecyparis", "agriocharis", "phalaris", "naris", "eleocharis", "hydrocharis", "paris", "polaris", "capparis" };
		String[] result2 = w.getEndsWith("aris", "n");
		//printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 = { "sightsing", "ring", "ping", "hamstring", "sling", "ding", "spring", "bring", "string", "wing", "wring", "ting", "sight-sing", "sing", "cling", "unstring", "sting", "swing", "fling" };
		String[] result3 = w.getEndsWith("ing", "v");
		//printArr(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "intraspecies", "offsides", "isosceles", "interspecies", "behind-the-scenes"};
		String[] result4 = w.getEndsWith("es", "a");
		//printArr(result4);
		setEqual(expected4, result4);

		String[] expected5 = { "besides", "betimes", "ofttimes", "sometimes", "unawares", "oftentimes" };
		String[] result5 = w.getEndsWith("es", "r");
		//printArr(result5);
		setEqual(expected5, result5);

		// it got the previous result
		String[] expected6 = {};
		String[] result6 = w.getEndsWith("qwes", "r");
		//printArr(result5);
		setEqual(expected6, result6);

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
	public void testGetStartsWithStringStringInt()
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
		String[] expected = { "wearable", "wearer", "weariness", "wearing"};
		String[] result = w.getStartsWith("wear", "n");
		//println(result);
		setEqual(expected, result);

		String[] expected2 = { "yodeling", "yodeller", "young", "yogacara", "youthfulness", "yolk", "yottabit", "youth", "yoke", "yokohama", "yogurt", "yobo", "yodh", "yodel", "yogi", "yob", "yorkshire", "yoga", "yobbo", "yowl", "younker", "yobibit", "yokel", "yoghurt", "youngness", "yore", "yottabyte", "yoghourt", "yorktown", "youngstown", "youngster", "yosemite", "yoruba", "yokuts", "york", "yobibyte" };
		String[] result2 = w.getStartsWith("yo", "n");
		//		printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 = { "yoke", "yowl", "yodel" };
		String[] result3 = w.getStartsWith("yo", "v");
		//		printArr(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "yogic", "young", "yokelish", "yon", "yonder", "yogistic", "younger", "youthful", "youngish" };
		String[] result4 = w.getStartsWith("yo", "a");
		//		printArr(result4);
		setEqual(expected4, result4);

		String[] expected5 = { "yonder", "yon", "youthfully" };
		String[] result5 = w.getStartsWith("yo", "r");
		//		printArr(result5);
		setEqual(expected5, result5);

		try{
			String[] result6 = w.getStartsWith("yo", "z");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetRegexMatchStringStringInt()
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
	public void testGetSoundsLikeStringStringInt()
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
		String[] expected = { "tabbouleh", "tableau", "tabooli", "tepal", "tiepolo", "tipple", "tivoli", "tubful", "tubule", "tupelo", "tuvalu" };
		String[] result = w.getSoundsLike("table", "n");
		//printArr(result);
		setEqual(expected, result);

		String[] expected2 ={ "pouf", "pep", "papaya", "phoebe", "peeve", "pave", "pavo", "poppy", "pupa", "papa", "peavy", "pipe", "pap", "pib", "paba", "poof", "pipa", "poop", "pob", "piaffe", "poove", "papio", "peavey", "papaw", "pub", "payoff", "puff", "peep", "pope", "papaia", "pip", "pawpaw", "pappa", "papua", "pup", "pouffe", "peba", "phobia", "piaf", "pop" };
		String[] result2 = w.getSoundsLike("puppy", "n");
		//		printArr(result2);
		setEqual(expected2, result2);

		String[] expected3 ={ "peeve", "puff", "pipe", "peep", "pup", "pip", "pop", "pave" };
		String[] result3 = w.getSoundsLike("puppy", "v");
		//		printArr(result3);
		setEqual(expected3, result3);

		String[] expected5 = { "pop", "puff", "puffy", "peppy" };
		String[] result5 = w.getSoundsLike("puppy", "a");
		//		printArr(result5);
		setEqual(expected5, result5);

		String[] expected6 = { "pop" };
		String[] result6 = w.getSoundsLike("puppy", "r");
		//		printArr(result6);
		setEqual(expected6, result6);

		String[] expected7 = { };
		String[] result7 = w.getSoundsLike("dacszff", "r");
		//		printArr(result7);
		setEqual(expected7, result7);
		try{
			w.getSoundsLike("table", "o");
			ok(false);
		}
		catch(Exception e){
			ok(e);
		}
	}

	@Test
	public void testGetWildcardMatchStringStringInt()
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
		String[] result = w.getWildcardMatch("t?le", "n"); // single-letter
		setEqual(expected, result);

		String[] expected2 = { "teasdale", "teakettle", "teasle"};  
		String[] result2 = w.getWildcardMatch("tea*le", "n"); // multiple-letter
		//println(result2);
		setEqual(expected2, result2);

		//TODO
	}

	@Test
	public void testFilterIntStringStringInt()
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
		/*String[] expected = { "tabbouleh", "tableau", "tabooli", "tepal", "tiepolo", "tipple", "tivoli", "tubful", "tubule", "tupelo", "tuvalu" };
		String[] result = w.filter(RiWordNet.SOUNDS_LIKE, "table", "n");
		//println(result);
		setEqual(expected, result);
		 */
		//setEqualMulti
		//		setEqual(expected14, result14);


		String[] expected10 = { "tablet" };
		String[] result10 = w.filter(RiWordNet.WILDCARD_MATCH, "table?", "n");
		//printArr(result10);
		setEqual(expected10, result10);


		String[] expected11 = { "table lifting", "constable", "eatable", "inevitable accident", "work table", "vegetable garden", "pool table", "julienne vegetable", "comfortableness", "cruciferous vegetable", "abatable nuisance", "table turning", "solanaceous vegetable", "uncomfortableness", "profitableness", "drawing table", "coffee table", "roundtable", "table salt", "lord's table", "acceptableness", "table-tennis table", "stable gear", "ping-pong table", "water table", "pier table", "stable companion", "inflatable cushion", "console table", "gateleg table", "actuarial table", "tablet-armed chair", "king arthur's round table", "vegetable marrow", "leafy vegetable", "root vegetable", "trestle table", "toilet table", "table mustard", "memorial tablet", "knight of the round table", "livery stable", "timetable", "vegetable sheep", "training table", "portable saw", "portable", "tablefork", "relocatable program", "plane table", "disreputableness", "card table", "charitableness", "raw vegetable", "tableau vivant", "parsons table", "tea table", "sleeping tablet", "vegetable wax", "potable", "vegetable oyster", "table linen", "table of contents", "unsuitableness", "snooker table", "drafting table", "notable", "mutableness", "coffee-table book", "inhospitableness", "conference table", "billiard table", "pedestal table", "italian vegetable marrow", "inevitable", "police constable", "vegetable sponge", "table-tennis bat", "table talk", "kitchen table", "augean stables", "adjustable wrench", "habitableness", "graduated table", "vegetable oil", "unacceptableness", "vegetable patch", "table d'hote", "tablet", "suitableness", "tip table", "collectable", "john constable", "pin table", "table knife", "mortality table", "dressing table", "intractableness", "round-table conference", "pingpong table", "chief constable", "communion table", "tablespoonful", "permutableness", "disreputable person", "tableau", "refectory table", "stablemate", "vegetable soup", "table game", "periodic table", "turntable", "immutableness", "file allocation table", "irritable bowel syndrome", "tableware", "ratables", "excitable area", "table service", "unprofitableness", "adjustable spanner", "stableman", "table napkin", "correlation table", "tablecloth", "operating table", "vegetable silk", "dining-room table", "unpalatableness", "stableness", "table wine", "table-mountain pine", "portable computer", "tip-top table", "tablemate", "cocktail table", "breakfast table", "japanese table pine", "dinner table", "drop-leaf table", "portable circular saw", "vegetable hummingbird", "stable", "table tapping", "stable factor", "council table", "tablespoon", "palatableness", "excitableness", "tilt-top table", "gaming table", "high table", "table-tennis racquet", "vegetable tallow", "worktable", "decision table", "table rapping", "hospitableness", "vegetable ivory", "stableboy", "table tipping", "table tennis", "tabletop", "inevitableness", "dining table", "vegetable", "writing table", "table tilting", "charitable trust", "unstableness", "round table", "vegetable matter", "statistical table", "table lamp", "tractableness", "tableland", "table mat", "table saw" };
		String[] result11 = w.filter(RiWordNet.WILDCARD_MATCH, "*table*", "n");
		//printArr(result11);
		setEqual(expected11, result11);

		w.ignoreCompoundWords(true);
		String[] expected15 = { "tablespoon", "potable", "inevitable", "suitableness", "constable", "charitableness", "collectable", "worktable", "unstableness", "stableboy", "tablemate", "inhospitableness", "inevitableness", "tableau", "tractableness", "intractableness", "comfortableness", "stable", "hospitableness", "unprofitableness", "tableware", "excitableness", "eatable", "roundtable", "tablefork", "turntable", "immutableness", "stableman", "unpalatableness", "stablemate", "timetable", "stableness", "unsuitableness", "tablespoonful", "tabletop", "vegetable", "habitableness", "tableland", "notable", "mutableness", "permutableness", "palatableness", "acceptableness", "tablecloth", "tablet", "disreputableness", "portable", "ratables", "unacceptableness", "profitableness", "uncomfortableness" };
		String[] result15 = w.filter(RiWordNet.WILDCARD_MATCH, "*table*", "n");
		//printArr(result15);
		setEqual(expected15, result15);

		String[] expected = { "tuvalu", "tabooli", "tableau", "tepal", "tivoli", "tubule", "tipple", "tupelo", "tiepolo", "tabbouleh", "t-bill", "tubful" };
		String[] result = w.filter(RiWordNet.SOUNDS_LIKE, "table", "n");
		//printArr(result);
		setEqual(expected, result);


		String[] expected2 = { "tabletop", "tablecloth", "tablemate", "tablefork", "tablet", "tablespoonful", "tableau", "tableland", "tableware", "tablespoon" };
		String[] result2 = w.filter(RiWordNet.STARTS_WITH, "table", "n");
		//printArr(result2);
		setEqual(expected2, result2);


		String[] expected3 = { "bleat" };
		String[] result3 = w.filter(RiWordNet.ANAGRAMS, "table", "n");
		//printArr(result3);
		setEqual(expected3, result3);


		String[] expected4 = { "unsuitableness", "disreputableness", "tableau", "inevitableness", "notable", "portable", "palatableness", "habitableness", "mutableness", "collectable", "tableware", "roundtable", "acceptableness", "hospitableness", "tableland", "tractableness", "inevitable", "tablemate", "worktable", "tablecloth", "constable", "suitableness", "tablespoon", "stableness", "tablet", "inhospitableness", "uncomfortableness", "timetable", "potable", "ratables", "unprofitableness", "stableboy", "turntable", "intractableness", "stableman", "tabletop", "stablemate", "profitableness", "comfortableness", "tablefork", "charitableness", "stable", "unacceptableness", "unstableness", "unpalatableness", "eatable", "tablespoonful", "permutableness", "immutableness", "vegetable", "excitableness" };
		String[] result4 = w.filter(RiWordNet.CONTAINS, "table", "n");
		//printArr(result4);
		setEqual(expected4, result4);


		String[] expected5 = {};
		String[] result5 = w.filter(RiWordNet.CONTAINS, "tableauu", "n");
		//printArr(result5);
		setEqual(expected5, result5);

		String[] expected6 = {"collectable"};
		String[] result6 = w.filter(RiWordNet.ENDS_WITH, "ctable", "n");
		//printArr(result6);
		setEqual(expected6, result6);

		String[] expected8 = { "stable", "notable", "turntable", "potable", "table", "roundtable", "constable", "portable", "eatable", "worktable", "inevitable", "collectable", "vegetable", "timetable" };
		String[] result8 = w.filter(RiWordNet.REGEX_MATCH, ".*table", "n");
		//printArr(result8);
		setEqual(expected8, result8);

		String[] expected9 ={ "vegetable", "conductance", "comestible", "constable", "collectivism", "reflectance", "connective", "colette", "collapse", "telltale", "college", "collotype", "clientele", "eatable", "portable", "mollycoddle", "volleyball", "collage", "conjecture", "follicle", "sociable", "collectible", "worktable", "notable", "collet", "collect", "collembola", "vocable", "collywobbles", "roundtable", "molecule", "combustible", "collocalia", "collectivist", "collecting", "collective", "collegian", "syllable", "decolletage", "folktale", "colpocele", "corrective", "potable", "clientage", "collector", "collection", "timetable", "colleague", "conventicle", "spectacle", "convertible" };
		String[] result9 = w.filter(RiWordNet.SIMILAR_TO, "collectable", "n");
		//printArr(result9);
		setEqual(expected9, result9);

		String[] expected12 = { };
		String[] result12 = w.filter(RiWordNet.CONTAINS, "nahsuchword", "n");
		//printArr(result12);
		setEqual(expected12, result12);

		String[] expected14 = { "notable", "intractableness", "charitableness", "tableland", "disreputableness", "immutableness", "excitableness", "timetable", "mutableness", "stablemate", "stableness", "palatableness", "collectable", "permutableness", "tablespoon", "stableboy", "tablemate", "inevitable", "stableman", "uncomfortableness", "unacceptableness", "tablefork", "tableware", "acceptableness", "unpalatableness", "stable", "turntable", "portable", "inevitableness", "tablecloth", "constable", "worktable", "comfortableness", "tractableness", "profitableness", "tablet", "suitableness", "unprofitableness", "unstableness", "tablespoonful", "vegetable", "hospitableness", "unsuitableness", "potable", "ratables", "roundtable", "tableau", "habitableness", "inhospitableness", "eatable", "tabletop" };
		String[] result14 = w.filter(RiWordNet.CONTAINS, "table", "n");
		//printArr(result14);
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
		println(result13,true);
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

		w.ignoreCompoundWords(true);
		String expected4 = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result4 = w.getGloss(92124272);
		//println(result);
		assertEquals(expected4, result4);

		String expected5 = "try to locate or discover, or try to establish the existence of; "+'"'+"The police are searching for clues"+'"'+"; "+'"'+"They are searching for the missing man in the entire county"+'"';
		String result5 = w.getGloss(81318273);
		//println(result2);
		assertEquals(expected5, result5);

		String expected6 = "a whip with nine knotted cords; "+'"'+"British sailors feared the cat"+'"';
		String result6 = w.getGloss(92989061);
		//println(result6);
		assertEquals(expected6, result6);

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

		expected4 = new String[] { "Don't run--you'll be out of breath", "The children ran to the store" };
		//printArr(w.getExamples("run", "v"));
		setEqual(expected4, w.getExamples("run","v")); 

	

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

	}

	@Test
	public void testGetExamplesInt()
	{
		String[] expected = {"the tires showed uneven wear"};
		String[] result = w.getExamples(914586275);
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetAllExamples()
	{
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
		
	}

	@Test
	public void testGetCommonParents() {

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
