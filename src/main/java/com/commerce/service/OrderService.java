package com.commerce.service;

import com.commerce.config.entity.User;
import com.commerce.model.domain.OrderStatus;
import com.commerce.model.entity.Address;
import com.commerce.model.entity.Cart;
import com.commerce.model.entity.Order;
import com.commerce.model.entity.OrderItem;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Order> createOrder(User user, Address shippingAdress, Cart cart);
    Order findOrderById(Long id) throws Exception;
    List<Order> ussersOrderHistory(Long userId);
    List<Order> sellersOrder(Long sellerId);
    Order updateOrderStatus(Long orderId , OrderStatus orderStatus) throws Exception;
   Order cancelOrder(Long orderId, User user) throws Exception;
  OrderItem getOrderItemById(Long id) throws Exception;




}
