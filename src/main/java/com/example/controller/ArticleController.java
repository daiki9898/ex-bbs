package com.example.controller;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public String index(ArticleForm articleForm, CommentForm commentForm, Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "bbs";
    }

    @PostMapping("/post-article")
    public String postArticle(@Validated ArticleForm articleForm, BindingResult bindingResult,
                              CommentForm commentForm, Model model) {
        if (bindingResult.hasErrors()) {
            return index(articleForm, commentForm, model);
        }
        Article article = new Article();
        // フォームの中身をドメインにコピー.
        BeanUtils.copyProperties(articleForm, article);
        articleService.save(article);
        return "redirect:/";
    }

    @GetMapping("/delete-article/{id}")
    public String deleteArticle(@PathVariable Integer id) {
        articleService.delete(id);
        return "redirect:/";
    }

//    @PostMapping
//    public String postComment()
}
