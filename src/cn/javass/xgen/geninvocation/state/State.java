package cn.javass.xgen.geninvocation.state;

import cn.javass.xgen.geninvocation.DefaultGenInvocation;

/**
 * Created by HASEE on 2017/7/6.
 */

/**
 * 公共的状态的接口
 */
public interface State {
    /**
     * 执行状态所对应的功能处理
     * @param ctx 上下文实例对象
     */
    void doWork(DefaultGenInvocation ctx);
}
