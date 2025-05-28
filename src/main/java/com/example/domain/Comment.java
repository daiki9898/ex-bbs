package com.example.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * コメントを表すドメイン.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    /** 主キー(id) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名前 */
    private String name;

    /** コメント内容 */
    private String content;

    /** 記事id */
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
