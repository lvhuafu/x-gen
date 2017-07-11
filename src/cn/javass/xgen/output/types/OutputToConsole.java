package cn.javass.xgen.output.types;

import cn.javass.xgen.output.GenOutputEbi;

import java.util.Observable;

/**
 * Created by HASEE on 2017/7/11.
 */
public class OutputToConsole implements GenOutputEbi {
    @Override
    public void update(Observable o, Object obj) {
        String content = ""+obj;
//        System.out.println("now Content="+content);
    }
}
