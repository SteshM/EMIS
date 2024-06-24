package com.emis.EMIS.repositories;

import com.emis.EMIS.models.DocumentTypes;
import com.emis.EMIS.models.MenuCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypesRepo  extends JpaRepository<DocumentTypes,Integer> {
    MenuCodes findByDocumentTypeId(int documentTypeId);
}
