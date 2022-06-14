package com.example.paymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paymanagement.model.MoneyIn;
import com.example.paymanagement.repository.MoneyInRepository;

@Service
public class MoneyInService {
    @Autowired
    private MoneyInRepository moneyInRepository;

    public void saveMoneyIn(MoneyIn moneyIn) {
        moneyInRepository.save(moneyIn);
    }

    public void updateMoneyIn(MoneyIn moneyIn) {
        moneyInRepository.save(moneyIn);
    }

    public void deleteMoneyIn(long id) {
        moneyInRepository.deleteById(id);
    }

    public MoneyIn getMoneyInById(long id) {
        return moneyInRepository.findById(id).get();
    }

    public List<MoneyIn> getAllMoneyIn() {
        return (List<MoneyIn>) moneyInRepository.findAll();
    }

    public List<MoneyIn> getMoneyInByChapterId(long chapterId) {
        return moneyInRepository.getAllMoneyInByChapterId(chapterId);
    }
}
