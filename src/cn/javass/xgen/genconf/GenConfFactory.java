package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.implementors.GenConfImplementor;

/**
 * 简单工厂
 * Created by HASEE on 2017/6/27.
 */
public class GenConfFactory {
    private GenConfFactory() {
    }

    /**
     * 外部传入provider，生成GenConfEbi具体实例
     * 还需要实现一个默认的provider，用户不提供provider的时候使用。
     *
     * @param provider
     * @return
     */
    public static GenConfEbi createGenConfEbi(GenConfImplementor provider) {
        return GenConfEbo.getInstance(provider);
    }
}
