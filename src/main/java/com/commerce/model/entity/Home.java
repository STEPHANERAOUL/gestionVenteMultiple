package com.commerce.model.entity;



import lombok.Data;


import java.util.List;


@Data

public class Home {
   private List<HomeCategory> grid;
   private List<HomeCategory> ShopByCategories;
    private List<HomeCategory> electricCategories;
    private List<HomeCategory> dealCategories;
    private List<Deal> deal;



}
