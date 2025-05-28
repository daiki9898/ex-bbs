package com.example.form;

import jakarta.validation.constraints.NotBlank;
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
    private String name;
    /** 投稿内容 */
    @NotBlank(message = "投稿内容を入力してください")
    private String content;
}
