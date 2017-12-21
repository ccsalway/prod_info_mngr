package hello.web.controllers;

import hello.domain.Attribute;
import hello.domain.Product;
import hello.domain.AttributeRepository;
import hello.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/product/{prod_id}/attribute")
public class AttributeController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, @PathVariable Long prod_id) {
        Product product = productRepository.findById(prod_id);
        if (product == null) {
            return "products/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("attribute", new Attribute());
        return "products/attribute_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Model model, @PathVariable Long prod_id, @Valid Attribute attribute, BindingResult result) {
        Product product = productRepository.findById(prod_id);
        if (product == null) {
            return "products/products";
        }
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/attribute_add";
        }
        attributeRepository.save(attribute);
        return "redirect:/product/" + product.getId() + "/attribute/" + attribute.getId();
    }

}
