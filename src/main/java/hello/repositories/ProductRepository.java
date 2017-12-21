package hello.repositories;

import hello.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findById(Long id);

}
