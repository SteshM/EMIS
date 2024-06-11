package com.emis.EMIS;

import com.emis.EMIS.models.RolesEntity;
import com.emis.EMIS.repositories.RolesRepo;
import com.emis.EMIS.services.UserService;
import com.emis.EMIS.wrappers.requestDTOs.ProfileDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@OpenAPIDefinition(
		security = {
				@SecurityRequirement(
						name="bearerAuth"
				)
		}
)

@SecurityScheme(
		name="bearerAuth",
		description = "JWT bearer controller",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
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

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
