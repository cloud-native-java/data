package demo.customer;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends
 PagingAndSortingRepository<Customer, Long> {

 // <1>
 Optional<Customer> findByEmailContaining(String email);
}
