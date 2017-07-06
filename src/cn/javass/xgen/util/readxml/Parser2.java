package cn.javass.xgen.util.readxml;

import java.util.*;

/**
 * 根据字符串构造语法树
 * Created by HASEE on 2017/6/29.
 */
public class Parser2 {
    //定义常量
    private static final String BACKLASH = "/";
    private static final String DOT = ".";
    private static final String DOLLAR = "$";
    private static final String OPEN_DRACKET = "[";
    /**
     * 按照分解的先后顺序的元素名称
     */
    private static List<String> listEle = null;

    private static final String CLOSE_BRACKET = "]";

    private Parser2() {

    }

    /**
     * 根据传入的字符串表达式，通过解析，组合 为一个抽象的语法树
     *
     * @param expr 要解析的表达式
     * @return 表达式对应的抽象语法树
     */
    public static ReadXmlExpression parse(String expr) {
        // root/a/b/c
        listEle = new ArrayList<>();
        //第一大步：分析表达式，得到需要解析的元素名称和该元素对应的解析模型
        Map<String, ParseModel> mapPath = parseMapPath(expr);
        //第二大步：根据元素对应的解析模型--》转换成相应的解释器对象
        List<ReadXmlExpression> list = mapPath2Expression(mapPath);
        //第三大步：按照先后顺序组合成为抽象的语法树
        ReadXmlExpression retTree = buildTree(list);
        return retTree;
    }
    ////////////////////////////第一大步

    /**
     * 按照从左到右的顺序来解析表达式，得到相应的元素名称和其对应的解析模型
     *
     * @param expr
     * @return
     */
    private static Map<String, ParseModel> parseMapPath(String expr) {
        //root/a/b/c.name
        Map<String, ParseModel> mapPath = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(expr, BACKLASH);
        while (tokenizer.hasMoreTokens()) {
            String onePath = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) {
                //还有下一个，不是结尾
                setParsePath(onePath, false, false, mapPath);
            } else {
                //说明到结尾了
                int dotIndex = onePath.indexOf(DOT);
                if (dotIndex > 0) {
                    //说是属性结尾
                    String eleName = onePath.substring(0, dotIndex);
                    String propName = onePath.substring(dotIndex + 1);
                    //设置属性前面的元素
                    setParsePath(eleName, false, false, mapPath);
                    //设置属性
                    setParsePath(propName, true, true, mapPath);
                } else {
                    //说明是元素结尾
                    setParsePath(onePath, true, false, mapPath);
                }
                //已经解析到结尾了，就退出
                break;
            }
        }
        return mapPath;
    }

    private static void setParsePath(String eleName, boolean end, boolean propertyValue, Map<String, ParseModel> mapPath) {
        ParseModel pm = new ParseModel();
        pm.setEnd(end);
        pm.setPropertyValue(propertyValue);
        pm.setSingleValue(!(eleName.indexOf(DOLLAR) > 0));
        //去掉$
        eleName = eleName.replace(DOLLAR, "");
        int tempBegin = 0;
        int tempEnd = 0;
        if ((tempBegin = eleName.indexOf(OPEN_DRACKET)) > 0) {
            tempEnd = eleName.indexOf(CLOSE_BRACKET);
            pm.setCondition(eleName.substring(tempBegin + 1, tempEnd));
            eleName = eleName.substring(0, tempBegin);
        }
        pm.setEleName(eleName);

        mapPath.put(eleName, pm);
//        System.out.println(pm.toString());
        listEle.add(eleName);
    }
//////////////////////////第二大步

    /**
     * 根据元素对应的解析模型--》转换成相应的解释器对象
     *
     * @param mapPath
     * @return
     */
    private static List<ReadXmlExpression> mapPath2Expression(Map<String, ParseModel> mapPath) {
        //一定要按照分解的先后顺序来转换成相应的解释器对象。
        List<ReadXmlExpression> list = new ArrayList<>();
        for (String key : listEle) {
            ParseModel pm = mapPath.get(key);
            ReadXmlExpression obj = parseModel2ReadXmlExpression(pm);

            list.add(obj);
        }

        return list;
    }

    private static ReadXmlExpression parseModel2ReadXmlExpression(ParseModel pm) {
        ReadXmlExpression obj = null;
        if (!pm.isEnd()) {
            if (pm.isSingleValue()) {
                obj = new ElementExpression(pm.getEleName(), pm.getCondition());
            } else {
                obj = new ElementsExpression(pm.getEleName(), pm.getCondition());
            }
        } else {
            if (pm.isPropertyValue()) {
                if (pm.isSingleValue()) {
                    obj = new PropertyTerminalExpression(pm.getEleName());
                } else {
                    obj = new PropertysTerminalExpression(pm.getEleName());
                }
            } else {
                if (pm.isSingleValue()) {
                    obj = new ElementTerminalExptession(pm.getEleName(), pm.getCondition());
                } else {
                    obj = new ElementsTerminalExptession(pm.getEleName(), pm.getCondition());
                }
            }
        }
        return obj;
    }
//////////////////////////第三大步

    /**
     * 按照先后顺序组成抽象语法树
     *
     * @param listExptession
     * @return
     */
    private static ReadXmlExpression buildTree(List<ReadXmlExpression> listExptession) {
        //第一个对象，根对象，也就是返回去的对象
        ReadXmlExpression retRe = null;
        //记录当前父元素
        ReadXmlExpression preRe = null;

        for (ReadXmlExpression re : listExptession) {
            if (preRe == null) {
                preRe = re;
                retRe = re;
            } else {
                //把当前元素添加到父元素的下面，同时更新父元素
                if (preRe instanceof ElementExpression) {
                    ElementExpression ele = (ElementExpression) preRe;
                    ele.addEle(re);
                    preRe = re;
                } else if (preRe instanceof ElementsExpression) {
                    ElementsExpression ele = (ElementsExpression) preRe;
                    ele.addEle(re);
                    preRe = re;
                }
            }
        }
        return retRe;
    }
}
