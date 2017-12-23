package hello.domain.service;

import hello.domain.entity.Attribute;
import hello.domain.entity.Product;
import hello.domain.repository.AttributeRepository;
import hello.domain.repository.ProductRepository;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    public Page<Product> getAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    public Product getProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    public void exists(Long id) throws ProductNotFoundException {
        getProduct(id);
    }

    public Page<Attribute> getAttributes(Product product, Pageable pageable) {
        return attributeRepository.findByProduct(product, pageable);
    }

}
