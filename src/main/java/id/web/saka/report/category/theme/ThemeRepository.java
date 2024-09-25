package id.web.saka.report.category.theme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    @Query("SELECT s FROM Theme s WHERE s.name = :name ")
    Theme findByThemeName(@Param("name") String name);
}
