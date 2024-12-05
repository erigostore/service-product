package id.web.saka.report.category;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.web.saka.report.product.Product;
import id.web.saka.report.product.ProductStatus;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private static Logger LOG = (Logger) LoggerFactory.getLogger(CategoryService.class);
    public String getCategoryGinee(JsonNode jsonArrayCategoryId, JsonNode jsonArrayCategoryName) {
        Category category = new Category();

        if (jsonArrayCategoryId != null && jsonArrayCategoryId.isArray() && jsonArrayCategoryName.isArray()) {

            if(jsonArrayCategoryId.size() >= 3) {
                category.setSpu(jsonArrayCategoryId.get(0)+"_"+jsonArrayCategoryId.get(1)+"_"+jsonArrayCategoryId.get(2));
                category.setCategoryLevel1Id(jsonArrayCategoryId.get(0).asLong());
                category.setCategoryLevel2Id(jsonArrayCategoryId.get(1).asLong());
                category.setCategoryLevel3Id(jsonArrayCategoryId.get(2).asLong());
            }

            if(jsonArrayCategoryName.size() >= 3) {
                category.setSpu(categoryRepository.getCategoryByLevel(jsonArrayCategoryName.get(0).asText(), jsonArrayCategoryName.get(1).asText(), jsonArrayCategoryName.get(2).asText()));
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

        return category.getSpu();
    }

    public String getCategoryLevel1Name(Product product) {
        Category category = categoryRepository.findbySPu(product.getSpu());

        return category.getCategoryLevel1Name();
    }

    public boolean saveCategoryProductByUpload(String brand, String requestBody) throws JsonProcessingException {
        LOG.info("saveCategoryProductByUpload|"+brand+"="+requestBody+"|START");

        boolean isSaveSuccess = false;
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(requestBody).get("data");
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Category> categories = jsonNode.isArray()?  Arrays.stream(objectMapper.convertValue(jsonNode, Category[].class)).toList(): Collections.singletonList(objectMapper.convertValue(jsonNode, Category.class));  ;

        if(categories != null && categories.size() > 0) {
            LOG.info("saveCategoryProductByUpload|"+brand+"="+categories.size()+"|CATEGORIES SIZE");
            categoryRepository.saveAll(categories);
        }

        LOG.info("saveCategoryProductByUpload|"+brand+"="+categories+"|END");
        return isSaveSuccess;
    }
}
