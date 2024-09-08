package com.dev.blog.service;

import com.dev.blog.data.entity.Profile;
import com.dev.blog.data.entity.User;
import com.dev.blog.data.repository.ProfileRepository;
import com.dev.blog.dto.Req.ProfileReq;
import com.dev.blog.exception.BlogException;
import com.dev.blog.message.Message;
import com.dev.blog.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService implements IProfileService{

    @Autowired
    ProfileRepository repository;
    @Autowired
    Message message;
    @Override
    public List<Profile> getProfiles() {
        return repository.findAll();
    }

    @Override
    public Profile getCurentUserProfile() throws BlogException {
        User user = AuthUtils.getLoggedInUser();
        return repository.findByCreatorId(user.getId())
                .orElseThrow(() -> new BlogException(message.get("profile.error.get")));
    }

    @Override
    public Profile addProfile(ProfileReq profile) throws BlogException {
        User user = AuthUtils.getLoggedInUser();
        Optional<Profile> existingProfile = repository.findByCreatorId(user.getId());
        if(existingProfile.isPresent()) {
            throw new BlogException(message.get("profile.error.add"));
        }
        Profile newProfile = Profile.builder()
                .username(profile.getUsername())
                .bio(profile.getBio())
                .imageUrl(profile.getImageUrl())
                .creator(user)
                .build();
        repository.save(newProfile);
        return newProfile;
    }

    @Override
    public Profile updateProfile(ProfileReq profileReq) throws BlogException {
        User user = AuthUtils.getLoggedInUser();
        Profile profile = repository.findByCreatorId(user.getId())
                .orElseThrow(() -> new BlogException(message.get("profile.error.update")));

        profile.setUsername(profileReq.getUsername());
        profile.setBio(profileReq.getBio());
        profile.setImageUrl(profileReq.getImageUrl());
        repository.save(profile);
        return profile;
    }
}
