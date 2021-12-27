package com.bezkoder.spring.security.postgresql.payload.request;

import com.bezkoder.spring.security.postgresql.models.EAchievements;
import com.bezkoder.spring.security.postgresql.models.User;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class CreateUserProfileRequest {
    private String firstName;
    private String lastName;
    private String photo;
    private Integer points;
    private String phone;
    private Set<EAchievements> achievements;
    private Set<Long> eventIds;
    private Long userId;

}
