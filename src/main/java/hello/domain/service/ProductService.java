package hello.domain.service;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Product;
import hello.domain.repository.ProductRepository;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeService attributeService;

    // -------------------------------------------------------------

    private boolean nameAvailable(String name, Long id) {
        if (id == null) {
            return productRepository.findByNameEquals(name) == null;
        } else {
            return productRepository.findByNameEqualsAndIdIsNot(name, id) == null;
        }
    }

    public synchronized void save(Product product, BindingResult result) {
        // synchronized to avoid name duplication
        if (!result.hasErrors()) {
            if (!nameAvailable(product.getName(), product.getId())) {
                result.addError(new FieldError("product", "name", "must be unique"));
            } else {
                productRepository.save(product);
            }
        }
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    // -------------------------------------------------------------

    public Page<Product> getProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    public Product getProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    public Page<Attribute> getAttributes(Product product, Pageable pageable) {
        return attributeService.getAttributes(product, pageable);
    }

}
