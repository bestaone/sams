package cn.webestar.sams.basic.beans.api;

import cn.webestar.sams.basic.common.domain.BasicDO;

public abstract class CreateBody extends Body {

    public abstract <T extends BasicDO> T toDomain();

}
