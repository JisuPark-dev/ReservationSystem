package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dto.StoreDto;
import zerobase.reservation.repository.StoreRepository;
import zerobase.reservation.service.StoreService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class StoreController {
    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @PostMapping("/store")
    public ResponseEntity<StoreDto> create(
            @RequestBody StoreDto storeDto
            ) {
        storeDto = storeService.join(storeDto);
        return ResponseEntity.ok(storeDto);
    }

    @GetMapping("/store")
    public ResponseEntity<List<StoreDto>> findAll() {
        List<StoreDto> storeDtos = storeService.findAll();
        return ResponseEntity.ok(storeDtos);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<StoreDto> findById(@PathVariable("storeId") Long id) {
        StoreDto storeDto = storeService.findById(id);
        return ResponseEntity.ok(storeDto);

    }

    @PutMapping("/store/{storeId}")
    public ResponseEntity<StoreDto> update(
            @RequestBody StoreDto storeDto,
            @PathVariable("storeId") Long id
    ) {
        storeDto = storeService.updateStore(storeDto, id);
        return ResponseEntity.ok(storeDto);
    }

    @DeleteMapping("/store/{storeId}")
    public void delete(
            @PathVariable("storeId") Long id
    ) {
        storeService.deleteStore(id);
    }


}
