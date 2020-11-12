package com.java.eval.web.repository;

import com.java.eval.web.model.Artist;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {
}
