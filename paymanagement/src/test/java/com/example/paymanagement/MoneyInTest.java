package com.example.paymanagement;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.paymanagement.model.MoneyIn;
import com.example.paymanagement.repository.MoneyInRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class MoneyInTest {

    @Autowired
    private MoneyInRepository moneyInService;

    @Test
    public void getByChaperId() {
        List<MoneyIn> test = moneyInService.getAllMoneyInByChapterId(1);
        for (MoneyIn moneyIn : test) {
            System.out.println(moneyIn.getMoney_in_id());
        }
        Assertions.assertThat(test).isNotNull();
    }
}
