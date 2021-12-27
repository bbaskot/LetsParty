package com.bezkoder.spring.security.postgresql.payload.request;

import com.bezkoder.spring.security.postgresql.models.Concert;
import com.bezkoder.spring.security.postgresql.models.EStatus;
import com.bezkoder.spring.security.postgresql.models.Location;
import com.bezkoder.spring.security.postgresql.models.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {
    public String eventName;

    private Set<Long> stageIds;
    private EStatus status;
    private String description;

    private Long locationId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String photo;
    private Long points;
    private String facebook;
    private String instagram;
    private String youtube;
}
