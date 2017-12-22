package hello.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

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
