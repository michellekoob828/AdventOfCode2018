package mk.adventofcode2018.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import mk.adventofcode2018.vo.FabricPositionVO;

public class Day3Processor {

	public static final String FILE_NAME = "src/InputFileDay3.txt";
	
	public static final String AT_SYMBOL = "@";
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String SPACE = " ";
	public static final String X = "x";
	
	public void process() throws FileNotFoundException{
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		Map<FabricPositionVO, Integer> fabricPositionMap = new HashMap<FabricPositionVO, Integer>();
		
		try {
			String line = bufr.readLine();
			
			while(line != null){
				int inchesFromLeft = Integer.parseInt(line.substring(line.indexOf(AT_SYMBOL)+2, line.indexOf(COMMA)));
				int inchesFromTop = Integer.parseInt(line.substring(line.indexOf(COMMA)+1, line.indexOf(COLON)));
				int width = Integer.parseInt(line.substring(line.indexOf(COLON)+2, line.indexOf(X)));
				int height = Integer.parseInt(line.substring(line.indexOf(X)+1));
				
				for(int x = inchesFromLeft; x<=(inchesFromLeft+width-1); x++) {
					for(int y = inchesFromTop; y<=(inchesFromTop+height-1); y++) {
						FabricPositionVO position = new FabricPositionVO(x,y);
						int positionCount = 0;
						if(fabricPositionMap.containsKey(position)) {
							positionCount = fabricPositionMap.get(position) + 1;
							fabricPositionMap.remove(position);
						} else {
							positionCount = 1;
						}
						fabricPositionMap.put(position, positionCount);
					}
				}
				
				line = bufr.readLine(); 
			}
			bufr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<FabricPositionVO, Integer> result = fabricPositionMap.entrySet().stream()
				.filter(map -> map.getValue() >= 2)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println("Result is: " + result.size());
		
	} 
	
}
