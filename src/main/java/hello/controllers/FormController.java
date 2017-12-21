package hello.controllers;

import hello.entities.Product;
import hello.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class FormController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveForm(Model model, @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", "Form has errors");
            return "form";
        }
        return "view";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewEntry(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("product", productRepository.findById(id));
        return "view";
    }

}