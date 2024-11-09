package BootstrapData;
import dao.CustomerRepository;
import dao.DivisionRepository;
import entities.Customer;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
@Data
@Component
public class BootstrapData {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    @PostConstruct
    public void loadInitialData() {
        if (customerRepository.count() == 0) { // Only load if no customers exist

            Customer customer1 = new Customer(
                    null,
                    "Taylor",
                    "Swift",
                    "13 Nashville Ave",
                    "37201",
                    "555-1989",
                    new Date(),
                    new Date(),
                    divisionRepository.findAll().get(7),
                    null
            );

            Customer customer2 = new Customer(
                    null,
                    "Justin",
                    "Timberlake",
                    "888 Lakeview Dr",
                    "90001",
                    "555-2020",
                    new Date(),
                    new Date(),
                    divisionRepository.findAll().get(7),
                    null
            );

            Customer customer3 = new Customer(
                    null,
                    "Beyonce",
                    "Knowles",
                    "777 Houston St",
                    "77001",
                    "555-4444",
                    new Date(),
                    new Date(),
                    divisionRepository.findAll().get(5),
                    null
            );

            Customer customer4 = new Customer(
                    null,
                    "Ariana",
                    "Grande",
                    "123 Thank U St",
                    "33101",
                    "555-5555",
                    new Date(),
                    new Date(),
                    divisionRepository.findAll().get(2),
                    null
            );

            Customer customer5 = new Customer(
                    null,
                    "Bruno",
                    "Mars",
                    "24K Magic Rd",
                    "94101",
                    "555-6006",
                    new Date(),
                    new Date(),
                    divisionRepository.findAll().get(3),
                    null
            );

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.save(customer4);
            customerRepository.save(customer5);

            System.out.println("Sample customers added!");
        } else {
            System.out.println("Sample customers already exist!");
        }
    }
}
