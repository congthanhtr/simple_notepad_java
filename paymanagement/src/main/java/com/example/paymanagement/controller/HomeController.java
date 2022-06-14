package com.example.paymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.paymanagement.model.Chapter;
import com.example.paymanagement.service.ChapterService;

@Controller
public class HomeController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("allChapters", chapterService.getAllChapter());
        
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/new_chapter")
    public String newChapter(Model model) {
        model.addAttribute("new_chapter", new Chapter());

        return "new_chapter";
    }

    @PostMapping("/new_chapter")
    public String newChapter(@ModelAttribute("new_chapter") Chapter chapter) {
        chapterService.saveChapter(chapter);

        return "redirect:/";
    }
}
