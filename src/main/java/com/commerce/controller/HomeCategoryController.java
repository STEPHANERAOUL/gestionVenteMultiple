package com.commerce.controller;


import com.commerce.config.service.HomeCategoryService;
import com.commerce.config.service.UserService;
import com.commerce.model.entity.Home;
import com.commerce.model.entity.HomeCategory;
import com.commerce.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeCategoryController {
    private final HomeCategoryService homeCategoryService;
    private final UserService userService;
    private final HomeService homeService;


    @GetMapping("/home-page")
    public ResponseEntity<Home> getHomePageDate(){
        //Home homData =
        return  null;

    }

    @PostMapping("/home/categories")
    public  ResponseEntity<Home> createHomeCategories(
            @RequestBody List<HomeCategory> homeCategories

            ){
        List<HomeCategory> categories = homeCategoryService.CreateHomeCategories(homeCategories);
        Home home= homeService.createHomePageData(categories);
        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);

    }
    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory(){
        List<HomeCategory> categories= homeCategoryService.getAllHomeCategories();
        return ResponseEntity.ok(categories);

    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory

    ) throws Exception {
        HomeCategory  updateHomeCategory = homeCategoryService.updateHomeCategory(homeCategory,id);
        return ResponseEntity.ok(updateHomeCategory);

    }


}
