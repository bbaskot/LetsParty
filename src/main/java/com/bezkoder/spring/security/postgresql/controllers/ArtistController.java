package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Artist;
import com.bezkoder.spring.security.postgresql.payload.request.CreateArtistRequest;
import com.bezkoder.spring.security.postgresql.payload.request.LoginRequest;
import com.bezkoder.spring.security.postgresql.payload.request.UpdateArtistRequest;
import com.bezkoder.spring.security.postgresql.payload.response.CreateArtistResponse;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/artist")
public class ArtistController {
        @Autowired
        ArtistRepository artistRepository;
        @PostMapping("/save")
        public ResponseEntity<?> createArtist(@Valid @RequestBody CreateArtistRequest createArtistRequest) {
          if(artistRepository.findByArtistName(createArtistRequest.getArtistName()).isPresent()){
               return ResponseEntity
                     .badRequest()
                     .body(new MessageResponse("Error: Artist name is already taken!"));
         }

            Artist artist=new Artist();
            artist.setArtistName(createArtistRequest.getArtistName());
            artist.setDescription(createArtistRequest.getDescription());
            artist.setGenre(createArtistRequest.getGenre());
            artist.setInstagram(createArtistRequest.getInstagram());
            artist.setFacebook(createArtistRequest.getFacebook());
            artist.setYoutube(createArtistRequest.getYoutube());
            artist.setPhoto(createArtistRequest.getPhoto());
            artist.setCreatedAt(LocalDateTime.now());
            artist.setUpdatedAt(LocalDateTime.now());
            artistRepository.save(artist);
            return ResponseEntity.ok().body(artist);

        }
        /*@GetMapping("/{artistName}")
        public ResponseEntity<?> getArtist(@PathVariable(value ="artistName") String artistName){
            Artist artist = artistRepository.findByArtistName(artistName).orElseThrow();
            return ResponseEntity.ok(new CreateArtistResponse(
                artist.getId(),
                artist.getArtistName(),
                artist.getGenre(),
                artist.getDescription(),
                artist.getFacebook(),
                artist.getInstagram(),
                artist.getSpotify(),
                artist.getYoutube(),
                artist.getCreatedAt(),
                artist.getUpdatedAt())
            );

        }*/
        @GetMapping("/{artistId}")
        public ResponseEntity<?> getArtist(@PathVariable(value ="artistId") Long artistId){
            Artist artist = artistRepository.findById(artistId).orElseThrow();
            return ResponseEntity.ok().body(artist);

        }
        @GetMapping("/all")
        public ResponseEntity<?> getAllArtists(){
            return ResponseEntity.ok(artistRepository.findAll());
        }
        @DeleteMapping("/delete/{artistId}")
        public String deleteUser(@PathVariable(value="artistId") Long artistId){
            if(artistRepository.findById(artistId).isPresent()) {
                artistRepository.deleteById(artistId);
                return "Artist deleted. ";
            }else {
                return "Can't find artist. ";
            }
        }
        @PutMapping("/update/{artistId}")
        public ResponseEntity<?> updateArtist(@PathVariable(value="artistId") Long artistId, @Valid @RequestBody UpdateArtistRequest updateArtistRequest)throws RuntimeException{

            Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new RuntimeException("Bilo sta"));
            if(updateArtistRequest.getArtistName() != null)
                artist.setArtistName(updateArtistRequest.getArtistName());
            if(updateArtistRequest.getDescription() != null)
                artist.setDescription(updateArtistRequest.getDescription());
            if(updateArtistRequest.getFacebook() != null)
                artist.setFacebook(updateArtistRequest.getFacebook());
            if(updateArtistRequest.getGenre() != null)
                artist.setGenre(updateArtistRequest.getGenre());
            if(updateArtistRequest.getInstagram() != null)
                artist.setInstagram(updateArtistRequest.getInstagram());
            if(updateArtistRequest.getSpotify() != null)
                artist.setSpotify(updateArtistRequest.getSpotify());
            if(updateArtistRequest.getYoutube() != null)
                artist.setYoutube(updateArtistRequest.getYoutube());
            artist.setUpdatedAt(LocalDateTime.now());
            if(updateArtistRequest.getPhoto()!=null)
                artist.setPhoto(updateArtistRequest.getPhoto());
            artistRepository.save(artist);
            return ResponseEntity.ok().body(artist);


        }
}
