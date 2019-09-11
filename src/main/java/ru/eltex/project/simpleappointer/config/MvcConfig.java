package ru.eltex.project.simpleappointer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This config class needed to set up the default mvc login page.
 * Req for Spring Security
 *
 * @author skwardlow
 * @version 1.0
 * @see WebMvcConfigurer
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * addViewControllers method setting up a login page as a default, provided by MVC
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
