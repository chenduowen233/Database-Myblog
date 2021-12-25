package com.gm.wj.controller;

import com.gm.wj.entity.Movie;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.MovieService;
import com.gm.wj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;


@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/api/movies")
    public Result listMovies() {
        return ResultFactory.buildSuccessResult(movieService.list());
    }

    @PostMapping("/api/admin/content/movies")
    public Result addOrUpdateMovies(@RequestBody @Valid Movie movie) {
        movieService.addOrUpdate(movie);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @PostMapping("/api/admin/content/movies/delete")
    public Result deleteMovie(@RequestBody @Valid Movie movie) {
        movieService.deleteById(movie.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @GetMapping("/api/msearch")
    public Result msearchResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return ResultFactory.buildSuccessResult(movieService.list());
        } else {
            return ResultFactory.buildSuccessResult(movieService.Search(keywords));
        }
    }

    @GetMapping("/api/mcategories/{cid}/movies")
    public Result listByMcategory(@PathVariable("cid") int cid) {
        if (0 != cid) {
            return ResultFactory.buildSuccessResult(movieService.listByMcategory(cid));
        } else {
            return ResultFactory.buildSuccessResult(movieService.list());
        }
    }

    @PostMapping("/api/admin/content/movies/covers")
    public String coversUpload(MultipartFile file) {
        String folder = "C:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
