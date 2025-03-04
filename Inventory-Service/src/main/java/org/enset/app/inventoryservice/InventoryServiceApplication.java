package org.enset.app.inventoryservice;

import org.enset.app.inventoryservice.entities.Product;
import org.enset.app.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("Computer")
					.price(3200)
					.quantity(11)
					.build());
			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("Printer")
					.price(1299)
					.quantity(10)
					.build());
			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("Smart Phone")
					.price(5400)
					.quantity(8)
					.build());

			productRepository.findAll().forEach(p -> {
				System.out.println("===================");
				System.out.println(p.toString());
				System.out.println("===================");
			});

		};
	}
}