package com.bezkoder.spring.security.postgresql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String eventName;
    @OneToMany(targetEntity = Stage.class,cascade = CascadeType.ALL)
    @JoinColumn(name="event_fk",referencedColumnName = "id")
    private Set<Stage> stages;
    private EStatus status;
    private String description;
    @OneToOne
    @JoinColumn(name = "artist_id")
    private Location location;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String photo;
    private Long points;
    private String facebook;
    private String instagram;
    private String youtube;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
