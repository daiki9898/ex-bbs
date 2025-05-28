package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * コメントを表すフォーム.
 */
@Getter
@Setter
@ToString
public class CommentForm {
    /** 名前 */
    @NotBlank(message = "名前を入力してください")
    private String name;
    /** コメント */
    @NotBlank(message = "コメントを入力してください")
    private String content;
    /** 記事id(外部key参照:Article.id) */
    @NotNull(message = "記事のidは空ではいけません")
    private Integer articleId;
}
