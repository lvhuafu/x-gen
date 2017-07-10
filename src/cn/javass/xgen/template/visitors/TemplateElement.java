package cn.javass.xgen.template.visitors;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

/**
 * Created by HASEE on 2017/7/10.
 */
public class TemplateElement {
    private ModuleConfModel moduleConf;

    public TemplateElement(ModuleConfModel moduleConf){
        this.moduleConf = moduleConf;
    }

    /**
     * 接受访问的方法，也就是预留的调用通道
     * @param v
     * @return
     */
    public Object accept(Visitor v){
        return v.visitTemplateElement(this);
    }

    public ModuleConfModel getModuleConf() {
        return moduleConf;
    }
}
