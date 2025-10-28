package com.digitalmenu.backend.infrastructure.config;

import com.digitalmenu.backend.business.model.User;
import com.digitalmenu.backend.business.model.UserType;
import com.digitalmenu.backend.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = User.builder()
                    .name("Admin")
                    .email("admin@digitalmenu.com")
                    .password(passwordEncoder.encode("123456"))
                    .type(UserType.ADMIN)
                    .build();
            userRepository.save(user);
            System.out.println("Usuário admin criado com sucesso!");
        }
    }

}
