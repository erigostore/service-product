package id.web.saka.report.product.ginee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GineeProductRepository extends JpaRepository<GineeProduct, String> {

}
