package com.commerce.service;

import com.commerce.model.entity.Home;
import com.commerce.model.entity.HomeCategory;

import java.util.List;

public interface HomeService {

    public Home createHomePageData(List<HomeCategory> allCategories);


}
