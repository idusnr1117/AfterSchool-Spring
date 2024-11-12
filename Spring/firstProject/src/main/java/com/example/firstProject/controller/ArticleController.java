package com.example.firstProject.controller;

import com.example.firstProject.entitiy.Article;
import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.repositoty.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결!
    private ArticleRepository articleRepository;

    @GetMapping("/article/new")
    public String newArticleForm()
    {
        return "article/new";
    }

    @PostMapping("/article/create")
    public String createArticle(ArticleForm form)
    {
        log.info(form.toString());
//        System.out.println(form.toString());

        // 1. Dto 객체를 변환 -> Entity!!
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 명령!!
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "";
    }

}