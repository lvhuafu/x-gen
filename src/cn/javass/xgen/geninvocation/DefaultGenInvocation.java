package cn.javass.xgen.geninvocation;

import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.state.DefaultBeginState;
import cn.javass.xgen.geninvocation.state.State;

/**
 * Created by HASEE on 2017/7/6.
 */
public class DefaultGenInvocation extends java.util.Observable implements GenInvocation {
    /**
     * 持有一个状态对象
     */
    private State state = null;

    /**
     * 具体要生成的类型标识
     */
    private String needGenType = "";
    /**
     * 当前被generate模块的配置数据
     */
    private ModuleConfModel moduleConf;

    public Object getTempContent() {
        return tempContent;
    }

    public void setTempContent(Object tempContent) {
        this.tempContent = tempContent;
    }

    /**
     * 存放多个解析过程当中的临时内容
     */
    private Object tempContent = null;

    /**
     * 通知内容已经生成好了，可以触发联动了
     * @param obj
     */
    public void setContentOver(Object obj){
        this.setChanged();
        this.notifyObservers(obj);
    }

    public ModuleConfModel getModuleConf() {
        return moduleConf;
    }

    public void setModuleConf(ModuleConfModel moduleConf) {
        this.moduleConf = moduleConf;
    }

    public DefaultGenInvocation(String needGenType, ModuleConfModel moduleConf){
        this.moduleConf = moduleConf;
        this.needGenType = needGenType;
    }

    /**
     * 执行工作，在每个state完成自己的工作之后
     */
    public void dowork(){
        this.state.doWork(this);
    }
    public void setState(State state){
        this.state = state;
    }
    @Override
    public void executeGen() {
        //设置generate调用流程开始需要执行的状态
        State state = new DefaultBeginState();
        //让状态执行工作
        state.doWork(this);
        //第一大步：按照一定的流程调用相应的生成实现，获取生成的内容

        //第二大步：把生成的内容输出

    }
    public String getNeedGenType() {
        return needGenType;
    }

    public void setNeedGenType(String needGenType) {
        this.needGenType = needGenType;
    }
}
