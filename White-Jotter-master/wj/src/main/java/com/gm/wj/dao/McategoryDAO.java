package com.gm.wj.dao;

import com.gm.wj.entity.Mcategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface McategoryDAO extends JpaRepository<Mcategory, Integer> {

}
