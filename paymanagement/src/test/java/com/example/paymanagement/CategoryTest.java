package com.example.paymanagement;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.paymanagement.model.Category;
import com.example.paymanagement.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testAdd() {
        Category chapter = new Category();
        chapter.setCategory_name("Mua sắm");
        Category test = categoryRepository.save(chapter);


        Assertions.assertThat(test).isNotNull();
    }

    @Test
    public void testgetAll() {
        List<Category> test = (List<Category>) categoryRepository.findAll();

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.get(0).getCategory_name().equals("Giáo dục"));
    }
}