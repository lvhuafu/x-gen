package cn.javass.xgen.genconf.implementors.dynanicparse;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

import java.util.Map;

/**
 * Created by HASEE on 2017/7/5.
 */
public class ParseContext {
    public Map<String,ExtendConfModel> parse(GenConfModel gm, Map<String, ExtendConfModel> mapEcms){
        //现在只支持两种语法，因此选择策略算法的过程时可以固定的，我们就在这里选择
        //目前的约定是只在Module配置的ExtendsConf里面才能使用变量
        //如果$()里面是一个单独的单词，那就使用属性直接替换，否则就使用beanshell
        for (String key : mapEcms.keySet()) {
            ExtendConfModel ecm = mapEcms.get(key);
            ecm.setValue(this.parseOne(gm, mapEcms, ecm.getValue()));
            mapEcms.put(key,ecm);
        }
        return mapEcms;
    }
    private String parseOne(GenConfModel gm,Map<String, ExtendConfModel> mapEcms,String value){
        //1.首先判断是否有${,如果没有，那就不用解析
        if(value!=null&&value.indexOf("${")>=0){
            //2.如果有${,那么把里面的值取出来，再进行判断
            int begin = value.indexOf("${");
            int end = begin+"${".length()+value.substring(begin+"${".length()).indexOf("}");
            String prep = value.substring(begin+"${".length(),end);
            ParseStrategy ps = null;
            if(isWord(prep)){
                ps = new PropertyReplaceStrategy();
            }else {
                ps = new BeansShellStrategy();
            }
            //得到解析后的数据
            String tempStr = ps.parseDynamicContent(gm,mapEcms,prep);
            //然后把动态数据替换回去
            value = value.replace("${"+prep+"}",tempStr);
            //继续递归
            value = parseOne(gm,mapEcms,value);
        }
        return value;
    }
    private boolean isWord(String s){
        char cs[] = s.toCharArray();
        for (char c:cs) {
            if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){

            }else {
                return false;
            }
        }
        return true;
    }
}
