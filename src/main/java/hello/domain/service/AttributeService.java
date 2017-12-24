package hello.domain.service;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Option;
import hello.domain.entity.enities.Product;
import hello.domain.repository.AttributeRepository;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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

    private boolean nameAvailable(Product product, String name, Long id) {
        if (id == null) {
            return attributeRepository.findByProductAndNameEquals(product, name) == null;
        } else {
            return attributeRepository.findByProductAndNameEqualsAndIdIsNot(product, name, id) == null;
        }
    }

    public synchronized void save(Attribute attribute, BindingResult result) {
        // synchronized to avoid name duplication
        if (!result.hasErrors()) {
            if (!nameAvailable(attribute.getProduct(), attribute.getName(), attribute.getId())) {
                result.addError(new FieldError("attribute", "name", "must be unique"));
            } else {
                if (attribute.isNew()) {
                    int nextPos = (int) getValueOrDefault(attributeRepository.findMaxPosition(attribute.getProduct().getId()), -1);
                    attribute.setPosition(nextPos + 1);
                }
                attributeRepository.save(attribute);
            }
        }
    }

    public synchronized void delete(Long id) {
        // synchronized so the positions can be updated
        attributeRepository.delete(id);
    }

    // -------------------------------------------------------------

    public Product getProduct(Long prod_id) throws ProductNotFoundException {
        return productService.getProduct(prod_id);
    }

    public Attribute getAttribute(Product product, Long id) throws AttributeNotFoundException {
        Attribute attribute = attributeRepository.findByProductAndId(product, id);
        if (attribute == null) {
            throw new AttributeNotFoundException(product);
        }
        return attribute;
    }

    public Page<Attribute> getAttributes(Product product, Pageable pageable) {
        return attributeRepository.findByProduct(product, pageable);
    }

    public Page<Option> getOptions(Attribute attribute, Pageable pageable) {
        return optionService.getOptions(attribute, pageable);
    }

}
