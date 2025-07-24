package id.web.saka.report.product.ginee;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.web.saka.report.product.ginee.DTO.GineeProductDTO;
import id.web.saka.report.product.ginee.DTO.GineeProductVariantDTO;
import id.web.saka.report.util.Env;
import id.web.saka.report.util.Util;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GineeProductService {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(GineeProductService.class);

    @Autowired
    private Env env;

    @Autowired
    private GineeProductRepository gineeProductRepository;

    public void getListGineeMasterProduct(String brand) {

        int page = 1;

        for(int i = 0; i < page; i++) {

            page = fetchProductsFromGinee(brand, i);

            i++;
        }

    }

    private int fetchProductsFromGinee(String brand, int page) {
        LOG.info("postProductMasterFromGinee|Brand:"+brand+"|Page:"+page+"|START");

        try {
            ObjectMapper objectMapper; objectMapper = new ObjectMapper();
            RestTemplate restTemplate; restTemplate = new RestTemplate();

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            HttpHeaders headers; headers = new HttpHeaders();
            headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            headers.set("X-Advai-Country", "ID");
            headers.set("Authorization", Util.buildGineeSignatureErigo(brand, HttpMethod.POST, "/openapi/product/master/v1/list"));

            String pageJson = "{ \"page\":" + page + ", \"size\":200 }";

            HttpEntity<String> request = new HttpEntity<String>(pageJson, headers);

            String productMasterJsonString = restTemplate.postForObject("https://api.ginee.com/openapi/product/master/v1/list", request, String.class);

            LOG.info("postProductMasterFromGinee|Brand:"+brand+"|Page:"+page+"|JSON="+productMasterJsonString+"|RESPONSE");

            page = objectMapper.readTree(productMasterJsonString).get("data").get("total").asInt(page);

            JsonNode jsonNode = objectMapper.readTree(productMasterJsonString).get("data").get("content");

            if(!jsonNode.isEmpty() && jsonNode.isArray()) {
                List<GineeProductDTO> gineeProductDTOS = List.of(objectMapper.convertValue(jsonNode, GineeProductDTO[].class));

                saveGineeProduct(gineeProductDTOS);
                LOG.info("getStatusToGineeOutboundManual="+gineeProductDTOS.size()+"|RESPONSE");
            }

        } catch (JsonProcessingException e) {
            LOG.error("postProductMasterFromGinee|Brand:"+brand+"|Page:"+page+"|message:"+e.getMessage());
            throw new RuntimeException(e);
        }

        LOG.info("postProductMasterFromGinee|Brand:"+brand+"|Page:"+page+"|END");

        return page;
    }

    private void saveGineeProduct(List<GineeProductDTO> gineeProductDTOS) {
        LOG.info("saveGineeProduct|"+gineeProductDTOS.size()+"|START");

        for(GineeProductDTO gineeProductDTO : gineeProductDTOS) {

            gineeProductRepository.saveAll(getGineeProductFromDTO(gineeProductDTO));

        }

    }

    private List<GineeProduct> getGineeProductFromDTO(GineeProductDTO gineeProductDTO) {
        GineeProduct gineeProduct;
        List<GineeProduct> gineeProducts = new ArrayList<>();

        for(GineeProductVariantDTO gineeProductVariantDTO :  gineeProductDTO.getVariants()) {
            gineeProduct = new GineeProduct();

            gineeProduct.setProductId(gineeProductDTO.getProductId());
            gineeProduct.setVariantId(gineeProductVariantDTO.getVariantId());
            gineeProduct.setMsku(gineeProductVariantDTO.getMsku());
            gineeProduct.setProductName(gineeProductDTO.getName() + ((gineeProductVariantDTO.getVariantNames()!=null && !String.join("-", gineeProductVariantDTO.getVariantNames()).contains("-"))?("-" + String.join("-", gineeProductVariantDTO.getVariantNames())):""));
            gineeProduct.setProductVariant(gineeProductVariantDTO.getVariantNames() != null ? String.join("-", gineeProductVariantDTO.getVariantNames()) : "");
            gineeProduct.setProductImages(gineeProductDTO.getImages() != null ? String.join(",", gineeProductDTO.getImages()) : "");

            gineeProducts.add(gineeProduct);
        }

        return gineeProducts;
    }

}
