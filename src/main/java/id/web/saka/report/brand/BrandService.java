package id.web.saka.report.brand;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand getBrandById(int brandId) {

        return brandRepository.findById(brandId).orElse(null);
    }

    public Brand getBrandByName(String brandName) {
        return brandRepository.findByName(brandName);
    }

    public String getAllBrands() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Brand> brands = brandRepository.findAll();

        return objectMapper.writeValueAsString(brands);
    }
}
