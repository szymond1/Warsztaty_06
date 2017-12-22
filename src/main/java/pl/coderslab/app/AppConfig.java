package pl.coderslab.app;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan(basePackages = { "pl.coderslab.controller", "pl.coderslab.entity", "pl.coderslab.bean" })
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"pl.coderslab.repository"})	// Spring Data
public class AppConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;							// widoki
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();							// pola statyczne
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
	LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
	emfb.setPersistenceUnitName("Warsztaty_06");
	return emfb; }
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
	JpaTransactionManager tm = new JpaTransactionManager(emf);
	return tm; }										// Entity Manager
	
//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//	registry.addConverter(authorConverter());	
//	registry.addConverter(publisherConverter());		// wywoływanie konwetera
//	}
//	
//	@Bean
//	public AuthorConverter authorConverter() {
//		return new AuthorConverter();
//	}
//	
//	@Bean
//	public PublisherConverter publisherConverter() {
//		return new	PublisherConverter();
//	}
//	
//	@Bean(name="localeResolver")
//	public LocaleContextResolver getLocaleContextResolver() {
//	SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//	localeResolver.setDefaultLocale(new Locale("pl","PL"));					//plik tłumaczeń	
//	return localeResolver; 
//	}
//	
//	@Bean
//	public Validator validator() {
//	return new LocalValidatorFactoryBean();								// bean validatora	
//	}
	
}