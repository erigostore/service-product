package id.web.saka.report.category.size;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    // This interface will automatically inherit methods for CRUD operations
    // from JpaRepository, such as findAll(), findById(), save(), deleteById(), etc.
}
