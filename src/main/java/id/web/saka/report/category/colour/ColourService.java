package id.web.saka.report.category.colour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColourService {

    @Autowired
    private ColourRepository colourRepository;
    public Colour getColourId(String colourName) {
        Colour colour = null;

        if(colourName != null) {
            colour = colourRepository.findByColourName(colourName.toUpperCase().trim());
        }

        if(colour == null) {
            colour = colourRepository.findByColourName("NA");
        }

        return colour;
    }

    public Colour getColourName(int colourId) {
        Optional<Colour> colourOptional = colourRepository.findById(colourId);

        return colourOptional.get();
    }
}
