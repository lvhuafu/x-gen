package cn.javass.xgen.template.visitors;

/**
 * Created by HASEE on 2017/7/10.
 */
public interface Visitor {
    Object visitTemplateElement(TemplateElement te);
}
