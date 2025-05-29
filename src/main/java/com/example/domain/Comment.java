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
    private String commentName;
    /** コメント内容 */
    private String commentContent;
    /** 記事id(外部key参照:Article.id) */
    private Integer articleId;
}
