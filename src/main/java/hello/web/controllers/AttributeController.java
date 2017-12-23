package hello.web.controllers;

import hello.domain.entity.Attribute;
import hello.domain.entity.Option;
import hello.domain.entity.Product;
import hello.domain.service.AttributeService;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product/{prod_id}/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, @PathVariable Long prod_id) throws ProductNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        model.addAttribute("product", product);
        model.addAttribute("attribute", new Attribute());
        return "products/attribute_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Model model, @PathVariable Long prod_id, @Valid Attribute attribute, BindingResult result) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        if (!attributeService.nameAvailable(product, attribute.getName())) {
            result.addError(new FieldError("attribute", "name", "must be unique"));
        }
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("result", result);
            return "products/attribute_add";
        }
        attribute.setProduct(product);
        attributeService.save(attribute);
        return "redirect:/product/" + product.getId() + "/attribute/" + attribute.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long prod_id, @PathVariable Long id) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = attributeService.getAttribute(product, id); // throws AttributeNotFoundException
        model.addAttribute("product", product);
        model.addAttribute("attribute", attribute);
        return "products/attribute_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(Model model, @PathVariable Long prod_id, @Valid @ModelAttribute Attribute attribute, BindingResult result) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        attributeService.exists(product, attribute.getId()); // throws AttributeNotFoundException
        if (!attributeService.nameAvailable(product, attribute.getName(), attribute.getId())) {
            result.addError(new FieldError("attribute", "name", "must be unique"));
        }
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("result", result);
            return "products/attribute_edit";
        }
        attribute.setProduct(product);
        attributeService.save(attribute);
        return "redirect:/product/" + product.getId() + "/attribute/" + attribute.getId();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long prod_id, @PathVariable Long id) {
        try {
            attributeService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // record no longer exists - possibly deleted by another user
        }
        return "redirect:/product/" + prod_id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model,
                       @PathVariable Long prod_id, @PathVariable Long id,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "6") int pageSize
    ) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = attributeService.getAttribute(product, id); // throws AttributeNotFoundException
        // requesting options separately (as opposed to using LAZY) allows the use of paging
        Page<Option> options = attributeService.getOptions(attribute, new PageRequest(page, pageSize));
        model.addAttribute("product", product);
        model.addAttribute("attribute", attribute);
        model.addAttribute("options", options);
        return "products/attribute_view";
    }

}
