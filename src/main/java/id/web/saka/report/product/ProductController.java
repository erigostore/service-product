package id.web.saka.report.product;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import id.web.saka.report.util.Env;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private Env env;
    @RequestMapping(value = "/getGineeMasterProductId/{brand}/{password}")
    public ResponseEntity getGineeMasterProductId(@RequestBody String requestBody, @PathVariable String brand, @PathVariable String password) throws JsonProcessingException {
        LOG.info("getGineeMasterProductId|"+brand+"="+requestBody+"|START");
        boolean isMasterDataSaved = false, isPassword = false;
        if((brand.equals("POLKA") && password.equals(env.getPolkaWebhookSecretKey())) ||
                ((brand.equals("ERIGO") && password.equals(env.getErigoWebhookSecretKey())))
        ) {
            isPassword = true;
            isMasterDataSaved = productService.saveMasterProduct(brand, requestBody);
        }

        LOG.info("getGineeMasterProductId|"+brand+"|isSaved="+isMasterDataSaved+"|isPassword="+isPassword+"|END");
        return ResponseEntity.ok("{ \"status\": true }");
    }

    @RequestMapping(value = "/setSapMasterProductId/{brand}/{status}")
    public ResponseEntity setSapMasterProductId(@PathVariable String brand, @PathVariable String status) throws Exception {
        LOG.info("setSapMasterProductId|"+brand+"="+brand+"|status="+status+"|START");
        boolean isMasterDataSaved = false;

         productService.setSaveSAPMasterProduct(brand, status);

        LOG.info("setSapMasterProductId|"+brand+"|isSaved="+isMasterDataSaved+"|status="+status+"|END");
        return ResponseEntity.ok("{ \"status\": true }");
    }
}
