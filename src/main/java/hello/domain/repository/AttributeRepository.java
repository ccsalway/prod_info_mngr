package hello.domain.repository;

import hello.domain.model.Attribute;
import hello.domain.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

    Set<Attribute> findByProduct(Product product);

    Attribute findByProductAndId(Product product, Long id);

    Attribute findByProductAndNameEquals(Product product, String name);

    Attribute findByProductAndNameEqualsAndIdIsNot(Product product, String name, Long id);
}
