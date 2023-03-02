package com.example.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Movie;
import com.example.demo.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	public MovieResource() {
		// TODO Auto-generated constructor stub
	}
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId")String movieId) {
		
		//https://api.themoviedb.org/3/movie/700?api_key=55fd3b2e904fd20e99f052117df1b859&language=en-US
		
		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey + "&language=en-US", MovieSummary.class);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}

}
