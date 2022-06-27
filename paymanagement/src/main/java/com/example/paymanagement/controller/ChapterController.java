package com.example.paymanagement.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.paymanagement.model.Category;
import com.example.paymanagement.model.Chapter;
import com.example.paymanagement.model.MoneyIn;
import com.example.paymanagement.model.MoneyOut;
import com.example.paymanagement.service.CategoryService;
import com.example.paymanagement.service.ChapterService;
import com.example.paymanagement.service.MoneyInService;
import com.example.paymanagement.service.MoneyOutService;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MoneyInService moneyInService;

    @Autowired
    private MoneyOutService moneyOutService;;

    @GetMapping("/{chapter_id}")
    public String viewChapter(@PathVariable("chapter_id") long chapterId, Model model) {
        model.addAttribute("chapter", chapterService.getChapterById(chapterId));
        model.addAttribute("moneyIn", moneyInService.getMoneyInByChapterId(chapterId));
        model.addAttribute("moneyOut", moneyOutService.getMoneyOutByChapterId(chapterId));
        return "chapterhome";
    }

    @GetMapping("/{chapter_id}/new")
    public String newRecord(@PathVariable("chapter_id") long chapterId, Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        // model.addAttribute("chapter", chapterService.getChapterById(chapterId));

        return "new_record";
    }

    @PostMapping("/{chapter_id}/new")
    public String newRecord(@PathVariable("chapter_id") long chapterId,
                            // @ModelAttribute("chapter") Chapter chapter, 
                            @RequestParam("category_choice") Category category_choice, 
                            @RequestParam("amount") long money,
                            @RequestParam("thuchi") String thuchi,
                            @RequestParam("description") String description) {
                                
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  

        Chapter chapter = chapterService.getChapterById(chapterId);

        if (thuchi.equals("money_in")) {
            moneyInService.saveMoneyIn(new MoneyIn(dtf.format(now), money, description, category_choice, chapter));
            chapterService.updateTotalIn(chapter, money);
        }
        else {
            moneyOutService.saveMoneyOut(new MoneyOut(dtf.format(now), money, description, category_choice, chapter));
            chapterService.updateTotalOut(chapter, money);
        }
        
        return "redirect:/chapter/" + chapterId;
    }

    @GetMapping("/{chapter_id}/edit")
    public String editChapter(@PathVariable("chapter_id") long chapterId, Model model) {
        model.addAttribute("chapter", chapterService.getChapterById(chapterId));
        return "edit_chapter";
    }

    @PostMapping("/{chapter_id}/edit")
    public String editChapter(@PathVariable("chapter_id") long chapterId,
                                @RequestParam("chapter_name") String chapter_name,
                                @RequestParam("total_in") long total_in,
                                @RequestParam("total_out") long total_out) {
        Chapter chapter = chapterService.getChapterById(chapterId);
        chapter.setChapter_name(chapter_name);
        chapter.setTotal_in(total_in);
        chapter.setTotal_out(total_out);
        chapterService.saveChapter(chapter);
        return "redirect:/chapter/" + chapterId;
    }

    @PostMapping("/{chapter_id}/delete")
    public String deleteChapter(@PathVariable("chapter_id") long chapterId) {
        chapterService.deleteChapter(chapterId);
        return "redirect:/";
    }
}
