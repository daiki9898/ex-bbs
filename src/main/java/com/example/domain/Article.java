package com.example.domain;

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
public class Article {
    /** 主キー(id)
     * (外部key:Comment.articleId)
     * */
    private Integer id;
    /** 投稿者名 */
    private String articleName;
    /** 投稿内容 */
    private String articleContent;
    /** コメント一覧 */
    private List<Comment> comments;
}
