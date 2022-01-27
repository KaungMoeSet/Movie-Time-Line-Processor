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
	
	public static void main(String[] args) {
		MovieClip clip1 = MovieClip.parseString("0:20:50 first movie");
		MovieClip clip2 = MovieClip.parseString("3:20:80 first movie");
		MovieClip clip3 = MovieClip.parseString("1:35:10 first movie");
		Movie movie = new Movie("Joined Movie clips", TimeStampSpecifier.DURATION);
		movie.addMovieClip(clip1).addMovieClip(clip2).addMovieClip(clip3);
		System.out.println(movie);
	}
}
