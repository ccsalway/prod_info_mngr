package hello.controllers;

import hello.entities.Product;
import hello.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class FormController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadForm(Model model) {
        model.addAttribute("product", new Product());
        return "form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveForm(Model model, @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", "Form has errors");
            return "form";
        }
        productRepository.save(product);
        return "redirect:/view/" + product.getId();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewOne(Model model, @PathVariable Long id) {
        model.addAttribute("product", productRepository.findById(id));
        return "view";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String viewAll(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "list";
    }

}