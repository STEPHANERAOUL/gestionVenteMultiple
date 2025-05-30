package com.commerce.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private  Seller seller;

    private Long totalEarnings=0L;
    private Long totalSals= 0L;
    private Long totalRefunds = 0L;
    private Long totalTax = 0L;
    private Long netEarning = 0L;
    private Integer totalOrders = 0;
    private Integer canceledOrders= 0;
    private Integer totalTransactions = 0;



}
