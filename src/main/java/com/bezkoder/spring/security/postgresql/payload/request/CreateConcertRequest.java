package com.bezkoder.spring.security.postgresql.payload.request;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateConcertRequest {
    private Long artistId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
}
