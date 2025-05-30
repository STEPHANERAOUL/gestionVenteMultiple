package com.commerce.service;

import com.commerce.model.entity.Deal;

import java.util.List;

public interface DealService {
    List<Deal> getDeals();
    Deal createDeal(Deal deal);
    Deal updateDeal(Deal deal);
    void deleteDeal(Deal deal);





}
