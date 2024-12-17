package fr.meya.witcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "fr.meya.witcher.infrastructure.adapter.out", "fr.meya.witcher.domain.port.out" })
@ComponentScan(basePackages = {
		"fr.meya.witcher.infrastructure.adapter.out",
		"fr.meya.witcher.application.service",
		"fr.meya.witcher.domain.model"})
public class WitcherToolkitBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(WitcherToolkitBackApplication.class, args);

	}

}
