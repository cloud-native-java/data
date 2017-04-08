package demo.account;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends
 PagingAndSortingRepository<Account, Long> {

}
