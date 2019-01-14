package fr.allemandg.freezercatalogAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreezerCatalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreezerCatalogApiApplication.class, args);
		
		System.out.println("API is live!");
	}

}

