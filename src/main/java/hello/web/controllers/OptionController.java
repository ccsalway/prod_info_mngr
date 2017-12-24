package hello.web.controllers;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Option;
import hello.domain.entity.enities.Product;
import hello.domain.service.OptionService;
import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.OptionNotFoundException;
import hello.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/product/{prod_id}/attribute/{attr_id}/option")
public class OptionController {

    @Autowired
    private OptionService optionService;

    //-------------------------------------------------------

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model,
                      @PathVariable Long prod_id,
                      @PathVariable Long attr_id
    ) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = optionService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = optionService.getAttribute(product, attr_id); // throws AttributeNotFoundException
        model.addAttribute("product", product);
        model.addAttribute("attribute", attribute);
        model.addAttribute("option", new Option());
        return "option_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Model model,
                       @PathVariable Long prod_id,
                       @PathVariable Long attr_id,
                       @Valid Option option,
                       BindingResult result
    ) throws ProductNotFoundException, AttributeNotFoundException {
        Product product = optionService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = optionService.getAttribute(product, attr_id); // throws AttributeNotFoundException
        option.setAttribute(attribute);
        optionService.save(option, result);
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("attribute", attribute);
            model.addAttribute("result", result);
            return "option_add";
        }
        return "redirect:/product/" + product.getId() + "/attribute/" + attribute.getId() + "/option/" + option.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @PathVariable Long prod_id,
                       @PathVariable Long attr_id,
                       @PathVariable Long id
    ) throws ProductNotFoundException, AttributeNotFoundException, OptionNotFoundException {
        Product product = optionService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = optionService.getAttribute(product, attr_id); // throws AttributeNotFoundException
        Option option = optionService.getOption(product, attribute, id); // throws OptionNotFoundException
        model.addAttribute("product", product);
        model.addAttribute("attribute", attribute);
        model.addAttribute("option", option);
        model.addAttribute("form", option);
        return "option_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(Model model,
                         @PathVariable Long prod_id,
                         @PathVariable Long attr_id,
                         @Valid @ModelAttribute Option form,
                         BindingResult result
    ) throws ProductNotFoundException, AttributeNotFoundException, OptionNotFoundException {
        Product product = optionService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = optionService.getAttribute(product, attr_id); // throws AttributeNotFoundException
        Option option = optionService.getOption(product, attribute, form.getId()); // throws AttributeNotFoundException
        form.setAttribute(attribute);
        form.setPosition(option.getPosition());  // not updated from the form
        optionService.save(form, result);
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("attribute", attribute);
            model.addAttribute("option", option); // to separate current values with new values
            model.addAttribute("result", result);
            return "option_edit";
        }
        return "redirect:/product/" + product.getId() + "/attribute/" + attribute.getId() + "/option/" + option.getId();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long prod_id, @PathVariable Long attr_id, @PathVariable Long id) {
        try {
            optionService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // record no longer exists - possibly deleted by another user
        }
        return "redirect:/product/" + prod_id + "/attribute/" + attr_id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model,
                       @PathVariable Long prod_id,
                       @PathVariable Long attr_id,
                       @PathVariable Long id
    ) throws ProductNotFoundException, AttributeNotFoundException, OptionNotFoundException {
        Product product = optionService.getProduct(prod_id); // throws ProductNotFoundException
        Attribute attribute = optionService.getAttribute(product, attr_id); // throws AttributeNotFoundException
        Option option = optionService.getOption(product, attribute, id); // throws OptionNotFoundException
        model.addAttribute("product", product);
        model.addAttribute("attribute", attribute);
        model.addAttribute("option", option);
        return "option_view";
    }

}
