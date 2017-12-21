package hello.web.controllers;

import hello.domain.Product;
import hello.domain.AttributeRepository;
import hello.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "products/product_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Model model, @Valid Product product, BindingResult result) {
        if (productRepository.findByNameLike(product.getName()) != null) {
            result.addError(new FieldError("product", "name", "must be unique"));
        }
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/product_add";
        }
        productRepository.save(product);
        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return "products/products";
        }
        model.addAttribute("product", product);
        return "products/product_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(Model model, @Valid Product product, BindingResult result) {
        if (productRepository.findByNameLikeAndIdNotIn(product.getName(), product.getId()) != null) {
            result.addError(new FieldError("product", "name", "must be unique"));
        }
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/product_edit";
        }
        productRepository.save(product);
        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        productRepository.delete(id);
        return "products/products";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return "products/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("attributes", attributeRepository.findByProduct(product));
        return "products/product_view";
    }

}