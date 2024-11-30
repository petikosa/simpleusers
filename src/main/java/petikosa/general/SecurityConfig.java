package petikosa.general;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import petikosa.services.UserService;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/public/**").permitAll())
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/auth/**").authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService users() {
        List<UserDetails> users = userService.getAllUsers().stream().map(user -> User.withDefaultPasswordEncoder()
                .username(user.username)
                .password(user.password)
                .build()).toList();
        return new InMemoryUserDetailsManager(users);
    }

}
