package com.commerce.service.impl;

import com.commerce.model.entity.Seller;
import com.commerce.model.entity.SellerReport;
import com.commerce.repository.SellerReportRepository;
import com.commerce.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
    private  final SellerReportRepository sellerReportRepository;



    @Override
    public SellerReport getSellerReport(Seller seller) {

        SellerReport sr= sellerReportRepository.findAllBySellerId(seller.getId());


        if (sr==null){

            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sr;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
