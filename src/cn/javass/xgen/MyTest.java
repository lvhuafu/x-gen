package cn.javass.xgen;

import cn.javass.xgen.dispatch.GenFacade;
import cn.javass.xgen.genconf.GenConfEbi;
import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.genconf.implementors.xmlimpl.GenConfXmlImpl;

/**
 * Created by HASEE on 2017/6/29.
 */
public class MyTest {
    public static void main(String[] args)throws Exception {
        //1.表达式---》抽象语法树---》解析器
        //2.抽象语法树---》解释器模式去解释执行
//        ElementExpression genConf = new ElementExpression("GenConf","");
//        ElementExpression needGens = new ElementExpression("NeedGens","");
//        ElementsExpression needGen = new ElementsExpression("NeedGen","");
//        ElementsExpression params = new ElementsExpression("Params","");
//        ElementsTerminalExptession param = new ElementsTerminalExptession("Param", "id=fileName1");
//        //组装抽象语法树
//        genConf.addEle(needGens);
//        needGens.addEle(needGen);
//        needGen.addEle(params);
//        params.addEle(param);
//        String[] ss1;
//        String[] ss;
//        long a1 = System.currentTimeMillis();
//        Context ctx = Context.getInstance("cn/javass/xgenconfxml/GenConf.xml");
//        for (int i = 0; i <10000 ; i++) {
//            ss1 = Parser2.parse("GenConf/NeedGens/NeedGen/Params/Param$").interpret(ctx);
//            ss = Parser2.parse("GenConf/NeedGens/NeedGen/Params/Param$.id$").interpret(ctx);
//        }
//        long a2 = System.currentTimeMillis();
//        System.out.println("now use time is "+(a2-a1));
//        for (String s : ss) {
//            System.out.println("ss=="+s);
//        }
//        ElementExpression genConf1 = new ElementExpression("GenConf","");
//        ElementExpression themes = new ElementExpression("Themes","");
//        ElementExpression theme = new ElementExpression("Theme", "");
//        PropertyTerminalExpression id = new PropertyTerminalExpression("id");
//        genConf1.addEle(themes);
//        themes.addEle(theme);
//        theme.addEle(id);
//        Context ctx1 = Context.getInstance("cn/javass/xgen/xgenconfxml/GenConf.xml");
//        String[] ss1 = genConf1.interpret(ctx);
//        System.out.println("ss[0]="+ss1[0]);
//        ElementExpression genConf = new ElementExpression("GenConf","");
//        ElementExpression constants = new ElementExpression("Constants","");
//        ElementsTerminalExptession constant = new ElementsTerminalExptession("Constant","");
//        genConf.addEle(constants);
//        constants.addEle(constant);
//        Context ctx1 = Context.getInstance("cn/javass/xgen/xgenconfxml/GenConf.xml");
//        String[] ss1 = genConf.interpret(ctx1);
//        for (String s : ss1) {
//            System.out.println("ss=="+s);
//        }
//        GenConfEbi ebi = GenConfFactory.createGenConfEbi(new GenConfXmlImpl());
//        System.out.println("gm------"+ebi.getMapModuleConf());
        GenFacade.generate();
    }
}
