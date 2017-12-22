package hello.domain.repository;

import hello.domain.model.Attribute;
import hello.domain.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

    List<Attribute> findByProduct(Product product);

}
