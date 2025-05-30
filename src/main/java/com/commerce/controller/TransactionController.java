package com.commerce.controller;

import com.commerce.model.entity.Order;
import com.commerce.model.entity.Seller;
import com.commerce.model.entity.Transaction;
import com.commerce.service.SellerService;
import com.commerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final SellerService  sellerService;


    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionBySeller(
                @RequestHeader("Authorization") String jwt ) throws Exception {

        Seller seller = sellerService.getSellerProfile(jwt);
        List<Transaction> transactions =transactionService.getTransactionsBySellerId(seller);
        return  ResponseEntity.ok(transactions);
        }
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions =transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);


    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody Order order){

        Transaction transaction = transactionService.createTransaction(order);
        return ResponseEntity.ok(transaction);

    }






}
