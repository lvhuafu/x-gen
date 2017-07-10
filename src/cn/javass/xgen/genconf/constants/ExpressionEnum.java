package cn.javass.xgen.genconf.constants;

/**
 * Created by HASEE on 2017/7/4.
 */
public enum ExpressionEnum {
    propBeginStr("${"),propEndStr("}"),methodBeginStr("$["),methodEndStr("]"),template("template"),
    fileName("fileName"),Location("Location"),themeXmlName("ThemeConf.xml"),dot("."),separator("/"),dollar("$"),openBracket("["),closeBracket("]"),equal("="),comma(","),xml("xml"),xmlFilePre("cn/javass/xgenconfxml/");
    private String expr;
    private ExpressionEnum(String expr){
        this.expr = expr;
    }
    public String getExpr(){
        return this.expr;
    }
}
