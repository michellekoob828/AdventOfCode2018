package mk.adventofcode2018.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2Processor {

	public static final String FILE_NAME = "src/InputFileDay2.txt";
	
	public void process() throws FileNotFoundException{
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		int rowCountExactly2 = 0;
		int rowCountExactly3 = 0;
		
		try {
			String line = bufr.readLine();
			
			while(line != null){
				Map<Character,Integer> letterCount = this.determineLetterCount(line);				
				
				boolean containsExactly2 = this.containsExactCount(2, letterCount);
				boolean containsExactly3 = this.containsExactCount(3, letterCount);
				
				if(containsExactly2) {
					rowCountExactly2++;
				}
				
				if(containsExactly3) {
					rowCountExactly3++;
				}
				
				line = bufr.readLine(); 
			}
			bufr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		System.out.print("Final cross sum is ");
		System.out.print(rowCountExactly2);
		System.out.print(" * ");
		System.out.print(rowCountExactly3);
		System.out.print(" = ");
		System.out.println(rowCountExactly2 * rowCountExactly3);
	} 
	
	public void processPart2() throws FileNotFoundException {
		List<String> inputList = this.readInputIntoList();
		boolean boxIdsFound = false;
		
		for(int i = 0; i<inputList.size(); i++) {
			if(boxIdsFound) {
				break;
			}
			
			String boxIdCompareA = inputList.get(i);
			for(int j = i+1; j<inputList.size(); j++) {
				if(boxIdsFound) {
					break;
				}
				
				String boxIdCompareB = inputList.get(j);
				
				int letterDifferences = 0;
				for(int k=0;k<boxIdCompareA.length();k++) {
					if(boxIdCompareA.charAt(k) != boxIdCompareB.charAt(k)) {
						letterDifferences++;
					}
				}
				
				if(letterDifferences == 1) {
					boxIdsFound = true;
					for(int k=0;k<boxIdCompareA.length();k++) {
						if(boxIdCompareA.charAt(k) == boxIdCompareB.charAt(k)) {
							System.out.print(boxIdCompareA.charAt(k));
						}
					}
				}
			}
		}
	}
	
	private Map<Character,Integer> determineLetterCount(String line) {
		Map<Character,Integer> letterCount = new HashMap<Character,Integer>();
		
		for (char c: line.toCharArray()) {
			if(letterCount.containsKey(c)) {
				int oldCount = letterCount.get(c);
				int newCount = ++oldCount;
				letterCount.remove(c);
				letterCount.put(c, newCount);
			} else {
				letterCount.put(c, 1);
			}
		}
		
		return letterCount;
	}
	
	private boolean containsExactCount(int exactCount, Map<Character,Integer> letterCount) {
		for(Map.Entry<Character,Integer> entry :letterCount.entrySet()) {
			if(exactCount == entry.getValue()) {
				return true;
			}
		}
		
		return false;
	}
	
	private List<String> readInputIntoList() throws FileNotFoundException {
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		List<String> inputList = new ArrayList<String>();
		
		try {
			String line = bufr.readLine();
			
			while(line != null){
				inputList.add(line);
				line = bufr.readLine(); 
			}
			bufr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputList;
	}
}
