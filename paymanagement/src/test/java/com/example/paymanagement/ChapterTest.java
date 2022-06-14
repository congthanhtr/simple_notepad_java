package com.example.paymanagement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.paymanagement.model.Chapter;
import com.example.paymanagement.repository.ChapterRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ChapterTest {
    @Autowired
    private ChapterRepository chapterRepository;

    @Test 
    public void testAdd() {
        Chapter chapter = new Chapter();
        chapter.setChapter_name("Thang 3");
        chapter.setTotal_in(1000);
        chapter.setTotal_out(0);
        Chapter test = chapterRepository.save(chapter);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getChapter_name().equals("Thang 3"));
    }

    @Test 
    public void testgetMoney() {
        Chapter chapter = chapterRepository.findById(Long.valueOf(1)).get();
        Assertions.assertThat(chapter.getTotal_in()).isEqualTo(20000);

    }
}
