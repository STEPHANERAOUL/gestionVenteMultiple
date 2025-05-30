package com.commerce.config.service;

import com.commerce.model.entity.HomeCategory;

import java.util.List;

public interface HomeCategoryService {
    HomeCategory createHomeCategory(HomeCategory homeCategory);
    List<HomeCategory> CreateHomeCategories(List<HomeCategory>homeCategories);
    HomeCategory updateHomeCategory(HomeCategory homeCategory,Long id) throws Exception;
    List<HomeCategory>getAllHomeCategories();




}
