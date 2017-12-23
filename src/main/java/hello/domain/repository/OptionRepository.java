package hello.domain.repository;

import hello.domain.entity.Attribute;
import hello.domain.entity.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends PagingAndSortingRepository<Option, Long> {

    Page<Option> findByAttributeEquals(Attribute attribute, Pageable pageable);

    Option findByAttributeAndNameEquals(Attribute attribute, String name);

    Option findByAttributeAndNameEqualsAndIdIsNot(Attribute attribute, String name, Long id);

    Option findByAttributeAndIdIs(Attribute attribute, Long id);
}
