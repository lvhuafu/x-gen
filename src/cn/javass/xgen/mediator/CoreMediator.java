package cn.javass.xgen.mediator;

/**
 * Created by HASEE on 2017/7/5.
 */

import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.xmlimpl.GenConfXmlImpl;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.Collection;

/**
 * 核心框架的中介者对象
 */
public class CoreMediator {
    //实现成为单例
    private static CoreMediator mediator = new CoreMediator();
    private CoreMediator(){

    }
    public static CoreMediator getInstance(){
        return mediator;
    }
    //////////////////////////////////////////
    public GenConfImplementor getDefaultGenConfProvider(){
        return new GenConfXmlImpl();
    }
    public Collection<ModuleConfModel> getNeedGenModuleConf(GenConfImplementor providrer){
        return GenConfFactory.createGenConfEbi(providrer).getMapModuleConf().values();
    }
}
