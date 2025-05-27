package com.example.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Integer id;
    private String name;
    private String content;
    private Integer articleId;
}
