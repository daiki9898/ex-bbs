package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * テーブルarticlesを操作するDao.
 * (findAllでは、JOINのためcommentsテーブルも操作する)
 */
@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final NamedParameterJdbcTemplate template;
    private final JdbcTemplate jdbcTemplate;
    private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = rs -> {
        // 記事のidと、記事のマップ※順番を保証するために、HashMapではなく、LinkedHashMapを使用
        Map<Integer, Article> articleMap = new LinkedHashMap<>();

        while (rs.next()) {
            Integer articleId = rs.getInt("a_id");
            Article article = articleMap.get(articleId);
            // マップに記事が入っていない場合
            if (article == null) {
                // 記事の作成
                article = Article.builder()
                        .id(rs.getInt("a_id"))
                        .articleName(rs.getString("a_name"))
                        .articleContent(rs.getString("a_content"))
                        .comments(new ArrayList<>())
                        .build();
                // 新しく記事を入れる.
                articleMap.put(articleId, article);
            }
            // コメントがある場合のみ
            if (rs.getObject("c_id") != null) {
                // コメントの作成
                Comment comment = Comment.builder()
                        .id(rs.getInt("c_id"))
                        .commentName(rs.getString("c_name"))
                        .commentContent(rs.getString("c_content"))
                        .articleId(rs.getInt("c_article_id"))
                        .build();
                // 記事にコメントを追加していく.
                article.getComments().add(comment);
            }
        }
        // 記事をリストに詰め返す.
        return new ArrayList<>(articleMap.values());
    };

    /**
     * 記事をDBに保存する.
     *
     * @param article 記事
     */
    public void save(Article article) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        String sql = "INSERT INTO articles (name, content) VALUES (:articleName, :articleContent)";
        template.update(sql, param);
    }

    /**
     * 全記事を新規順で取得する.
     *
     * @return 記事一覧
     */
    public List<Article> findAll() {
        String sql = """
                SELECT a.id as a_id, a.name as a_name, a.content as a_content,
                 c.id as c_id, c.name as c_name, c.content as c_content, c.article_id as c_article_id
                 FROM articles as a
                 LEFT OUTER JOIN comments as c
                 ON a.id = c.article_id
                 ORDER BY a.id DESC, c.id DESC
                """;
        return template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);
    }

    /**
     * 記事を削除する.(関連するコメントも消える)
     *
     * @param id id
     */
    public void delete(Integer id) {
        String sql = "DELETE FROM articles WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        template.update(sql, param);
    }
}
