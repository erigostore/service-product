package id.web.saka.report.category.size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    public String getAllSizes() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(
            sizeRepository.findAll()
        );

    }

    public List<String> getSizes(int sizeId) {
        List<String> sizeList = new ArrayList<>();
        Size size = sizeRepository.getReferenceById(sizeId);

        if(size.getSize1()!= null) { sizeList.add(size.getSize1()); }
        if(size.getSize2()!= null) { sizeList.add(size.getSize2()); }
        if(size.getSize3()!= null) { sizeList.add(size.getSize3()); }
        if(size.getSize4()!= null) { sizeList.add(size.getSize4()); }
        if(size.getSize5()!= null) { sizeList.add(size.getSize5()); }
        if(size.getSize6()!= null) { sizeList.add(size.getSize6()); }
        if(size.getSize7()!= null) { sizeList.add(size.getSize7()); }
        if(size.getSize8()!= null) { sizeList.add(size.getSize8()); }
        if(size.getSize9()!= null) { sizeList.add(size.getSize9()); }
        if(size.getSize10()!= null) { sizeList.add(size.getSize10()); }
        if(size.getSize11()!= null) { sizeList.add(size.getSize11()); }
        if(size.getSize12()!= null) { sizeList.add(size.getSize12()); }
        if(size.getSize13()!= null) { sizeList.add(size.getSize13()); }
        if(size.getSize14()!= null) { sizeList.add(size.getSize14()); }
        if(size.getSize15()!= null) { sizeList.add(size.getSize15()); }
        if(size.getSize16()!= null) { sizeList.add(size.getSize16()); }
        if(size.getSize17()!= null) { sizeList.add(size.getSize17()); }
        if(size.getSize18()!= null) { sizeList.add(size.getSize18()); }
        if(size.getSize19()!= null) { sizeList.add(size.getSize19()); }
        if(size.getSize20()!= null) { sizeList.add(size.getSize20()); }

        return sizeList;
    }
}
