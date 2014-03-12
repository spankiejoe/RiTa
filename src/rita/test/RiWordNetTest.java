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
    String[] expected = { "scout","grub","antique","comparison-shop","hunt","drag","shop","dowse","browse","seek","scrabble","quest after","search","fish","pursue","angle","shell","want","surf","seek out","window-shop","look for","divine","grope","leave no stone unturned","go after","gather","grope for","quest for","feel","fumble","dredge","finger" };
    //println(w.getSynonyms(81318273), true);
    setEqualMulti(expected, "getSynonyms", 81318273); 
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
	}

	@Test
	public void testGetHypernymsStringString()
	{
    String[] expected2 = { "canid" ,"canine"};
    String[] result2 = w.getHypernyms("dog", "n");
    //println(result2);
    setEqual(expected2, result2);
    
		String[] expected = { "root" };
		String[] result = w.getHypernyms("carrot", "n");
		//println(result);
		setEqual(expected, result);


		String[] expected3 = { "bush" ,"shrub"};
		String[] result3 = w.getHypernyms("rose", "n");
		setEqual(expected3, result3);
		//println(result3);

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
		String[] result = w.getContains("active", "n", 3);
		//println(result);
		setEqual(expected, result);

		String[] expected2 = { "avalokiteshvara", "avalokitesvara" };
		String[] result2 = w.getContains("kite", "n", 2);
		//printArr(result2);
		setEqual(expected2, result2);
		//fail("Not yet implemented");

		w.ignoreCompoundWords(false);
		String[] expected3 = { "active agent", "active air defense", "active application" };
		String[] result3 = w.getContains("active", "n", 3);
		//println(result3);
		setEqual(expected3, result3);

		String[] expected4 = { "avalokiteshvara", "avalokitesvara" };
		String[] result4 = w.getContains("kite", "n", 2);
		setEqual(expected4, result4);

		String[] expected5 = { };
		String[] result5 = w.getContains("kittxx", "n", 2);
		//println(result5);
		setEqual(expected5, result5);

		String[] expected7 = { "hell-kite","avalokitesvara", "black kite", "blatherskite", "avalokiteshvara", "white-tailed kite", "sport kite", "stunt kite", "kite balloon", "kite tail", "box kite", "hell-kite", "samarskite", "kitembilla", "melkite", "greenockite", "swallow-tailed kite" };
		String[] result7 = w.getContains("kite", "n", 2000);
		//printArr(result7);
		setEqual(expected7, result7);

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
		String[] expected = { "collectable", "constable", "eatable", "inevitable"};
		String[] result = w.getRegexMatch(".*table", "n", 4);
		//println(result);
		setEqual(expected, result);
		//TODO later

	}

	@Test
	public void testGetRegexMatchStringString()
	{
		String[] expected = { "collectable", "constable", "eatable", "inevitable", "notable", "portable", "potable", "roundtable", "stable", "table", "timetable", "turntable", "vegetable", "worktable" };
		String[] result = w.getRegexMatch(".*table", "n");
		//println(result);
		setEqual(expected, result);
		//fail("Not yet implemented");

		//TODO later
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
		String[] expected = { "tabbouleh", "tableau", "tabooli", "tepal", "tiepolo", "tipple", "tivoli", "tubful", "tubule", "tupelo", "tuvalu" };
		String[] result = w.filter(RiWordNet.SOUNDS_LIKE, "table", "n");
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetGlossStringString()
	{
		String expected = "impairment resulting from long use; "+'"'+"the tires showed uneven wear"+'"';
		String result = w.getGloss("wear", "n");
		//println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testGetAllGlosses()
	{
		String[] expected = {"impairment resulting from long use; "+
				'"'+"the tires showed uneven wear"+'"', 
				"a covering designed to be worn on a person's body", 
				"the act of having on your person as a covering or adornment; "+
						'"'+"she bought it for everyday wear"+'"'
		};
		String[] result = w.getAllGlosses("wear", "n");
		//println(result);
		setEqual(expected, result);
	}

	@Test
	public void testGetGlossInt()
	{
		String expected = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result = w.getGloss(92124272);
		//println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testGetDescriptionInt()
	{
		String expected = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result = w.getDescription(92124272);
		//println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testGetDescriptionStringString()
	{
		String expected = "feline mammal usually having thick soft fur and no ability to roar: domestic cats; wildcats";
		String result = w.getDescription("cat", "n");
		//println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testGetExamplesCharSequenceCharSequence()
	{
		String[] expected = {"the tires showed uneven wear"};
		String[] result = w.getExamples("wear", "n");
		//println(result);
		setEqual(expected, result);
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
	}

	@Test
	public void testGetCommonParents() {

		String[] expected = {"clothing", "vesture", "wear", "wearable", "habiliment"};
		String[] result = w.getCommonParents("activewear", "beachwear", "n");
		setEqual(expected, result);
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

    String[] input3 = {"back", "space", "back space", "back_space"};
    boolean[] expected3 = {false, false, true, true};
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
    catch (Exception e)
    {
      e.printStackTrace();
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
