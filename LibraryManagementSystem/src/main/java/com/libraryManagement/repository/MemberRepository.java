package com.libraryManagement.repository;

import com.libraryManagement.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Member getMemberById(Long memberId) {
        return entityManager.find(Member.class, memberId);
    }
}
