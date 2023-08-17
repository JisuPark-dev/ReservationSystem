package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.StoreDto;
import zerobase.reservation.service.StoreService;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@Transactional
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/store")
    public ResponseEntity<Store> create(
            @RequestBody StoreDto storeDto) {
        Store store = storeService.join(storeDto);
        return ResponseEntity.ok(store);
    }

}
