package hello.web.controllers;

import hello.domain.entity.Product;
import hello.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewAll(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        int pageSize = 10;

        Page<Product> products = productService.getAll(new PageRequest(page, pageSize));
        model.addAttribute("page", products);
        return "products/products";
    }

}