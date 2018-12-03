package mk.adventofcode2018.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Processor {
	
	public static final String FILE_NAME = "src/InputFileDay1.txt";

	public void process() throws FileNotFoundException {
		
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		int frequency = 0;
				
		try {
			String line = bufr.readLine();
			
			while(line != null){
				frequency = this.updateFrequency(Integer.parseInt(line), frequency);
				line = bufr.readLine(); 
			}
			
			System.out.println("Frequency " + frequency);
			bufr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void processPart2() throws FileNotFoundException {
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		int frequency = 0;
		List<Integer> inputList = new ArrayList<Integer>();
		List<Integer> frequencies = new ArrayList<Integer>();
		frequencies.add(frequency);
		boolean foundFrequency = false;
				
		try {
			String line = bufr.readLine();
			
			while(line != null){
				frequency = this.updateFrequency(Integer.parseInt(line), frequency);
				System.out.println("Frequency " + frequency);
				foundFrequency = this.isFrequencyInList(frequency, frequencies);
				if(foundFrequency) {
					System.out.println("The first frequency reached twice is " + frequency);
					break;
				} else {
					inputList.add(Integer.parseInt(line));
					frequencies.add(frequency);
				}
				line = bufr.readLine(); 
			}
			
			bufr.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while(!foundFrequency) {
			frequency = this.updateFrequency(inputList.get(i), frequency);
			foundFrequency = this.isFrequencyInList(frequency, frequencies);
			if(foundFrequency) {
				System.out.println("The first frequency reached twice is " + frequency);
			} else {
				frequencies.add(frequency);
				i++;
				if(i == inputList.size()) {
					i=0;
				}
			}
		}
	}
	
	private int updateFrequency(int frequencyChange, int frequency) {
		frequency += frequencyChange;
		return frequency;
	}
	
	private boolean isFrequencyInList(int frequency, List<Integer> frequencies) {
		if(frequencies.contains(frequency)) {
			return true;
		}
		
		return false;
	}
}
