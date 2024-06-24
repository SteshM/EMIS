package com.emis.EMIS.repositories;

import com.emis.EMIS.models.MenuCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCodesRepo extends JpaRepository<MenuCodes,Integer> {
    MenuCodes findByMenuCodeId(int menuCodeId);
}
