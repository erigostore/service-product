package id.web.saka.report.category.theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public Theme getThemeByThemeIdNameAndCategoryLevel1(String themeName, String productName, String categoryLevel1Name) {
        Theme theme = null;

        if(themeName != null) {
            theme = themeRepository.findByThemeName(themeName.toUpperCase().trim());
        }

        if(theme == null){
            List<Theme> themeList = themeRepository.findAll();

            for(Theme themeSelect : themeList) {
                if(productName.toUpperCase().replaceAll("\\s","").trim().contains(themeSelect.getName().toUpperCase().replaceAll("\\s","").trim())) {
                    return themeSelect;
                }
            }
        }

        if(theme == null) {
            theme = themeRepository.findByThemeName(categoryLevel1Name.toUpperCase().trim());
        }

        //TODO : is still null should be create new Theme

        return theme;
    }

    public Theme getThemeId(Integer themeId) {
        Optional<Theme> optionalTheme = themeRepository.findById(themeId);

        return optionalTheme.get();
    }

    public String changeThemeIdToString(Integer themeId) {
        String themeIdString = themeId.toString();

        if(themeIdString.length() == 1) {
            themeIdString = "0" + themeIdString;
        }

        return themeIdString;
    }
}
