package hello.domain.service;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Option;
import hello.domain.entity.enities.Product;
import hello.domain.repository.OptionRepository;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.OptionNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static hello.core.Helpers.getValueOrDefault;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private AttributeService attributeService;

    // -------------------------------------------------------------

    private boolean nameAvailable(Attribute attribute, String name, Long id) {
        if (id == null) {
            return optionRepository.findByAttributeAndNameEquals(attribute, name) == null;
        } else {
            return optionRepository.findByAttributeAndNameEqualsAndIdIsNot(attribute, name, id) == null;
        }
    }

    public synchronized void save(Option option, BindingResult result) {
        // synchronized to avoid name duplication and next position overwriting
        if (!result.hasErrors()) {
            if (!nameAvailable(option.getAttribute(), option.getName(), option.getId())) {
                result.addError(new FieldError("option", "name", "must be unique"));
            } else {
                if (option.isNew()) {
                    int nextPos = (int) getValueOrDefault(optionRepository.findMaxPosition(option.getAttribute().getId()), -1);
                    option.setPosition(nextPos + 1);
                }
                optionRepository.save(option);
            }
        }
    }

    public synchronized void delete(Long id) {
        // synchronized so the positions can be updated
        // TODO: minus 1 positions higher
        optionRepository.delete(id);
    }

    // -------------------------------------------------------------

    public Product getProduct(Long prod_id) throws ProductNotFoundException {
        return productService.getProduct(prod_id);
    }

    public Attribute getAttribute(Product product, Long attr_id) throws AttributeNotFoundException {
        return attributeService.getAttribute(product, attr_id);
    }

    public Option getOption(Product product, Attribute attribute, Long id) throws OptionNotFoundException {
        Option option = optionRepository.findByAttributeAndIdIs(attribute, id);
        if (option == null) {
            throw new OptionNotFoundException(product, attribute);
        }
        return option;
    }

    public Page<Option> getOptions(Attribute attribute, Pageable pageable) {
        return optionRepository.findByAttributeEquals(attribute, pageable);
    }

}
