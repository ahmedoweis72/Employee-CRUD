package com.task.bootstrap;
import com.task.model.Employee;
import com.task.repositories.EmpRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class BootstrapData {
    @Bean
   public CommandLineRunner commandLineRunner(
           EmpRepositories repositories) {
        return args -> {
            Employee Ahmed=new Employee();
            Ahmed.setFirstName("Ahmed");
            Ahmed.setLastName("Ramadan");
            Ahmed.setAge(21);
            Employee Taha=new Employee();
            Taha.setFirstName("Taha");
            Taha.setLastName("Ramadan");
            Taha.setAge(22);
            Employee Gamal=new Employee();
            Gamal.setFirstName("Taha");
            Gamal.setLastName("Gamal");
            Gamal.setAge(23);
           repositories.saveAll(List.of(Ahmed,Taha,Gamal));
        };
    }

}
