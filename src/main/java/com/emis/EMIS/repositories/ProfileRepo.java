package com.emis.EMIS.repositories;

import com.emis.EMIS.models.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<ProfileEntity,Integer> {
    ProfileEntity findByProfile(String profile);
    ProfileEntity findByProfileId(int profileId);

}
