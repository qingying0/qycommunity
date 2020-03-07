package com.github.qingying0.community.service;

import com.github.qingying0.community.entity.Category;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qingying0
 * @since 2020-03-02
 */
public interface ICategoryService {

    List<Category> getAll();
}
