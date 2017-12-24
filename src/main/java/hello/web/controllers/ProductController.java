package hello.web.controllers;

import hello.domain.entity.Attribute;
import hello.domain.entity.Product;
import hello.domain.service.ProductService;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
        productService.save(product, result);
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/product_add";
        }
        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.getProduct(id); // throws ProductNotFoundException
        model.addAttribute("product", product);
        return "products/product_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(Model model, @Valid @ModelAttribute Product product, BindingResult result) throws ProductNotFoundException {
        productService.exists(product.getId()); // throws ProductNotFoundException
        productService.save(product, result);
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "products/product_edit";
        }
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
    public String view(Model model, @PathVariable Long id,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "8") int pageSize)
            throws ProductNotFoundException
    {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        // requesting attributes separately (as opposed to using LAZY) allows the use of paging
        Page<Attribute> attributes = productService.getAttributes(product, new PageRequest(page, pageSize));
        model.addAttribute("attributes", attributes);
        return "products/product_view";
    }

}