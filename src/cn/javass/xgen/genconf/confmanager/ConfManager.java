package cn.javass.xgen.genconf.confmanager;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.genconf.vo.NeedGenModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式懒汉式
 * Created by HASEE on 2017/6/27.
 */
//负责真正的获取配置数据
//然后把获取的配置数据设置到属性上，缓存下来
public class ConfManager {
    private static ConfManager manager = null;

    public static ConfManager getInstance(GenConfImplementor provider) {
        if (manager == null) {
            manager = new ConfManager(provider);
        }
        return manager;
    }

    private ConfManager(GenConfImplementor provider) {
        readConf(provider);
    }

    //定义需要缓存的数据
    private GenConfModel genCof = new GenConfModel();
    private Map<String, ModuleConfModel> mapModuleCof = new HashMap<>();

    private void readConf(GenConfImplementor provider) {
        //这里去真正的获取配置数据
        readGenConf(provider);
        //然后把获取的配置数据设置到属性上，缓存下来
        for (NeedGenModel ngm : genCof.getNeedGens()) {
            readOneModuleGenConf(ngm);
        }
    }

    private void readOneModuleGenConf(NeedGenModel ngm) {
        ModuleConfModel mcm = new ModuleConfModel();
        String providerClassName = this.genCof.getThemeById(ngm.getTheme()).getMapProviders().get(ngm.getProvider());
        ModuleGenConfImplementor userGenConfImpl = null;
        try {
            /**
             * 通过反射获取实例。
             */
            userGenConfImpl = (ModuleGenConfImplementor) Class.forName(providerClassName).newInstance();
        } catch (Exception err) {
            err.printStackTrace();
        }
        mcm = userGenConfImpl.getBaseModuleConfModel(ngm.getMapParas());
        mcm.setUseTheme(ngm.getTheme());
        //解析Extend应该放到其他解析的后面，因为如果有动态解析的话，需要前面的值。
        mcm.setMapExtends(userGenConfImpl.getMapExtends(genCof,ngm.getMapParas()));
        mcm.setMapNeedGenTepys(userGenConfImpl.getMapNeedGenTypes(ngm.getMapParas()));
        /**
         * 设置到缓存里面
         */
        this.mapModuleCof.put(mcm.getModuleId(), mcm);
    }

    private void readGenConf(GenConfImplementor provider) {
        genCof.setNeedGens(provider.getNeedGens());
        genCof.setMapConstants(provider.getMapContents());
        genCof.setThemes(provider.getTheme());
    }

    public GenConfModel getGenCof() {
        return genCof;
    }

    public Map<String, ModuleConfModel> getMapModuleCof() {
        return mapModuleCof;
    }
}
