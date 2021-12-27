package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Concert;
import com.bezkoder.spring.security.postgresql.payload.request.CreateArtistRequest;
import com.bezkoder.spring.security.postgresql.payload.request.CreateConcertRequest;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.ArtistRepository;
import com.bezkoder.spring.security.postgresql.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/concert")
public class ConcertController {
    @Autowired
    ConcertRepository concertRepository;
    @Autowired
    ArtistRepository artistRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createConcert(@Valid @RequestBody CreateConcertRequest createConcertRequest) {
        if(!artistRepository.findById(createConcertRequest.getArtistId()).isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Can't find artist"));
        }
        Concert concert = new Concert();
        concert.setArtist(artistRepository.findById(createConcertRequest.getArtistId()).orElseThrow());
        concert.setStartAt(createConcertRequest.getStartAt());
        concert.setEndAt(createConcertRequest.getEndAt());
        concert.setCreatedAt(LocalDateTime.now());
        concert.setUpdatedAt(LocalDateTime.now());
        concertRepository.save(concert);
        return ResponseEntity.ok().body(concert);

    }
    @GetMapping("/{concertId}")
    public ResponseEntity<?> getConcert(@PathVariable(value ="concertId") Long concertId) {
        if(!concertRepository.findById(concertId).isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Bad concert id "));
        }
        Concert concert = concertRepository.findById(concertId).orElseThrow();
        return ResponseEntity.ok().body(concert);
    }

    @DeleteMapping("/delete/{concertId}")
    public String deleteUser(@PathVariable(value="concertId") Long concertId){
        if(concertRepository.findById(concertId).isPresent()) {
            concertRepository.deleteById(concertId);
            return "Concert deleted. ";
        }else {
            return "Can't find concert. ";
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllConcerts() {
        return ResponseEntity.ok(concertRepository.findAll());
    }
    @PutMapping("/update/{concertId}")
    public ResponseEntity<?> updateConcert(@PathVariable Long concertId,@Valid @RequestBody CreateConcertRequest createConcertRequest){
        if(!concertRepository.findById(concertId).isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Bad concert id "));
        }
        Concert concert = concertRepository.findById(concertId).orElseThrow();
        if(createConcertRequest.getArtistId()!=null)
            concert.setArtist(artistRepository.findById(createConcertRequest.getArtistId()).orElseThrow());
        if(createConcertRequest.getStartAt()!=null)
            concert.setStartAt(createConcertRequest.getStartAt());
        if(createConcertRequest.getEndAt()!=null)
            concert.setEndAt(createConcertRequest.getEndAt());
        concert.setUpdatedAt(LocalDateTime.now());
        concertRepository.save(concert);
        return ResponseEntity.ok(concert);

    }

}
