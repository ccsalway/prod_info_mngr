package hello.web.controllers;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Option;
import hello.domain.entity.enities.Product;
import hello.domain.service.AttributeService;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product/{prod_id}/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    //-------------------------------------------------------

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model,
                      @PathVariable Long prod_id
    ) throws ProductNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        model.addAttribute("product", product);
        model.addAttribute("attribute", new Attribute());
        return "attribute_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Model model,
                       @PathVariable Long prod_id,
                       @Valid Attribute attribute,
                       BindingResult result
    ) throws ProductNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        attribute.setProduct(product);
        attributeService.save(attribute, result);
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("result", result);
            return "attribute_add";
        }
        return "redirect:/product/" + product.getId() + "/attribute/" + attribute.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @PathVariable Long prod_id,
                       @PathVariable Long id
    ) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = attributeService.getAttribute(product, id); // throws AttributeNotFoundException
        model.addAttribute("product", product);
        model.addAttribute("attribute", attribute);
        model.addAttribute("form", attribute);
        return "attribute_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(Model model,
                         @PathVariable Long prod_id,
                         @Valid @ModelAttribute Attribute form,
                         BindingResult result
    ) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = attributeService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = attributeService.getAttribute(product, form.getId()); // throws AttributeNotFoundException
        form.setProduct(product);
        form.setPosition(attribute.getPosition());  // not updated from the form
        attributeService.save(form, result);
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("attribute", attribute); // to separate current values with new values
            model.addAttribute("result", result);
            return "attribute_edit";
        }
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
        return "attribute_view";
    }

}
