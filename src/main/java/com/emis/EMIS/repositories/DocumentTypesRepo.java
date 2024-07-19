package com.emis.EMIS.repositories;

import com.emis.EMIS.models.DocumentTypes;
import com.emis.EMIS.models.MenuCodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentTypesRepo  extends JpaRepository<DocumentTypes,Integer> {
    DocumentTypes findByDocumentTypeId(int documentTypeId);

    List<DocumentTypes> findByMenuCodes(MenuCodes menuCodes);
}
