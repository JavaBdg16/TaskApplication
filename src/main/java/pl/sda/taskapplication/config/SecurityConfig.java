package pl.sda.taskapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // setupInMemoryAuthentication(auth);

        auth.userDetailsService(userDetailsService);
    }

    private void setupInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jnowak")
                .password("{noop}alamakota")
                .authorities("ROLE_USER")
                .and()
                .withUser("akowalski")
                .password("{noop}1234")
                .authorities("ROLE_ADMIN");
    }
}
