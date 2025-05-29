package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^.{0,50}", message = "名前は50字以内で入力してください")
    private String commentName;

    /** コメント内容 */
    @NotBlank(message = "コメントを入力してください")
    @Pattern(regexp = "^.{0,100}", message = "コメントは100字以内で入力してください")
    private String commentContent;

    /** 記事id(外部key参照:Article.id) */
    @NotNull(message = "記事のidは空ではいけません")
    private Integer articleId;
}
