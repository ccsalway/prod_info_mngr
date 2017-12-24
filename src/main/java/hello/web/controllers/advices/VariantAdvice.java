package hello.web.controllers.advices;

import hello.exceptions.ProductNotFoundException;
import hello.web.controllers.VariantController;
import hello.web.controllers.VariantsController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {VariantsController.class, VariantController.class})
public class VariantAdvice {

    //@ExceptionHandler, @InitBinder, and @ModelAttribute

    @ExceptionHandler(ProductNotFoundException.class)
    public String invalidProduct() {
        return "redirect:/variants";
    }

}