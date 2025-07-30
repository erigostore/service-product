package id.web.saka.report.brand;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product/brand")
public class BrandController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @CrossOrigin
    @RequestMapping(value = "/getAllBrands")
    public ResponseEntity getAllBrands() throws JsonProcessingException {
        LOG.info("getAllBrands" + "|START");

        String jsonResponse = brandService.getAllBrands();

        LOG.info("getAllBrands|" + jsonResponse + "|END");
        return ResponseEntity.ok( jsonResponse );
    }

}
