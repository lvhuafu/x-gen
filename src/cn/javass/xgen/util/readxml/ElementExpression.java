package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HASEE on 2017/6/29.
 */
public class ElementExpression extends ReadXmlExpression {
    public List<ReadXmlExpression> getEles() {
        return eles;
    }

    public void setEles(List<ReadXmlExpression> eles) {
        this.eles = eles;
    }

    /**
     * 用来记录组合的子ReadXmlExpression元素。
     */
    private List<ReadXmlExpression> eles = new ArrayList<>();
    /**
     * 元素的名称
     */
    private String eleName = "";
    /**
     * 用来判断的条件
     */
    private String condition = "";

    public ElementExpression(String eleName, String condition) {
        this.condition = condition;
        this.eleName = eleName;
    }

    public void addEle(ReadXmlExpression ele) {
        this.eles.add(ele);
    }

    public boolean remove(ReadXmlExpression ele){
        this.eles.remove(ele);
        return true;
    }

    public void removeAllEles(){
        this.eles.clear();
    }

    @Override
    public String[] interpret(Context ctx) {
        //1.维护父级节点记录
        //1.1先取出父元素
        List<Element> pEles = ctx.getPreEles();
        Element ele = null;
        List<Element> nowEles = new ArrayList<>();
        if(pEles.size()==0) {
            //1.2判断父元素是否存在，不存在是根元素
            ele = ctx.getDocument().getDocumentElement();
            pEles.add(ele);
            ctx.setPreEles(pEles);
        }else {
            //1.3如果存在，那么久应该找到当前表达是所对应的元素，就把这个元素设置成父级节点。
            for (Element pEle : pEles) {
                nowEles.addAll(ctx.getNowEles(pEle,eleName));
                if(nowEles.size()>0){
                    //找一个就停止
                    break;
                }
            }
            if(nowEles.size()>0&&ctx.judgeCondition(nowEles.get(0),condition)){
                List<Element> tempList = new ArrayList<>();
                tempList.add(nowEles.get(0));
                ctx.setPreEles(tempList);
            }
        }
        //2.循环解释子元素
        String ss[] = null;
        for (ReadXmlExpression temEle:eles){
            ss = temEle.interpret(ctx);
        }
        return ss;
    }
    @Override
    public Object clone(){
        ElementExpression obj = null;
        try {
            obj = (ElementExpression)super.clone();
            List<ReadXmlExpression> objEles = new ArrayList<>();
            for (ReadXmlExpression re:eles) {
                objEles.add((ReadXmlExpression)re.clone());
            }
            obj.setEles(objEles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
