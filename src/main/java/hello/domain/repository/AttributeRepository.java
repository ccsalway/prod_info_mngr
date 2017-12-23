package hello.domain.repository;

import hello.domain.entity.Attribute;
import hello.domain.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

    Set<Attribute> findByProduct(Product product);

    Attribute findByProductAndId(Product product, Long id);

    Attribute findByProductAndNameEquals(Product product, String name);

    Attribute findByProductAndNameEqualsAndIdIsNot(Product product, String name, Long id);
}
