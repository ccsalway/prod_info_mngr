package hello.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findById(Long id);

    Product findByNameLike(String name);

    Product findByNameLikeAndIdNotIn(String name, Long id);

}
