package mx.edu.utez.sda.p1sp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    private DataSource datasource;

    public void globalConfigure(AuthenticationManagerBuilder auth)
        throws Exception{
        auth.jdbcAuthentication()
                .dataSource(datasource);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/", "/home").permitAll();
            requests.anyRequest().authenticated();
        });
        http.formLogin((login) -> {
            login.loginPage("/login").permitAll();
        });
        http.logout((logout) -> {
            logout.permitAll();
        });

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("rodrigo")
                .password("root")
                .roles("ADMIN")
                .build();
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("alex")
                .password("root")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}