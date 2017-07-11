package cn.javass.xgen.output.types;

import cn.javass.xgen.mediator.CoreMediator;
import cn.javass.xgen.output.GenOutputEbi;
import cn.javass.xgen.output.types.outputtofile.AbstractFactory;
import cn.javass.xgen.output.types.outputtofile.PlainTxtFactory;

import java.util.Collection;
import java.util.Observable;

/**
 * Created by HASEE on 2017/7/11.
 */
public class OutputToFile implements GenOutputEbi {
    @Override
    public void update(Observable o, Object obj) {
        //1.首先要得到要输出的内容
        String content = "" +obj;
        //2.创建输出文件的文件夹
        AbstractFactory af = new PlainTxtFactory();//可以做成静态
        af.createGenOutPathPackages().genPackages(CoreMediator.getInstance().getObserverModuleConf(o),
                CoreMediator.getInstance().getObserverGenTypeId(o));
        //3.输出成文件
        String outPathAndFileName = af.createGenOutPathPackages().getOutPathAndFileName(
                CoreMediator.getInstance().getObserverModuleConf(o),
                CoreMediator.getInstance().getObserverGenTypeId(o)
        );
        af.createOutter().writeContent(outPathAndFileName,content);
    }
}
