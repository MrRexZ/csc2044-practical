package week8.ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AbstractWordCount {

	public static void main(String[] args) {
		String path = "src/week8/ex3/abstract";
		HashMap<Integer, HashMap<String, Integer>> abstractCountMap = new HashMap<Integer, HashMap<String, Integer>>();
		Set<String> vocab = new HashSet<String>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
			Scanner sc2 = null;
			try {
				sc2 = new Scanner(new File(path + i));
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
						vocab.add(s);
						wordCountMap.put(s, curVal != null ? ++curVal : 1);
					}
				}
			}
			abstractCountMap.put(i, wordCountMap);
		}
		
		for (int  i = 0 ; i < 10; i++) {
			HashMap<String, Integer> curHashMap = abstractCountMap.get(i);
			for (String curVocab : vocab) {
				Integer curVal = curHashMap.get(curVocab);
				curHashMap.put(curVocab, curVal != null ? curVal : 0);
			}
			abstractCountMap.put(i, curHashMap);
		}

		for (Map.Entry m : abstractCountMap.entrySet()) {
			System.out.print(m.getKey()+ " : ");
			for (Map.Entry e : abstractCountMap.get(m.getKey()).entrySet()) {
				System.out.print(e.getValue()+ " ");
			}
			System.out.println();
			
		}

	}

}
