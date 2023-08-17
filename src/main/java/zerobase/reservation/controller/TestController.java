package zerobase.reservation.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.dao.Test;
import zerobase.reservation.service.TestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping("/test")
    void save(@RequestBody String text) {
        testService.join(text);
    }

    @GetMapping("/test")
    List<Test> findAll() {
        return testService.findALl();
    }
}
