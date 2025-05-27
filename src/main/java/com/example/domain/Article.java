package com.example.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    private Integer id;
    private String name;
    private String content;
    private List<Comment> comments;
}
