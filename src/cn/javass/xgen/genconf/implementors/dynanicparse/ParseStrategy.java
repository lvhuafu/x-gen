package cn.javass.xgen.genconf.implementors.dynanicparse;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;

import java.util.Map;

/**
 * Created by HASEE on 2017/7/5.
 */
public interface ParseStrategy {
    /**
     * 解析动态内容，获取完整的字符串
     * @param gm 包含参数值
     * @param mapEcms 包含参数值
     * @param expr 需要动态解析部分的字符串
     * @return 进过动态解析后的完整内容字符串
     */
    String parseDynamicContent(GenConfModel gm, Map<String,ExtendConfModel> mapEcms,String expr);
}
