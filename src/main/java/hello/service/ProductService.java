package hello.service;

import hello.domain.model.Attribute;
import hello.domain.repository.AttributeRepository;
import hello.domain.model.Product;
import hello.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    // -------------------------------------------------------------

    public boolean nameAvailable(String name) {
        return productRepository.findByNameEquals(name) == null;
    }

    public boolean nameAvailable(String name, Long id) {
        return productRepository.findByNameEqualsAndIdIsNot(name, id) == null;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    public Page<Product> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    public List<Attribute> findAttributes(Product product) {
        return attributeRepository.findByProduct(product);
    }

}
