package com.dev.blog.service;

import com.dev.blog.data.entity.Profile;
import com.dev.blog.dto.Req.ProfileReq;
import com.dev.blog.exception.BlogException;

import java.util.List;

public interface IProfileService {
    List<Profile> getProfiles();
    Profile getCurentUserProfile() throws BlogException;
    Profile addProfile(ProfileReq profile) throws BlogException;
    Profile updateProfile(ProfileReq profile) throws BlogException;
}
