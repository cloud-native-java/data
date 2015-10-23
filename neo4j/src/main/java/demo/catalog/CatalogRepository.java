package demo.catalog;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CatalogRepository extends PagingAndSortingRepository<Catalog, String> {
}
