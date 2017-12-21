package hello.web.controllers;

import hello.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewAll(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/products";
    }

}