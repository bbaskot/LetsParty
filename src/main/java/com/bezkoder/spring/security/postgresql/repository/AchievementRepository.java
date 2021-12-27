package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.models.Achievement;
import com.bezkoder.spring.security.postgresql.models.EAchievements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    @Override
    Optional<Achievement> findById(Long aLong);
    Optional<Achievement> findByName(EAchievements name);
}
