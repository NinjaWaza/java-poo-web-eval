package com.java.eval.web.repository;

import com.java.eval.web.model.Album;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Integer> {
}
