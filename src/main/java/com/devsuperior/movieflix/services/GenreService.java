package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;

import com.devsuperior.movieflix.entities.Genre;

import com.devsuperior.movieflix.repositories.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> findAll() {

        var genreList = genreRepository.findAllByOrderByIdAsc();

        var resultList = new ArrayList<GenreDTO>();


        for (Genre g : genreList) {
            var genreDTO = new GenreDTO();
            genreDTO.setId(g.getId());
            genreDTO.setName(g.getName());

            resultList.add(genreDTO);
        }

        return resultList;
    }
}
