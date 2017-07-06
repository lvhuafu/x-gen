package cn.javass.xgen.genconf.implementors;

import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;

import java.util.List;
import java.util.Map;

/**
 * Created by HASEE on 2017/6/28.
 */
//获取核心框架配置数据的接口。
public interface GenConfImplementor {
    //获取核心框架配置中注册的需要生成的模块的配置数据
    List<NeedGenModel> getNeedGens();

    //获取核心框架配置中注册的外部主题的配置数据
    List<ThemeModel> getTheme();

    //获取核心框架配置中注册的定义的公共常量。
    Map<String, String> getMapContents();
}
