package cn.javass.xgen.genconf.implementors;

import cn.javass.xgen.genconf.vo.GenTypeModel;

import java.util.Map;

/**
 * Created by HASEE on 2017/6/28.
 */

public interface ThemeConfImplementor {
    /**
     * 根据theme id 和在很想框架里面注册theme时配置的相应的参数，来获取theme中定义的能生成的功能类型。
     *
     * @param themeId
     * @param param
     * @return
     */
    Map<String, GenTypeModel> getMapGenTypes(String themeId, Map<String, String> param);

    /**
     * @param themeId
     * @param param
     * @return
     */

    Map<String, String> getMapGenOutTypes(String themeId, Map<String, String> param);

    /**
     * @param themeId
     * @param param
     * @return
     */
    Map<String, String> getMapProviders(String themeId, Map<String, String> param);

}
