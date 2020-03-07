package com.github.qingying0.community.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class Category {
    @Id
    private Long id;

    private String name;

    @Column(name = "follow_count")
    private Integer followCount;

    private Integer state;
}
