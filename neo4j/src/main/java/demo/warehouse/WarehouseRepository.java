package demo.warehouse;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WarehouseRepository extends PagingAndSortingRepository<Warehouse, String> {

}
