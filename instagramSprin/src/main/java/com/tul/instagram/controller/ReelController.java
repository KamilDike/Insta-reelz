package com.tul.instagram.controller;

import com.tul.instagram.model.Medias;
import com.tul.instagram.model.Reel;
import com.tul.instagram.service.ProfileService;

import com.tul.instagram.service.ReelService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/add")
public class ReelController {

    private ReelService reelService;
    private ProfileService profileService;

    public ReelController(ReelService reelService, ProfileService profileService) {
        this.reelService = reelService;
        this.profileService = profileService;
    }

    @GetMapping("/{name}/{category}")
    public void fetchMedia(@PathVariable String name, @PathVariable String category) {
        Medias medias = profileService.getProfileAll(name);

        medias.getVideos().forEach(video -> {
            try {
                reelService.saveReel(new Reel(medias.getAvatarUrl(), medias.getChannelName(),
                        video.getVideoUrl(), video.getTitle()), category);
            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        });
    }
}
