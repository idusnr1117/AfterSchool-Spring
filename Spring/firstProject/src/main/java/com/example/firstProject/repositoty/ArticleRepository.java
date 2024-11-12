package com.example.firstProject.repositoty;

import com.example.firstProject.entitiy.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {



}
