package com.commerce.service.impl;

import com.commerce.model.domain.HomeCategorySection;
import com.commerce.model.entity.Deal;
import com.commerce.model.entity.Home;
import com.commerce.model.entity.HomeCategory;
import com.commerce.repository.DealRepository;
import com.commerce.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

private  final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {
    List<HomeCategory> gridCategories = allCategories.stream().toList().
            stream().filter(homeCategory -> homeCategory.getSection()==
            HomeCategorySection.GRID).collect(Collectors.toList());
        List<HomeCategory> shopByCategories = allCategories.stream().filter(homeCategory ->
                homeCategory.getSection()==HomeCategorySection.SHOP_BY_CATEGORIES )
                .collect(Collectors.toList());
        List<HomeCategory> electricCategories = allCategories
                .stream().filter(homeCategory ->
                        homeCategory.getSection()==HomeCategorySection.ELECTRIC_CATEGORIES)
                .collect(Collectors.toList());
        List<HomeCategory>dealCategories = allCategories.stream().
                filter(homeCategory -> homeCategory.getSection()==HomeCategorySection.DEALS).toList();

        List<Deal>createdDeal =new ArrayList<>();
        if (dealRepository.findAll().isEmpty()){
            List<Deal> deals = allCategories.stream()
                    .filter(homeCategory -> homeCategory.getSection()==HomeCategorySection.DEALS).map(
                            homeCategory -> new Deal(null,10,homeCategory)

                    ).collect(Collectors.toList());
            createdDeal=dealRepository.saveAll(deals);

        }else createdDeal = dealRepository.findAll();
        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectricCategories(electricCategories);
        home.setDeal(createdDeal);
        home.setDealCategories(dealCategories);
        return home;


    }
}
