package movie_timeline;

public class MovieClip {

	int totalSeconds; //total duration in sec
	int offset; //start offset in sec
	int hours, minutes, seconds; //for duration 
	TimeStamp startTimeStamp, endTimeStamp; // actual time stamp
	TimeStampSpecifier specifer;
	
	
	public MovieClip(int hours, int minutes, int seconds, TimeStampSpecifier specifer) {
		super();
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.totalSeconds = hours*3600 + minutes*60 + seconds;
		this.offset = 0;
		this.specifer = specifer;
	}
	
	public MovieClip(int hours, int minutes, int seconds) {
		this(hours, minutes, seconds, TimeStampSpecifier.DURATION);
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
}
