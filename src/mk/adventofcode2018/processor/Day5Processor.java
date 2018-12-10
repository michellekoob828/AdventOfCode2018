package mk.adventofcode2018.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day5Processor {

	public static final String FILE_NAME = "src/InputFileDay5.txt";
	public static final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	public static final String EMPTY_STRING = "";
	
	public void process() throws FileNotFoundException{
		String inputPolymer = this.readInputFile();
		
		String finalPolymer = this.processPolymer(inputPolymer);
		
		//System.out.println("Final polymer: " + finalPolymer);
		System.out.println("Final polymer length: " + finalPolymer.length());
	} 
	
	public void processPart2() throws FileNotFoundException{
		String inputPolymer = this.readInputFile();
		String finalPolymer = inputPolymer;
		
		for(int i=0; i<LOWERCASE_ALPHABET.length(); i++) {
			String polymer = this.removeUnitFromPolymer(inputPolymer, LOWERCASE_ALPHABET.charAt(i));
			String processedPolymer = this.processPolymer(polymer);
			
			if(finalPolymer.length() > processedPolymer.length()) {
				finalPolymer = processedPolymer;
			}
		}
		
		System.out.println("Final polymer length: " + finalPolymer.length());
	}

	private String readInputFile() throws FileNotFoundException {
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		String line = "";
		
		try { 
			line = bufr.readLine();
			
			bufr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return line;
	}
	
	private String removeUnitFromPolymer(String s, Character c) {
		String lowercaseLetter = Character.toString(c);
		String uppercaseLetter = Character.toString(Character.toUpperCase(c));
		
		s = s.replace(lowercaseLetter, EMPTY_STRING);
		s = s.replace(uppercaseLetter, EMPTY_STRING);
		
		return s;
	}
	
	private String processPolymer(String inputPolymer) {
		String outputPolymer = this.reactPolymer(inputPolymer);
		
		while(!outputPolymer.equals(inputPolymer)) {
			inputPolymer = outputPolymer;
			outputPolymer = this.reactPolymer(inputPolymer);
		}
		
		return outputPolymer;
	}
	
	private String reactPolymer(String inputPolymer) {
		StringBuilder outputPolymer = new StringBuilder();
		
		for(int i=0; i<inputPolymer.length(); i++) {
			//System.out.println(i);
			
			if((i+1) < inputPolymer.length()) {
				Character c1 = inputPolymer.charAt(i);
				Character c1Lower = Character.toLowerCase(c1);
				Character c2 = inputPolymer.charAt(i+1);
				Character c2Lower = Character.toLowerCase(c2);
				
				//System.out.println("c1: " + c1 + " c2: " + c2);
				
				if(!c1.equals(c2) &&
						c1Lower.equals(c2Lower)) {
					i++;
					//System.out.println("react");
				} else {
					outputPolymer.append(c1);
					//System.out.println("no reaction");
				}
			} else {
				outputPolymer.append(inputPolymer.charAt(i));
			}
		}
		
		return outputPolymer.toString();
	}
}
