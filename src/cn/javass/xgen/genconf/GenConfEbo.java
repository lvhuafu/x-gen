package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.confmanager.ConfManager;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.Map;

/**
 * 单例模式 饿汉式
 * Created by HASEE on 2017/6/26.
 */
//负责完成配置管理模块的业务功能
public class GenConfEbo implements GenConfEbi {
    private static GenConfEbo ebo = null;

    private GenConfEbo(GenConfImplementor provider) {
        this.provider = provider;
    }

    public static GenConfEbi getInstance(GenConfImplementor provider) {
        if (ebo == null) {
            ebo = new GenConfEbo(provider);
        }
        return ebo;
    }

    /**
     * 持有获取核心框架配置数据的具体实现接口对象。
     */
    private GenConfImplementor provider = null;

    @Override
    public GenConfModel getGenConf() {
        //获取相应的配置数据
        return ConfManager.getInstance(provider).getGenCof();
    }

    @Override
    public Map<String, ModuleConfModel> getMapModuleConf() {
        //获取相应的配置数据
        return ConfManager.getInstance(provider).getMapModuleCof();
    }
}
