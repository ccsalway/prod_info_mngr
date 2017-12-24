package hello.domain.service;

import hello.domain.entity.Attribute;
import hello.domain.entity.Option;
import hello.domain.entity.Product;
import hello.domain.repository.AttributeRepository;
import hello.domain.repository.OptionRepository;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

import static hello.core.Helpers.getValueOrDefault;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OptionService optionService;

    // -------------------------------------------------------------

    public Product getProduct(Long prod_id) throws ProductNotFoundException {
        return productService.getProduct(prod_id);
    }

    // -------------------------------------------------------------

    public void exists(Product product, Long id) throws AttributeNotFoundException {
        getAttribute(product, id);
    }

    public boolean nameAvailable(Product product, String name) {
        return attributeRepository.findByProductAndNameEquals(product, name) == null;
    }

    public boolean nameAvailable(Product product, String name, Long id) {
        return attributeRepository.findByProductAndNameEqualsAndIdIsNot(product, name, id) == null;
    }

    public synchronized void save(Attribute attribute) {
        if (attribute.isNew()) {
            int nextPos = (int) getValueOrDefault(attributeRepository.findMaxPosition(attribute.getProduct().getId()), -1);
            attribute.setPosition(nextPos + 1);
        }
        attributeRepository.save(attribute);
    }

    public synchronized void delete(Long id) {
        // synchronised because we need to update the positions after deletion
        attributeRepository.delete(id);
    }

    public Attribute getAttribute(Product product, Long id) throws AttributeNotFoundException {
        Attribute attribute = attributeRepository.findByProductAndId(product, id);
        if (attribute == null) {
            throw new AttributeNotFoundException(product);
        }
        return attribute;
    }

    public Page<Option> getOptions(Attribute attribute, Pageable pageable) {
        return optionService.getOptions(attribute, pageable);
    }
}
