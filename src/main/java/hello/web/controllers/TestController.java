package hello.web.controllers;

import hello.domain.entity.Attribute;
import hello.domain.entity.Option;
import hello.domain.entity.Product;
import hello.domain.service.AttributeService;
import hello.domain.service.OptionService;
import hello.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private OptionService optionService;

    @RequestMapping("/loaddata")
    public @ResponseBody String loadTestData() {
        Product product;
        Attribute attribute;
        Option option;

        for (long p = 0; p < 50; p++) {
            product = new Product();
            product.setName(String.format("test_product_%d", p));
            product.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            productService.save(product);

            for (long a = 0; a < 10; a++) {
                attribute = new Attribute();
                attribute.setProduct(product);
                attribute.setName(String.format("test_attribute_%d", a));
                attributeService.save(attribute);

                for (long o = 0; o < 5; o++) {
                    option = new Option();
                    option.setAttribute(attribute);
                    option.setName(String.format("test_option_%d", o));
                    optionService.save(option);
                }

                log.info("Added Options for " + attribute.getName());
            }

            log.info("Added Attributes for " + product.getName());
        }

        return "Data loaded..";
    }

}
