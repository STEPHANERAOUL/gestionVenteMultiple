package com.commerce.service;

import com.commerce.model.entity.Order;
import com.commerce.model.entity.Seller;
import com.commerce.model.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionsBySellerId(Seller seller);
    List<Transaction> getAllTransactions();







}
