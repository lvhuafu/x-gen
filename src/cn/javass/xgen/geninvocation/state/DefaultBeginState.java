package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;

/**
 * Created by HASEE on 2017/7/6.
 */
public class DefaultBeginState implements State {
    @Override
    public void doWork(DefaultGenInvocation ctx) {

        //这里不去实现真正的功能，用来设置第一个state
        ctx.setState(new GenState());
        ctx.dowork();
    }
}
