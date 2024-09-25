package id.web.saka.report.sap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SapStatusRepository extends JpaRepository<SapStatus, Long> {
}
