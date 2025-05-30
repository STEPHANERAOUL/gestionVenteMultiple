package com.commerce.repository;

import com.commerce.model.entity.SellerReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReportRepository extends JpaRepository<SellerReport,Long> {
    SellerReport findAllBySellerId(Long sellerId);


}
