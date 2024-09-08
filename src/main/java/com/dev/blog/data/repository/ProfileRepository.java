package com.dev.blog.data.repository;

import com.dev.blog.data.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByCreatorId(String userId);
}
