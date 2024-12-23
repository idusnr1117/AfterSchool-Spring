package com.example.firstProject.service;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repositoty.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service // 해당 클래스를 서비스로 인식하여 스프링 부트에 객체를 생성(등록)
public class ArticleService {

    @Autowired // DI(의존주입)
    private ArticleRepository articleRepository;

    public List<Article> index()
    {
        return articleRepository.findAll();
    }

    public Article show(Long id)
    {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto)
    {
        Article article = dto.toEntity();
        if (article.getId() != null)
        {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto)
    {
        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();

        // 2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || !target.getId().equals(article.getId()))
        {
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id)
    {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null)
        {
            return null;
        }

        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional // 해당 메소드를 트랜잭션으로 묶는다!
    public List<Article> createArticles(List<ArticleForm> dtos)
    {
//      dto 믂음을 entity 묶음으로 변환
//      List<Article> articleList = new ArrayList<>();

//      for (ArticleForm dto : dtos)
//      {
//          Article entity = dto.toEntity();
//          articleList.add(entity);
//      }

        List<Article> articleList = dtos.stream().map
                (dto -> dto.toEntity()).collect(Collectors.toList());
//      List<Article> articleList = dtos.stream().map
//              (ArticleForm::toEntity).toList();

//      entity 묶음을 DB로 저장
        articleList.stream().forEach(article -> articleRepository.save(article));
//      articleList.forEach(article -> articleRepository.save(article));

//      강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalAccessError("오류 발생"));

//      결과값 반환
        return articleList;
    }

}
