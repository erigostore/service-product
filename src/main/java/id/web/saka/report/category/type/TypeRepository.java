package id.web.saka.report.category.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    List<Type> findAllByThemeIdIs(int themeId);

    List<Type> findAllByBrandIdIsAndThemeIdIs(int brandId, int themeId);

    Type findFirstByTypeLevel1Is(String typeLevel1);
}
