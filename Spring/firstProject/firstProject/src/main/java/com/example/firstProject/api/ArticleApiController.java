package com.example.firstProject.api;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repositoty.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired // DI(의존주입)
    private ArticleRepository articleRepository;

    // GET(가져오기)
    @GetMapping("/api/articles")
    public List<Article> index()
    {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id)
    {
        return articleRepository.findById(id).orElse(null);
    }

    // POST(보내기)
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto)
    {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH(수정하기)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto)
    {
        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();

        // 2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || !target.getId().equals(article.getId()))
        {
            // 400, 잘못된 요청 응답
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article);
        Article updated =  articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE(삭제하기)
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id)
    {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        // 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
