package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

/**
 * Created by HASEE on 2017/7/7.
 */
public class ReadTemplateContent extends GenDecorator {

    public ReadTemplateContent(GenComponent component) {
        super(component);
    }
    @Override
    public Object operation(ModuleConfModel moduleConf, String genTypeId, Object obj) {
        //通常会作为装饰器的第一个，也就是内核。它的里面不再有装饰器
        //从模板管理模块里面获取具体的模板内容
        //然后返回这个内容
        return CoreMediator.getInstance().getTemplateContent(moduleConf,genTypeId);
    }

}
