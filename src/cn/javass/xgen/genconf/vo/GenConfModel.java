package cn.javass.xgen.genconf.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * x-gen核心框架配置对应的数据model
 * Created by HASEE on 2017/6/26.
 */
public class GenConfModel {
    /*
    描述注册的多个用户需要生成的模块的model
     */
    private List<NeedGenModel> needGens = new ArrayList<>();
    /*
    描述注册的多个外部主题的model
     */
    private List<ThemeModel> themes = new ArrayList<>();
    /*
    描述通用的常量定义的集合
     */
    private Map<String, String> mapConstants = new HashMap<>();

    public List<NeedGenModel> getNeedGens() {
        return needGens;
    }

    public void setNeedGens(List<NeedGenModel> needGens) {
        this.needGens = needGens;
    }

    public void setMapConstants(Map<String, String> mapConstants) {
        this.mapConstants = mapConstants;
    }

    public Map<String, String> getMapConstants() {
        return mapConstants;
    }

    public List<ThemeModel> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeModel> themes) {
        this.themes = themes;
    }

    public ThemeModel getThemeById(String themeId) {
        for (ThemeModel tm : this.themes) {
            if (tm.getId().equals(themeId)) {
                return tm;
            }
        }
        return new ThemeModel();
    }

    @Override
    public String toString() {
        return "GenConfModel{" +
                "needGens=" + needGens +
                ", themes=" + themes +
                ", mapConstants=" + mapConstants +
                '}';
    }
}
