package com.commerce.controller;

import com.commerce.config.entity.User;
import com.commerce.config.service.UserService;
import com.commerce.model.domain.PaymentMethod;
import com.commerce.model.entity.Order;
import com.commerce.model.entity.PaymentOrder;
import com.commerce.model.entity.Seller;
import com.commerce.model.entity.SellerReport;
import com.commerce.reponse.ApiResponse;
import com.commerce.reponse.PaymentLinkResponse;
import com.commerce.service.PaymentService;
import com.commerce.service.SellerReportService;
import com.commerce.service.SellerService;
import com.commerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private  final PaymentService paymentService;
    private final SellerService sellerService;
    private final UserService userService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;

/*
    @PostMapping("/{paymentMethod}/order/{orderId)")
    public ResponseEntity<PaymentLinkResponse> paymentHandler(
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        PaymentLinkResponse paymentLinkResponse;

        if (paymentMethod.equals(PaymentMethod.RAZORPAY)){
            paymentLinkResponse= paymentService.createRazorpayPaymentLink(
                    order.getAmount(),
                    order.getId());


        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);

    }*/
    @GetMapping("/{paymentId}")
public ResponseEntity<ApiResponse> paymentSuccessHandler(
        @PathVariable String paymentId,
        @RequestParam String paymentLinkId,
        @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        PaymentLinkResponse paymentLinkResponse;

        PaymentOrder paymentOrder = paymentService
                .getPaymentOrderByPaymentId(paymentLinkId);
        boolean paymentSuccess = paymentService.ProccedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkId

        );
        if (paymentSuccess){
            for(Order order:paymentOrder.getOrders()){
                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport report = sellerReportService.getSellerReport(seller);
                report.setTotalOrders(report.getTotalOrders()+1);
                report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
                report.setTotalSals(report.getTotalSals()+order.getOrderItems().size());

                sellerReportService.updateSellerReport(report);
            }


        }

        ApiResponse res = new ApiResponse();
        res.setMessage("payment successful");
        //res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.CREATED);




    }



}
