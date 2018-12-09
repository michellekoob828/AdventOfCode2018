package mk.adventofcode2018.vo;

import java.util.HashMap;

public class GuardSleepLogVO {
	private int totalMinutesAsleep;
	private HashMap<Integer, Integer> timestampMinutesAsleep;
	
	public GuardSleepLogVO() {
		this.totalMinutesAsleep = 0;
		this.timestampMinutesAsleep = new HashMap<Integer,Integer>();
	}
	
	public int getTotalMinutesAsleep() {
		return totalMinutesAsleep;
	}
	public void setTotalMinutesAsleep(int totalMinutesAsleep) {
		this.totalMinutesAsleep = totalMinutesAsleep;
	}
	public HashMap<Integer, Integer> getTimestampMinutesAsleep() {
		return timestampMinutesAsleep;
	}
	public void setTimestampMinutesAsleep(HashMap<Integer, Integer> timestampMinutesAsleep) {
		this.timestampMinutesAsleep = timestampMinutesAsleep;
	}
	
}
