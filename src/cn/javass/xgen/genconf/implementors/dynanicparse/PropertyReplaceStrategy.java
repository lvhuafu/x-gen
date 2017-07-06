package cn.javass.xgen.genconf.implementors.dynanicparse;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

import java.util.Map;

/**
 * Created by HASEE on 2017/7/5.
 */
public class PropertyReplaceStrategy implements ParseStrategy {
    @Override
    public String parseDynamicContent(GenConfModel gm, Map<String, ExtendConfModel> mapEcms, String expr) {
        String retStr = "";
        ExtendConfModel ecm = mapEcms.get(expr);
        if(ecm!=null){
            retStr = ecm.getValue();
        }
        return retStr;
    }
}
