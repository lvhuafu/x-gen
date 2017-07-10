package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.GenTypeModel;
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

    ///////////////////////提供点友好方法

    /**
     * 根据需要生成的模块配置和theme中的生成类型的编号，来获取相应的theme中的生成类型的model
     * @param moduleConf
     * @param needGenTypeId
     * @return
     */
    GenTypeModel getThemeGenType(ModuleConfModel moduleConf,String needGenTypeId);

    /**
     * 根据需要生成的模块配置和theme中的输出类型的编号，来获取相应的输出类型的类定义
     * @param moduleConf
     * @param genOutTypeId
     * @return
     */
    String getThemeGenOutTypeClass(ModuleConfModel moduleConf,String genOutTypeId);
}
