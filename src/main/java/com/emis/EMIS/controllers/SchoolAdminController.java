package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/schAdmin")
public class SchoolAdminController {
private SchoolAdminService schoolAdminService;

}
