package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.domain.Purchase;
import com.pieterjd.familiefeest.repository.EventItemRepository;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import com.pieterjd.familiefeest.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final EventRegistrationRepository eventRegistrationRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseController(EventRegistrationRepository err, PurchaseRepository pr) {
        this.eventRegistrationRepository = err;
        this.purchaseRepository = pr;
    }


    @GetMapping("/{eventCode}")
    public List<Purchase> getPurchases(@PathVariable String eventCode) {
        return eventRegistrationRepository
                .findByCodeEquals(eventCode)
                .orElseThrow(() -> new RuntimeException("Invalid eventCode"))
                .getPurchasedItems();
    }


    @PostMapping("/{eventCode}")
    public Purchase addPurchase(@PathVariable String eventCode, @RequestBody Purchase purchase) {
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("Invalid eventcode"));
        System.out.println("EVENTREGISTRATION: " + er.getCode());
        System.out.println("PURCHASING - " + purchase.toString());
        purchaseRepository.save(purchase);
        er.getPurchasedItems().add(purchase);
        eventRegistrationRepository.save(er);
        return purchase;

    }

    @DeleteMapping("/{eventCode}")
    public void deletePurchase(@PathVariable String eventCode, @RequestBody Purchase purchase) {
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("Invalid eventcode"));
        er.getPurchasedItems().remove(purchase);
        eventRegistrationRepository.save(er);
        purchaseRepository.delete(purchase);
    }
}
