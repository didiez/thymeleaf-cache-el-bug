package es.didiez.thymeleafcacheelbug;

import javax.servlet.ServletException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.thymeleaf.exceptions.TemplateProcessingException;

@WebMvcTest(controllers = Application.class)
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void renderingTemplateShouldFailWhenUsingInsecureExpression() throws Exception {
        Exception exception = assertThrows(ServletException.class, () -> {
            mockMvc.perform(get("/"));
        });
        
        assertTrue(exception.getCause() instanceof TemplateProcessingException);
    }
    
    @Test
    public void renderingTemplateShouldFailWhenUsingInsecureExpressionCachedPreviously() throws Exception {
        Exception exception = assertThrows(ServletException.class, () -> {
            mockMvc.perform(get("/safe"));
            mockMvc.perform(get("/"));
        });
        
        assertTrue(exception.getCause() instanceof TemplateProcessingException);
    }
}
