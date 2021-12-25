package com.gm.wj.service;

import com.gm.wj.dao.McategoryDAO;
import com.gm.wj.entity.Mcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McategoryService {
    @Autowired
    McategoryDAO mcategoryDAO;

    public List<Mcategory> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return mcategoryDAO.findAll(sort);
    }

    public Mcategory get(int id) {
        Mcategory m= mcategoryDAO.findById(id).orElse(null);
        return m;
    }
}
