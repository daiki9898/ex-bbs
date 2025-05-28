package com.example.service;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 記事を操作するサービス.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    /**
     * 記事を保存する.
     *
     * @param article 記事
     */
    public void save(Article article) {
        articleRepository.save(article);
    }

    /**
     * 記事一覧を新規順で取得する.
     *
     * @return 記事一覧
     */
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    /**
     * 記事を削除する.
     *
     * @param id id
     */
    public void delete(Integer id) {
        articleRepository.delete(id);
    }
}
