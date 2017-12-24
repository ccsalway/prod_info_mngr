package hello.domain.repository;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

    Page<Attribute> findByProduct(Product product, Pageable pageable);

    Attribute findByProductAndId(Product product, Long id);

    Attribute findByProductAndNameEquals(Product product, String name);

    Attribute findByProductAndNameEqualsAndIdIsNot(Product product, String name, Long id);

    @Query("select max(a.position) from Attribute a where a.product.id = ?1")
    Object findMaxPosition(Long product_id);  // could return null

}
