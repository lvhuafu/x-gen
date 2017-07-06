package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.constants.GenConfEnum;

/**
 * Created by HASEE on 2017/7/4.
 */
public abstract class CommonBuilder<T> {
    /**
     * 用来记录分步骤拼接的字符串，也就是最终产品
     */
    protected abstract StringBuffer getBuilderBuffer();

    /**
     * 获取实际的构建器对象
     * @return
     */
    protected abstract T getBuilderClassInstance();
    /**
     * 拼接"."//支持连缀
     * @return
     */
    public T addDot(){
        getBuilderBuffer().append(ExpressionEnum.dot.getExpr());
        return getBuilderClassInstance();
    }
    public T addSeparator(){
        getBuilderBuffer().append(ExpressionEnum.separator.getExpr());
        return getBuilderClassInstance();
    }
    public T addDollar(){
        getBuilderBuffer().append(ExpressionEnum.dollar.getExpr());
        return getBuilderClassInstance();
    }
    public T addopenBracket(){
        getBuilderBuffer().append(ExpressionEnum.openBracket.getExpr());
        return getBuilderClassInstance();
    }
    public T addcloseBracket(){
        getBuilderBuffer().append(ExpressionEnum.closeBracket.getExpr());
        return getBuilderClassInstance();
    }
    public T addequal(){
        getBuilderBuffer().append(ExpressionEnum.equal.getExpr());
        return getBuilderClassInstance();
    }
    public T addcomma(){
        getBuilderBuffer().append(ExpressionEnum.comma.getExpr());
        return getBuilderClassInstance();
    }
    public T addxml(){
        getBuilderBuffer().append(ExpressionEnum.xml.getExpr());
        return getBuilderClassInstance();
    }
    public T addxmlFilePre(){
        getBuilderBuffer().append(ExpressionEnum.xmlFilePre.getExpr());
        return getBuilderClassInstance();
    }
    public T addId(){
        getBuilderBuffer().append(GenConfEnum.id);
        return getBuilderClassInstance();
    }
    /**
     * 拼接传入值
     * @return
     */
    public T addOtherValue(String value){
        getBuilderBuffer().append(value);
        return getBuilderClassInstance();
    }
    public String build(){
        return getBuilderBuffer().toString();
    }
}
