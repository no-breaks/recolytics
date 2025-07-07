package com.recolytics.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // ✅ avoids conflict with PostgreSQL reserved word
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String role = "USER"; // Default role
}

