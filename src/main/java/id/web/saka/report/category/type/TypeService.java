package id.web.saka.report.category.type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.web.saka.report.brand.Brand;
import id.web.saka.report.brand.BrandService;
import id.web.saka.report.category.colour.ColourService;
import id.web.saka.report.category.gender.GenderService;
import id.web.saka.report.category.theme.ThemeService;
import id.web.saka.report.category.type.DTO.TypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ThemeService themeService;


    public String getAllTypes(int brandId, int themeId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Type> typeList =  typeRepository.findAllByThemeIdIs(themeId); //NOTE Temporary no filter by brand.

        List<TypeDTO> typeDTOList = getTypeDTOListFromTypeList(typeList);

        return objectMapper.writeValueAsString(typeDTOList);
    }


    private List<TypeDTO> getTypeDTOListFromTypeList(List<Type> typeList) {
        TypeDTO typeDTO; Brand brand;
        List<TypeDTO> typeDTOList = new ArrayList<>();


        for(Type type : typeList) {
            typeDTO = new TypeDTO();

            brand = brandService.getBrandById(type.getBrandId());

            typeDTO.setId(type.getId());
            typeDTO.setBrand(brand.getName());
            typeDTO.setBrandCode(brand.getCode());
            typeDTO.setTheme(themeService.getThemeId(type.getThemeId()).getName());
            typeDTO.setTypeCode(type.getCode());
            typeDTO.setTypeLevel1(type.getTypeLevel1());
            typeDTO.setTypeLevel2(type.getTypeLevel2());
            typeDTO.setTypeLevel3(type.getTypeLevel3());

            typeDTOList.add(typeDTO);
        }

        return typeDTOList;
    }

    public Type getTypeById(int typeId) {
        return typeRepository.getReferenceById(typeId);
    }

    public void update(Type type) {

        typeRepository.save(type);

    }

    public Type getTypeByName(String typeLevel1) {

        Type typeEntity = typeRepository.findFirstByTypeLevel1Is(typeLevel1);

        if(typeEntity == null) {
            typeEntity = new Type();
            typeEntity.setId(0);
        }

        return typeEntity;
    }
}
