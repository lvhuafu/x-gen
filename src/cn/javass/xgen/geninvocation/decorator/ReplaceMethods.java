package cn.javass.xgen.geninvocation.decorator;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

/**
 * Created by HASEE on 2017/7/10.
 */
public class ReplaceMethods extends GenDecorator {
    public ReplaceMethods(GenComponent component) {
        super(component);
    }
    @Override
    public Object operation(ModuleConfModel moduleConf, String genTypeId, Object obj) {
        //需要获取上一步处理完成的内容
        obj = this.component.operation(moduleConf,genTypeId,obj);

        //需要对模板内容进行分解
        //1.找到需要替换变量
        //2.找到变量对应的属性数据
        //3.进行替换

        //把这些功能实现到模板管理里面
        return CoreMediator.getInstance().templateReplaceMethods(obj);
    }
}
