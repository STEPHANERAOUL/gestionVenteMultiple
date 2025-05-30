package com.commerce.service;

import com.commerce.config.entity.User;
import com.commerce.model.entity.Order;
import com.commerce.model.entity.PaymentOrder;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import java.util.Set;

public interface PaymentService {

    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrdeById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean ProccedPaymentOrder(PaymentOrder paymentOrder,
                                String paymentId,
                                String paymentLinkId) throws RazorpayException;

    PaymentLink createRazorpayPaymentLink(User user, Long amount,
                                          Long orderId) throws RazorpayException;

    String createStripePaymentLink(User user,Long amount, Long orderId) throws StripeException;



}
