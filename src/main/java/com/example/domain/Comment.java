package com.example.domain;

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
public class Comment {
    /** 主キー(id) */
    private Integer id;
    /** 名前 */
    private String name;
    /** コメント */
    private String content;
    /** 記事id(外部key参照:Article.id) */
    private Integer articleId;
}
