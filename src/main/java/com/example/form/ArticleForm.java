package com.example.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleForm {
    @NotBlank(message = "投稿者名を入力してください")
    private String name;
    @NotBlank(message = "投稿内容を入力してください")
    private String content;
}
