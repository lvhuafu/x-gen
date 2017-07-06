package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.ThemeConfImplementor;
import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;
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
public class GenConfXmlImpl implements GenConfImplementor {
    //1.通过builder来获取用于取值的字符串
    //2.通过parser去获取到配置的数据
    //3.然后把这些数据组装成GenConfImplementor所需要的样子
    @Override
    public List<NeedGenModel> getNeedGens() {
        return readNeedGends();
    }

    @Override
    public List<ThemeModel> getTheme() {
        return readThemes();
    }

    @Override
    public Map<String, String> getMapContents() {
        return readMapConstants();
    }

    private Context getContext() {
        Context c = null;
        try {
            c = Context.getInstance(new GenConfBuilder().addxmlFilePre().addGenConf().addDot().addxml().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    /////////////////////////////////////////////////
    private Map<String,String> readMapConstants(){
        Map<String,String> map = new HashMap<>();
        Context c = getContext();
        String[] ids = parseConstantids(c);
        String[] values = parseConstantvalues(c);
        for (int i = 0; i < ids.length; i++) {
            map.put(ids[i],values[i]);
        }
        return map;
    }
    private String[] parseConstantids(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
                .addConstant().addDollar()
                .addDot().addId().addDollar()
                .build());
        return re.interpret(c);
    }
    private String[] parseConstantvalues(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
                .addConstant().addDollar()
                .build());
        return re.interpret(c);
    }
    /////////////////////////////////////////////////
    private List<ThemeModel> readThemes(){
        List<ThemeModel> retList = new ArrayList<>();
        Context c = getContext();
        String[] ids = parseThemeids(c);
        String[] locations = parseThemelocations(c);
        for (int i = 0; i <ids.length ; i++) {
            ThemeModel tm = new ThemeModel();
            ThemeConfImplementor themeImpl = new ThemeXmlImpl();
            Map<String,String> params = new HashMap<>();
            params.put(ExpressionEnum.Location.getExpr(),locations[i]);
            tm.setId(ids[i]);
            tm.setLocation(locations[i]);
            tm.setMapGenOutTypes(themeImpl.getMapGenOutTypes(ids[i],params));
            tm.setMapGenTypes(themeImpl.getMapGenTypes(ids[i],params));
            tm.setMapProviders(themeImpl.getMapProviders(ids[i],params));

            retList.add(tm);
        }
        return retList;
    }
    private String[] parseThemelocations(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
                .addTheme().addDollar()
                .build());
        return re.interpret(c);
    }
    private String[] parseThemeids(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
                .addTheme().addDollar()
                .addDot().addId().addDollar()
                .build());
        return re.interpret(c);
    }
///////////////////////////////////////////////////////////////////////////
    private List<NeedGenModel> readNeedGends() {
        List<NeedGenModel> retList = new ArrayList<>();
        Context c = getContext();
        //获取数据
        String[] needGenIds = parseNeedGenIds(c);
        String[] needGenProviders = parseNeedGenProviders(c);
        String[] needGenThemes = parseNeedThemes(c);
        //开始组装
        for (int i = 0; i <needGenIds.length ; i++) {
            NeedGenModel ngm  = new NeedGenModel();
            ngm.setId(needGenIds[i]);
            ngm.setProvider(needGenProviders[i]);
            ngm.setTheme(needGenThemes[i]);
            //去获取参数的值
            String[] paramIds = parseparamIds(c,ngm.getId());
            String[] paramValues = parseparamValues(c,ngm.getId());
            //组装param
            Map<String,String> mapParams = new HashMap<>();
            for (int j = 0; j <paramIds.length ; j++) {
                mapParams.put(paramIds[j],paramValues[j]);
            }
            ngm.setMapParas(mapParams);
            retList.add(ngm);
        }
        return retList;
    }
    private String[] parseparamValues(Context c,String needGenid){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addopenBracket().addId().addequal().addOtherValue(needGenid).addcloseBracket().addSeparator()
                .addParams().addDollar().addSeparator().addParam().addDollar()
                .build());
        return re.interpret(c);
    }
    private String[] parseparamIds(Context c,String needGenid){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addopenBracket().addId().addequal().addOtherValue(needGenid).addcloseBracket().addSeparator()
                .addParams().addDollar().addSeparator().addParam().addDollar()
                .addDot().addId().addDollar().build());
        return re.interpret(c);
    }
    private String[] parseNeedThemes(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addDot().addthemeid().addDollar().build());
        return re.interpret(c);
    }
    private String[] parseNeedGenIds(Context c){
        c.init();
        //genconf/needgens/needgen$.id$
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
        .addNeedGen().addDollar().addDot().addId().build());
        return re.interpret(c);
    }
    private String[] parseNeedGenProviders(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
                .addNeedGen().addDollar().addDot().addprovider().addDollar().build());
        return re.interpret(c);
    }
}
