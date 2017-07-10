package cn.javass.xgen.geninvocation;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.decorator.*;

/**
 * Created by HASEE on 2017/7/7.
 */
public abstract class BaseGenAction {
    /**
     * 模板方法，执行每个功能的具体generate过程
     * @param moduleConf
     * @return
     */
    public Object generate(ModuleConfModel moduleConf) {
        //1.得到用来封装generate内容的对象
        Object obj = null;
        //2.执行具体generate之前需要执行的功能
        Object before =  this.beforeAction(moduleConf);
        if(before!=null){
            obj = this.executeDecorators(moduleConf,obj,(GenComponent)before);
        }
        beforeAction(moduleConf);
        //3.真正执行action的功能
        obj = execute(moduleConf,obj);
        //4.执行具体generate之后要执行的功能
        afterAction(moduleConf);
        Object after =  this.beforeAction(moduleConf);
        if(after!=null){
            obj = this.executeDecorators(moduleConf,obj,(GenComponent)after);
        }

        return obj;
    }
    ////////////////////////////////////

    /**
     * 原语操作，一个工厂方法，初始化封装generate生成内容的对象
     * @return
     */
    public abstract Object initObject();

    /**
     * 钩子操作，在执行action之前要实现的功能
     * @param moduleConf
     */
    public GenComponent beforeAction(ModuleConfModel moduleConf){
        return null;
    }

    /**
     * 原语操作，执行action的generate方法
     * @param moduleConf
     * @param obj
     * @return
     */
    public abstract Object execute(ModuleConfModel moduleConf,Object obj);
    /**
     * 钩子操作，在执行action之后要实现的功能
     * @param moduleConf
     */
    public GenComponent afterAction(ModuleConfModel moduleConf){
        return null;
    }

    ///////////////////////////////////////////

    /**
     * 执行action的装饰器对象
     *
     * @param moduleConf
     * @param obj
     * @param gc
     */
    public abstract Object executeDecorators(ModuleConfModel moduleConf, Object obj, GenComponent gc);

    /**
     * 提供给子类的公共方法，缺省执行action操作前的装饰器对象
     * @param moduleConf
     * @return
     */
    public GenComponent defaultBeforeAction(ModuleConfModel moduleConf){
        GenComponent gc = new DefaultComponent();
        //1.读取相应的模板文件
        GenComponent d1 = new ReadTemplateContent(gc);
        //2.
        GenComponent d2 = new ReplaceProperty(d1);

        GenComponent d3 = new ReplaceMethods(d2);
        return d3;
    }
}
