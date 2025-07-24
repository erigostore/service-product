package id.web.saka.report.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findDistinctFirstByBrandAndMskuContainingIgnoreCase(String brand, String msku);

    Product findDistinctFirstByBrandAndSpuContainingIgnoreCaseAndVariantContainingIgnoreCase(String brand, String spu, String variant);

    List<Product> findAllByBrandAndSpuContainingIgnoreCase(String brand, String spu);

    @Query("SELECT s FROM Product s WHERE s.status =:status AND s.brand =:brand GROUP BY s.spu ")
    List<Product> findByStatusGroupBySpu(@Param("brand") String brand, @Param("status") String status);

    List<Product> findBySpu(@Param("spu") String spu);

    List<Product> findAllBySpuIsLikeIgnoreCaseAndMskuIsLikeIgnoreCaseAndVariantIsLikeIgnoreCase(String spu, String msku, String variant);

    @Transactional
    @Modifying
    @Query("UPDATE Product s SET s.status =:status WHERE s.msku =:msku ")
    void updateStatusByMsku(@Param("status") String status, @Param("msku") String msku);

    @Transactional
    @Modifying
    @Query("UPDATE Product s SET s.purchasePrice =:purchasePrice WHERE s.msku =:msku ")
    void updatePurchasePrice(String msku, Long purchasePrice);
}
