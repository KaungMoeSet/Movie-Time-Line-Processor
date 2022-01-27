package movie_timeline;

public class MovieClip {

	String name;
	private int totalSeconds; //total duration in sec
	private int offsetSeconds; //start offset in sec
	private int hours, minutes, seconds; //for duration 
	private TimeStamp startTimeStamp, endTimeStamp; // actual time stamp
	private TimeStampSpecifier specifer;
	
	
	public MovieClip(String name, int hours, int minutes, int seconds, TimeStampSpecifier specifer) {
		super();
		this.name = name;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.totalSeconds = hours*3600 + minutes*60 + seconds;
		this.offsetSeconds = 0;
		this.specifer = specifer;
	}
	
	public MovieClip(String name, int hours, int minutes, int seconds) {
		this(name, hours, minutes, seconds, TimeStampSpecifier.DURATION);
	}

	public TimeStampSpecifier getSpecifer() {
		return specifer;
	}

	public MovieClip setTimpeStampSpecifier(TimeStampSpecifier specifer) {
		this.specifer = specifer;
		return this;
	}
	
	public MovieClip setTotalSeconds(int totalSeconds) {
		this.totalSeconds = totalSeconds;
		hours = totalSeconds/3600;
		minutes = (totalSeconds - hours*3600)/60;
		seconds = totalSeconds - hours*3600 - minutes*60;
		return this;
	}
	
	public MovieClip setOffset(int offset) {
		this.offsetSeconds = offset;	
		startTimeStamp = new TimeStamp(offsetSeconds);
		endTimeStamp = new TimeStamp(offsetSeconds+getTotalSeconds());
		return this;
	}
	
	public int getTotalSeconds() {
		return totalSeconds;
	}
	
	public TimeStamp getStartTimeStamp() {
		if(startTimeStamp==null)
			startTimeStamp = new TimeStamp(offsetSeconds);		
		return startTimeStamp;
	}
	
	public TimeStamp getEndTimeStamp() {
		if(endTimeStamp==null)
			endTimeStamp = new TimeStamp(offsetSeconds+getTotalSeconds());		
		return endTimeStamp;
	}

	@Override
	public String toString() {
		String str;
		switch (specifer) {
		case START_TIME:
			str = "%s %s".formatted(getStartTimeStamp(), name);
			break;
		case END_TIME:
			str = "%s %s".formatted(getEndTimeStamp(), name);
		default:
			str = "%s %s".formatted(new TimeStamp(getTotalSeconds()), name);
			break;
		}
		return str;
	}
	
	//create parsestring for the string format: "    HHH:MM:SS     name  "
	//this has to input the duration format
	public static MovieClip parseString(String str) {
		String temp = str.trim();
/*		int index=0;
		for(int i=0; i<str.length(); i++) {
			if(temp.charAt(i)==' ') {
				index = i;
				break;
			}			
		}
		*/
		
		int index = temp.indexOf(' ');
		//split time and name
		String timeStampStr = temp.substring(0, index);
		String[] args = timeStampStr.split(":");
		String name = temp.substring(index).trim();
		
		return new MovieClip(name, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
	} // can use for initialize cause return is MovieClip obj 
	
	public static void main(String[] args) {
		
		MovieClip c1 = MovieClip.parseString("      3:79:88 Avengeres    ");
		System.out.println(c1);
	}
}
