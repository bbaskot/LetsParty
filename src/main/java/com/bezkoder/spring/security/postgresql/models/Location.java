package com.bezkoder.spring.security.postgresql.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "locations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CountryCode countryCode;
    private String city;
    private String adress;
    private String region;
    private Float lattitude;
    private Float longitude;
    private Float radius;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
