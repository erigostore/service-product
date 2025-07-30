package id.web.saka.report.category.colour;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColourService {

    @Autowired
    private ColourRepository colourRepository;

    public Colour getColourName(String colourName) {
        Colour colour = null;

        if(colourName != null) {
            colour = colourRepository.findByColourName(colourName.toUpperCase().trim());
        }

        if(colour == null) {
            colour = colourRepository.findByColourName("NA");
        }

        return colour;
    }

    public Colour getColourCode(int colourId) {
        Optional<Colour> colourOptional = colourRepository.findById(colourId);

        return colourOptional.get();
    }

    public String getAllColours() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Colour> colourList = colourRepository.findAll();

        return objectMapper.writeValueAsString(colourList);
    }
}
