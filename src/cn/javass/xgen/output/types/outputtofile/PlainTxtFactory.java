package cn.javass.xgen.output.types.outputtofile;

import cn.javass.xgen.output.types.outputtofile.plaintxt.GenOutPathPackageImpl;
import cn.javass.xgen.output.types.outputtofile.plaintxt.OutterImpl;

/**
 * Created by HASEE on 2017/7/11.
 */
public class PlainTxtFactory implements AbstractFactory {
    @Override
    public GenOutPathPackages createGenOutPathPackages() {
        return new GenOutPathPackageImpl();
    }

    @Override
    public Outter createOutter() {
        return new OutterImpl();
    }
}
