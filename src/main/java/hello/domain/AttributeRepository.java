package hello.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

    Attribute findByProduct(Product product);

}
