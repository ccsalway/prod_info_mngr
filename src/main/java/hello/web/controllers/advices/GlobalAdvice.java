package hello.web.controllers.advices;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalAdvice {

    //@ExceptionHandler, @InitBinder, and @ModelAttribute

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // trim all form fields
        StringTrimmerEditor trim = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trim);
    }

    @ModelAttribute("requestUri")
    public String requestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("currentYear")
    public int currentYear() {
        return java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    }

}
