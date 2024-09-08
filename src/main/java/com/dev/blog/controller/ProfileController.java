package com.dev.blog.controller;

import com.dev.blog.data.entity.Profile;
import com.dev.blog.dto.Res.BaseRes;
import com.dev.blog.dto.Req.ProfileReq;
import com.dev.blog.exception.BlogException;
import com.dev.blog.message.Message;
import com.dev.blog.service.IProfileService;
import com.dev.blog.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("api/profile")
@Validated
public class ProfileController {
    @Autowired
    IProfileService profileService;
    @Autowired
    Message message;
    @GetMapping("all")
    @ResponseBody
    public ResponseEntity<BaseRes> getProfiles(){
        List<Profile> profiles = profileService.getProfiles();
        return ResponseUtils.getResponseEntity(profiles, message.get("profile.success.get"));
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<BaseRes> getProfile() throws BlogException {
        Profile profile = profileService.getCurentUserProfile();
        return ResponseUtils.getResponseEntity(profile, message.get("profile.success.get"));
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<BaseRes> addProfile(@RequestBody ProfileReq profileReq) throws BlogException {
        Profile profile = profileService.addProfile(profileReq);
        return ResponseUtils.getResponseEntity(profile, message.get("profile.success.add"));
    }

    @PutMapping("")
    @ResponseBody
    public ResponseEntity<BaseRes> updateProfile(@RequestBody ProfileReq profileReq) throws BlogException {
        Profile profile = profileService.updateProfile(profileReq);
        return ResponseUtils.getResponseEntity(profile, message.get("profile.success.update"));
    }
}
