package com.example.controller;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 記事情報(記事,コメント)を操作するコントローラ.
 */
@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    /**
     * 投稿画面を表示する.
     *
     * @param articleForm 記事
     * @param commentForm　コメント
     * @param model モデル
     * @return 投稿画面
     */
    @GetMapping
    public String index(ArticleForm articleForm, CommentForm commentForm, Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "bbs";
    }

    /**
     * 記事を投稿する.
     * (異常系:投稿画面に戻る)
     *
     * @param articleForm 記事
     * @param bindingResult エラーメッセージ一覧
     * @param commentForm コメント
     * @param model モデル
     * @return 投稿画面
     */
    @PostMapping("/post-article")
    public String postArticle(@Validated ArticleForm articleForm, BindingResult bindingResult,
                              CommentForm commentForm, Model model) {
        if (bindingResult.hasErrors()) {
            return index(articleForm, commentForm, model);
        }
        // フォームの中身をドメインに詰める.
        Article article = Article.builder()
                .name(articleForm.getArticleName())
                .content(articleForm.getArticleContent())
                .build();
        articleService.save(article);
        return "redirect:/";
    }

    /**
     * 記事を削除する.
     *
     * @param id id
     * @return 投稿画面
     */
    @GetMapping("/delete-article/{id}")
    public String deleteArticle(@PathVariable Integer id) {
        articleService.delete(id);
        return "redirect:/";
    }

    /**
     * コメントを投稿する.
     *(異常系:投稿画面に戻る)
     *
     * @param commentForm コメント
     * @param bindingResult エラーメッセージ一覧
     * @param articleForm 記事
     * @param model モデル
     * @return 投稿画面
     */
    @PostMapping("/post-comment")
    public String postComment(@Validated CommentForm commentForm, BindingResult bindingResult,
                              ArticleForm articleForm, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("targetArticleId", commentForm.getArticleId());
            return index(articleForm, commentForm, model);
        }
        // フォームの中身をドメインに詰める.
        Comment comment = Comment.builder()
                .name(commentForm.getCommentName())
                .content(commentForm.getCommentContent())
                .article(articleService.findById(commentForm.getArticleId()))
                .build();
        commentService.save(comment);
        return "redirect:/";
    }
}
