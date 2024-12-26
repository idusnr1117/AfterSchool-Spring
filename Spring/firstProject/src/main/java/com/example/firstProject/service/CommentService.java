package com.example.firstProject.service;

import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import com.example.firstProject.repositoty.ArticleRepository;
import com.example.firstProject.repositoty.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId)
    {
        // 조회 : 댓글 목록
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 변환 : 엔티티 -> dto
        List<CommentDto> dtos = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++)
        {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        // 위 반복문을 스트림으로 변경하여 작성해볼 것!!
        // comments.stream().map(CommentDto::createCommentDto).forEachOrdered(dtos::add);

        // 반환
        return dtos;
    }

    public CommentDto create(Long articleId, CommentDto dto)
    {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다!"));
        
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        
        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // Dto로 변경하여 반환
        return CommentDto.createCommentDto(created);

    }

    public CommentDto update(Long id, CommentDto dto)
    {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다!"));

        // 댓글 수정
        target.patch(dto);

        // DB로 수정
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 Dto로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id)
    {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(target);
        
        // 삭제 댓글을 Dto로 변환
        return CommentDto.createCommentDto(target);
    }

}
