package hello.web.controllers;

import hello.domain.model.Attribute;
import hello.domain.model.Product;
import hello.domain.repository.AttributeRepository;
import hello.domain.repository.ProductRepository;
import hello.service.AttributeService;
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
    private AttributeService attributeService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, @PathVariable Long prod_id) {
        Product product = attributeService.getProduct(prod_id);
        if (product == null) {
            return "products/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("attribute", new Attribute());
        return "products/attribute_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Model model, @PathVariable Long prod_id, @Valid Attribute attribute, BindingResult result) {
        Product product = attributeService.getProduct(prod_id);
        if (product == null) {
            return "products/products";
        }
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/attribute_add";
        }
        attributeService.save(attribute);
        return "redirect:/product/" + product.getId() + "/attribute/" + attribute.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable Long prod_id, @PathVariable Long id) {
        Product product = attributeService.getProduct(prod_id);
        if (product == null) {
            return "redirect:/products";
        }
        Attribute attribute = attributeService.getAttribute(product, id);
        if (attribute == null) {
            return "redirect:/product/" + prod_id;
        }
        model.addAttribute("product", product);
        model.addAttribute("attribute", attribute);
        model.addAttribute("options", attributeService.getOptions(attribute));
        return "products/product_view";
    }


}
