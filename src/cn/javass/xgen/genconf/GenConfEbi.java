package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.Map;

/**
 * Created by HASEE on 2017/6/26.
 */
public interface GenConfEbi {
    /*
    获取x-gen核心框架运行所需的配置数据model
     */
    GenConfModel getGenConf();

    /*
    获取需要生成的所有模块的配置
    return 包含所有需要生成的所有模块的配置树的Map，key-模块的id，value-相应的模块的配置数据的model
     */
    Map<String, ModuleConfModel> getMapModuleConf();
}
