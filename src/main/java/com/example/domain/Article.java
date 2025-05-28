package com.example.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * 記事を表すドメイン.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class Article {
    /** 主キー(id) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 投稿者名 */
    private String name;

    /** 投稿内容 */
    private String content;

    /** コメント一覧 */
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "article", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;
}
