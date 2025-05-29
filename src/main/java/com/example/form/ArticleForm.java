package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 記事を保持するフォーム.
 */
@Getter
@Setter
@ToString
public class ArticleForm {
    /** 投稿者名 */
    @NotBlank(message = "投稿者名を入力してください")
    @Pattern(regexp = "^.{0,50}", message = "投稿者名は50字以内で入力してください")
    private String articleName;

    /** 投稿内容 */
    @NotBlank(message = "投稿内容を入力してください")
    @Pattern(regexp = "^.{0,100}", message = "投稿内容は100字以内で入力してください")
    private String articleContent;
}
