package mybootapp.web;

import mybootapp.model.Groupe;
import mybootapp.repo.GroupRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses =  GroupRepository.class)
@EntityScan(basePackageClasses = Groupe.class)
public class Starter extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//Création groupe default
		//Création personne default ( admin ? )
		//Génération des objets aléatoires

		super.onStartup(servletContext);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Starter.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("--- addResourceHandlers");
		registry.addResourceHandler("/webjars/**")//
				.addResourceLocations("/webjars/");
	}
}
