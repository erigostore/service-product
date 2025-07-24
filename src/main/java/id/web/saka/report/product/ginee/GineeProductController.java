package id.web.saka.report.product.ginee;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*@RequestMapping("product/ginee")*/
public class GineeProductController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(GineeProductController.class);

    @Autowired
    private GineeProductService gineeProductService;

    @RequestMapping(value = "/getListGineeMasterProduct/{brand}")
    public ResponseEntity getListGineeMasterProduct(@PathVariable String brand) {
        LOG.info("getListGineeMasterProduct|"+brand+"|START");

        gineeProductService.getListGineeMasterProduct(brand);

        LOG.info("getListGineeMasterProduct|" + "|END");
        return ResponseEntity.ok("{ \"status\": true }");
    }

}
