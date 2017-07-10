package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.implementors.ThemeConfImplementor;
import cn.javass.xgen.genconf.vo.GenTypeModel;
import cn.javass.xgen.util.readxml.Context;
import cn.javass.xgen.util.readxml.Parser;
import cn.javass.xgen.util.readxml.ReadXmlExpression;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HASEE on 2017/7/4.
 */
public class ThemeXmlImpl implements ThemeConfImplementor {
    //通过parser去获取到配置的数据，然后把这些数据组装成ThemeConfImplementor所需要的样子
    @Override
    public Map<String, GenTypeModel> getMapGenTypes(String themeId, Map<String, String> param) {
        Map<String, GenTypeModel> map = new HashMap<>();
        String[] genTypeIds = parseGenTypeIds(this.getContext(param));
        String[] genTypeValues = parseGenValues(this.getContext(param));
        for (int i = 0; i <genTypeIds.length ; i++) {
            GenTypeModel gtm = new GenTypeModel();
            gtm.setId(genTypeIds[i]);
            gtm.setGenTypeClass(genTypeValues[i]);
            String[] paramIds = parseGenTypeParamIds(this.getContext(param),gtm.getId());
            String[] paramaValues = parseGenTypeParamValues(this.getContext(param),gtm.getId());
            Map<String,String> paramMap = new HashMap<>();
            for (int j = 0; j < paramIds.length; j++) {
                paramMap.put(paramIds[j],paramaValues[j]);
            }
            gtm.setMapParams(paramMap);
            map.put(gtm.getId(),gtm);
        }
        return map;
    }

    @Override
    public Map<String, String> getMapGenOutTypes(String themeId, Map<String, String> param) {
        Map<String,String> map = new HashMap<>();

        String[] genOutTypeIds = parseOutTypeIds(this.getContext(param));
        String[] genOutTypeValues = parseOutTypeValues(this.getContext(param));
        for (int i = 0; i <genOutTypeIds.length ; i++) {
            map.put(genOutTypeIds[i],genOutTypeValues[i]);
        }
        return map;
    }
    @Override
    public Map<String, String> getMapProviders(String themeId, Map<String, String> param) {
        Map<String,String> map = new HashMap<>();

        String[] genProviderIds = parseProviderIds(this.getContext(param));
        String[] genProviderValues = parseProviderValues(this.getContext(param));
        for (int i = 0; i <genProviderIds.length ; i++) {
            map.put(genProviderIds[i],genProviderValues[i]);
        }
        return map;
    }
    private Context getContext(Map<String, String> param) {
        Context c = null;
        try {
            c = Context.getInstance(
                    param.get(ExpressionEnum.Location.getExpr())+ExpressionEnum.separator.getExpr()+ ExpressionEnum.themeXmlName.getExpr()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    ///////////////////////////////////////////////////////////
    private String[] parseGenTypeParamValues(Context c,String genTypeId){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenTypeses().addSeparator().addGenTypese().addDollar()
                        .addopenBracket().addId().addequal().addOtherValue(genTypeId).addcloseBracket().addSeparator()
                        .addParams().addSeparator()
                        .addParam().addDollar()
                        .build()
        );
        return re.interpret(c);
    }
    private String[] parseGenTypeParamIds(Context c,String genTypeId){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenTypeses().addSeparator().addGenTypese().addDollar()
                        .addopenBracket().addId().addequal().addOtherValue(genTypeId).addcloseBracket().addSeparator()
                        .addParams().addSeparator()
                        .addParam().addDollar()
                        .addDot().addId().addDollar()
                        .build()
        );
        return re.interpret(c);
    }
    private String[] parseGenTypeIds(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                .addGenTypeses().addSeparator().addGenTypese().addDollar()
                .addDot().addId().addDollar().build()
        );
        return re.interpret(c);
    }
    private String[] parseGenValues(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenTypeses().addSeparator().addGenTypese().addDollar()
                        .addDot().addtype().addDollar()
                        .build()
        );
        return re.interpret(c);
    }
    ///////////////////////////////////////////////////////////////
    private String[] parseOutTypeIds(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenOutTpyes().addSeparator().addGenOutTpye().addDollar()
                        .addDot().addId().addDollar().build()
        );
        return re.interpret(c);
    }
    private String[] parseOutTypeValues(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addGenOutTpyes().addSeparator().addGenOutTpye().addDollar()
                        .addDot().addtype().addDollar().build()
        );
        return re.interpret(c);
    }
    ////////////////////////////////////////////////////////////
    private String[] parseProviderIds(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addProviders().addSeparator().addProvider().addDollar()
                        .addDot().addId().addDollar().build()
        );
        return re.interpret(c);
    }
    private String[] parseProviderValues(Context c){
        c.init();
        ReadXmlExpression re = Parser.parse(
                new ThemeBuilder().addTheme().addSeparator()
                        .addProviders().addSeparator().addProvider().addDollar()
                        .addDot().addtype().addDollar().build()
        );
        return re.interpret(c);
    }
}
