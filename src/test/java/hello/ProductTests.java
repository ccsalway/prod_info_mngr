package hello;

import hello.domain.entity.Product;
import hello.domain.repository.AttributeRepository;
import hello.domain.repository.ProductRepository;
import hello.domain.service.ProductService;
import hello.web.controllers.ProductController;
import hello.web.controllers.ProductsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {Application.class})
public class ProductTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private AttributeRepository attributeRepository;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public ProductService productService() {
            return new ProductService();
        }
    }

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @Before
    public void setupTest() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Zaphod");
        product1.setDescription("Beeblebrox");
        productRepository.save(product1);
        //assertThat(productRepository.save(product1)).isNotEqualTo(null);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Tommy");
        product2.setDescription("Shelby");
        productRepository.save(product2);
    }

    @Test
    public void shouldNotValidateWhenNameEmpty() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);

        Product product = new Product();
        product.setId(1L);
        product.setName("");
        product.setDescription("Description is this");

        Validator validator = createValidator();
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

        assertThat(constraintViolations.size()).isEqualTo(2);

        for (ConstraintViolation<Product> violation : constraintViolations) {
            assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
            assertThat(violation.getMessage()).isIn(Arrays.asList("may not be empty", "size must be between 1 and 32"));
        }
    }

    @Test
    public void testShowProductListHtml() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/products"))
                .andExpect(model().attributeExists("requestUri"))
                .andExpect(model().attributeExists("currentYear"));
    }

    @Test
    public void testShowProductItemHtml() throws Exception {
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/product_view"))
                .andExpect(model().attributeExists("requestUri"))
                .andExpect(model().attributeExists("currentYear"));
    }
}