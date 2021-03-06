package com.bezkoder.spring.security.postgresql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "achievements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EAchievements name;
}
