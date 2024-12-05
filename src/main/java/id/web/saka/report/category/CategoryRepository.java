package id.web.saka.report.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(" SELECT t FROM Category t WHERE t.categoryLevel1Id = :categoryLevel1Id AND t.categoryLevel2Id = :categoryLevel2Id AND t.categoryLevel3Id = :categoryLevel3Id ")
    Category findbyCategoryLevelId(Long categoryLevel1Id, Long categoryLevel2Id, Long categoryLevel3Id);
    @Query(" SELECT t FROM Category t WHERE t.spu = :spu ")
    Category findbySPu(String spu);

    @Query(" SELECT t.spu FROM Category t WHERE t.categoryLevel1Name = :categoryLevel1Name AND t.categoryLevel2Name = :categoryLevel2Name AND t.categoryLevel3Id = :categoryLevel3Name ")
    String getCategoryByLevel(String categoryLevel1Name, String categoryLevel2Name, String categoryLevel3Name);
}
