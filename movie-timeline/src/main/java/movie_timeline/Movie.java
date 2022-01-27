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
	
	public Movie addMovieClip(MovieClip clip) {
		
		clip.setTimpeStampSpecifier(specifier);
		
		clip.setOffset(totalSecondsElapsed);
		
		totalSecondsElapsed += clip.getTotalSeconds();
		
		movieClips.add(clip);
		
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(movieClips.size());
		String str = "MOVIE: %s".formatted(name);
		sb.append(str).append(System.lineSeparator());
		sb.append("*".repeat(str.length())).append(System.lineSeparator());
		for(MovieClip clip : movieClips) {
			sb.append(clip.toString()).append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	
}
