package id.web.saka.report.category.gender;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import id.web.saka.report.category.colour.ColourController;
import id.web.saka.report.category.colour.ColourService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product/gender")
public class GenderController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(ColourController.class);

    @Autowired
    private GenderService genderService;

    @CrossOrigin
    @RequestMapping(value = "/getAllGenders")
    public ResponseEntity getAllGenders() throws JsonProcessingException {
        LOG.info("getAllGenders" + "|START");

        String jsonResponse = genderService.getAllGenders();

        return ResponseEntity.ok( jsonResponse );
    }

}
