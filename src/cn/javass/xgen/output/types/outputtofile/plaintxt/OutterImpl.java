package cn.javass.xgen.output.types.outputtofile.plaintxt;

import cn.javass.xgen.output.types.outputtofile.Outter;
import cn.javass.xgen.util.file.FileHelper;

/**
 * Created by HASEE on 2017/7/11.
 */
public class OutterImpl implements Outter {
    @Override
    public boolean writeContent(String outPathAndFileName, String content) {
        FileHelper.writeFile(outPathAndFileName, content);
        return true;
    }
}
