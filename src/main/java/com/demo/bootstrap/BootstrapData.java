package com.demo.bootstrap;

import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.model.Role;
import com.demo.repositories.EmpRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@Configuration
public class BootstrapData {

    private final PasswordEncoder passwordEncoder;

    public BootstrapData(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner commandLineRunner(EmpRepositories repositories) {
        return args -> {
            Department dept = new Department("IT", "Technology Department");
            Employee ahmed = new Employee();
                ahmed.setFirstName("Ahmed");
                ahmed.setLastName("Ramadan");
                ahmed.setUsername("ahmed");
                ahmed.setPassword(passwordEncoder.encode("password"));
                ahmed.setAge(21);
                ahmed.setRole(Role.USER);

                Employee abdallah = new Employee();
                abdallah.setFirstName("Abdallah");
                abdallah.setLastName("Ramadan");
                abdallah.setUsername("abdallah");
                abdallah.setPassword(passwordEncoder.encode("password"));
                abdallah.setAge(26);
                abdallah.setRole(Role.MANAGER);

                Employee mohamed = new Employee();
                mohamed.setFirstName("Mohamed");
                mohamed.setLastName("Ramadan");
                mohamed.setUsername("mohamed");
                mohamed.setPassword(passwordEncoder.encode("password"));
                mohamed.setAge(30);
                mohamed.setRole(Role.ADMIN);
                dept.setEmployees(List.of(ahmed, abdallah, mohamed));
                repositories.saveAll(List.of(ahmed, abdallah, mohamed));

        };
    }
}
