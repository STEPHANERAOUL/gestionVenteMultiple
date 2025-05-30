package com.commerce.service;

import com.commerce.model.entity.Seller;
import com.commerce.model.entity.SellerReport;

public interface SellerReportService {
SellerReport getSellerReport(Seller seller);
SellerReport updateSellerReport(SellerReport sellerReport);

}
