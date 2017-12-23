package hello.domain.repository;

import hello.domain.entity.Attribute;
import hello.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

    Page<Attribute> findByProduct(Product product, Pageable pageable);

    Attribute findByProductAndId(Product product, Long id);

    Attribute findByProductAndNameEquals(Product product, String name);

    Attribute findByProductAndNameEqualsAndIdIsNot(Product product, String name, Long id);
}
