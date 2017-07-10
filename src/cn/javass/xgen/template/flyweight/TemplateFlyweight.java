package cn.javass.xgen.template.flyweight;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

/**
 * Created by HASEE on 2017/7/10.
 */
public interface TemplateFlyweight {
    /**
     * 真正的根据配置和默认的语法来替换模板的属性值
     * @param moduleConf
     * @param content 作为这次处理的起点内容
     * @return 经过处理后的模板内容
     */
    Object replaceProperties(ModuleConfModel moduleConf,Object content);

    /**
     * 根据配置和默认的语法来运行方法，并替换模板中的相应位置的值
     * @param moduleConf
     * @param content 作为这次处理的起点内容
     * @return 经过处理后的模板内容
     */
    Object replaceMethods(ModuleConfModel moduleConf,Object content);

    /**
     * 获取原始的模板文件的内容
     * @return
     */
    Object getRawContent();
}
