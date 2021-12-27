package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Achievement;
import com.bezkoder.spring.security.postgresql.models.EAchievements;
import com.bezkoder.spring.security.postgresql.models.Event;
import com.bezkoder.spring.security.postgresql.models.UserProfile;
import com.bezkoder.spring.security.postgresql.payload.request.CreateConcertRequest;
import com.bezkoder.spring.security.postgresql.payload.request.CreateUserProfileRequest;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.AchievementRepository;
import com.bezkoder.spring.security.postgresql.repository.EventRepository;
import com.bezkoder.spring.security.postgresql.repository.UserProfileRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
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
@RequestMapping("/api/user-profile")
public class UserProfileController {
    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AchievementRepository achievementRepository;
    @Autowired
    EventRepository eventRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createUserProfile(@Valid @RequestBody CreateUserProfileRequest createUserProfileRequest) {
        if(!userRepository.existsById(createUserProfileRequest.getUserId())){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find user. "));
        }
        Iterator<Long> iti = createUserProfileRequest.getEventIds().iterator();
        Set<Event> events = new HashSet<>();
        while (iti.hasNext()){
            if(!eventRepository.existsById(iti.next())){
                return ResponseEntity.badRequest().body(new MessageResponse("Can't find event. "));
            }
        }
        iti = createUserProfileRequest.getEventIds().iterator();
        while(iti.hasNext()){
            events.add(eventRepository.findById(iti.next()).orElseThrow());
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setEvents(events);
        userProfile.setUser(userRepository.findById(createUserProfileRequest.getUserId()).orElseThrow());
        Iterator<EAchievements> iterator =createUserProfileRequest.getAchievements().iterator();
        Set<Achievement> achievements = new HashSet<>();
        while(iterator.hasNext()) {
            achievements.add(achievementRepository.findByName(iterator.next()).orElseThrow());
        }
        userProfile.setAchievements(achievements);
        userProfile.setFirstName(createUserProfileRequest.getFirstName());
        userProfile.setLastName(createUserProfileRequest.getLastName());
        userProfile.setPhoto(createUserProfileRequest.getPhoto());
        userProfile.setPhone(createUserProfileRequest.getPhone());
        userProfile.setPoints(createUserProfileRequest.getPoints());
        userProfile.setCreatedAt(LocalDateTime.now());
        userProfile.setUpdatedAt(LocalDateTime.now());
        userProfileRepository.save(userProfile);
        return ResponseEntity.ok().body(userProfile);
    }
    @DeleteMapping("/delete/{userProfileId}")
    ResponseEntity<?> deleteUserProfile(@PathVariable(value = "userProfileId") Long userProfileId){
        if(!userProfileRepository.existsById(userProfileId)){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find user profile"));
        }
        userProfileRepository.deleteById(userProfileId);
        return ResponseEntity.ok().body(new MessageResponse("User Profile deleted! "));
    }
    @GetMapping("/{userProfileId}")
    ResponseEntity<?> getUserProfile(@PathVariable(value = "userProfileId")Long userProfileId){
        if(!userProfileRepository.existsById(userProfileId)){
            return ResponseEntity.badRequest().body(new MessageResponse("Cant find User Profile"));
        }
        return ResponseEntity.ok().body(userProfileRepository.findById(userProfileId));
    }
    @GetMapping("/all")
    ResponseEntity<?> getAllUserProfiles(){
        return ResponseEntity.ok().body(userProfileRepository.findAll());
    }
    @PutMapping("/update/{userProfileId}")
    ResponseEntity<?> updateUserProfile(@Valid @RequestBody CreateUserProfileRequest createUserProfileRequest,@PathVariable(value = "userProfileId") Long userProfileId){
        if(!userProfileRepository.existsById(userProfileId)){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find user profile"));
        }

        UserProfile userProfile = userProfileRepository.findById(userProfileId).orElseThrow();
        if(createUserProfileRequest.getUserId()!=null) {
            if(!userRepository.existsById(createUserProfileRequest.getUserId())){
                return ResponseEntity.badRequest().body(new MessageResponse("Can't find user. "));
            }
            userProfile.setUser(userRepository.findById(createUserProfileRequest.getUserId()).orElseThrow());
        }
        if(createUserProfileRequest.getAchievements()!=null) {
            Iterator<EAchievements> iterator = createUserProfileRequest.getAchievements().iterator();
            Set<Achievement> achievements = new HashSet<>();
            while (iterator.hasNext()) {
                achievements.add(achievementRepository.findByName(iterator.next()).orElseThrow());
            }
            userProfile.setAchievements(achievements);
        }
        if(createUserProfileRequest.getEventIds()!=null){
            Iterator<Long> iti = createUserProfileRequest.getEventIds().iterator();
            Set<Event> events = new HashSet<>();
            while (iti.hasNext()){
                if(!eventRepository.existsById(iti.next())){
                    return ResponseEntity.badRequest().body(new MessageResponse("Can't find event. "));
                }
            }
            iti = createUserProfileRequest.getEventIds().iterator();
            while(iti.hasNext()){
                events.add(eventRepository.findById(iti.next()).orElseThrow());
            }
            userProfile.setEvents(events);
        }
        if(createUserProfileRequest.getFirstName()!=null)
            userProfile.setFirstName(createUserProfileRequest.getFirstName());
        if(createUserProfileRequest.getLastName()!=null)
            userProfile.setLastName(createUserProfileRequest.getLastName());
        if(createUserProfileRequest.getPhoto()!=null)
            userProfile.setPhoto(createUserProfileRequest.getPhoto());
        if(createUserProfileRequest.getPhone()!=null)
            userProfile.setPhone(createUserProfileRequest.getPhone());
        if(createUserProfileRequest.getPoints()!=null)
            userProfile.setPoints(createUserProfileRequest.getPoints());
        userProfile.setUpdatedAt(LocalDateTime.now());
        userProfileRepository.save(userProfile);
        return ResponseEntity.ok().body(userProfile);

    }

}
