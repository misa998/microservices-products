package com.misa.inventoryservice.controller;

import com.misa.inventoryservice.dto.InventoryResponse;
import com.misa.inventoryservice.exception.ApiRequestException;
import com.misa.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        validateSkuCodes(skuCode);
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }

    private void validateSkuCodes(List<String> skuCode) {
        if(skuCode == null || skuCode.isEmpty()){
            throw new ApiRequestException("Please provide sku codes.");
        }
    }
}
