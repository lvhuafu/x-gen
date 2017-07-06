package cn.javass.xgen.util.readxml;

/**
 * Created by HASEE on 2017/6/29.
 */

public abstract class ReadXmlExpression implements Cloneable{
    public abstract String[] interpret(Context ctx);
    @Override
    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
