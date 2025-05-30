package com.commerce.controller;

import com.commerce.model.entity.Deal;
import com.commerce.reponse.ApiResponse;
import com.commerce.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/deals")
public class DealController {

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeals(@RequestBody Deal deals){
        Deal createDeals = dealService.createDeal(deals);
        return new ResponseEntity<>(createDeals, HttpStatus.ACCEPTED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long id,
            @RequestBody Deal deal
    ) throws Exception {
        Deal updatedDeal= dealService.updateDeal(deal,id);
        return ResponseEntity.ok(updatedDeal);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeals(@PathVariable Long id) throws Exception {
        dealService.deleteDeal(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deal deleted");
      //  apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);



    }


}
