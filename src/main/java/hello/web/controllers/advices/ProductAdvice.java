package hello.web.controllers.advices;

import hello.exceptions.AttributeNotFoundException;
import hello.exceptions.OptionNotFoundException;
import hello.exceptions.ProductNotFoundException;
import hello.web.controllers.ProductController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {ProductController.class})
public class ProductAdvice {

    //@ExceptionHandler, @InitBinder, and @ModelAttribute

    @ExceptionHandler(ProductNotFoundException.class)
    public String invalidProduct() {
        return "redirect:/products";
    }

    @ExceptionHandler(AttributeNotFoundException.class)
    public String invalidAttribute(AttributeNotFoundException e) {
        return "redirect:/product/" + e.getProduct().getId();
    }

    @ExceptionHandler(OptionNotFoundException.class)
    public String invalidOption(OptionNotFoundException e) {
        return "redirect:/product/" + e.getProduct().getId() + "/attribute/" + e.getAttribute().getId();
    }

}