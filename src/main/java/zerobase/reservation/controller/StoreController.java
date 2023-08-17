package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.MemberDto;
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
            @RequestBody StoreDto storeDto
            ) {
        Store store = storeService.join(storeDto);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/store")
    public ResponseEntity<List<Store>> findAll() {
        List<Store> stores = storeService.findAll();
//        List<Store> stores = storeRepository.findAll();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<Store> findById(@PathVariable("storeId") Long id) {
        Store store = storeService.findById(id);
        return ResponseEntity.ok(store);

    }

    @PutMapping("/store/{storeId}")
    public ResponseEntity<Store> update(
            @RequestBody StoreDto storeDto,
            @PathVariable("storeId") Long id
    ) {
        Store store = storeService.updateStore(storeDto, id);
        return ResponseEntity.ok(store);
    }

    @DeleteMapping("/store/{storeId}")
    public void delete(
            @PathVariable("storeId") Long id
    ) {
        storeService.deleteStore(id);
    }


}
