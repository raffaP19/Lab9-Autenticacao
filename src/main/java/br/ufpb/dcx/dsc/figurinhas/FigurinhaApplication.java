package br.ufpb.dcx.dsc.figurinhas;

import br.ufpb.dcx.dsc.figurinhas.models.User;
import br.ufpb.dcx.dsc.figurinhas.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FigurinhaApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(FigurinhaApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return args -> {
            String adminUsername = "admin";

            if (userRepository.findByUsername(adminUsername) == null) {
                System.out.println("Criando usuário admin...");

                User adminUser = new User();
                adminUser.setUsername(adminUsername);
                adminUser.setPassword(bCryptPasswordEncoder.encode("admin123"));
                adminUser.setNome("Administrador");
                adminUser.setEmail("admin@figurinhas.com");

                userRepository.save(adminUser);
                System.out.println("Usuário admin criado com sucesso!");
            } else {
                System.out.println("Usuário admin já existe.");
            }
        };

    }

}