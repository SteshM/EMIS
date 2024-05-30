package com.emis.EMIS;

import com.emis.EMIS.models.RolesEntity;
import com.emis.EMIS.repositories.RolesRepo;
import com.emis.EMIS.services.UserService;
import com.emis.EMIS.wrappers.requestDTOs.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EmisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmisApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Autowired
	RolesRepo rolesRepo;
	@Override
	public void run(String... args) throws Exception {
		RolesEntity role = RolesEntity.builder()
				.role("basic role")
				.roleId(1)
				.build();
		RolesEntity role2 = RolesEntity.builder()
						.role("super role")
				.roleId(2)
								.build();

		rolesRepo.save(role);
		rolesRepo.save(role2);

	}
}
