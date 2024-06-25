package com.emis.EMIS.repositories;

import com.emis.EMIS.models.IdentityType;
import com.emis.EMIS.models.SupportingDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportingDocumentsRepo extends JpaRepository<SupportingDocuments,Integer> {

    SupportingDocuments findBySupportId(int supportId);
}
