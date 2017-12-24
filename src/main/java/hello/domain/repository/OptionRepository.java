package hello.domain.repository;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends PagingAndSortingRepository<Option, Long> {

    Page<Option> findByAttributeEquals(Attribute attribute, Pageable pageable);

    Option findByAttributeAndNameEquals(Attribute attribute, String name);

    Option findByAttributeAndNameEqualsAndIdIsNot(Attribute attribute, String name, Long id);

    Option findByAttributeAndIdIs(Attribute attribute, Long id);

    @Query("select max(o.position) from Option o where o.attribute.id = ?1")
    Object findMaxPosition(Long attribute_id);  // could return null

}
