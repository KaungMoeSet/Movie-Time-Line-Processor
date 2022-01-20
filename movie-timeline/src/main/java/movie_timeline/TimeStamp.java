package movie_timeline;

public class TimeStamp {

	int timeSeconds;
	int hours, minutes, seconds;
	
	public TimeStamp(int timeSeconds) {
		super();
		this.timeSeconds = timeSeconds;
		doConversion();
	}
	
	private void doConversion() {
		hours = timeSeconds/3600;
		minutes = (timeSeconds - hours*3600)/60;
		seconds = timeSeconds - hours*3600 - minutes*60;
	}

	@Override
	public String toString() {
		return String.format("%s: %s: %s", Utils.toDoubleDigits(hours), Utils.toDoubleDigits(minutes), Utils.toDoubleDigits(seconds));
	}
	
	public static void main(String[] args) {
		TimeStamp ts = new TimeStamp(70);
		System.out.println(ts);
	}
	
}
