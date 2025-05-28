package com.example.service;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * コメントを操作するサービス.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * コメントを保存する.
     *
     * @param comment コメント
     */
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
