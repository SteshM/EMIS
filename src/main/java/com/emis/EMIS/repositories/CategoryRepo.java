package com.emis.EMIS.repositories;

import com.emis.EMIS.models.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<CategoriesEntity,Integer> {
}
