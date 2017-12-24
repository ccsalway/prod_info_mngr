package hello.domain.repository;

import hello.domain.entity.enities.Product;
import hello.domain.entity.enities.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends PagingAndSortingRepository<Variant, Long> {

    Page<Variant> findByProduct(Product product, Pageable pageable);

}
