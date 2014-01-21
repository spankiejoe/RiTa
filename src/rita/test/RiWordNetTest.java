package rita.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static rita.support.QUnitStubs.*;

import java.util.*;

import org.junit.Test;

import rita.RiWordNet;
import rita.wordnet.WordnetPos;
import rita.wordnet.jwnl.data.Synset;

/*
 * Compare results to: http://wordnetweb.princeton.edu/perl/webwn
 * 
 * (ALEX)
 * TODO: Add all negative cases (where there is no match in db)
 * TODO: Make sure all methods return non-deterministic arrays
 * TODO: Documentation (JSON) for all methods 
 */
public class RiWordNetTest
{
  static RiWordNet w;
  
  static { 
    
    SILENT = false;
    long ts = System.currentTimeMillis();
    w = new RiWordNet("/WordNet-3.1");
    System.out.println("[INFO] Loaded in "+(System.currentTimeMillis()-ts)+"ms");
  }
  
  @Test
  public void testRiWordnetString() {
    ok(w); // see above
    ok(w.exists("hello"));
  }

  @Test
  public void testGetSenseIdsStringString()
  {
    int[] expected = { 92124272, 910172934, 99919605, 93614083, 92989061, 92986962, 92130460, 9903174 };
    int[] result = w.getSenseIds("cat", "n");
    //for (int i = 0; i < result.length; i++)
      //print(result[i]+", ");
    //println(result);
    deepEqual(expected, result);
  }
  
