package com.NavyaLearning.EcommerceApp.Repo;

import com.NavyaLearning.EcommerceApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JPARepository have Data you are working with and Primary Key type

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
