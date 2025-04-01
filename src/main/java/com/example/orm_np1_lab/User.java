package com.example.orm_np1_lab;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer depth;

    // 기존 관계: User -> Post
    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.SUBSELECT)
    private List<Post> posts;

    // 🔽 셀프 1:N 관계 (부하 유저들)
    @OneToMany
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "user_subordinates",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "subordinate_id")
    )
    private List<User> subordinates;

    // 🔽 셀프 N:1 관계 (상위 유저)
    @ManyToOne
    @JoinTable(
            name = "user_subordinates",
            joinColumns = @JoinColumn(name = "subordinate_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id")
    )
    private User manager;
}
