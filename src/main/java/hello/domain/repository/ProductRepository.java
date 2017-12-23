package hello.domain.repository;

import hello.domain.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findByNameEquals(String name);

    Product findByNameEqualsAndIdIsNot(String name, Long id);

}
