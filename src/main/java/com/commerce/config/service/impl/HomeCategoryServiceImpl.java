package com.commerce.config.service.impl;

import com.commerce.config.repository.UserRepository;
import com.commerce.config.service.HomeCategoryService;
import com.commerce.model.entity.HomeCategory;
import com.commerce.repository.HomeCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {


    private final HomeCategoryRepository homeCategoryRepository;
    private final UserRepository userRepository;

    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {



        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> CreateHomeCategories(List<HomeCategory> homeCategories) {
        if (homeCategoryRepository.findAll().isEmpty()){
            return homeCategoryRepository.saveAll(homeCategories);
        }
        return homeCategoryRepository.findAll();

    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id)throws Exception {

        HomeCategory existingCategory = homeCategoryRepository.findById(id)
                .orElseThrow(()->new Exception("categories not fround"));
        if (homeCategory.getImage()!=null){
            existingCategory.setImage(homeCategory.getImage());
        }
        if (homeCategory.getCategoryId()!=null){
            existingCategory.setCategoryId(homeCategory.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategory);
    }

    @Override
    public List<HomeCategory> getAllHomeCategories() {
        return homeCategoryRepository.findAll();
    }
}
