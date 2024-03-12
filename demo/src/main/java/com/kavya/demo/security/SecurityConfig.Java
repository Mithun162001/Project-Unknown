import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/signup", "/login").permitAll() // Allow access to signup and login without authentication
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login") // Specify custom login page
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .sessionManagement()
                .maximumSessions(1) // Allow only one session per user
                .expiredUrl("/login?expired=true"); // Redirect to login page if session expired
    }
}
