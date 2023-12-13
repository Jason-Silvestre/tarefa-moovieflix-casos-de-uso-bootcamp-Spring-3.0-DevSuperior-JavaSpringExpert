package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findAllByOrderByTitleAsc(Pageable pageable);
    Page<Movie> findAllByGenreId(Pageable pageable, Long genreId);
}
