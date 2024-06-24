package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SchoolContacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolContactsRepo extends JpaRepository<SchoolContacts,Integer> {
}
