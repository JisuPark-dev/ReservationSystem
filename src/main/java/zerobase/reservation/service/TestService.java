package zerobase.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.dao.Test;
import zerobase.reservation.repository.TestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void join(String text) {
        Test test = Test.builder()
                .text(text)
                .build();
        testRepository.save(test);
    }

    public List<Test> findALl() {
        return testRepository.findAll();
    }

}
