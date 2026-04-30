package com.spring.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// qui vengono definiti i bean per la configurazione di come Spring Security
// gestirà Authentication & Authorization 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // esponiamo un bean per la gestione della filter chain: prima di comunicare 
    // direttamente con il controller viene fatto un controllo preliminare su cosa può fare o meno quell'utente
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // settiamo i valori dell'oggetto HTTPSecurity che definisce il comportamento
        // a seguito di determinate richieste HTTP
        http.authorizeHttpRequests(requests -> requests
            // quì viene specificato il livello di accesso necessario per
            // fare determinate richieste HTTP al server
            .requestMatchers("/user").hasAuthority("USER")
            .requestMatchers("/admin").hasAuthority("ADMIN")
            .requestMatchers("/").permitAll()
            .requestMatchers("/**").permitAll())
            // configurazione base che mostra una pagina di login quando si prova
            // ad accedere al sito
            .formLogin(Customizer.withDefaults())
            // disabilita cors e csfr in fase di sviluppo
            .cors(cors -> cors.disable())
            .csrf(csfr -> csfr.disable());

        // costruiamo con i valori impostati
        return http.build();
    }

    
    @Bean
    // deleghiamo l'encoding delle pw al db nel nostro caso, mettendo un tag {noop}
    // all'inizio della pw. Questo lascia intendere a spring che stiamo conservando le
    // password in chiaro nel db. SOLO IN TESTING
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // una volta passati i filter l'auth manager contatta il nostro auth provider
    // a cui ovviamente dobbiamo passare un service per trovare l'utente nel db
    // e il pw encoder per verificare che la pw inserita dall'utente matchi con quella
    // presente nel db
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
    
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
}
