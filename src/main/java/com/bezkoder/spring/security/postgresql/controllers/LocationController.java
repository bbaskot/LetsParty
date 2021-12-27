package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Location;
import com.bezkoder.spring.security.postgresql.payload.request.CreateLocationRequest;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    LocationRepository locationRepository;
    @PostMapping("/create")
    ResponseEntity<?> createLocation(@Valid@RequestBody CreateLocationRequest createLocationRequest){
        Location location = new Location();
        location.setAdress(createLocationRequest.getAdress());
        location.setCity(createLocationRequest.getCity());
        location.setRegion(createLocationRequest.getRegion());
        location.setCountryCode(createLocationRequest.getCountryCode());
        location.setLattitude(createLocationRequest.getLattitude());
        location.setLongitude(createLocationRequest.getLongitude());
        location.setRadius(createLocationRequest.getRadius());
        location.setCreatedAt(LocalDateTime.now());
        location.setUpdatedAt(LocalDateTime.now());
        locationRepository.save(location);
        return ResponseEntity.ok().body(location);
    }
    @GetMapping("/all")
    ResponseEntity<?> getAllLocations(){
        return ResponseEntity.ok().body(locationRepository.findAll());
    }
    @GetMapping("/{locationId}")
    ResponseEntity<?> getLocation(@PathVariable(value = "locationId") Long locationId){
        if(!locationRepository.findById(locationId).isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find location. "));
        }
        return ResponseEntity.ok().body(locationRepository.findById(locationId));
    }
    @DeleteMapping
    ResponseEntity<?> deleteLocation(@PathVariable(value = "locationId") Long locationId){
        if(!locationRepository.findById(locationId).isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find location. "));
        }
        locationRepository.deleteById(locationId);
        return ResponseEntity.ok().body(new MessageResponse("Deleted location"));
    }
    @PutMapping("/update/{locationId}")
    ResponseEntity<?> updateLocation(@PathVariable(value = "locationId") Long locationId, @Valid@RequestBody CreateLocationRequest createLocationRequest){
        if(!locationRepository.findById(locationId).isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Can't find location. "));

        }
        Location location = locationRepository.findById(locationId).orElseThrow();
        if(createLocationRequest.getAdress()!=null)
            location.setAdress(createLocationRequest.getAdress());
        if(createLocationRequest.getCity()!=null)
            location.setCity(createLocationRequest.getCity());
        if(createLocationRequest.getRegion()!=null)
            location.setRegion(createLocationRequest.getRegion());
        if(createLocationRequest.getCountryCode()!=null)
            location.setCountryCode(createLocationRequest.getCountryCode());
        if(createLocationRequest.getLattitude()!=null)
            location.setLattitude(createLocationRequest.getLattitude());
        if(createLocationRequest.getLongitude()!=null)
            location.setLongitude(createLocationRequest.getLongitude());
        if(createLocationRequest.getRadius()!=null)
            location.setRadius(createLocationRequest.getRadius());
        location.setUpdatedAt(LocalDateTime.now());
        locationRepository.save(location);
        return ResponseEntity.ok().body(location);

    }

}
