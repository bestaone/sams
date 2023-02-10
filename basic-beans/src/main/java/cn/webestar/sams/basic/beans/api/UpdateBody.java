package cn.webestar.sams.basic.beans.api;

import cn.webestar.sams.basic.common.domain.BasicDO;

public abstract class UpdateBody extends Body{

    public abstract Long getId();

    public abstract <T extends BasicDO> T toDomain();

}
