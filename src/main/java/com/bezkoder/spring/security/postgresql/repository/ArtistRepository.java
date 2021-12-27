package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
    Optional<Artist> findByArtistName(String artistName);
    Optional<Artist> findById(Long id);
}
