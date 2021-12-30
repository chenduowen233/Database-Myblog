package com.gm.wj.service;

import com.gm.wj.dao.MovieDAO;
import com.gm.wj.entity.Movie;
import com.gm.wj.entity.Mcategory;
import com.gm.wj.redis.RedisService;
import com.gm.wj.util.CastUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {
    @Autowired
    private MovieDAO movieDAO;
    @Autowired
    private McategoryService mcategoryService;
    @Autowired
    private RedisService redisService;

    public List<Movie> list() {
        List<Movie> movies;
        String key = "movielist";
        Object movieCache = redisService.get(key);

        if (movieCache == null) {
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            movies = movieDAO.findAll(sort);
            redisService.set(key, movies);
        } else {
            movies = CastUtils.objectConvertToList(movieCache, Movie.class);
        }
        return movies;
    }

    public void addOrUpdate(Movie movie) {
        redisService.delete("movielist");
        movieDAO.save(movie);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("movielist");
    }

    public void deleteById(int id) {
        redisService.delete("movielist");
        movieDAO.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("movielist");
    }

    public List<Movie> listByMcategory(int cid) {
        Mcategory mcategory = mcategoryService.get(cid);
        return movieDAO.findAllByMcategory(mcategory);
    }

    public List<Movie> Search(String keywords) {
        return movieDAO.findAllByTitleLikeOrDirectorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
