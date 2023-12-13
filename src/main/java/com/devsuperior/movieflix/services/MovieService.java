package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;

import com.devsuperior.movieflix.dto.MovieCardDTO;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;

import com.devsuperior.movieflix.entities.Movie;

import com.devsuperior.movieflix.repositories.MovieRepository;

import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieDetailsDTO findById(Long id) {

        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        MovieDetailsDTO movieDetailsDTO = new MovieDetailsDTO(movie);
        movieDetailsDTO.setId(movie.getId());
        movieDetailsDTO.setTitle(movie.getTitle());
        movieDetailsDTO.setSubTitle(movie.getSubTitle());
        movieDetailsDTO.setYear(movie.getYear());
        movieDetailsDTO.setImgUrl(movie.getImgUrl());
        movieDetailsDTO.setSynopsis(movie.getSynopsis());

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(movie.getGenre().getId());
        genreDTO.setName(movie.getGenre().getName());

        movieDetailsDTO.setGenre(genreDTO);

        return movieDetailsDTO;
    }

    public Page<MovieCardDTO> findAllMovie(Pageable pageable, Long genreId) {

        Page<Movie> movieList;

        if (genreId == 0) {
            movieList = movieRepository.findAllByOrderByTitleAsc(pageable);
        } else {
            movieList = movieRepository.findAllByGenreId(pageable, genreId);
        }

        return movieList.map(movie -> {
            MovieCardDTO movieCardDTO = new MovieCardDTO();
            movieCardDTO.setId(movie.getId());
            movieCardDTO.setTitle(movie.getTitle());
            movieCardDTO.setSubTitle(movie.getSubTitle());
            movieCardDTO.setYear(movie.getYear());
            movieCardDTO.setImgUrl(movie.getImgUrl());

            return movieCardDTO;
        });
    }
}
