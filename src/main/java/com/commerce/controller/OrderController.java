package com.commerce.controller;


import com.commerce.config.entity.User;
import com.commerce.config.service.UserService;
import com.commerce.model.domain.PaymentMethod;
import com.commerce.model.entity.*;
import com.commerce.reponse.PaymentLinkResponse;
import com.commerce.repository.PaymentOrderRepository;
import com.commerce.service.*;
import com.razorpay.PaymentLink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private  final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private  final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepository paymentOrderRepository;

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address spippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart= cartService.findUserCart(user);
        Set<Order> orders = orderService.createOrder(user,spippingAddress,cart);

        PaymentOrder paymentOrder = paymentService.createOrder(user,orders);
        PaymentLinkResponse res= new PaymentLinkResponse();
        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink payment=paymentService.createRazorpayPaymentLink(user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());

            String paymentUrl = payment.get("short_url");
            String paymentUrlId = payment.get("id");


            res.setPayment_link_url(paymentUrl);
            res.setGetPayment_link_id(paymentUrlId);
            paymentOrder.setPaymentLinkId(paymentUrlId);
            paymentOrderRepository.save(paymentOrder);

        }
        else {
            String paymentUrl= paymentService.createStripePaymentLink(user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());
            res.setPayment_link_url(paymentUrl);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(
            @RequestHeader("Authorization")
            String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.ussersOrderHistory(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);

    }
    @GetMapping("/{ordersId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId,@RequestHeader("Authorization")
            String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);


    }
@GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @PathVariable Long OrderItemId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        OrderItem orderItem = orderService.getOrderItemById(OrderItemId);
        return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);

}
@PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.cancelOrder(orderId,user);

       Seller seller = sellerService.getSellerById(order.getSellerId());
        SellerReport report= sellerReportService.getSellerReport(seller);
        report.setCanceledOrders(report.getTotalOrders()+1);
        report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
        sellerReportService.updateSellerReport(report);

        return ResponseEntity.ok(order);

}




}
