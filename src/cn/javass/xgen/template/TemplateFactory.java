package cn.javass.xgen.template;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

/**
 * Created by HASEE on 2017/7/10.
 */
public class TemplateFactory {
    private TemplateFactory() {
    }

    public static TemplateEbi createTemplateEbi(ModuleConfModel moduleConf,String genTypeId) {
        return new DefaultTemplateEbo(moduleConf,genTypeId);
    }
}
