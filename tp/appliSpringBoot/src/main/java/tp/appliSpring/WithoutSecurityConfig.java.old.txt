package tp.appliSpring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * si ajout de spring-boot-starter-security dans pom.xml
 * et aucune config de securité alors securité par defaut
 * username=user
 * mot_passe=a lire dans la console a chaque redémarrage de l'appli
 */

@Configuration
@Profile("!withSecurity")
public class WithoutSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**/*.*").permitAll()
		.and().cors() //enable CORS (avec @CrossOrigin sur class @RestController)
		.and().csrf().disable();
	} 
}