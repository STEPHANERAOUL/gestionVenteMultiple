package com.commerce.service.impl;

import com.commerce.model.entity.Deal;
import com.commerce.model.entity.HomeCategory;
import com.commerce.repository.DealRepository;
import com.commerce.repository.HomeCategoryRepository;
import com.commerce.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;


    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategory homeCategory =homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
        Deal newDeal = dealRepository.save(deal);
        newDeal.setCategory(homeCategory);
        newDeal.setDiscount(deal.getDiscount());

        return dealRepository.save(newDeal);
    }

    @Override
    public Deal updateDeal(Deal deal) {
        return null;
    }

    @Override
    public void deleteDeal(Deal deal) {

    }
}
