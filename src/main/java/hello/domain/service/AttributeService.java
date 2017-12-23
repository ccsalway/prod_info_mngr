package hello.domain.service;

import hello.domain.entity.Attribute;
import hello.domain.entity.Option;
import hello.domain.entity.Product;
import hello.domain.repository.AttributeRepository;
import hello.domain.repository.OptionRepository;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AttributeService {

    @Autowired
    private ProductService productService;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private OptionRepository optionRepository;

    // -------------------------------------------------------------

    public Product getProduct(Long prod_id) throws ProductNotFoundException {
        return productService.getProduct(prod_id);
    }

    // -------------------------------------------------------------

    public boolean nameAvailable(Product product, String name) {
        return attributeRepository.findByProductAndNameEquals(product, name) == null;
    }

    public boolean nameAvailable(Product product, String name, Long id) {
        return attributeRepository.findByProductAndNameEqualsAndIdIsNot(product, name, id) == null;
    }

    public void save(Attribute attribute) {
        attributeRepository.save(attribute);
    }

    public Attribute getAttribute(Product product, Long id) throws AttributeNotFoundException {
        Attribute attribute = attributeRepository.findByProductAndId(product, id);
        if (attribute == null) {
            throw new AttributeNotFoundException(product);
        }
        return attribute;
    }

    public void exists(Product product, Long id) throws AttributeNotFoundException {
        getAttribute(product, id);
    }

    public Set<Option> getOptions(Attribute attribute) {
        return optionRepository.findByAttributeEquals(attribute);
    }

    public void delete(Long id) {
        attributeRepository.delete(id);
    }
}
