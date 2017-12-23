package hello.service;

import hello.domain.model.Attribute;
import hello.domain.model.Option;
import hello.domain.model.Product;
import hello.domain.repository.OptionRepository;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.OptionNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OptionService {

    @Autowired
    private ProductService productService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private OptionRepository optionRepository;

    // -------------------------------------------------------------

    public Product getProduct(Long prod_id) throws ProductNotFoundException {
        return productService.getProduct(prod_id);
    }

    public Attribute getAttribute(Product product, Long attr_id) throws AttributeNotFoundException {
        return attributeService.getAttribute(product, attr_id);
    }

    // -------------------------------------------------------------

    public boolean nameAvailable(Attribute attribute, String name) {
        return optionRepository.findByAttributeAndNameEquals(attribute, name) == null;
    }

    public boolean nameAvailable(Attribute attribute, String name, Long id) {
        return optionRepository.findByAttributeAndNameEqualsAndIdIsNot(attribute, name, id) == null;
    }


    public void save(Option option) {
        optionRepository.save(option);
    }

    public Option getOption(Product product, Attribute attribute, Long id) throws OptionNotFoundException {
        Option option = optionRepository.findByAttributeAndIdIs(attribute, id);
        if (option == null) {
            throw new OptionNotFoundException(product, attribute);
        }
        return option;
    }

    public Set<Option> getOptions(Attribute attribute) {
        return optionRepository.findByAttributeEquals(attribute);
    }

    public void exists(Product product, Attribute attribute, Long id) throws OptionNotFoundException {
        getOption(product, attribute, id);
    }

    public void delete(Long id) {
        optionRepository.delete(id);
    }
}
