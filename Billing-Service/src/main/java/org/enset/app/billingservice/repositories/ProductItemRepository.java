package org.enset.app.billingservice.repositories;

import org.enset.app.billingservice.entities.ProductItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
}
