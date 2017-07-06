package cn.javass.xgen.dispatch.command;

import cn.javass.xgen.dispatch.excutechain.DefaultHandler;
import cn.javass.xgen.dispatch.excutechain.GenHandler;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HASEE on 2017/7/6.
 */
public class DefaultCommand implements GenCommand {
    /**
     * 具体要生成的模快的配置model
     */
    private ModuleConfModel moduleConf;

    public DefaultCommand(ModuleConfModel moduleConf){
        this.moduleConf = moduleConf;
    }

    @Override
    public void excute() {
        //应该调用后续的模块去实现真正的功能

        //根据配置来组装动态职责链，也就是按照生成配置数据来组合需要生成的功能
        List<String> listNeedGenTypes = new ArrayList<>();
        //1.先把配置中配置的需要生成的功能的类型获取到，并放入到list里面
        for (String s : moduleConf.getMapNeedGenTepys().keySet()) {
            listNeedGenTypes.add(s);
        }
        //2.把list转换到map中，key就是顺序值，value就是需要生成的类型的处理器
        Map<Integer,GenHandler> mapHandler = new HashMap<>();
        for (int i = 0; i <listNeedGenTypes.size() ; i++) {
            mapHandler.put(i+1,new DefaultHandler(listNeedGenTypes.get(i)));
        }
    }
}