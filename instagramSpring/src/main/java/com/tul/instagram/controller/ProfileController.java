package com.tul.instagram.controller;

import com.tul.instagram.model.Medias;
import com.tul.instagram.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Medias> getUser(@PathVariable String name) {
        Medias out = profileService.getProfile(name);
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @GetMapping("/{id}/{pageCursor}")
    public ResponseEntity<Medias> getUser(@PathVariable String id, @PathVariable String pageCursor) {
        Medias out = profileService.getProfile(id, pageCursor);
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @GetMapping("/{name}/all")
    public ResponseEntity<Medias> getUserAll(@PathVariable String name) {
        Medias out = profileService.getProfileAll(name);
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
