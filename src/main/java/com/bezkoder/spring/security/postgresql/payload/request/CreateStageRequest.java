package com.bezkoder.spring.security.postgresql.payload.request;

import com.bezkoder.spring.security.postgresql.models.Concert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CreateStageRequest {
    @Size(max = 50)
    private String stageName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Set<Long> concertIds;
}
