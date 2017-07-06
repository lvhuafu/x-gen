package cn.javass.xgen.genconf.implementors;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.List;
import java.util.Map;

/**
 * Created by HASEE on 2017/6/28.
 */
public interface ModuleGenConfImplementor {

    //根据核心框架里面注册的需要生成模块的配置参数来获取相应的需要生成的模块的配置数据model，数据只有基本部分。
    ModuleConfModel getBaseModuleConfModel(Map<String, String> param);

    //根据核心框架里面注册的需要生成模块的配置参数，来获取需要生成模块中配置的需要生成的功能类型。
    Map<String, List<String>> getMapNeedGenTypes(Map<String, String> param);

    Map<String, ExtendConfModel> getMapExtends(GenConfModel gm, Map<String, String> param);
}
