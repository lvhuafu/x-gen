package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.implementors.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.implementors.dynanicparse.ParseContext;
import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.util.readxml.Context;
import cn.javass.xgen.util.readxml.Parser;
import cn.javass.xgen.util.readxml.ReadXmlExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HASEE on 2017/7/4.
 */
public class ModuleGenConfXmlImpl implements ModuleGenConfImplementor {
    //通过parser去获取到配置的数据，然后把这些数据组装成ModuleGenConfImplementor所需要的样子
    @Override
    public ModuleConfModel getBaseModuleConfModel(Map<String, String> param) {
        ModuleConfModel mcm = new ModuleConfModel();
        //设置模块标识
        this.parseModuleId(mcm,this.getContext(param));
        return mcm;
    }

    @Override
    public Map<String, List<String>> getMapNeedGenTypes(Map<String, String> param) {
        return this.parseNeedGenTypes(this.getContext(param));
    }

    @Override
    public Map<String, ExtendConfModel> getMapExtends(GenConfModel gm,Map<String, String> param) {
        Map<String,ExtendConfModel> map = new HashMap<>();

        String[] extendIds = parseExtendIds(this.getContext(param));
        String[] isSingles = parseIsSingles(this.getContext(param));
        String[] value = parseValue(this.getContext(param));
        for (int i = 0; i <extendIds.length ; i++) {
            ExtendConfModel ecm = new ExtendConfModel();
            ecm.setId(extendIds[i]);
            ecm.setSingle(Boolean.parseBoolean(isSingles[i]));
            ecm.setValue(value[i]);
            if(!ecm.isSingle()){
                ecm.setValues(ecm.getValue().split(ExpressionEnum.comma.getExpr()));
            }
            map.put(ecm.getId(),ecm);
        }
        //等待读取完数据，再来进行动态解析
        ParseContext pctx = new ParseContext();
        map = pctx.parse(gm,map);
        return map;
    }
    private String[] parseValue(Context ctx){
        ctx.init();
        ReadXmlExpression re = Parser.parse(
                new ModuleGenConfBuilder().addModuleGenConf()
                        .addSeparator().addExtendConfs().addSeparator()
                        .addExtendConf().addDollar()
                        .build()
        );
        return re.interpret(ctx);
    }
    private String[] parseIsSingles(Context ctx){
        ctx.init();
        ReadXmlExpression re = Parser.parse(
                new ModuleGenConfBuilder().addModuleGenConf()
                        .addSeparator().addExtendConfs().addSeparator()
                        .addExtendConf().addDollar()
                        .addDot().addisSingle().addDollar()
                        .build()
        );
        return re.interpret(ctx);
    }
    private String[] parseExtendIds(Context ctx){
        ctx.init();
        ReadXmlExpression re = Parser.parse(
                new ModuleGenConfBuilder().addModuleGenConf()
                        .addSeparator().addExtendConfs().addSeparator()
                        .addExtendConf().addDollar()
                        .addDot().addId().addDollar()
                        .build()
        );
        return re.interpret(ctx);
    }
    /////////////////////////////////////////////////////////
    private Context getContext(Map<String, String> param) {
        Context c = null;
        try {
            c = Context.getInstance(new GenConfBuilder().addxmlFilePre().addOtherValue(param.get(ExpressionEnum.fileName.getExpr())).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    //////////////////////////////////////////////////
    private void parseModuleId(ModuleConfModel mcm, Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ModuleGenConfBuilder().addModuleGenConf().addDot().addId()
                        .build()
        );
        String ss[] = re.interpret(c);
        mcm.setModuleId(ss[0]);
    }
    ////////////////////////////////////////////////////////
    private Map<String, List<String>> parseNeedGenTypes(Context ctx){
        ctx.init();
        Map<String, List<String>> map = new HashMap<>();
        ReadXmlExpression re = Parser.parse(
                new ModuleGenConfBuilder().addModuleGenConf().addSeparator().addNeedGenTypes().addSeparator()
                        .addNeedGenType().addDollar().addDot().addId().addDollar()
                        .build()
        );
        String[] needGenTypes = re.interpret(ctx);
        for (String s:needGenTypes) {
            map.put(s,parseNeedGenOutTypes(ctx,s));
        }
        return map;
    }
    private List<String> parseNeedGenOutTypes(Context ctx,String needGenId){
        ctx.init();
        List<String> list = new ArrayList<>();
        ReadXmlExpression re = Parser.parse(
                new ModuleGenConfBuilder().addModuleGenConf().addSeparator().addNeedGenTypes().addSeparator()
                        .addNeedGenType().addDollar()
                        .addopenBracket().addId().addequal().addOtherValue(needGenId).addcloseBracket().addSeparator()
                        .addNeedGenOutType().addDollar()
                        .addDot().addId().addDollar()
                        .build()
        );
        String[] ss = re.interpret(ctx);
        for (String s:ss) {
            list.add(s);
        }
        return list;
    }
}
