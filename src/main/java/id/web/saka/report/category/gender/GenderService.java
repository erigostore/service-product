package id.web.saka.report.category.gender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public String getAllGenders() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(genderRepository.findAll());
    }

    public Gender getGenderById(int genderId) {
        return  genderRepository.getReferenceById(genderId);
    }

    public Gender getGenderByCode(String genderCode) {

        return genderRepository.findByCode(genderCode);
    }
}
