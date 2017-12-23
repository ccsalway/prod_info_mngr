package hello.web.controllers;

import hello.domain.entity.Product;
import hello.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    // By default, the URL query parameters recognized are page, to specify page number limit,
    // to specify how many results to return on a page, and sort to specify the query method parameter on which to sort.
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewAll(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        Page<Product> products = productService.getAll(new PageRequest(page, 10));
        model.addAttribute("products", products);
        return "products/products";
    }

}