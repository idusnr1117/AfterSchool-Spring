package com.example.firstProject.service;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링부트와 연동한 통합 테스트 수행
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void index()
    {
        // 예상 결과
        Article a = new Article(1L, "가가가가가", "1111");
        Article b = new Article(2L, "나나나나나", "2222");
        Article c = new Article(3L, "다다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제 결과
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void showSuccess()
    {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가가", "1111");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void showFail()
    {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void createSuccess()
    {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void createFail()
    {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void updateSuccess()
    {
        // 예상
        Long id = 1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article article = dto.toEntity();

        // 실제
        Article target = articleService.update(id, dto);

        // 비교
        assertEquals(article.toString(), target.toString());
    }

    @Test
    @Transactional
    void updateSuccessNoContent()
    {
        // 예상
        Long id = 1L;
        String title = "가나다라";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article excepted = new Article(id, title, "1111");

        // 실제
        Article target = articleService.update(id, dto);

        // 비교
        assertEquals(excepted.toString(), target.toString());
    }

    @Test
    @Transactional
    void updateFail()
    {
        // 예상
        Long id = -1L;
        String title = "가나다라";
        String content = "1234329482";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article excepted = null;

        // 실제
        Article target = articleService.update(id, dto);

        // 비교
        assertEquals(excepted, target);
    }

    @Test
    @Transactional
    void deleteSuccess()
    {
        // 예상
        Long id = 1L;
        Article excepted = new Article(id, "가가가가가", "1111");

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(excepted.toString(), article.toString());
    }

    @Test
    @Transactional
    void deleteFail()
    {
        // 예상
        Long id = -1L;
        Article excepted = null;

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(excepted, article);
    }

}