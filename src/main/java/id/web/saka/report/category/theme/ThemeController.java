package id.web.saka.report.category.theme;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import id.web.saka.report.category.colour.ColourController;
import id.web.saka.report.category.gender.GenderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product/theme")
public class ThemeController {

    private static Logger LOG = (Logger) LoggerFactory.getLogger(ColourController.class);

    @Autowired
    private ThemeService themeService;

    @CrossOrigin
    @RequestMapping(value = "/getAllThemes")
    public ResponseEntity getAllThemes() throws JsonProcessingException {
        LOG.info("getAllThemes" + "|START");

        String jsonResponse = themeService.getAllThemes();

        return ResponseEntity.ok( jsonResponse );
    }

}
