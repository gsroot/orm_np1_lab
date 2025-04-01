package com.example.orm_np1_lab;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    private Long userId;
    private LocalDateTime orderDate;

    // ... 생략: getter, setter
}
