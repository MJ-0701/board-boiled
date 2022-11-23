package com.example.comumnity.domain.board.domain.repository;

import com.example.comumnity.domain.board.domain.Board;
import com.example.comumnity.domain.board.domain.QBoard;
import com.example.comumnity.domain.board.web.dto.BoardDto;
import com.example.comumnity.domain.board.web.dto.QBoardDto;
import com.example.comumnity.domain.board.web.dto.search.BoardSearchCondition;
import com.example.comumnity.global.support.QuerydslSupportCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.comumnity.domain.board.domain.QBoard.*;
import static org.springframework.util.StringUtils.hasText;

public class BoardRepositoryImpl extends QuerydslSupportCustom implements BoardRepositoryCustom {

    public BoardRepositoryImpl(EntityManager em) {
        super(Board.class);
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    }





    @Override
    public List<BoardDto> search(BoardSearchCondition condition) {

        return getQueryFactory().select(new QBoardDto(
                board.title,
                board.userId,
                board.password,
                board.gender,
                board.tag,
                board.viewCount,
                board.likeCount,
                board.declaration
                ))
                .from(board)
                .where(
                        userIdEq(condition.getUserId()),
                        titleEq(condition.getTitle())
                )
                .fetch();
    }

    @Override
    public Page<BoardDto> searchPageSimple(BoardSearchCondition condition, Pageable pageable) {
        return null;
    }

    @Override
    public Page<BoardDto> searchPageComplex(BoardSearchCondition condition, Pageable pageable) {
        return null;
    }

    private BooleanExpression userIdEq(String userId) {
        return hasText(userId) ? board.userId.eq(userId) : null;
    }

    private BooleanExpression titleEq(String title) {
        return hasText(title) ? board.title.eq(title) : null;
    }

//    private BooleanExpression ageBetween(int ageLoe, int ageGoe) { // 보여주기식 조합 예쩨
//
//        return ageGoe(ageGoe).and(ageLoe(ageLoe));
//    }

//    private BooleanExpression ageGoe(Integer ageGoe) {
//        return ageGoe != null ? member.age.goe(ageGoe) : null;
//    }
//
//    private BooleanExpression ageLoe(Integer ageLoe) {
//        return ageLoe !=null ? member.age.loe(ageLoe) : null;
//    }
}
