package hello.domain.service;

import hello.domain.entity.enities.Product;
import hello.domain.entity.enities.Variant;
import hello.domain.repository.VariantRepository;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class VariantService {

    @Autowired
    private ProductService productService;
    @Autowired
    private VariantRepository variantRepository;

    // -------------------------------------------------------------

    public Page<Product> getProducts(PageRequest pageRequest) {
        return productService.getProducts(pageRequest);
    }

    public Product getProduct(Long prod_id) throws ProductNotFoundException {
        return productService.getProduct(prod_id);
    }

    public Page<Variant> getVariants(Product product, PageRequest pageRequest) {
        return variantRepository.findByProduct(product, pageRequest);
    }

}
