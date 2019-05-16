package com.pieterjd.familiefeest.repository;
import com.pieterjd.familiefeest.domain.Purchase;
import com.pieterjd.familiefeest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
}
