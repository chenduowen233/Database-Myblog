package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movie")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String title;

    private String director;

    private String date;

    private String grade;

    private String abs;

    private String cover;

    @ManyToOne
    @JoinColumn(name="cid")
    private Mcategory mcategory;
}
