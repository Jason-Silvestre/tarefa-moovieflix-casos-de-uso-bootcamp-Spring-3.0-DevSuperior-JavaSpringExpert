package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;

import com.devsuperior.movieflix.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping(value = "/{id}")
	 public MovieDetailsDTO getMovieById(@PathVariable Long id){
		return movieService.findById(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping
	public Page<MovieCardDTO>getMovies(Pageable pageable, @RequestParam(name = "genreId", defaultValue = "0") Long genreId){
		return movieService.findAllMovie(pageable, genreId);
	}

}
