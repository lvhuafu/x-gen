package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ThemeEnum;

/**
 * Created by HASEE on 2017/7/4.
 */
public class ThemeBuilder extends CommonBuilder<ThemeBuilder> {
    private StringBuffer buffer = new StringBuffer();

    public ThemeBuilder addTheme(){
        buffer.append(ThemeEnum.Theme);
        return this;
    }
    public ThemeBuilder addGenTypese(){
        buffer.append(ThemeEnum.GenType);
        return this;
    }
    public ThemeBuilder addGenTypeses(){
        buffer.append(ThemeEnum.GenTypes);
        return this;
    }
    public ThemeBuilder addParams(){
        buffer.append(ThemeEnum.Params);
        return this;
    }

    public ThemeBuilder addParam(){
        buffer.append(ThemeEnum.Param);
        return this;
    }
    public ThemeBuilder addtype(){
        buffer.append(ThemeEnum.type);
        return this;
    }
    public ThemeBuilder addGenOutTypes(){
        buffer.append(ThemeEnum.GenOutTypes);
        return this;
    }
    public ThemeBuilder addGenOutType(){
        buffer.append(ThemeEnum.GenOutType);
        return this;
    }
    public ThemeBuilder addProviders(){
        buffer.append(ThemeEnum.Providers);
        return this;
    }
    public ThemeBuilder addProvider(){
        buffer.append(ThemeEnum.Provider);
        return this;
    }
    @Override
    protected StringBuffer getBuilderBuffer() {
        return this.buffer;
    }

    @Override
    protected ThemeBuilder getBuilderClassInstance() {
        return this;
    }
    public String build(){
        return buffer.toString();
    }
}
