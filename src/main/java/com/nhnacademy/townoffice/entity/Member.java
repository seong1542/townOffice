package com.nhnacademy.townoffice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Members")
@AllArgsConstructor
public class Member {
    @Id
    private String id;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        ROLE_ADMIN, ROLE_MEMBER, ROLE_GUEST
    }
}
