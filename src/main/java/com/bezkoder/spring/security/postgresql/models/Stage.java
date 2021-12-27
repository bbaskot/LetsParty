package com.bezkoder.spring.security.postgresql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 50)
    private String stageName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @OneToMany(targetEntity = Concert.class,cascade = CascadeType.ALL)
    @JoinColumn(name="stage_fk",referencedColumnName = "id")
    private Set<Concert> concerts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
