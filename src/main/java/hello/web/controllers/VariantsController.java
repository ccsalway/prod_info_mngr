package hello.web.controllers;

import hello.domain.entity.enities.Product;
import hello.domain.entity.enities.Variant;
import hello.domain.service.VariantService;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/variants")
public class VariantsController {

    @Autowired
    private VariantService variantService;

    //-------------------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listProducts(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "10") int pageSize
    ) {
        Page<Product> products = variantService.getProducts(new PageRequest(page, pageSize));
        model.addAttribute("products", products);
        return "variants_products";
    }

    @RequestMapping(value = "/{prod_id}", method = RequestMethod.GET)
    public String listVariants(Model model,
                               @PathVariable Long prod_id,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "10") int pageSize
    ) throws ProductNotFoundException {
        Product product = variantService.getProduct(prod_id);
        // getting variants separately allows paging
        Page<Variant> variants = variantService.getVariants(product, new PageRequest(page, pageSize));
        model.addAttribute("product", product);
        model.addAttribute("variants", variants);
        return "variants";
    }

    @RequestMapping(value = "/{prod_id}/add", method = RequestMethod.GET)
    public String addVariant(Model model,
                             @PathVariable Long prod_id
    ) throws ProductNotFoundException {
        Product product = variantService.getProduct(prod_id);
        model.addAttribute("product", product);
        return "variant_add";
    }

}