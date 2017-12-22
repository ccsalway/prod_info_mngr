package hello.web.controllers;

import hello.domain.model.Product;
import hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "products/product_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute Product product, BindingResult result) {
        if (!productService.nameAvailable(product.getName())) {
            result.addError(new FieldError("product", "name", "must be unique"));
        }
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/product_add";
        }
        productService.save(product);
        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "products/product_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(Model model, @Valid @ModelAttribute Product product, BindingResult result) {
        if (!productService.nameAvailable(product.getName(), product.getId())) {
            result.addError(new FieldError("product", "name", "must be unique"));
        }
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/product_edit";
        }
        productService.save(product);
        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        try {
            productService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // record no longer exists - possibly deleted by another user
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("attributes", productService.getAttributes(product));
        return "products/product_view";
    }

}