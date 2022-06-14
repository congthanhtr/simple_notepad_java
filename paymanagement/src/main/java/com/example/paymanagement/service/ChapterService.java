package com.example.paymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paymanagement.model.Chapter;
import com.example.paymanagement.repository.ChapterRepository;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public void saveChapter(Chapter chapter) {
        chapterRepository.save(chapter);
    }

    public void updateChapter(Chapter chapter) {
        chapterRepository.save(chapter);
    }

    public void deleteChapter(long id) {
        chapterRepository.deleteById(id);
    }

    public Chapter getChapterById(long id) {
        return chapterRepository.findById(id).get();
    }

    public List<Chapter> getAllChapter() {
        return (List<Chapter>) chapterRepository.findAll();
    }

    public void updateTotalIn(Chapter chapter, Long totalIn) {
        chapter.setTotal_in(chapter.getTotal_in() + totalIn);
        chapterRepository.save(chapter);
    }

    public void updateTotalOut(Chapter chapter, Long totalOut) {
        chapter.setTotal_out(chapter.getTotal_out() + totalOut);
        chapterRepository.save(chapter);
    }
}
