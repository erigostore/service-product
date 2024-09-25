package id.web.saka.report.category;

import com.fasterxml.jackson.databind.JsonNode;
import id.web.saka.report.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public Long getCategoryGinee(JsonNode jsonArrayCategoryId, JsonNode jsonArrayCategoryName) {
        Category category = new Category();

        if (jsonArrayCategoryId != null && jsonArrayCategoryId.isArray() && jsonArrayCategoryName.isArray()) {

            if(jsonArrayCategoryId.size() >= 3) {
                category.setCategoryLevel1Id(jsonArrayCategoryId.get(0).asLong());
                category.setCategoryLevel2Id(jsonArrayCategoryId.get(1).asLong());
                category.setCategoryLevel3Id(jsonArrayCategoryId.get(2).asLong());
            }

            if(jsonArrayCategoryName.size() >= 3) {
                category.setCategoryLevel1Name(jsonArrayCategoryName.get(0).asText());
                category.setCategoryLevel2Name(jsonArrayCategoryName.get(1).asText());
                category.setCategoryLevel3Name(jsonArrayCategoryName.get(2).asText());
            }

            Category categoryFindByLevel = categoryRepository.findbyCategoryLevelId(category.getCategoryLevel1Id(), category.getCategoryLevel2Id(), category.getCategoryLevel3Id());

            if(categoryFindByLevel != null) {
                category = categoryFindByLevel;
            } else {
                category = categoryRepository.save(category);
            }

        }

        if(category.getId() == null) {
            return Long.valueOf(0);
        }

        return category.getId();
    }

    public String getCategoryLevel1Name(Product product) {
        Category category = categoryRepository.findbyCategoryId(product.getCategoryId());

        return category.getCategoryLevel1Name();
    }
}
