package com.commerce.model.entity;

import com.commerce.config.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;


    private String code;
   //pourcentage de remise
    private  double discountPercentage;
    //Validité de la date locale Date de début
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    //valeur minimale de la commande
    private double minimumOrdervalue;

    private  boolean isActive = true;
    @ManyToMany(mappedBy = "usedCoupons")
    private Set<User> usedByUsers = new HashSet<>();

}
