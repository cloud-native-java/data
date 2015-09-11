package orders.repositories;

import orders.domain.Shipment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShipmentRepository extends PagingAndSortingRepository<Shipment, String> {

}
