package com.commerce.model.entity;

import com.commerce.config.entity.User;
import com.commerce.model.domain.PaymentMethod;
import com.commerce.model.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private  Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;

    private  String paymentLinkId;


    @ManyToOne
    private User user ;
    @OneToMany
    private Set<Order> orders = new HashSet<>();






}
