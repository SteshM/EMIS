package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SchoolDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolDocumentRepo extends JpaRepository<SchoolDocuments,Integer> {
    SchoolDocuments findBySchoolDocId(int schoolDocId);
}
