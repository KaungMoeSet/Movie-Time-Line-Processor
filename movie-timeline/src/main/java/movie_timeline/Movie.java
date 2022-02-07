package movie_timeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
		String str = "MOVIE: %s [TIME STAMP FORMAT: %s]".formatted(name, specifier);
		sb.append(str).append(System.lineSeparator());
		sb.append("*".repeat(str.length())).append(System.lineSeparator());
		for(MovieClip clip : movieClips) {
			sb.append(clip.toString()).append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	public void saveToFile(String fileName) {
		File file = new File(fileName);
		try(FileWriter writer = new FileWriter(file)){
			writer.write(toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Movie parseTextFile(String fileName, TimeStampSpecifier inputSpecifier, TimeStampSpecifier outputSpecifier) {
		Movie movie = new Movie("null", outputSpecifier);
		try(Scanner scanner = new Scanner(new File(fileName))) {
			while(scanner.hasNextLine()) {
				MovieClip clip = MovieClip.parseString(scanner.nextLine());
				clip.setTimpeStampSpecifier(inputSpecifier);
				movie.addMovieClip(clip);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return movie;
	}
	public static void main(String[] args) {
/*		MovieClip clip1 = MovieClip.parseString("0:20:50 first movie");
		MovieClip clip2 = MovieClip.parseString("3:20:80 first movie");
		MovieClip clip3 = MovieClip.parseString("1:35:10 first movie");
		Movie movie = new Movie("Joined Movie clips", TimeStampSpecifier.DURATION);
		movie.addMovieClip(clip1).addMovieClip(clip2).addMovieClip(clip3);
		System.out.println(movie);
		
		movie.saveToFile("./src/main/resources/test2.txt"); */
		String fileName = "./src/main/resources/test2.txt";
		Movie movie = Movie.parseTextFile(fileName, TimeStampSpecifier.DURATION, TimeStampSpecifier.START_TIME);
		movie.setName("A great Movie");
		System.out.println(movie);
	}
}
