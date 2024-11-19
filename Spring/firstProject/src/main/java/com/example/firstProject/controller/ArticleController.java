package com.example.firstProject.controller;

import com.example.firstProject.entity.Article;
import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.repositoty.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결!
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm()
    {
        return "articles/new";
    }

    @PostMapping("/articles/create")
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

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) // 주소 줄에 있는 변수를 가져온다.
    {
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // id를 가져오는데 없을 경우를 예외처리 하지 않으면 오류가 뜨기에 예외처리를 하라고 오류를 띄움
        // Optional<Article> articleEntity = articleRepository.findById(id);

        // 2. 가져온 데이터를 모델에 등록함.
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정!
        return "articles/show";
    }

}