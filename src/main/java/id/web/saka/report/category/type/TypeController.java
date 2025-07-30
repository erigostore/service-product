package id.web.saka.report.category.type;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import id.web.saka.report.category.CategoryController;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product/type")
public class TypeController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private TypeService typeService;


    @CrossOrigin
    @RequestMapping(value = "/getAllTypes/{brandId}/{themeId}")
    public ResponseEntity getAllTypes(@PathVariable int brandId, @PathVariable int themeId ) throws JsonProcessingException {
        LOG.info("getAllTypes|brandId:" + brandId + "|themeId:" + themeId + "|START");

        String jsonResponse = typeService.getAllTypes(brandId, themeId);

        return ResponseEntity.ok( jsonResponse );
    }

}
