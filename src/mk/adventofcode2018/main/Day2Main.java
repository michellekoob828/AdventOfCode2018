package mk.adventofcode2018.main;

import java.io.FileNotFoundException;

import mk.adventofcode2018.processor.Day2Processor;

public class Day2Main {

	public static void main(String[] args) {
		Day2Processor day2 = new Day2Processor();
		try {
			day2.process();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
