package cn.javass.xgen.template;

/**
 * Created by HASEE on 2017/7/10.
 */
public interface TemplateEbi {
    /**
     * 按照默认的语法去替换模板中的属性变量
     * @return
     */
    Object replaceProperties();

    /**
     * 按照默认的语法运行方法，并把结果替换到模板中。
     * 使用访问者来实现
     * @return
     */
    Object replaceMethods();

    /**
     * 获取模板当前的内容，可能不是原始内容，也可能不是最终的内容
     * 而是经过处理的内容
     * @return
     */
    Object getNowContent();
}
