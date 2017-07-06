package cn.javass.xgen.dispatch;

import cn.javass.xgen.dispatch.command.CndInvoker;
import cn.javass.xgen.dispatch.command.DefaultCommand;
import cn.javass.xgen.dispatch.command.GenCommand;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.mediator.CoreMediator;
/**
 * Created by HASEE on 2017/7/5.
 */
public class GenFacade {
    //单例
    //工具类的实现
    /**
     * 防止客户端无谓的创造实例
     */
    private GenFacade(){

    }
    /**
     * 提供给外部使用的方法，客户端通过这个方法来请求生成器运行，
     * 生成所需要的配置数据按照默认的配置来
     */
    public static void generate() {
        generate(CoreMediator.getInstance().getDefaultGenConfProvider());
    }
    /**
     * 提供给外部使用的方法，客户端通过这个方法来请求生成器运行，
     * 生成所需要的配置数据按照 从 传入的获取配置数据的实现中获取。
     * @param providrer 获取配置数据的具体实现
     */
    public static void generate(GenConfImplementor providrer) {
        //循环生成在核心框架里面配置的需要生成的模块
        for (ModuleConfModel mcm :CoreMediator.getInstance().getNeedGenModuleConf(providrer)) {
            //具体的去生成每个模块
            genOneModule(mcm);
        }
    }

    /**
     * 具体的去生成每个模块
     * @param mcm
     */
    private static void genOneModule(ModuleConfModel mcm){
        //发出一个命令，让x-gen按照配置去生成相应的内容
        //1.创建命令对象
        GenCommand cmd = new DefaultCommand(mcm);
        //2.创建incoker
        CndInvoker invoker = new CndInvoker();
        invoker.setCmd(cmd);
        //3.通过invoker执行命令
        invoker.doCommand();

    }
}
