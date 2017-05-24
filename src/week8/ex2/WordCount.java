package week8.ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {
		String path = "src/week8/ex2/abstract";
		HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
	    Scanner sc2 = null;
	    try {
	        sc2 = new Scanner(new File(path));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	    while (sc2.hasNextLine()) {
	            Scanner s2 = new Scanner(sc2.nextLine());
	        while (s2.hasNext()) {
	        	String oString = s2.next();
	            String s = oString.replaceAll("[^a-zA-Z]", "");
	            if (!s.equals("")) {
	            	Integer curVal = wordCountMap.get(s);
	            	wordCountMap.put(s, curVal != null ? ++curVal : 1);
	            }	
	        }
	        
	        for (Map.Entry m : wordCountMap.entrySet()) {
	        	System.out.println(m.getKey() + " " + m.getValue());
	        }
	    }
	}

}
