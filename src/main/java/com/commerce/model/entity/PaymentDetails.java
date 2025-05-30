package com.commerce.model.entity;


import com.commerce.model.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    private  String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private  String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;


    private PaymentStatus Status;







}
