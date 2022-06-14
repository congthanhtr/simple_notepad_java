package com.example.paymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paymanagement.model.MoneyOut;
import com.example.paymanagement.repository.MoneyOutRepository;

@Service
public class MoneyOutService {
    @Autowired
    private MoneyOutRepository moneyOutRepository;

    public void saveMoneyOut(MoneyOut moneyOut) {
        moneyOutRepository.save(moneyOut);
    }

    public void updateMoneyOut(MoneyOut moneyOut) {
        moneyOutRepository.save(moneyOut);
    }

    public void deleteMoneyOut(long id) {
        moneyOutRepository.deleteById(id);
    }

    public MoneyOut getMoneyOutById(long id) {
        return moneyOutRepository.findById(id).get();
    }

    public List<MoneyOut> getAllMoneyOut() {
        return (List<MoneyOut>) moneyOutRepository.findAll();
    }

    public List<MoneyOut> getMoneyOutByChapterId(long chapterId) {
        return moneyOutRepository.getAllMoneyOutByChapterId(chapterId);
    }
}
