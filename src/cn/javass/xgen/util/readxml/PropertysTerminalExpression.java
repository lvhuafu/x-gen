package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by HASEE on 2017/6/29.
 */
public class PropertysTerminalExpression extends ReadXmlExpression{

    /**
     * 属性名字
     */
    private String propName;
    public PropertysTerminalExpression(String propName){
        this.propName = propName;
    }
    @Override
    public String[] interpret(Context ctx) {
        //1.获取父元素多个
        List<Element> pEles = ctx.getPreEles();
        String[] ss  = new String[pEles.size()];
        //2.直接取该元素的属性的值
        for (int i = 0; i < pEles.size(); i++) {
            ss[i] = pEles.get(i).getAttribute(propName);
        }
        return ss;
    }
    @Override
    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
