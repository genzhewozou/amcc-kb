package com.nroad.amcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.nroad.amcc.support.stream.KbSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
@EnableBinding({KbSource.class})
public class AmccKbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmccKbApplication.class, args);
	}
	
}
