package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Concert;
import com.bezkoder.spring.security.postgresql.models.Stage;
import com.bezkoder.spring.security.postgresql.payload.request.CreateConcertRequest;
import com.bezkoder.spring.security.postgresql.payload.request.CreateStageRequest;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.ConcertRepository;
import com.bezkoder.spring.security.postgresql.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stage")
public class StageController {
    @Autowired
    StageRepository stageRepository;
    @Autowired
    ConcertRepository concertRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createStage(@Valid @RequestBody CreateStageRequest createStageRequest) {
        Iterator<Long> iterator = createStageRequest.getConcertIds().iterator();
        while(iterator.hasNext()){
            if(!concertRepository.findById(iterator.next()).isPresent()){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Can't find concert"));
            }
        }
        Stage stage = new Stage();
        Set<Concert> concerts=new HashSet<>();
        iterator = createStageRequest.getConcertIds().iterator();
        while (iterator.hasNext()){
            concerts.add(concertRepository.findById(iterator.next()).orElseThrow());
        }
        stage.setStageName(createStageRequest.getStageName());
        stage.setConcerts(concerts);
        stage.setStartAt(createStageRequest.getStartAt());
        stage.setEndAt(createStageRequest.getEndAt());
        stage.setCreatedAt(LocalDateTime.now());
        stage.setUpdatedAt(LocalDateTime.now());
        stageRepository.save(stage);
        return ResponseEntity.ok().body(stage);

    }
    @GetMapping("/{stageId}")
    public ResponseEntity<?> getStage(@PathVariable(value ="stageId") Long stageId) {
        if(!stageRepository.findById(stageId).isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find stage "));
        }
        return ResponseEntity.ok(stageRepository.findById(stageId));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getStage() {
        return ResponseEntity.ok().body(stageRepository.findAll());
    }
    @PutMapping("/update/{stageId}")
    public ResponseEntity<?> updateStage(@PathVariable(value ="stageId") Long stageId,@Valid @RequestBody CreateStageRequest createStageRequest) {
        if(!stageRepository.findById(stageId).isPresent()){
           return ResponseEntity.badRequest().body(new MessageResponse("Can't find stage "));
        }
        Iterator<Long> iterator = createStageRequest.getConcertIds().iterator();
        while(iterator.hasNext()){
            if(!concertRepository.findById(iterator.next()).isPresent()){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Can't find concert"));
            }
        }
        Stage stage = stageRepository.findById(stageId).orElseThrow();
        if(createStageRequest.getConcertIds()!=null) {
            Set<Concert> concerts = new HashSet<>();
            iterator = createStageRequest.getConcertIds().iterator();
            while (iterator.hasNext()) {
                concerts.add(concertRepository.findById(iterator.next()).orElseThrow());
            }
            stage.setConcerts(concerts);
        }
        if(createStageRequest.getStageName()!=null)
            stage.setStageName(createStageRequest.getStageName());
        if(createStageRequest.getStartAt()!=null)
            stage.setStartAt(createStageRequest.getStartAt());
        if(createStageRequest.getEndAt()!=null)
            stage.setEndAt(createStageRequest.getEndAt());
        stage.setUpdatedAt(LocalDateTime.now());
        stageRepository.save(stage);
        return ResponseEntity.ok().body(stage);

    }

    @DeleteMapping("/{stageId}")
    public ResponseEntity<?> deleteStage(@PathVariable(value = "stageId")Long stageId){
        if(!stageRepository.findById(stageId).isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find stage"));
        }
        stageRepository.deleteById(stageId);
        return ResponseEntity.ok().body(new MessageResponse("Stage deleted"));
    }
}
