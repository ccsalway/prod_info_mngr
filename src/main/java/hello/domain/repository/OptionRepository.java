package hello.domain.repository;

import hello.domain.model.Attribute;
import hello.domain.model.Option;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends PagingAndSortingRepository<Option, Long> {

    List<Option> findByAttributeEquals(Attribute attribute);

    Option findByAttributeAndNameEquals(Attribute attribute, String name);

    Option findByAttributeAndNameEqualsAndIdIsNot(Attribute attribute, String name, Long id);

    Option findByAttributeAndIdIs(Attribute attribute, Long id);
}