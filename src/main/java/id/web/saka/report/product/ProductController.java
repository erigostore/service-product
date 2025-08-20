package id.web.saka.report.product;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import id.web.saka.report.util.Env;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

    @CrossOrigin
    @RequestMapping(value = "/getMasterProductGroupBySpu/{brand}/{status}")
    public ResponseEntity getMasterProductGroupBySpu(@PathVariable String brand, @PathVariable String status) throws JsonProcessingException {
        LOG.info("getMasterProductGroupBySpu|"+brand+"|"+ status+"|START");

        String jsonObject = productService.getMasterProductGroupBySpu(brand, status);

        LOG.info("getMasterProductGroupBySpu|"+brand+"|JSON isEmpty ="+jsonObject.isEmpty()+"|END");
        return ResponseEntity.ok(jsonObject);
    }

    @CrossOrigin
    @RequestMapping(value = "/getMasterProductPdf/{brand}/{status}")
    public ResponseEntity<byte[]> getMasterProductPdf(@RequestBody String requestBody, @PathVariable String brand, @PathVariable String status) throws IOException {
        LOG.info("getMasterProductPdf|"+brand+"="+requestBody+"|START");

        ByteArrayOutputStream baos = productService.getMasterProductPdf(brand, status, requestBody);

        byte[] pdfBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength( pdfBytes.length );
        headers.setContentDispositionFormData("attachment", "document.pdf"); // Suggest filename
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/setSapMasterProductId/{brand}/{status}")
    public ResponseEntity setSapMasterProductId(@PathVariable String brand, @PathVariable String status) throws Exception {
        LOG.info("setSapMasterProductId|"+brand+"="+brand+"|status="+status+"|START");
        boolean isMasterDataSaved = false;

         productService.setSaveSAPMasterProduct(brand, status);

        LOG.info("setSapMasterProductId|"+brand+"|isSaved="+isMasterDataSaved+"|status="+status+"|END");
        return ResponseEntity.ok("{ \"status\": true }");
    }

    @CrossOrigin
    @RequestMapping(value = "/setRevotaMasterProduct/{brand}/{password}")
    public ResponseEntity setRevotaMasterProduct(@RequestBody String requestBody, @PathVariable String brand, @PathVariable String password) throws JsonProcessingException {
        LOG.info("setRevotaMasterProduct|"+brand+"="+requestBody+"|START");
        boolean isMasterDataSaved = false, isPassword = false;

        if((brand.equals("POLKA") && password.equals(env.getPolkaWebhookSecretKey())) ||
                ((brand.equals("ERIGO") && password.equals(env.getErigoWebhookSecretKey())))
        ) {
            isMasterDataSaved = productService.saveMasterProductRevota(brand, requestBody);
        }

        LOG.info("setRevotaMasterProduct|"+brand+"|isSaved="+isMasterDataSaved+"|isPassword="+isPassword+"|END");
        return ResponseEntity.ok("{ \"status\": true }");
    }

    @CrossOrigin
    @RequestMapping(value = "/setNewMasterProductManual")
    public ResponseEntity setNewMasterProductManual(@RequestBody String requestBody) throws JsonProcessingException {
        LOG.info("setNewMasterProductManual|"+requestBody+"|START");


        String response = productService.setNewMasterProductManual(requestBody);


        LOG.info("setNewMasterProductManual|"+requestBody+"|Response="+response+"|END");

        return ResponseEntity.ok("{ \"status\": \""+response+"\" }");
    }
    @CrossOrigin
    @RequestMapping(value = "/setSapMasterProductCogs/{brand}/{password}")
    public ResponseEntity setSapMasterProductCogs(@RequestBody String requestBody, @PathVariable String brand, @PathVariable String password) throws JsonProcessingException {
        LOG.info("setSapMasterProductCogs|"+brand+"="+requestBody+"|START");
        boolean isMasterDataSaved = false, isPassword = false;

        if((brand.equals("POLKA") && password.equals(env.getPolkaWebhookSecretKey())) ||
                ((brand.equals("ERIGO") && password.equals(env.getErigoWebhookSecretKey())))
        ) {
            isMasterDataSaved = productService.saveMasterProductCogs(brand, requestBody);
        }

        LOG.info("setSapMasterProductCogs|"+brand+"|isSaved="+isMasterDataSaved+"|isPassword="+isPassword+"|END");
        return ResponseEntity.ok("{ \"status\": true }");
    }

    @CrossOrigin
    @RequestMapping(value = "/findMasterProductBySkuBarcodeSize/{brand}/{password}")
    public ResponseEntity findMasterProductBySkuBarcodeSize(@RequestBody String requestBody, @PathVariable String brand, @PathVariable String password) throws JsonProcessingException {
        LOG.info("findMasterProductBySkuBarcodeSize|"+brand+"="+requestBody+"|START");
        String jsonObject = "";

        if((brand.equals("POLKA") && password.equals(env.getPolkaWebhookSecretKey())) ||
                ((brand.equals("ERIGO") && password.equals(env.getErigoWebhookSecretKey())))
        ) {
            jsonObject = productService.findMasterProductBySkuBarcodeSize(brand, requestBody);
        }

        LOG.info("findMasterProductBySkuBarcodeSize|"+brand+"|jsonObject="+jsonObject+"|END");

        return ResponseEntity.ok(jsonObject);
    }

    /*@CrossOrigin(origins = {"https://dashboard.erigostore.co.id/", "http://103.49.238.3:8585/", "http://103.135.49.140:8585/", "http://localhost:5173/",  "http://localhost:8080/", "http://172.16.1.23:8585/",  "http://172.16.1.23:8686/"} ,  maxAge = 3600)*/
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/searchProductsByTextAndbyBarcodeOrSku/{brand}/{searchType}/{searchText}")
    public ResponseEntity searchProductsByTextAndbyBarcodeOrSku(@PathVariable String brand, @PathVariable String searchType, @PathVariable String searchText) throws JsonProcessingException {
        LOG.info("searchProductsByTextAndbyBarcodeOrSku|brand="+brand+"|searchType="+searchType+"|searchText="+searchText+"|START");
        String jsonObject = "";

        jsonObject = productService.searchProductsByTextAndbyBarcodeOrSku(brand, searchType, searchText);

        LOG.info("searchProductsByTextAndbyBarcodeOrSku|brand="+brand+"|searchType="+searchType+"|searchText="+searchText+"|Result=" + jsonObject+"|END");
        return ResponseEntity.ok(jsonObject);
    }

    /*@CrossOrigin(origins = {"https://dashboard.erigostore.co.id/", "http://103.135.49.140:8585/", "http://localhost:5173/",  "http://localhost:8080/", "http://172.16.1.23:8585/",  "http://172.16.1.25:8585/"} ,  maxAge = 3600)*/
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/searchMultiProductsByTextAndbyBarcodeOrSku/{brand}/{searchType}/{searchText}")
    public ResponseEntity searchMultiProductsByTextAndbyBarcodeOrSku(@PathVariable String brand, @PathVariable String searchType, @PathVariable String searchText) throws JsonProcessingException {
        LOG.info("searchProductsByTextAndbyBarcodeOrSku|brand="+brand+"|searchType="+searchType+"|searchText="+searchText+"|START");
        String jsonObject = "";

        jsonObject = productService.searchMultiProductsByTextAndbyBarcodeOrSku(brand, searchType, searchText);

        LOG.info("searchMultiProductsByTextAndbyBarcodeOrSku|brand="+brand+"|searchType="+searchType+"|searchText="+searchText+"|Result=" + jsonObject+"|END");
        return ResponseEntity.ok(jsonObject);
    }
}
