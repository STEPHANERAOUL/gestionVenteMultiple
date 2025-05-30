package com.commerce.controller;

import com.commerce.model.domain.OrderStatus;
import com.commerce.model.entity.Order;
import com.commerce.model.entity.Seller;
import com.commerce.service.OrderService;
import com.commerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/orders")
public class SellerOrderController {

    private final OrderService orderService;
    private final SellerService sellerService;


    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<Order> order = orderService.sellersOrder(seller.getId());
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);

    }
    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId,
            @PathVariable OrderStatus orderStatus

            ) throws Exception {


        Order orders=orderService.updateOrderStatus(orderId,orderStatus);
        return new ResponseEntity<>(orders ,HttpStatus.ACCEPTED);

    }

  //  @DeleteMapping("/{orderId}/delete")



}
