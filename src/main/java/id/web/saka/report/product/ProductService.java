package id.web.saka.report.product;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.web.saka.report.category.CategoryService;
import id.web.saka.report.category.colour.Colour;
import id.web.saka.report.category.colour.ColourService;
import id.web.saka.report.category.theme.Theme;
import id.web.saka.report.category.theme.ThemeService;
import id.web.saka.report.product.DTO.ProductLabelDTO;
import id.web.saka.report.sap.SapStatus;
import id.web.saka.report.sap.SapStatusRepository;
import id.web.saka.report.util.Env;
import id.web.saka.report.util.Util;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.json.data.JsonDataSource;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductService {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ColourService colourService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private SapStatusRepository sapStatusRepository;

    @Autowired
    private Env env;

    public boolean saveMasterProduct(String brand, String requestBody) throws JsonProcessingException {


        boolean isSaveSuccess = false;
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;

        jsonNode = objectMapper.readTree(requestBody).get("payload");
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Product product = objectMapper.convertValue(jsonNode, Product.class);

        if(product != null) {
            product.setStatusEnum(ProductStatus.NEW);

            productRepository.saveAll(getMasterProductDetailGinee(brand, product));
            isSaveSuccess = true;
        }

        return isSaveSuccess;
    }

    private List<Product> getMasterProductDetailGinee(String brand, Product product) throws JsonProcessingException {
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();
        RestTemplate restTemplate; restTemplate = new RestTemplate();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String url = "https://api.ginee.com/openapi/product/master/v1/get?productId="+product.getId();

        HttpHeaders headers; headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("X-Advai-Country", "ID");
        headers.set("Authorization", Util.buildGineeSignatureErigo(brand, HttpMethod.GET, "/openapi/product/master/v1/get"));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        JsonNode jsonNode = objectMapper.readTree(response.getBody()).get("data");
        product = objectMapper.convertValue(jsonNode, Product.class);

        Colour colour = colourService.getColourId(product.getColour());

        product.setBrand(brand);
        product.setSpu(categoryService.getCategoryGinee(objectMapper.readTree(response.getBody()).get("data").get("fullCategoryId"), objectMapper.readTree(response.getBody()).get("data").get("fullCategoryName")));
        product.setStatusEnum(ProductStatus.NEW);
        product.setColour(colour.getName());
        product.setColourId(colour.getCode());

        Theme theme = themeService.getThemeByThemeIdNameAndCategoryLevel1(product.getTheme(), product.getName(), categoryService.getCategoryLevel1Name(product));
        product.setThemeId(theme.getCode());
        product.setTheme(theme.getName());

        return getMasterVariantsProductDetailGinee(product, jsonNode.get("variations"));
    }

    private List<Product>  getMasterVariantsProductDetailGinee(Product product, JsonNode variations) {
        Product productVariant = null;
        List<Product> variantProducts = new ArrayList<>();

        if(variations.isArray()) {
            Iterator<JsonNode> jsonNodeIterator = variations.iterator();
            while (jsonNodeIterator.hasNext()) {
                JsonNode jsonNode = jsonNodeIterator.next();
                productVariant = new Product();

                productVariant.setBrand(product.getBrand());
                productVariant.setSpu(product.getSpu());
                productVariant.setName(product.getName());
                productVariant.setStatusEnum(ProductStatus.NEW);
                productVariant.setMsku(jsonNode.get("sku").asText());
                productVariant.setId(jsonNode.get("id").asText());
                productVariant.setPurchasePrice(jsonNode.get("purchasePrice").asLong());
                productVariant.setSellingPrice(jsonNode.get("sellingPrice").get("amount").asLong());
                productVariant.setColourId(product.getColourId());
                productVariant.setColour(product.getColour());
                productVariant.setTheme(productVariant.getTheme());
                productVariant.setThemeId(product.getThemeId());
                productVariant.setCreateDatetime(product.getCreateDatetime());
                productVariant.setUpdateDatetime(product.getUpdateDatetime());
                productVariant.setVariant(Util.arrayJsonNodetoString(jsonNode.get("optionValues")));

                variantProducts.add(productVariant);
            }
        }

        return variantProducts;
    }

    public void setSaveSAPMasterProduct(String brand, String status) throws Exception {
        List<Product> productSpus = productRepository.findByStatusGroupBySpu(brand, status);

        ObjectMapper objectMapper; objectMapper = new ObjectMapper();
        RestTemplate restTemplate; restTemplate = new RestTemplate();

        HttpHeaders headers; headers = new HttpHeaders();
        headers.setBearerAuth(Util.buildTokenSignatureSAP());
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        for(Product productSpu : productSpus) {
            JSONObject productSAPJsonObject = new JSONObject();
            Map<String, Object> invarticle = new HashMap<>();
            invarticle.put("BrandKey", "IGO");
            invarticle.put("BrandName", productSpu.getBrand());
            invarticle.put("categoryInit", themeService.changeThemeIdToString(productSpu.getThemeId()));
            invarticle.put("categoryName", themeService.getThemeId(productSpu.getThemeId()).getName());
            invarticle.put("TypeInit", productSpu.getThemeId()); //TODO : Need checks Type Init and Type Name Init in SAP Database
            invarticle.put("TypeName", categoryService.getCategoryLevel1Name(productSpu));
            invarticle.put("articleCode", productSpu.getSpu());
            invarticle.put("articleName", productSpu.getName());
            invarticle.put("colourInit", productSpu.getColourId());
            invarticle.put("colourName", colourService.getColourName(productSpu.getColourId()).getName());
            invarticle.put("sex", "U");
            invarticle.put("basePrice", productSpu.getPurchasePrice()+"");
            invarticle.put("salePrice", productSpu.getSellingPrice()+"");
            invarticle.put("notes", "-");
            invarticle.put("itemgroup", 101);
            //invarticle.put("code_CatType", productSpu.getCategoryId()); //TODO : Need checks code_catType

            List<Product> productMsku = productRepository.findBySpu(productSpu.getSpu());

            for(Product product : productMsku) {
                Map<String, Object> inventory = new HashMap<>();
                inventory.put("articleCode", product.getSpu());
                inventory.put("barcode", product.getMsku());
                inventory.put("sizes", product.getVariant());
                inventory.put("CurrentBasePrice", product.getPurchasePrice()+"");
                inventory.put("CurrentSalePrice", product.getSellingPrice()+"");
                inventory.put("qty", 0);
                inventory.put("CurrentSalePrice_validated", product.getSellingPrice());

                productSAPJsonObject.append("inventory", inventory);
            }

            productSAPJsonObject.put("invarticle", new Map[]{invarticle});

            HttpEntity<String> request = new HttpEntity<String>(productSAPJsonObject.toString(), headers);
            String orderResultAsJsonStr = restTemplate.postForObject("http://"+env.getSapApiKeyIp()+"/api/Master/Item", request, String.class);

            String jsonResponseStatus = objectMapper.readTree(orderResultAsJsonStr).get("status").asText();

            if(jsonResponseStatus.equals("SUCCEED")) {
                JsonNode jsonNodeSapStatusList = objectMapper.readTree(orderResultAsJsonStr).get("data");

                if(jsonNodeSapStatusList==null) {
                    return;
                }

                List<SapStatus> sapStatuses = jsonNodeSapStatusList.isArray()? Arrays.stream(objectMapper.convertValue(jsonNodeSapStatusList, SapStatus[].class)).toList():null;

                sapStatusRepository.saveAll(sapStatuses);

                for(id.web.saka.report.sap.SapStatus sapStatus : sapStatuses) {
                    if(sapStatus.getStatus().equals("SUCCEED")) {
                        productRepository.updateStatusByMsku(ProductStatus.UPDATE.toString(), sapStatus.getMsku());
                    }
                }
            }

        }

    }

    public boolean saveMasterProductRevota(String brand, String requestBody) throws JsonProcessingException {
        LOG.info("saveMasterProductRevota|start");
        boolean isSaveSuccess = false;
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(requestBody).get("data");
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Product> products = jsonNode.isArray()?  Arrays.stream(objectMapper.convertValue(jsonNode, Product[].class)).toList(): Collections.singletonList(objectMapper.convertValue(jsonNode, Product.class));  ;

        if(products != null && products.size() > 0) {
            int i = 0;

            for(Product product : products) {
                productRepository.save(getMasterProductDetailRevota(brand, product));

                if(i > 999) {
                    productRepository.flush();
                    LOG.info("saveMasterProductRevota|flushed");
                    i = 0;
                    isSaveSuccess = true;
                }

                LOG.info("saveMasterProductRevota|Saved="+product.toString());
                i++;
            }
            
            saveMasterProductToOktopusPos(products);

        }

        return isSaveSuccess;
    }

    private void saveMasterProductToOktopusPos(List<Product> products) {
        RestTemplate restTemplate = new RestTemplate();

        String url = env.getErigoServicePosurl()+"/pos/product/addNewOktopusProduct/ERIGO";

        HttpHeaders headers; headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        JSONObject orderJsonObject = new JSONObject();
        orderJsonObject.put("data", products);

        HttpEntity<String> request = new HttpEntity<String>(orderJsonObject.toString(), headers);
        String orderResultAsJsonStr = restTemplate.postForObject(url, request, String.class);

        LOG.info("saveMasterProductToOktopusPos="+orderResultAsJsonStr);
    }

    public boolean saveMasterProductCogs(String brand, String requestBody) throws JsonProcessingException {
        LOG.info("saveMasterProductCogs|start");
        boolean isSaveSuccess = false;
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(requestBody).get("data");
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Product> products = jsonNode.isArray()?  Arrays.stream(objectMapper.convertValue(jsonNode, Product[].class)).toList(): Collections.singletonList(objectMapper.convertValue(jsonNode, Product.class));  ;

        if(products != null && products.size() > 0) {
            int i = 0;
            for(Product product : products) {
                productRepository.updatePurchasePrice(product.getMsku(), product.getPurchasePrice());

                if(i > 999) {
                    productRepository.flush();
                    LOG.info("saveMasterProductCogs|flushed");
                    isSaveSuccess = true;
                    i = 0;
                }

                LOG.info("saveMasterProductCogs|Saved="+product.toString());
                i++;
            }

        }

        return isSaveSuccess;
    }
    public String findMasterProductBySkuBarcodeSize(String brand, String requestBody) throws JsonProcessingException {
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(requestBody).get("data");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Product product = objectMapper.convertValue(jsonNode, Product.class);

        List<Product> products = productRepository.findAllBySpuIsLikeIgnoreCaseAndMskuIsLikeIgnoreCaseAndVariantIsLikeIgnoreCase(product.getSpu(), product.getMsku(), product.getVariant());

        return new ObjectMapper().writeValueAsString(products);
    }

    private Product getMasterProductDetailRevota(String brand, Product product) {
        Colour colour = colourService.getColourId(product.getColour());

        product.setId(product.getMsku());
        product.setStatusEnum(ProductStatus.NEW);
        product.setColourId(colour.getCode());
        product.setCreateDatetime(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
        product.setUpdateDatetime(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));

        Theme theme = themeService.getThemeByThemeIdNameAndCategoryLevel1(product.getCategory(), product.getName(), product.getType());
        product.setThemeId(theme.getCode());
        product.setTheme(theme.getName());

        return product;
    }

    public String getMasterProductGroupBySpu(String brand, String status) throws JsonProcessingException {

        List<Product> products = productRepository.findByStatusGroupBySpu(brand, status);

        return new ObjectMapper().writeValueAsString(products);
    }

    public String searchProductsByTextAndbyBarcodeOrSku(String brand, String searchType, String searchText) throws JsonProcessingException {
        Product product = null;
        List<Product> products = new ArrayList<>();
        String[] lines = searchText.split(",");

        if(lines != null) {
            for(String line : lines) {
                if("bybarcode".equalsIgnoreCase(searchType)) {
                    product = searchProductByBarcode(brand, line.split("-"));
                } else if ("bysku".equalsIgnoreCase(searchType)) {
                    product = searchProductBySku(brand, line.split("-"));
                }
                if(product!=null) {products.add(product);}
            }
        }

        return new ObjectMapper().writeValueAsString(products);
    }


    public String searchMultiProductsByTextAndbyBarcodeOrSku(String brand, String searchType, String searchText) throws JsonProcessingException  {
        List<Product> products = new ArrayList<>();

        if("bypurespu".equalsIgnoreCase(searchType)) {
            products = productRepository.findAllByBrandAndSpuContainingIgnoreCase(brand, searchText);
        }

        if(products == null && products.isEmpty()) {
            return "{}";
        }

        return new ObjectMapper().writeValueAsString(products);
    }

    private Product searchProductByBarcode(String brand, String[] line) {
        Product product = null;

        if(line!=null && line.length==2) { //NOTE format data example 00ERXC0604-20
            product = productRepository.findDistinctFirstByBrandAndMskuContainingIgnoreCase(brand, line[0].replaceAll("\\s+","").toUpperCase());
            if(product!=null && line[1].matches("-?\\d+")) {product.setQuantity(Integer.parseInt(line[1]));}
            if(product==null) { product = getFillNotFoundProduct("NOT AVAILABLE",  line[0], "NOT AVAILABLE"); }
        }

        return product;
    }

    private Product searchProductBySku(String brand, String[] line) {
        Product product = null;

        if(line!=null && line.length==3) { //NOTE format data example XC137-XXL-20
            product = productRepository.findDistinctFirstByBrandAndSpuContainingIgnoreCaseAndVariantContainingIgnoreCase(brand, line[0].replaceAll("\\s+","").toUpperCase(), line[1].replaceAll("\\s+","").toUpperCase());
            if(product!=null && line[2].matches("-?\\d+")) {product.setQuantity(Integer.parseInt(line[2]));}
            if(product==null) { product = getFillNotFoundProduct(line[0], "NOT AVAILABLE", line[0]); }
        }

        return product;
    }

    private Product getFillNotFoundProduct(String spu, String msku, String variant) {
        Product emptyProduct = new Product();

        emptyProduct.setSpu(spu);
        emptyProduct.setMsku(msku);
        emptyProduct.setName("NOT AVAILABLE");
        emptyProduct.setVariant(variant);
        emptyProduct.setQuantity(0);

        return emptyProduct;
    }


    /*https://stackoverflow.com/questions/60642696/jasperreports-json-data-report-shows-null-values-when-run-in-java*/
    public ByteArrayOutputStream getMasterProductPdf(String brand, String status, String jsonData) throws JsonProcessingException {
        LOG.info("getMasterProductPdf|brand="+brand+"|status="+status+"|jsonData="+jsonData+"|CONVERT JSON");
        ObjectMapper objectMapper; objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData).get("data");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<ProductLabelDTO> productLabelDTOList = List.of(objectMapper.convertValue(jsonNode, ProductLabelDTO[].class));

        List<Product> products = convertProductLabelDTOToProduct(brand, productLabelDTOList);

        JSONObject productJsonObject = new JSONObject();
        productJsonObject.put("data", products);

        return getMasterProductPdf(productJsonObject.toString());
    }

    private List<Product> convertProductLabelDTOToProduct(String brand, List<ProductLabelDTO> productLabelDTOList) {
        LOG.info("convertProductLabelDTOToProduct|brand="+brand+"|productLabelDTOList="+productLabelDTOList+"|START");

        Product productQuery = null;
        List<Product> products = new ArrayList<>();
        for(ProductLabelDTO productLabelDTO : productLabelDTOList) {
            int size = productLabelDTO.getQuantity();
            productQuery = productRepository.findDistinctFirstByBrandAndMskuContainingIgnoreCase(brand, productLabelDTO.getMsku());

            for(int i = 0; i < size; i++) {
                products.add(productQuery);
            }
        }

        return products;
    }

    private ByteArrayOutputStream getMasterProductPdf(String jsonData) {
        LOG.info("getMasterProductPdf|jsonData="+jsonData+"|FINAL JSON");
        ByteArrayOutputStream baos;

        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/templates/Barcode_Json_105_22.jrxml"));

            LOG.info("getMasterProductPdf|jasperDesign="+jasperDesign);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            //jsonData = "{ \"data\" : [{ brand: \"ERIGO\", msku: \"00ERSI0015\", spu: \"ER.IGO-SI003\", name: \"JOGGER PANTS ALEXA STRIPE NAVY\", type: \"JOGGER PANTS\", variant:\"36\", colour:\"NAVY\", selling_price:450000 }]  }"; // Your JSON data, Sample for testing*/

            ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(jsonData.getBytes());
            JsonDataSource ds = new JsonDataSource(jsonDataStream, "data");

            Map parameters = new HashMap();
            Locale locale = new Locale( "id", "ID" );
            parameters.put( JRParameter.REPORT_LOCALE, locale );

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

            byte[] pdfByte = JasperExportManager.exportReportToPdf(jasperPrint);

            baos = new ByteArrayOutputStream(pdfByte.length);
            baos.write(pdfByte, 0, pdfByte.length);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

        return baos;
    }
}
