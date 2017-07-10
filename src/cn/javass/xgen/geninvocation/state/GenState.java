package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.BaseGenAction;
import cn.javass.xgen.geninvocation.DefaultGenInvocation;
import cn.javass.xgen.mediator.CoreMediator;

/**
 * Created by HASEE on 2017/7/6.
 */
public class GenState implements State {
    @Override
    public void doWork(DefaultGenInvocation ctx) {
        //首先实现业务功能
        //1.获取每个theme对应的Action
        String className = CoreMediator.getInstance().getNeedGenTypeClass(ctx.getNeedGenType(), ctx.getModuleConf());
        //2.调用Action来生成内容
        Object obj = null;
        try {
            obj = ((BaseGenAction) Class.forName(className).newInstance()).generate(ctx.getModuleConf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置临时的内容
        ctx.setTempContent(obj);

        //设置下一个state
        ctx.setState(new OutState());
        ctx.dowork();
    }
}
