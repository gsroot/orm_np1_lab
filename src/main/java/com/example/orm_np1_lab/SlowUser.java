package com.example.orm_np1_lab;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "slow_users")
public class SlowUser {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<SlowPost> posts;

    // 🔽 셀프 1:N 관계 (부하 유저들)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "slow_user_subordinates",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "subordinate_id")
    )
    private List<SlowUser> subordinates;

    // 🔽 셀프 N:1 관계 (상위 유저)
    @ManyToOne
    @JoinTable(
            name = "slow_user_subordinates",
            joinColumns = @JoinColumn(name = "subordinate_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id")
    )
    private SlowUser manager;
}
