package com.example.repository;

import com.example.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * テーブルcommentsを操作するDao.
 */
@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final NamedParameterJdbcTemplate template;
//    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);

    /**
     * コメントをDBに保存する.
     *
     * @param comment コメント
     */
    public void save(Comment comment) {
        String sql = """
                INSERT INTO comments (name, content, article_id)
                 VALUES (:name, :content, :articleId)
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        template.update(sql, param);
    }
}
