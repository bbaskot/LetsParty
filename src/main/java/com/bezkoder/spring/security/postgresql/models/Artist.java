package com.bezkoder.spring.security.postgresql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "artists",uniqueConstraints = {
        @UniqueConstraint(columnNames = "artistName")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 50)
    private String artistName;
    @NotBlank
    @Size(max = 50)
    private String genre;
    @Size(max = 150)
    private String description;
    @Size(max = 150)
    private String facebook;
    @Size(max = 150)
    private String instagram;
    @Size(max = 150)
    private String spotify;
    @Size(max = 150)
    private String youtube;
    private String photo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
