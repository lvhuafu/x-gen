package cn.javass.xgen.geninvocation;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

/**
 * Created by HASEE on 2017/7/6.
 */
public class GenInvocationFactory {
    private GenInvocationFactory(){

    }

    public static GenInvocation createGenInvocation(String needGenType, ModuleConfModel moduleConf){
        return new DefaultGenInvocation(needGenType,moduleConf);
    }
}
