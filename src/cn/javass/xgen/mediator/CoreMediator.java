package cn.javass.xgen.mediator;

/**
 * Created by HASEE on 2017/7/5.
 */

import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.xmlimpl.GenConfXmlImpl;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.geninvocation.DefaultGenInvocation;
import cn.javass.xgen.genproxy.GenProxyFactory;
import cn.javass.xgen.template.TemplateEbi;
import cn.javass.xgen.template.TemplateFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observer;

/**
 * 核心框架的中介者对象
 */
public class CoreMediator {
    //实现成为单例
    private static CoreMediator mediator = new CoreMediator();

    private CoreMediator() {

    }

    public static CoreMediator getInstance() {
        return mediator;
    }

    //////////////////////////////////////////
    public GenConfImplementor getDefaultGenConfProvider() {
        return new GenConfXmlImpl();
    }

    public Collection<ModuleConfModel> getNeedGenModuleConf(GenConfImplementor providrer) {
        return GenConfFactory.createGenConfEbi(providrer).getMapModuleConf().values();
    }

    public void needProxyGen(String needGenType, ModuleConfModel moduleConf) {
        GenProxyFactory.createGenProxy(needGenType, moduleConf).executeGen();
    }

    public String getNeedGenTypeClass(String needGenType, ModuleConfModel moduleConf) {
        return GenConfFactory.createGenConfEbi().getThemeGenType(moduleConf, needGenType).getGenTypeClass();
    }

    public Object getTemplateContent(ModuleConfModel moduleConf, String genTypeId) {
        //直接传递模板管理的对象
        return TemplateFactory.createTemplateEbi(moduleConf,genTypeId);
    }

    public Object templateReplaceProperties(Object obj) {
        return ((TemplateEbi)obj).replaceProperties();
    }

    public Object templateReplaceMethods(Object obj) {
        return ((TemplateEbi)obj).replaceMethods();
    }
    public void registerObservers(DefaultGenInvocation ctx) {
        //1.获得相应的NeedGenOutType的id
        List<String> needGenOutTypes = ctx.getModuleConf().getMapNeedGenTypes().get(ctx.getNeedGenType());
        //2.根据NeedGenOutType的id获取相应的outType类的定义
        for (String genOutTypeId : needGenOutTypes) {
            String genOutTypeClass = GenConfFactory.createGenConfEbi().getThemeGenOutTypeClass(ctx.getModuleConf(), genOutTypeId);
            //3.用反射创建这些类的实例，这些类就是observers
            try {
                Observer o = (Observer) Class.forName(genOutTypeClass).newInstance();
                ctx.addObserver(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getThemeMbPathFile(ModuleConfModel moduleConf, String genTypeId) {
        return GenConfFactory.createGenConfEbi().getGenConf().getThemeById(moduleConf.getUseTheme()).getMapGenTypes().get(genTypeId).getMapParams().get("mbPathFile");
    }

    public String getThemePath(ModuleConfModel moduleConf) {
        return GenConfFactory.createGenConfEbi().getGenConf().getThemeById(moduleConf.getUseTheme()).getLocation();
    }

    public Map<String,String> getGenTypeParams(ModuleConfModel moduleConf,String genTypeId){
        return GenConfFactory.createGenConfEbi().getThemeGenType(moduleConf,genTypeId).getMapParams();
    }

    public ModuleConfModel getObserverModuleConf(Object obj){
        DefaultGenInvocation invocation = (DefaultGenInvocation)obj;
        return invocation.getModuleConf();
    }

    public String getObserverGenTypeId(Object obj){
        DefaultGenInvocation invocation = (DefaultGenInvocation)obj;
        return invocation.getNeedGenType();
    }
}
