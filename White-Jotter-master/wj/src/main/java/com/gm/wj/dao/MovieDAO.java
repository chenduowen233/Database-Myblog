package com.gm.wj.dao;

import com.gm.wj.entity.Movie;
import com.gm.wj.entity.Mcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieDAO extends JpaRepository<Movie,Integer> {
    List<Movie> findAllByMcategory(Mcategory mcategory);
    List<Movie> findAllByTitleLikeOrDirectorLike(String keyword1, String keyword2);
}
