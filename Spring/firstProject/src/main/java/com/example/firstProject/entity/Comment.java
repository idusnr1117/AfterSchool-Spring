package com.example.firstProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러 개가, 하나의 Article의 연관된다. 즉, 다대일관계이다.
    @JoinColumn(name = "article_id") // "articleId" 칼럼에 Article의 대표값을 저장!!
    private Article article;

    @Column
    private String nickname;
    @Column
    private String body;

}
