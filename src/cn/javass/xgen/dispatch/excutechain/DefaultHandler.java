package cn.javass.xgen.dispatch.excutechain;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;

/**
 * Created by HASEE on 2017/7/6.
 */
public class DefaultHandler extends GenHandler {
    /**
     * 命令的标识
     */
    private String needGenType = "";

    public DefaultHandler(String needGenType){
        this.needGenType = needGenType;
    }
    public void handlerRequest(ModuleConfModel mcm) {
        //1.本·职责对象要实现的功能：继续调用真正实现生成功能的模块
        CoreMediator.getInstance().needProxyGen(needGenType,mcm);
        //2.交给父类，继续职责链的后续处理
        super.handlerRequest(mcm);
    }
}
