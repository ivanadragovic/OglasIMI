package com.example.Projekat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@EnableWebSecurity
@Configuration

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/resources/**", "/assets/css/**", "/assets/fonts/**", "/assets/images/**","/assets/js/**").permitAll()
                        .antMatchers("/register", "/resources/**", "/assets/css/**", "/assets/fonts/**", "/assets/images/**","/assets/js/**").permitAll()
                        .antMatchers("/registracija").permitAll()
                        .antMatchers("/jobs", "/resources/**", "/assets/css/**", "/assets/fonts/**", "/assets/images/**","/assets/images/photos/**","/assets/js/**").permitAll()
                        .antMatchers("/contact", "/resources/**", "/assets/css/**", "/assets/fonts/**", "/assets/images/**","/assets/js/**").permitAll()
//                        .antMatchers("/logout", "/resources/**", "/assets/css/**", "/assets/fonts/**", "/assets/images/**","/assets/js/**").hasAnyAuthority("Administrator","Radnik","Poslodavac")
//                        .antMatchers("/team", "/resources/**", "/assets/css/**", "/assets/fonts/**", "/assets/images/**","/assets/js/**").permitAll()
                        .antMatchers("/delete/**","/deleteKorisnik/**").hasAuthority("Administrator")
//                        .antMatchers("/CVstrana/**", "/resources/**", "/assets/css/**", "/assets/fonts/**", "/assets/images/**","/assets/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                        .failureUrl("/login?error=true")
                .loginPage("/login").permitAll()
                .and()
                .logout()
                        .invalidateHttpSession(true)
                        .permitAll()
                        .logoutUrl("/logout")

                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()

                        ;





    }

//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;

    }



    //ZA SLIKU


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path korisnikUploadDir = Paths.get("./src/main/resources/static/assets/images/photos/");
        String korisnikUploadPath = korisnikUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/src/main/resources/static/assets/images/photos/**").addResourceLocations("file:/"+korisnikUploadPath+"/");
    }


//    You should configure them to be ignored by Spring Security. For example by adding such method to your WebSecurityConfig:
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/src/main/resources/static/assets/fonts/**", "/src/main/resources/static/assets/images/photos/**", "/src/main/resources/static/ assets/css/**");
    }

}
