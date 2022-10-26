package com.example.comumnity;

import com.example.comumnity.domain.user.domain.QUser;
import com.example.comumnity.domain.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class CommunityApplicationTests {

    @Autowired
    private EntityManager em;

    @Test
    void contextLoads() {

        User user = new User();
        em.persist(user);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUser qUser = QUser.user;

        User result = queryFactory.selectFrom(qUser).fetchOne();

        Assertions.assertThat(result).isEqualTo(user);
        Assertions.assertThat(result.getId()).isEqualTo(user.getId());
    }



}