  @Test
  public void testGetHypernymsStringString()
  {
	  String[] expected = { "root" };
	  String[] result = w.getHypernyms("carrot", "n");
	  //println(result);
	  deepEqual(expected, result);
	  //ok(1);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetAnagramsStringStringInt()
  {
	  //Expteced 1 result? without the original word?
	  String[] expected = { "night", "thing" };
	  String[] result = w.getAnagrams("night", "n", 2);
	  //println(result);
	  deepEqual(expected, result);
	  String[] expected2 = { "night"};
	  String[] result2 = w.getAnagrams("night", "n", 1);
	  //println(result2);
	  deepEqual(expected2, result2);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetAnagramsStringString()
  {
    String[] expected = { "night", "thing" };
    String[] result = w.getAnagrams("night", "n");
    //println(result);
    deepEqual(expected, result);
    String[] expected2 = { "dog", "god" };
    String[] result2 = w.getAnagrams("dog", "n");
    //println(result2);
    deepEqual(expected2, result2);

    String[] expected3 = { "bleat", "table" };
    String[] result3 = w.getAnagrams("table", "n");
    //println(result2);
    deepEqual(expected2, result2);
  }

  @Test
  public void testGetContainsStringStringInt()
  {
	  String[] expected = { "activeness", "activewear", "attractiveness" };
	  String[] result = w.getContains("active", "n", 3);
	  //println(result);
	  deepEqual(expected, result);
	  String[] expected2 = { "avalokiteshvara", "avalokitesvara" };
	  String[] result2 = w.getContains("kite", "n", 2);
	  //printArr(result2);
	  deepEqual(expected2, result2);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetContainsStringString()
  {
	  String[] expected = { "activeness", "activewear", "attractiveness", "inactiveness", "refractiveness", "unattractiveness" };
	  String[] result = w.getContains("active", "n");
	  //println(result);
	  deepEqual(expected, result);
	  String[] expected2 = { "avalokiteshvara", "avalokitesvara", "blatherskite", "greenockite", "kitembilla", "melkite", "samarskite" };
	  String[] result2 = w.getContains("kite", "n");
	  //println(result2);
	  deepEqual(expected2, result2);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetEndsWithStringStringInt()
  {
	  String[] expected = { "collectable", "constable", "eatable", "inevitable"};
	  String[] result = w.getEndsWith("table", "n", 4);
	  //println(result);
	  deepEqual(expected, result);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetEndsWithStringString()
  {
	  String[] expected = { "collectable", "constable", "eatable", "inevitable", "notable", "portable", "potable", "roundtable", "stable", "timetable", "turntable", "vegetable", "worktable" };
	  String[] result = w.getEndsWith("table", "n");
	  //println(result);
	  deepEqual(expected, result);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetStartsWithStringStringInt()
  {
	  String[] expected = { "turnabout", "turnaround", "turnbuckle", "turncoat", "turncock"};
	  String[] result = w.getStartsWith("turn", "n", 5);
	  //println(result);
	  deepEqual(expected, result);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetStartsWithStringString()
  {
	  String[] expected = { "wearable", "wearer", "weariness", "wearing"};
	  String[] result = w.getStartsWith("wear", "n");
	  //println(result);
	  deepEqual(expected, result);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetRegexMatchStringStringInt()
  {
	  String[] expected = { "collectable", "constable", "eatable", "inevitable"};
	  String[] result = w.getRegexMatch(".*table", "n", 4);
	  //println(result);
	  deepEqual(expected, result);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetRegexMatchStringString()
  {
	  String[] expected = { "collectable", "constable", "eatable", "inevitable", "notable", "portable", "potable", "roundtable", "stable", "table", "timetable", "turntable", "vegetable", "worktable" };
	  String[] result = w.getRegexMatch(".*table", "n");
	  //println(result);
	  deepEqual(expected, result);
	  //fail("Not yet implemented");
  }

  @Test
  public void testGetSoundsLikeStringStringInt()
  {
	  String[] expected = { "tabbouleh", "tableau", "tabooli"};
	  String[] result = w.getSoundsLike("table", "n", 3);
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetSoundsLikeStringString()
  {
	  String[] expected = { "tabbouleh", "tableau", "tabooli", "tepal", "tiepolo", "tipple", "tivoli", "tubful", "tubule", "tupelo", "tuvalu" };
	  String[] result = w.getSoundsLike("table", "n");
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetWildcardMatchStringStringInt()
  {
	  String[] expected = { "tale", "tile"};
	  String[] result = w.getWildcardMatch("t?le", "n", 2);
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetWildcardMatchStringString()
  {
	  String[] expected = { "tale", "tile", "tole"};
	  String[] result = w.getWildcardMatch("t?le", "n");
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testFilterIntStringPOSInt()
  {
	  String[] expected = { "tabbouleh", "tableau", "tabooli"};
	  String[] result = w.filter(RiWordNet.SOUNDS_LIKE, "table", WordnetPos.getPos("n"), 3);
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testFilterIntStringPOS()
  {
	  String[] expected = { "tabbouleh", "tableau", "tabooli", "tepal", "tiepolo", "tipple", "tivoli", "tubful", "tubule", "tupelo", "tuvalu" };
	  String[] result = w.filter(RiWordNet.SOUNDS_LIKE, "table", WordnetPos.getPos("n"));
	  //println(result);
	  deepEqual(expected, result);
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
	  String[] expected = {"impairment resulting from long use; "+'"'+"the tires showed uneven wear"+'"', "a covering designed to be worn on a person's body", 
			  "the act of having on your person as a covering or adornment; "+'"'+"she bought it for everyday wear"+'"'};
	  String[] result = w.getAllGlosses("wear", "n");
	  //println(result);
	  deepEqual(expected, result);
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
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAnyExample()
  {
	  String[] expected = {"The police are searching for clues", "They are searching for the missing man in the entire county",
			  "the students had to research the history of the Second World War for their history project", "He searched for information on his relatives on the web",
			  "The police searched the suspect", "We searched the whole house for the missing keys"};
	  String result = w.getAnyExample("search", "v");
	  //println(result);
	  //ssertTrue(Arrays.asList(expected).contains(result));
	  setContains(expected, result);
	  //deepEqual(expected, result);
  }

  @Test
  public void testGetExamplesInt()
  {
	  String[] expected = {"the tires showed uneven wear"};
	  String[] result = w.getExamples(914586275);
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllExamples()
  {
	  String[] expected = {"The police are searching for clues", "They are searching for the missing man in the entire county",
			  "the students had to research the history of the Second World War for their history project", "He searched for information on his relatives on the web",
			  "The police searched the suspect", "We searched the whole house for the missing keys"};
	  String[] result = w.getAllExamples("search", "v");
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetSynonymsIntInt()
  {
	  String[] expected = {"dowse", "surf", "scour", "shell", "angle", "drag", "fumble", "dredge", "want", "fish", "feel", "pursue", "search", "divine", 
			  "scrabble", "shop", "grope", "finger", "browse", "gather" ,"antique", "grub", "seek", "hunt"};
	  String[] result = w.getSynonyms(81318273, 4);
	  //println(w.getSenseIds("search", "v"));
	  //print(result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	  setContains(expected, result);
  }

  @Test
  public void testGetSynonymsInt()
  {
	  String[] expected = {"dowse", "surf", "scour", "shell", "angle", "drag", "fumble", "dredge", "want", "fish", "feel", "pursue", "search", "divine", 
			  "scrabble", "shop", "grope", "finger", "browse", "gather" ,"antique", "grub", "seek", "hunt"};
	  String[] result = w.getSynonyms(81318273);
	  //println(w.getSenseIds("search", "v"));
	  //println(result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
    setEqual(expected, result);
  }

  @Test
  public void testGetSynonymsStringStringInt()
  {
	  String[] expected = { "surf", "seek", "divine", "fish", "pursue", "browse", "feel", "scour", "want", "drag", "gather", "shop", "angle", "grope", "hunt", "dredge", "grub", "fumble", "finger" };
	  String[] result = w.getSynonyms("search", "v");
	  //println(w.getSenseIds("search", "v"));
	  //printArr(result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
	  setContains(expected, result);
    //deepEqual(expected, result);
  }

  @Test
  public void testGetSynonymsStringString()
  {
	  String[] expected = { "seek", "fish", "want", "surf", "pursue", "browse", "drag", "angle", "feel", "finger", "hunt", "dredge", "divine", "scour", "shop", "grope", "gather", "fumble", "grub" };
	  String[] result = w.getSynonyms("search", "v");
	  //println(w.getSenseIds("search", "v"));
	  //printArr(result);
	  setEqual(expected, result);
  }

  @Test
  public void testGetAllSynonymsStringStringInt()
  {
    String[] expected = { "gather", "divine", "frisk", "check", "look", "examine", "survey", "probe", "poke", "prospect", "research", "scour", "pursue", "skim", "autopsy", "intrude", "rummage", "candle", "fish", "seek", "finger", "scrutinise", "go", "ransack", "auscultate", "grope", "rifle", "angle", "mapquest", "want", "cruise", "comb", "rake", "raid", "drag", "hunt", "scrutinize", "scan", "pry", "experiment", "inspect", "nose", "surf", "feel", "grub", "peruse", "browse", "dredge", "shop", "google", "explore", "fumble" };
	  String[] result = w.getAllSynonyms("search", "v", 10);
	  //println(w.getSenseIds("search", "v"));
	  //printArr(result);
	  setContains(expected, result);
  }

  @Test
  public void testGetAllSynonymsStringString()
  {
	  String[] expected = { "gather", "divine", "frisk", "check", "look", "examine", "survey", "probe", "poke", "prospect", "research", "scour", "pursue", "skim", "autopsy", "intrude", "rummage", "candle", "fish", "seek", "finger", "scrutinise", "go", "ransack", "auscultate", "grope", "rifle", "angle", "mapquest", "want", "cruise", "comb", "rake", "raid", "drag", "hunt", "scrutinize", "scan", "pry", "experiment", "inspect", "nose", "surf", "feel", "grub", "peruse", "browse", "dredge", "shop", "google", "explore", "fumble" };
	  String[] result = w.getAllSynonyms("search", "v");
	  //println(w.getSenseIds("search", "v"));
	  //println(result);
    setEqual(expected, result);
  }

  @Test
  public void testGetCommonParents(){
	  String[] expected = {"clothing", "vesture", "wear", "wearable", "habiliment"};
	  String[] result = w.getCommonParents("activewear", "beachwear", "n");
	  //println(w.getSenseIds("search", "v"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetCommonParent()
  {
	  int expected = 3055525; // offset for expected synset
	  Synset result = w.getCommonParent(94292941, 92817909);
	  ///println(result.getOffset());
    equal(expected, result.getOffset());
  }

  @Test
  public void testGetSynsetStringString()
  {
	  String[] expected = {"sportswear"};
	  String[] result = w.getSynset("activewear", "n");
	  //println(w.getSenseIds("search", "v"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetSynsetStringStringBoolean()
  {
	  String[] expected = {"sportswear", "activewear"};
	  String[] result = w.getSynset("activewear", "n", true);
	  //println(w.getSenseIds("search", "v"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetSynsetInt()
  {
	  String[] expected = {"sportswear", "activewear"};
	  String[] result = w.getSynset(94292941);
	  //println(w.getSenseIds("activewear", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetAllSynsets()
  {
	  String[] expected = {"sportswear"};
	  String[] result = w.getAllSynsets("activewear", "n");
	  //println(w.getSenseIds("activewear", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetSenseCount()
  {
	  int expected = 6;
	  int result = w.getSenseCount("table", "n");
	  //println(w.getSenseIds("activewear", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }
  
  @Test
  public void testGetAntonymsStringString()
  {
    deepEqual(w.getAntonyms("day","n"), null);
    deepEqual(w.getAntonyms("night","n"), new String[]{"day"});
    
    deepEqual(w.getAntonyms("left", "a"), new String[]{"right"});
    deepEqual(w.getAntonyms("right", "a"), new String[]{"left"});
    
    deepEqual(w.getAntonyms("full", "a"), new String[]{"empty"});
    deepEqual(w.getAntonyms("empty", "a"), new String[]{"full"});
    
    deepEqual(w.getAntonyms("quickly", "r"), new String[]{"slowly"});
    deepEqual(w.getAntonyms("slowly", "r"), new String[]{"quickly"});
  }

  @Test
  public void testGetAntonymsInt()
  {
	  String[] expected = {"day"};
	  String[] result = w.getAntonyms(915192074);
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetAllAntonyms()
  {
	  String[] expected = {"day"};
	  String[] result = w.getAllAntonyms("night", "n");
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetHypernymsInt()
  {
	  String[] expected = {"period"};
	  String[] result = w.getHypernyms(915192074);
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetAllHypernyms()
  {
	  String[] expected = {"period", "dark", "darkness", "twilight", "dusk", "gloaming", "gloam", "nightfall", "evenfall", "fall", "crepuscule", "crepuscle"};
	  String[] result = w.getAllHypernyms("night", "n");
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetHypernymTree()
  {
	  String[] expected = {"night:nighttime:dark", "period", "measure:quantity:amount", "abstraction", "entity"};
	  String[] result = null;
		//try {
			result = w.getHypernymTree(915192074);
	/*	} catch (JWNLException e) {
			// TODO Auto-generated catch block
	 	e.printStackTrace();
		}*/
			
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetHyponymsStringString()
  {
	  String[] expected = {"weeknight"};
	  String[] result = w.getHyponyms("night", "n");
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetHyponymsInt()
  {
	  String[] expected = {"weeknight"};
	  String[] result = w.getHyponyms(915192074);
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetAllHyponyms()
  {
	  String[] expected = {"weeknight"};
	  String[] result = w.getAllHyponyms("night", "n");
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
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
	  deepEqual(true, w.isAdjective("big"));
	  deepEqual(true, w.isAdjective("bigger"));
	  deepEqual(true, w.isAdjective("biggest"));
	  deepEqual(true, w.isAdjective("old"));
	  deepEqual(true, w.isAdjective("elder"));
	  deepEqual(true, w.isAdjective("eldest"));
	  deepEqual(false, w.isAdjective("slowly"));
	  deepEqual(false, w.isAdjective("quite"));
  }

  @Test
  public void testIsAdverb()
  {
	  deepEqual(false, w.isAdverb("mary"));
	  deepEqual(true, w.isAdverb("slowly"));
	  deepEqual(true, w.isAdverb("quite"));
	  deepEqual(true, w.isAdverb("together"));
  }

  @Test
  public void testIsVerb()
  {
	  deepEqual(false, w.isVerb("mary"));
	  deepEqual(true, w.isVerb("run"));
	  deepEqual(true, w.isVerb("walk"));
	  deepEqual(true, w.isVerb("sing"));
  }

  @Test
  public void testIsNoun()
  {
	  deepEqual(true, w.isNoun("mary"));
	  deepEqual(true, w.isNoun("run"));
	  deepEqual(false, w.isNoun("slowly"));
	  deepEqual(false, w.isNoun("together"));
  }

  @Test
  public void testGetStems()
  {
	  String[] expected = {"produce"};
	  String[] result = w.getStems("produced", "v");
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testIsStem()
  {
	  deepEqual(false, w.isStem("waiting", "v"));
	  deepEqual(true, w.isStem("wait", "n"));
  }

  @Test
  public void testExists()
  {
	  deepEqual(true, w.exists("abc"));
	  deepEqual(true, w.exists("wait"));
	  deepEqual(false, w.exists("tesxx"));
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
  public void testLookupIndexWord()
  {
	//fail("Not yet implemented");
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
	  deepEqual("n", w.getPos(915297015));
	  deepEqual("n", w.getPos(91065863));
	  deepEqual("v", w.getPos(82644022));
	  deepEqual("v", w.getPos(82647547));
	  deepEqual("v", w.getPos(8721987));
	  deepEqual("v", w.getPos(82418420));
  }

  @Test
  public void testGetPosStr()
  {
	  String expected = "vn";
	  String result = w.getPosStr("wait");
	  //println(w.getSenseIds("night", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  //assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(result)));
  }

  @Test
  public void testGetBestPos()
  {
	  deepEqual("v", w.getBestPos("wait"));
	  deepEqual("n", w.getBestPos("dog"));
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
	  deepEqual(expected, result);
  }

  @Test
  public void testGetMeronymsStringString()
  {
	  String[] expected = {"row", "column"};
	  String[] result = w.getMeronyms("table", "n");
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetMeronymsInt()
  {
	  String[] expected = {"row", "column"};
	  String[] result = w.getMeronyms(98283156);
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllMeronyms()
  {
	  String[] expected = {"row", "column", "leg", "tabletop", "tableware"};
	  String[] result = w.getAllMeronyms("table", "n");
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetHolonymsStringString()
  {
	  String[] expected = {"body", "homo", "man", "human"};
	  String[] result = w.getHolonyms("arm", "n");
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetHolonymsInt()
  {
	  String[] expected = {"body", "homo", "man", "human"};
	  String[] result = w.getHolonyms(95571403);
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllHolonyms()
  {
	  String[] expected = {"body", "homo", "man", "human", "weaponry", "arms", "munition", "armchair", "garment"};
	  String[] result = w.getAllHolonyms("arm", "n");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetCoordinatesStringString()
  {
	  String[] expected = {"hindlimb", "forelimb", "flipper", "leg", "crus", "thigh", "cubitus", "forearm"};
	  String[] result = w.getCoordinates("arm", "n");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetCoordinatesInt()
  {
	  String[] expected = {"hindlimb", "forelimb", "flipper", "leg", "crus", "thigh", "arm", "cubitus", "forearm"};
	  String[] result = w.getCoordinates(95571403);
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllCoordinates()
  {
	  String[] expected = {"arity", "pagination", "folio", "paging", "decimal", "constant", "cardinality", "count", "factor", "prime", "score",
			  "record", "ordinal", "no.", "cardinal", "base", "radix", "quota", "linage", "lineage", "integer", "addend", "augend", "minuend", "subtrahend", "remainder",
			  "difference", "imaginary", "square", "cube", "biquadrate", "biquadratic", "quartic", "root", "dividend", "divisor", "quotient", "multiplier", "multiplicand"};
	  String[] result = w.getAllCoordinates("coordinate", "n");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
	  
	  String[] expected2 = {"stretch", "attend", "occupy", "fill", "populate", "dwell", "inhabit", "reach", "touch", "run", "go",
			  "pass", "lead", "extend", "cover", "continue", "lie", "sit", "face", "straddle", "follow", "rest", "belong", "come", "know", "experience", "suffer",
			  "endure", "meet", "feel", "enjoy", "witness", "find", "see"};
	  String[] result2 = w.getAllCoordinates("live", "v");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result2);
	  deepEqual(expected2, result2);
  }

  @Test
  public void testGetVerbGroupStringString()
  {
	  String[] expected = {"populate", "dwell", "inhabit"};
	  String[] result = w.getVerbGroup("live", "v");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetVerbGroupInt()
  {
	  String[] expected = {"populate", "dwell", "live", "inhabit"};
	  String[] result = w.getVerbGroup(82655932);
	  //println(w.getSenseIds("live", "v"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllVerbGroups()
  {
	  String[] expected = {"populate", "dwell", "inhabit", "survive", "last", "go", "endure", "be", "exist", "subsist", "know", "experience"};
	  String[] result = w.getAllVerbGroups("live", "v");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetDerivedTermsStringString()
  {
	  String[] expected = {"jubilant", "blithe", "gay", "mirthful", "merry", "happy"};
	  String[] result = w.getDerivedTerms("happily", "r");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetDerivedTermsInt()
  {
	  String[] expected = {"jubilant", "blithe", "gay", "mirthful", "merry", "happy"};
	  String[] result = w.getDerivedTerms(650835);
	  //println(w.getSenseIds("happily", "r"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllDerivedTerms()
  {
	  String[] expected = {"jubilant", "blithe", "gay", "mirthful", "merry", "happy"};
	  String[] result = w.getAllDerivedTerms("happily", "r");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAlsoSeesStringString()
  {
	  String[] expected = {"cheerful", "contented", "content", "glad", "elated", "euphoric", "felicitous", "joyful", "joyous"};
	  String[] result = w.getAlsoSees("happy", "a");
	  //println(w.getSenseIds("arm", "n"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAlsoSeesInt()
  {
	  String[] expected = {"cheerful", "contented", "content", "glad", "elated", "euphoric", "felicitous", "joyful", "joyous"};
	  String[] result = w.getAlsoSees(71151786);
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllAlsoSees()
  {
	  String[] expected = {"cheerful", "contented", "content", "glad", "elated", "euphoric", "felicitous", "joyful", "joyous"};
	  String[] result = w.getAllAlsoSees("happy", "a");
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetNominalizationsStringString()
  {
	  String[] expected = {"happiness"};
	  String[] result = w.getNominalizations("happy", "a");
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetNominalizationsInt()
  {
	  String[] expected = {"happiness"};
	  String[] result = w.getNominalizations(71151786);
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllNominalizations()
  {
	  String[] expected = {"happiness", "felicitousness", "felicity"};
	  String[] result = w.getAllNominalizations("happy", "a");
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetSimilarStringString()
  {
	  String[] expected = {"blessed", "blissful", "bright", "golden", "halcyon", "prosperous", "laughing", "riant"};
	  String[] result = w.getSimilar("happy", "a");
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetSimilarInt()
  {
	  String[] expected = {"blessed", "blissful", "bright", "golden", "halcyon", "prosperous", "laughing", "riant"};
	  String[] result = w.getSimilar(71151786);
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testGetAllSimilar()
  {
	  String[] expected = {"blessed", "blissful", "bright", "golden", "halcyon", "prosperous", "laughing", "riant", "fortunate", "willing", "felicitous"};
	  String[] result = w.getAllSimilar("happy", "a");
	  //println(w.getSenseIds("happy", "a"));
	  //println(result);
	  deepEqual(expected, result);
  }

  @Test
  public void testIsIgnoringCompoundWords()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testIgnoreCompoundWords()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testIsIgnoringUpperCaseWords()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testIgnoreUpperCaseWords()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testIterator()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testOrFilterIntArrayStringArrayPOSInt()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testOrFilterIntArrayStringArrayPOS()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testDispose()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testSetWordnetHome()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testGetSenseIdsIndexWord()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testPtnlToStrings()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testIsCompound()
  {
    fail("Not yet implemented");
  }

  /*
   * @After public void tearDown() { w = null; }
   */

}
