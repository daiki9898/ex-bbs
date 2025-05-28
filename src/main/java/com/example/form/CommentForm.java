package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentForm {
    @NotBlank(message = "名前を入力してください")
    private String name;
    @NotBlank(message = "コメントを入力してください")
    private String content;
    @NotNull(message = "記事のidは空ではいけません")
    private Integer articleId;
}
