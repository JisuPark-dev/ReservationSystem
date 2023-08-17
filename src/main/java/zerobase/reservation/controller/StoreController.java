package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.StoreDto;
import zerobase.reservation.repository.StoreRepository;
import zerobase.reservation.service.StoreService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Transactional
public class StoreController {
    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @PostMapping("/store")
    public ResponseEntity<Store> create(
            @RequestBody StoreDto storeDto) {
        Store store = storeService.join(storeDto);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/store")
    public ResponseEntity<List<Store>> findAll() {
        List<Store> stores = storeRepository.findAll();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<Store> findById(@PathVariable("storeId") Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isPresent()) {
            return ResponseEntity.ok(storeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
