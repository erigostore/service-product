package id.web.saka.report.category;


import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import id.web.saka.report.util.Env;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Autowired
    private Env env;

    @Autowired
    private CategoryService categoryService;
    private static Logger LOG = (Logger) LoggerFactory.getLogger(CategoryController.class);

    @CrossOrigin
    @RequestMapping(value = "/getCategoryProductByUpload/{brand}/{password}")
    public ResponseEntity getCategoryProductByUpload(@RequestBody String requestBody, @PathVariable String brand, @PathVariable String password) throws JsonProcessingException {
        LOG.info("getCategoryProductByUpload|"+brand+"="+requestBody+"|START");
        boolean isMasterDataSaved = false, isPassword = false;
        if((brand.equals("POLKA") && password.equals(env.getPolkaWebhookSecretKey())) ||
                ((brand.equals("ERIGO") && password.equals(env.getErigoWebhookSecretKey())))
        ) {
            isPassword = true;
            isMasterDataSaved = categoryService.saveCategoryProductByUpload(brand, requestBody);
        }

        LOG.info("getCategoryProductByUpload|"+brand+"|isSaved="+isMasterDataSaved+"|isPassword="+isPassword+"|END");
        return ResponseEntity.ok("{ \"status\": true }");
    }

}
