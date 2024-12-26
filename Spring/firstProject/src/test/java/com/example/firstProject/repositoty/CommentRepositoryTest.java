package com.example.firstProject.repositoty;

import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트!!
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId()
    {

        // 입력 데이터 준비
        Long articleId = 1L;

        // 실제 수행
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 예상
        Article article = new Article(1L, "가가가가가", "1111");
        List<Comment> expected = Arrays.asList();

        assertEquals(expected.toString(), comments.toString(), "1번글은 댓글이 없음");
    }

    @Test
    void findByNickname()
    {
        /* Case 1: "Park"의 모든 댓글 조회 */
        
        // 입력 데이터를 준비
        String nickname = "Park";
        // 실제 수행
        List<Comment> comments = commentRepository.findByNickname(nickname);
        // 예상
        Comment a = new Comment(1L, new Article(4L, "당신의 인생 열화는?", "댓글 ㄱㄱ", nickname, "굳 윌 팅대"));
        Comment b = new Comment(4L, new Article(4L, "당신의 소울 푸드는?", "댓글 ㄱㄱ", nickname, "굳 윌 팅대"));
        Comment c = new Comment(6L, new Article(4L, "당신의 당신의 취미는?", "댓글 ㄱㄱㄱ", nickname, "조깅"));

        List<Comment> expected = Arrays.asList(a, b, c);
        // 검증
        assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력!");
    }

}