package mk.adventofcode2018.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day2Processor {
	
	public static final String FILE_NAME = "src/InputFile.txt";

	public void process() throws FileNotFoundException {
		
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		int frequency = 0;
				
		try {
			String line = bufr.readLine();
			
			while(line != null){
				frequency = this.updateFrequency(line, frequency);
				line = bufr.readLine(); 
			}
			
			System.out.println("Frequency " + frequency);
			bufr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private int updateFrequency(String frequencyChangeString, int frequency) {
		int frequencyChange = Integer.parseInt(frequencyChangeString);
		frequency += frequencyChange;
		System.out.println("Frequency change " + frequencyChange);
		return frequency;
	}
}
