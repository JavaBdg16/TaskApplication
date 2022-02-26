package pl.sda.taskapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // setupInMemoryAuthentication(auth);

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/task", "/task/**", "/comment", "/comment/**")
                        .hasRole("USER")
                    .antMatchers("/admin")
                        .hasRole("ADMIN")
                    .antMatchers("/api", "/api/**")
                        .authenticated()
                    .anyRequest()
                        .permitAll();

        http.httpBasic();

        http
                .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/task", true);

        http.logout().logoutSuccessUrl("/goodbye");

        // why ** ?
        http.csrf().ignoringAntMatchers("/h2-console/**")
                // umożliwia wczytywanie stron w ramkach
                // z tego samego źródła; wymagane dla H2
                .and()
                .headers()
                .frameOptions()
                .sameOrigin();

        // TODO: why???
        http.cors().and().csrf().disable();
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
