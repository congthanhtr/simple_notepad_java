package com.example.seveneleven;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.shopping.model.Cart;
import com.example.shopping.model.MyUser;
import com.example.shopping.repository.MyUserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestMyUser {
    @Autowired
    private MyUserRepository userRepository;
    @Test
    public void addUser() {
        MyUser user = new MyUser("admin", "admin", "ADMIN", "0906034702", "HN");
        userRepository.save(user);
    }   
}
