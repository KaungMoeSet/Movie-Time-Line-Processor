package movie_timeline;

public class MovieClip {

	String name;
	private int totalSeconds; //total duration in sec
	private int offset; //start offset in sec
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
		this.offset = 0;
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
		this.offset = offset;
		return this;
	}
	
	public int getTotalSeconds() {
		return totalSeconds;
	}
	
	public TimeStamp getStartTimeStamp() {
		if(startTimeStamp==null)
			startTimeStamp = new TimeStamp(offset);
		
		return startTimeStamp;
	}
	
	public TimeStamp getEndTimeStamp() {
		if(endTimeStamp==null)
			endTimeStamp = new TimeStamp(offset+getTotalSeconds());
		
		return endTimeStamp;
	}
}
