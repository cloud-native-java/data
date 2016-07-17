package demo.account;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A {@link PagingAndSortingRepository} for the {@link Account} domain class
 * that provides basic data management capabilities that include paging and
 * sorting results.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
public interface AccountRepository
		extends
			PagingAndSortingRepository<Account, Long> {
}
