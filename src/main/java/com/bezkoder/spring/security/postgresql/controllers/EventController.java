package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Event;
import com.bezkoder.spring.security.postgresql.models.Stage;
import com.bezkoder.spring.security.postgresql.payload.request.CreateEventRequest;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.EventRepository;
import com.bezkoder.spring.security.postgresql.repository.LocationRepository;
import com.bezkoder.spring.security.postgresql.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    StageRepository stageRepository;
    @PostMapping("/create")
    ResponseEntity<?> createEvent(@Valid@RequestBody CreateEventRequest createEventRequest){
        if(createEventRequest.getLocationId()==null||createEventRequest.getStageIds()==null){
            return ResponseEntity.badRequest().body(new MessageResponse("Locations and stages can't be null "));
        }
        if(!locationRepository.findById(createEventRequest.getLocationId()).isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find location "));
        }
        Iterator<Long> iterator = createEventRequest.getStageIds().iterator();
        while(iterator.hasNext()){
            if(!stageRepository.findById(iterator.next()).isPresent()){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Can't find stage "));
            }
        }
        Event event = new Event();
        event.setEventName(createEventRequest.getEventName());
        event.setDescription(createEventRequest.getDescription());
        event.setPhoto(createEventRequest.getPhoto());
        event.setFacebook(createEventRequest.getFacebook());
        event.setInstagram(createEventRequest.getInstagram());
        event.setYoutube(createEventRequest.getYoutube());
        event.setPoints(createEventRequest.getPoints());
        event.setStatus(createEventRequest.getStatus());
        event.setStartAt(createEventRequest.getStartAt());
        event.setEndAt(createEventRequest.getEndAt());
        event.setLocation(locationRepository.findById(createEventRequest.getLocationId()).orElseThrow());
        iterator = createEventRequest.getStageIds().iterator();
        Set<Stage> stages = new HashSet<>();
        while (iterator.hasNext()){
            stages.add(stageRepository.findById(iterator.next()).orElseThrow());
        }
        event.setStages(stages);
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        eventRepository.save(event);
        return ResponseEntity.ok().body(event);
    }
    @GetMapping("/{eventId}")
    ResponseEntity<?> GetEvent(@PathVariable(value="eventId") Long eventId){
        if(!eventRepository.existsById(eventId)){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find event."));
        }
        return ResponseEntity.ok().body(eventRepository.findById(eventId));
    }
    @GetMapping("/all")
    ResponseEntity<?> GetAllEvents(){
        return ResponseEntity.ok().body(eventRepository.findAll());
    }
    @DeleteMapping("/delete/{eventId}")
    ResponseEntity<?> DeleteEvent(@PathVariable(value = "eventId") Long eventId){
        if(!eventRepository.existsById(eventId)){
            return ResponseEntity.badRequest().body(new MessageResponse("Cant find event "));
        }
        eventRepository.deleteById(eventId);
        return ResponseEntity.ok().body(new MessageResponse("Event Deleted"));
    }
    @PutMapping("/update/{eventId}")
    ResponseEntity<?> updateEvent(@Valid@RequestBody CreateEventRequest createEventRequest,@PathVariable(value = "eventId")Long eventId){
        if(!eventRepository.existsById(eventId)){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find event. "));
        }
        Event event = eventRepository.findById(eventId).orElseThrow();
        if(createEventRequest.getLocationId()!=null){
            if(!locationRepository.findById(createEventRequest.getLocationId()).isPresent()){
                return ResponseEntity.badRequest().body(new MessageResponse("Can't find location "));
            }
            event.setLocation(locationRepository.findById(createEventRequest.getLocationId()).orElseThrow());
        }
        if(createEventRequest.getStageIds()!=null){
            Iterator<Long> iterator = createEventRequest.getStageIds().iterator();
            while(iterator.hasNext()){
                if(!stageRepository.findById(iterator.next()).isPresent()){
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Can't find stage "));
                }
            }
            iterator = createEventRequest.getStageIds().iterator();
            Set<Stage> stages = new HashSet<>();
            while (iterator.hasNext()){
                stages.add(stageRepository.findById(iterator.next()).orElseThrow());
            }
            event.setStages(stages);
        }
        if(createEventRequest.getEventName()!=null)
            event.setEventName(createEventRequest.getEventName());
        if(createEventRequest.getDescription()!=null)
            event.setDescription(createEventRequest.getDescription());
        if(createEventRequest.getPhoto()!=null)
            event.setPhoto(createEventRequest.getPhoto());
        if(createEventRequest.getFacebook()!=null)
            event.setFacebook(createEventRequest.getFacebook());
        if(createEventRequest.getInstagram()!=null)
            event.setInstagram(createEventRequest.getInstagram());
        if(createEventRequest.getYoutube()!=null)
            event.setYoutube(createEventRequest.getYoutube());
        if(createEventRequest.getPoints()!=null)
            event.setPoints(createEventRequest.getPoints());
        if(createEventRequest.getStatus()!=null)
            event.setStatus(createEventRequest.getStatus());
        if(createEventRequest.getStartAt()!=null)
            event.setStartAt(createEventRequest.getStartAt());
        if(createEventRequest.getEndAt()!=null)
            event.setEndAt(createEventRequest.getEndAt());

        event.setUpdatedAt(LocalDateTime.now());
        eventRepository.save(event);
        return ResponseEntity.ok().body(event);

    }


}
