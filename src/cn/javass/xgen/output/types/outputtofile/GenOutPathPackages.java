package cn.javass.xgen.output.types.outputtofile;

import cn.javass.xgen.genconf.vo.ModuleConfModel;

/**
 * Created by HASEE on 2017/7/11.
 */
public interface GenOutPathPackages {
    /**
     * 根据配置来生成内容输出的文件夹
     * @param moduleConf
     * @param genTypeId
     * @return
     */
    boolean genPackages(ModuleConfModel moduleConf,String genTypeId);

    /**
     * 获取内容输出的到的文件的路径和文件名
     * @param moduleConf
     * @param genTypeId
     * @return
     */
    String getOutPathAndFileName(ModuleConfModel moduleConf,String genTypeId);
}
