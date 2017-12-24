package hello.web.controllers;

import hello.domain.entity.enities.Product;
import hello.domain.service.VariantService;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/variant/{prod_id}")
public class VariantController {

    @Autowired
    private VariantService variantService;

    //-------------------------------------------------------

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, @PathVariable Long prod_id) throws ProductNotFoundException {
        Product product = variantService.getProduct(prod_id);
        model.addAttribute(product);
        return "variant_add";
    }

}
