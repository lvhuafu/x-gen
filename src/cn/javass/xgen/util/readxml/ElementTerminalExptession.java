package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by HASEE on 2017/6/29.
 */
public class ElementTerminalExptession extends ReadXmlExpression{
    /**
     * 元素的名称
     */
    private String eleName = "";
    /**
     * 用来判断的条件
     */
    private String condition = "";

    public ElementTerminalExptession(String eleName, String condition) {
        this.condition = condition;
        this.eleName = eleName;
    }

    @Override
    public String[] interpret(Context ctx) {
        //1:获取到自己这个元素
        //1.1先获取到父元素
        List<Element> pEles = ctx.getPreEles();
        //1.2根据父元素和自己元素名字，查找到自己这个元素
        Element ele = null;
        if (pEles.size() == 0) {
            //说明是根元素
            ele = ctx.getDocument().getDocumentElement();
        } else {
            ele = ctx.getNowEles(pEles.get(0), eleName).get(0);
        }
        //2：判断这个元素是否满足条件
        if (!ctx.judgeCondition(ele, condition)) {
            return new String[0];
        }
        //3.获取这个元素的值
        String[] ss = new String[1];
        if (ele.getFirstChild() != null) {
            ss[0] = ele.getFirstChild().getNodeValue();
        } else {
            ss[0] = "";
        }
        return ss;
    }
    @Override
    public Object clone(){
        ElementTerminalExptession obj = null;
        try {
            obj = (ElementTerminalExptession)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
