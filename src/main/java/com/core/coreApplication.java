package com.core;

import com.core.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", modifyOnCreate = false)
public class coreApplication {

	@Bean
	public AuditorAware<Integer> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
         */
		Object o = SecurityContextHolder.getContext().getAuthentication();
		return () -> Optional.of(o == null ? 0 : (int)((UserModel) o).getId());
	}
	public static void main(String[] args) {
		SpringApplication.run(coreApplication.class, args);
	}
}
