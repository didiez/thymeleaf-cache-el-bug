# Themeleaf expression cache bug

An insecure expression (see https://github.com/thymeleaf/thymeleaf/issues/809) it is expected to raise a ThymeleafProcessingException.

When an expression is succesfully parsed, is cached by default in the thymeleaf EL cache (`EXPRESSION_CACHE` in `StandardCacheManager`).
After that, the very same expression, used in an insecure context (i.e `th:data-title=""${param.title}`) should raise an exception but the template is rendered without errors.

Steps to reproduce the bug:
  - Auto: `./mvnw test` 
  - Manual:
    1. `./mvnw spring-boot:run` 
    2. Go to http://localhost:8080 . You should see an error page
    3. Go to http://localhost:8080/safe . The template is rendered without errors.
    3. Go back to http://localhost:8080 . The template is rendered without errors but **it should raise an error**.
