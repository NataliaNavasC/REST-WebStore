package com.web.puzzlestore.BackEnd.utils.config;

import java.util.Arrays;
import java.util.List;

import com.web.puzzlestore.BackEnd.model.entities.Administrator;
import com.web.puzzlestore.BackEnd.model.entities.Puzzle;
import com.web.puzzlestore.BackEnd.model.entities.Role;
import com.web.puzzlestore.BackEnd.model.entities.ShoppingCart;
import com.web.puzzlestore.BackEnd.model.entities.User;
import com.web.puzzlestore.BackEnd.repository.IAdministratorRepository;
import com.web.puzzlestore.BackEnd.repository.IPuzzleRepository;
import com.web.puzzlestore.BackEnd.repository.IRoleRepository;
import com.web.puzzlestore.BackEnd.repository.IShoppingCartRepository;
import com.web.puzzlestore.BackEnd.repository.IUserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class BackConfiguration {

    @Autowired
    private Environment env;

    @Bean
    CommandLineRunner loadData(IShoppingCartRepository shoppingCartRepository,IUserRepository personRepository, IAdministratorRepository administratorRepository, IRoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, IPuzzleRepository puzzleRepository){
        return args -> {

            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            User user = new User();
            user.setUsername("Juan");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRol(userRole);
            personRepository.save(user);
            ShoppingCart newShoppingCart = new ShoppingCart();
            newShoppingCart.setUser(user);
            shoppingCartRepository.save(newShoppingCart);

            Administrator admin = new Administrator();
            admin.setUsername("Admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRol(adminRole);
            administratorRepository.save(admin);

            // Puzzles
            Puzzle p1 = new Puzzle((long)-1, "1x1", "desciption1", 100.0, "https://i.imgur.com/efakOv9.jpg");
            puzzleRepository.save(p1);
            
            Puzzle p2 = new Puzzle((long)-2, "2x2", "desciption2", 200.0, "https://i.imgur.com/sxCgDlo.jpg");
            puzzleRepository.save(p2);
            
            Puzzle p3 = new Puzzle((long)-3, "3x3", "desciption3", 300.0, "https://i.imgur.com/lNEWdCz.jpg");
            puzzleRepository.save(p3);

            Puzzle p4 = new Puzzle((long)-4, "4x4", "desciption4", 400.0, "https://i.imgur.com/EtWFJFj.jpg");
            puzzleRepository.save(p4);

            Puzzle p5 = new Puzzle((long)-5, "5x5", "desciption5", 500.0, "https://i.imgur.com/14mhYhd.jpg");
            puzzleRepository.save(p5);

            Puzzle p6 = new Puzzle((long)-6, "6x6", "desciption6", 600.0, "https://i.imgur.com/c4gMtbX.jpg");
            puzzleRepository.save(p6);

            Puzzle p7 = new Puzzle((long)-7, "7x7", "desciption7", 700.0, "https://i.imgur.com/REWDtCJ.jpg");
            puzzleRepository.save(p7);

        };
    }
            
    @Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
    
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(getListProperty("cors.allowed.origins"));
        config.setAllowedMethods(getListProperty("cors.allowed.methods"));
        config.setAllowedHeaders(getListProperty("cors.allowed.headers"));
        config.setExposedHeaders(getListProperty("cors.exposed.headers"));
        config.setAllowCredentials(getBooleanProperty("cors.allow.credentials"));
        config.setMaxAge(getLongProperty("cors.maxage"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    private List<String> getListProperty(String key){
        return Arrays.asList(env.getProperty(key).split(","));
    }

    private Boolean getBooleanProperty(String key){
        return Boolean.valueOf(env.getProperty(key));
    }

    private Long getLongProperty(String key){
        return Long.valueOf(env.getProperty(key));
    }
}
