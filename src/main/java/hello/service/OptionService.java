package hello.service;

import hello.domain.model.Attribute;
import hello.domain.model.Option;
import hello.domain.model.Product;
import hello.domain.repository.AttributeRepository;
import hello.domain.repository.OptionRepository;
import hello.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private OptionRepository optionRepository;

    // -------------------------------------------------------------

    public void save(Attribute attribute) {
        attributeRepository.save(attribute);
    }

    public Product getProduct(Long prod_id) {
        return productRepository.findOne(prod_id);
    }

    public Attribute getAttribute(Product product, Long id) {
        // find by product and id to avoid cross-product pollination
        // ie, someone selecting an attribute for a different product
        return attributeRepository.findByProductAndId(product, id);
    }

    public List<Option> getOptions(Attribute attribute) {
        return optionRepository.findByAttribute(attribute);
    }

}
