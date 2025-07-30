package id.web.saka.report.category.colour;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product/colour")
public class ColourController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(ColourController.class);

    @Autowired
    private ColourService colourService;

    @CrossOrigin
    @RequestMapping(value = "/getAllColours")
    public ResponseEntity getAllColours() throws JsonProcessingException {
        LOG.info("getAllColours" + "|START");

        String jsonResponse = colourService.getAllColours();

        return ResponseEntity.ok( jsonResponse );
    }

}
