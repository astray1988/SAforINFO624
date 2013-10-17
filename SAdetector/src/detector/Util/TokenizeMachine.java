/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package detector.Util;

/**
 *
 * @author CDVI
 */
import java.io.*;
import java.net.*;
import java.util.*;


public class TokenizeMachine
{
	public static String Default_StopWord =
		"a about add ago after all also am an and another any are as at be " +
	    "because been before being between big both but by came can come " +
	    "could did do does doing due each else end even far few for from get got had " +
	    "has have he her here him himself his how if in into is it its " +
	    "just i let lie like low make many me might more most much must " +
	    "my never no nor not now of off old on only or other our out over " +
	    "per pre put please re said say same see she should since so some still such " +
	    "take than that the their them then there these they this those " +
	    "through to too under up use ve very via want was way we well were " +
	    "what when where which while who will with would wow yes yet you your";
	
	public static String NonsenseWord = "&nbsp; &quot; 1 2 3 4 5 6 7 8 9 0 "+
		"! @ # $ % ^ & * ( ) - + < > \" \\ / ; : . , ? = _ | { } [ ] ~ ` '";
	
	public static HashSet<String> stopwords = new HashSet<String> ();
	
	Class stemClass;
	SnowballStemmer stemmer; 
//	public static void main(String[] args){
//            
//        }
	
	
	public TokenizeMachine() {
		try {
			
		
		String[] words = Default_StopWord.split(" ");
		for(String w: words) {
			stopwords.add(w);
		}
		
		words = NonsenseWord.split(" ");
		for(String w: words) {
			stopwords.add(w);
		}
		
		stemClass = Class.forName("detector.Util.englishStemmer");
	    
		stemmer = (SnowballStemmer) stemClass.newInstance();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> tokenize(String istr) {
		ArrayList<String> result = new ArrayList<String> ();
		istr = istr.replace(",", " ");
		istr = istr.replace(".", " ");
		istr = istr.replace("(", " ");
		istr = istr.replace(")", " ");
		istr = istr.replace("\"", " ");
		istr = istr.replace("[", " ");
		istr = istr.replace("]", " ");
		
		String[] words = istr.split(" ");
		
		for(String w: words) {
			w=w.trim().toLowerCase();
			if(w==null || w.equals("") ||stopwords.contains(w))
				continue;
			
			stemmer.setCurrent(w);
			stemmer.stem();
			result.add(stemmer.getCurrent().trim());
		}
		return result;
	}
}