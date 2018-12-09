package mk.adventofcode2018.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import mk.adventofcode2018.vo.GuardSleepLogVO;

public class Day4Processor {

	public static final String FILE_NAME = "src/InputFileDay4.txt";
	
	public static final String B = "b";
	public static final String LEFT_SQUARE_BRACKET = "[";
	public static final String LOG_MESSAGE_FALLS_ASLEEP = "falls asleep";
	public static final String LOG_MESSAGE_GUARD = "Guard";
	public static final String LOG_MESSAGE_WAKES_UP = "wakes up";
	public static final String POUND_SYMBOL = "#";
	public static final String RIGHT_SQUARE_BRACKET = "]";
	public static final String SPACE = " ";
	
	public void process() throws FileNotFoundException{
		Map<Date, String> inputRecords = this.readInputFile();
		
		Map<Date, String> sortedInputRecords  = inputRecords.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                		(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		Map<Integer, GuardSleepLogVO> minutesAsleepMap = this.trackMinutesAsleep(sortedInputRecords);
		
		int guardMostAsleep = this.findGuardMostAsleep(minutesAsleepMap);
		int minuteMostAsleep = this.findMinuteMostAsleep(minutesAsleepMap.get(guardMostAsleep));
		
		System.out.println("Final guard number: " + guardMostAsleep);
		System.out.println("Final minute most asleep: " + minuteMostAsleep);
		System.out.println("Final answer: " + (guardMostAsleep * minuteMostAsleep));
		
	} 
	
	public void processPart2() throws FileNotFoundException {
		Map<Date, String> inputRecords = this.readInputFile();
		
		Map<Date, String> sortedInputRecords  = inputRecords.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                		(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		Map<Integer, GuardSleepLogVO> minutesAsleepMap = this.trackMinutesAsleep(sortedInputRecords);
		
		this.findMinuteMostAsleep(minutesAsleepMap);
	}

	private Map<Date, String> readInputFile() throws FileNotFoundException {
		FileReader fr = new FileReader(FILE_NAME); 
		BufferedReader bufr = new BufferedReader(fr);
		
		Map<Date, String> inputRecords = new LinkedHashMap<Date, String>();
		
		try { 
			String line = bufr.readLine();
			
			while(line != null){
				String dateString = line.substring(line.indexOf(LEFT_SQUARE_BRACKET)+1, line.indexOf(RIGHT_SQUARE_BRACKET));
				Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateString);
				String inputLog = line.substring(line.indexOf(RIGHT_SQUARE_BRACKET)+2);
				
				inputRecords.put(date, inputLog);
				
				line = bufr.readLine(); 
			}
			
			bufr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return inputRecords;
		
	}
	
	private Map<Integer, GuardSleepLogVO> trackMinutesAsleep(Map<Date, String> sortedInputRecords) {
		Map<Integer, GuardSleepLogVO> sleepLogMap = new HashMap<Integer, GuardSleepLogVO>();
		
		int guardNumber = 0;
		int guardFallsAsleepTimestampMinutes = 0;
		GuardSleepLogVO guardSleepLog = new GuardSleepLogVO();
		
		for(Map.Entry<Date,String> record :sortedInputRecords.entrySet()) {
			Date logDate = record.getKey();
			String logMessage = record.getValue();
			
			if(LOG_MESSAGE_GUARD.equals(logMessage.substring(0, logMessage.indexOf(SPACE)))) {
				guardNumber = Integer.parseInt(logMessage.substring(logMessage.indexOf(POUND_SYMBOL)+1, logMessage.indexOf(B)-1));
				System.out.println("Guard number: " + guardNumber);
			} else if(LOG_MESSAGE_FALLS_ASLEEP.equals(logMessage)){
				guardFallsAsleepTimestampMinutes = logDate.getMinutes();
			} else if(LOG_MESSAGE_WAKES_UP.equals(logMessage)){
				if(sleepLogMap.containsKey(guardNumber)) {
					guardSleepLog = sleepLogMap.get(guardNumber);
				} else {
					guardSleepLog = new GuardSleepLogVO();
				}
				
				int totalMinutesAsleep = guardSleepLog.getTotalMinutesAsleep();
				HashMap<Integer, Integer> timestampMinutesAsleep = guardSleepLog.getTimestampMinutesAsleep();
				
				for(int t = guardFallsAsleepTimestampMinutes; t<logDate.getMinutes();t++) {
					if(timestampMinutesAsleep.containsKey(t)) {
						timestampMinutesAsleep.replace(t, timestampMinutesAsleep.get(t) + 1);
					} else {
						timestampMinutesAsleep.put(t, 1);
					}
					totalMinutesAsleep++;
				}
				
				guardSleepLog.setTimestampMinutesAsleep(timestampMinutesAsleep);
				guardSleepLog.setTotalMinutesAsleep(totalMinutesAsleep);
				
				if(sleepLogMap.containsKey(guardNumber)) {
					sleepLogMap.replace(guardNumber, guardSleepLog);
				} else {
					sleepLogMap.put(guardNumber, guardSleepLog);
				}
				
			} else {
				System.out.println("ERROR: invalid log message" + logMessage);
				System.out.println(logMessage.substring(0, logMessage.indexOf(SPACE)));
			}
		}
		
		return sleepLogMap;
	}
	
	private int findGuardMostAsleep(Map<Integer, GuardSleepLogVO> minutesAsleepMap) {
		int guardMostAsleep = 0;
		int highestMinutesAsleep = 0;
		
		for(Map.Entry<Integer, GuardSleepLogVO> guard : minutesAsleepMap.entrySet()) {
			if(guard.getValue().getTotalMinutesAsleep() > highestMinutesAsleep) {
				guardMostAsleep = guard.getKey();
				highestMinutesAsleep = guard.getValue().getTotalMinutesAsleep() ;
			}
		}
		
		return guardMostAsleep;
	}
	
	private int findMinuteMostAsleep(GuardSleepLogVO guardLog) {
		int minuteMostAsleep = 0;
		int minuteCount = 0;
		
		for(Map.Entry<Integer, Integer> timestampMinutesAsleep : guardLog.getTimestampMinutesAsleep().entrySet()) {
			if(timestampMinutesAsleep.getValue() > minuteCount) {
				minuteMostAsleep = timestampMinutesAsleep.getKey();
				minuteCount = timestampMinutesAsleep.getValue();
			}
		}
		
		return minuteMostAsleep;
	}
	
	private void findMinuteMostAsleep(Map<Integer, GuardSleepLogVO> minutesAsleepMap) {
		int guardNumber = 0;
		int timeStampMinute = 0;
		int timeStampCount = 0;
		
		for(Map.Entry<Integer, GuardSleepLogVO> guard : minutesAsleepMap.entrySet()) {
			for(Map.Entry<Integer, Integer> record : guard.getValue().getTimestampMinutesAsleep().entrySet()) {
				if(record.getValue() > timeStampCount) {
					guardNumber = guard.getKey();
					timeStampMinute = record.getKey();
					timeStampCount = record.getValue();
				}
			}
		}
		
		System.out.println("Final guard number: " + guardNumber);
		System.out.println("Final timeStampMinute: " + timeStampMinute);
		System.out.println("Final answer: " + (guardNumber * timeStampMinute));
	}
}
