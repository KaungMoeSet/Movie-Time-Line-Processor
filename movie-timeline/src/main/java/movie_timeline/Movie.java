package movie_timeline;

import java.util.ArrayList;

public class Movie {

	String name;
	ArrayList<MovieClip> movieClips;
	TimeStampSpecifier specifier;
	int totalSecondsElapsed = 0;
	
	public Movie(String name, TimeStampSpecifier specifier) {
		super();
		this.name = name;
		this.specifier = specifier;
		movieClips = new ArrayList<MovieClip>();
	}
	
	public Movie setName(String name) {
		this.name = name;
		return this;
	}
	
	public Movie setTimeStampSpecifier(TimeStampSpecifier specifier) {
		this.specifier = specifier;
		return this;
	}
}
