package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HASEE on 2017/6/26.
 */
public class NeedGenModel {
    private String id;
    private String provider;
    private String theme;

    private Map<String, String> mapParas = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Map<String, String> getMapParas() {
        return mapParas;
    }

    public void setMapParas(Map<String, String> mapParas) {
        this.mapParas = mapParas;
    }

    @Override
    public String toString() {
        return "NeedGenModel{" +
                "id='" + id + '\'' +
                ", provider='" + provider + '\'' +
                ", theme='" + theme + '\'' +
                ", mapParas=" + mapParas +
                '}';
    }
}
