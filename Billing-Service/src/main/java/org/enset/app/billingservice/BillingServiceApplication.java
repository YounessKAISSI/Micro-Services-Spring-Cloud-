package org.enset.app.billingservice;

import org.enset.app.billingservice.entities.Bill;
import org.enset.app.billingservice.entities.ProductItem;
import org.enset.app.billingservice.feign.CustomerRestClient;
import org.enset.app.billingservice.feign.ProductRestClient;
import org.enset.app.billingservice.model.Customer;
import org.enset.app.billingservice.model.Product;
import org.enset.app.billingservice.repositories.BillRepository;
import org.enset.app.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository,
										ProductItemRepository productItemRepository,
										CustomerRestClient customerRestClient,
										ProductRestClient productRestClient){

		return args -> {
			Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
			Collection<Product> products = productRestClient.getAllProducts().getContent();

			customers.forEach(customer -> {
				Bill bill = Bill.builder()
						.billingDate(new Date())
						.customerId(customer.getId())
						.build();

				billRepository.save(bill);

				products.forEach(product -> {
					ProductItem productItem = ProductItem.builder()
							.bill(bill)
							.productId(product.getId())
							.quantity(1 + new Random().nextInt(10))
							.unitPrice(product.getPrice())
							.build();

					productItemRepository.save(productItem);
				});
			});
		};
	}
}
