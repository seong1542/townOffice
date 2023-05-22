package com.nhnacademy.townoffice.repository;

import com.nhnacademy.townoffice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
