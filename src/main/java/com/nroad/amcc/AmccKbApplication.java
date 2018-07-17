package com.nroad.amcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nroad.amcc.support.stream.KbSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
@EnableBinding({KbSource.class})
//@RestController
public class AmccKbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmccKbApplication.class, args);
	}
	
//	@GetMapping("/local-projection/api/v1/view/service/group")
//	public Object get(@RequestParam("incomingNumber") String incomingNumber) {
//		
//		if("18992073230".equals(incomingNumber)) {
//			return "{\"serviceCode\":\"1010041034\",\"bakServiceCode\":\"\",\"type\":\"ACCEPT\",\"transferMusic\":\"\",\"holdMusic\":\"\",\"busyMusic\":\"\"}";
//		}else {
//			return "{\"serviceCode\":\"1010041035\",\"bakServiceCode\":\"\",\"type\":\"ACCEPT\",\"transferMusic\":\"\",\"holdMusic\":\"\",\"busyMusic\":\"\"}";
//		}
//		
//	}
	
	
}
