package com.github.qingying0.community.service.impl;

import com.github.qingying0.community.entity.Category;
import com.github.qingying0.community.mapper.CategoryMapper;
import com.github.qingying0.community.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qingying0
 * @since 2020-03-02
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAll() {
        return categoryMapper.selectAll();
    }
}
