package mk.adventofcode2018.main;

import java.io.FileNotFoundException;

import mk.adventofcode2018.processor.Day1Processor;

public class Day1Main {

	public static void main(String[] args) {
		Day1Processor day1 = new Day1Processor();
		try {
			day1.process();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
