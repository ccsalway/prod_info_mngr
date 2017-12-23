package hello.domain.repository;

import hello.domain.entity.Attribute;
import hello.domain.entity.Option;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OptionRepository extends PagingAndSortingRepository<Option, Long> {

    Set<Option> findByAttributeEquals(Attribute attribute);

    Option findByAttributeAndNameEquals(Attribute attribute, String name);

    Option findByAttributeAndNameEqualsAndIdIsNot(Attribute attribute, String name, Long id);

    Option findByAttributeAndIdIs(Attribute attribute, Long id);
}
