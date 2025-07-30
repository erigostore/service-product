package id.web.saka.report.category.size;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product/size")
public class SizeController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(SizeController.class);

    @Autowired
    private SizeService sizeService;

    @CrossOrigin
    @RequestMapping(value = "/getAllSizes")
    public ResponseEntity getAllSizes() throws JsonProcessingException {
        LOG.info("getAllSizes" + "|START");

        String jsonResponse = sizeService.getAllSizes();

        return ResponseEntity.ok( jsonResponse );
    }

}
