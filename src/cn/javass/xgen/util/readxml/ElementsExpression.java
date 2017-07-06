package cn.javass.xgen.util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**多个元素的表达
 * Created by HASEE on 2017/6/29.
 */
public class ElementsExpression extends ReadXmlExpression{
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

    public ElementsExpression(String eleName, String condition) {
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
        List<Element> pEles = ctx.getPreEles();
        //获取当前的元素，多个
        List<Element> nowEles = new ArrayList<>();
        for(Element pEle:pEles){
            nowEles.addAll(ctx.getNowEles(pEle,eleName));
        }
        //判断条件
        Iterator<Element> it = nowEles.iterator();
        while (it.hasNext()){
            Element ele = it.next();
            if(!ctx.judgeCondition(ele,condition)){
                it.remove();
            }
        }
        //设置父节点，多个
        ctx.setPreEles(nowEles);
        //2.循环解释子元素
        String[] ss = null;
        for (ReadXmlExpression tempEle:eles) {
            ss = tempEle.interpret(ctx);
        }
        return ss;
    }
    @Override
    public Object clone(){
        ElementsExpression obj = null;
        try {
            obj = (ElementsExpression)super.clone();
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
