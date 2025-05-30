package com.commerce.repository;

import com.commerce.model.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository  extends JpaRepository<Deal,Long> {
}
