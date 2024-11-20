package br.com.desafio.ntconsult;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class NtconsultApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtconsultApplication.class, args);
	}

}
