package com.bezkoder.spring.security.postgresql.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtistRequest {
    @NotBlank
    @Size(max = 20)
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


}
