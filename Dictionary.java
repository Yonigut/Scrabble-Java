

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.TreeSet;

public class Dictionary {
		
		Set <String> words;
	    

	    /**
	     * Constructs a Dictionary from words provided by a TokenScanner.
	     * <p>
	     * A word is any sequence of letters (see Character.isLetter) or apostrophe characters. All
	     * non-word tokens provided by the TokenScanner should be ignored.
	     * <p>
	     *
	     * @param ts Sequence of words to store in this Dictionary
	     * @throws IOException 
	     * @throws IllegalArgumentException If the provided token scanner is null
	     */
	    public Dictionary(TokenScanner ts) throws IOException {
	    	if (ts == null) {
	    		throw new IllegalArgumentException("TokenScanner is null");
	    	}
	    	else {
	    		words = new TreeSet<String>();
	    		while (ts.hasNext()) {
	    			String current = ts.next();
	    			if (TokenScanner.isWord(current)) {
	        			words.add(current.toLowerCase());
	    			}
	    		}
	    	}
	    }

	    /**
	     * Returns an instance of a Dictionary constructed from words from a file.
	     *
	     * @param filename Location of file from which to read words
	     * @return A Dictionary instance with words from the argued file
	     * @throws FileNotFoundException If the file does not exist
	     * @throws IOException If error while reading
	     */
	    public static Dictionary make(String filename) throws IOException {
	        Reader r = new FileReader(filename);
	        Dictionary d = new Dictionary(new TokenScanner(r));
	        r.close();
	        return d;
	     }

	    /**
	     * Returns the number of unique words in this Dictionary. This count is case insensitive: 
	     * if
	     * both "DOGS" and "dogs" appeared in the input file, it must only be counted once in the 
	     * sum.
	     * 
	     * @return Number of unique words in the dictionary
	     */
	    public int getNumWords() {
	        return words.size();
	    }

	    /**
	     * Tests whether the argued word is present in this Dictionary. Note that strings containing
	     * nonword characters (such as spaces) will not be in the Dictionary. If the word is not 
	     * in the
	     * Dictionary or if the word is null, this method returns false.
	     * 
	     * <p>
	     * This check should be case insensitive. For example, if the Dictionary contains 
	     * "dog" or "dOg"
	     * then isWord("DOG") should return true.
	     * <p>
	     * Calling this method must not re-open or re-read the source file.
	     *
	     * @param word A String token to check. Assume any leading or trailing whitespace has 
	     * already
	     *             been removed.
	     * @return Whether the word is in the dictionary
	     */
	    public boolean isWord(String word) {
	    	if (word == null || !TokenScanner.isWord(word)) {
	    		return false;
	    	}
	        return words.contains(word.toLowerCase());
	    }
	}

