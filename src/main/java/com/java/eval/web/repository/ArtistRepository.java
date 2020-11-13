package com.java.eval.web.repository;

import com.java.eval.web.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

    //@Query(value = "SELECT artist.artistid,artist.name FROM artist WHERE lower(artist.name) LIKE lower(%:name%)", nativeQuery = true)
    List<Artist> findByNameContaining(String name);
}
