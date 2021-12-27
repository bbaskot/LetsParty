package com.bezkoder.spring.security.postgresql.payload.response;

import java.time.LocalDateTime;

public class CreateArtistResponse {
    private Long id;
    private String artistName;
    private String genre;
    private String description;
    private String facebook;
    private String instagram;
    private String spotify;
    private String youtube;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CreateArtistResponse(Long id, String artistName, String genre, String description, String facebook, String instagram, String spotify, String youtube, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.artistName = artistName;
        this.genre = genre;
        this.description = description;
        this.facebook = facebook;
        this.instagram = instagram;
        this.spotify = spotify;
        this.youtube = youtube;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
