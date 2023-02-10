package cn.webestar.sams.microsvc.org.dto.corp;

import cn.webestar.sams.basic.beans.api.UpdateBody;
import cn.webestar.sams.basic.common.domain.BasicDO;

public class CorpUpdateBody extends UpdateBody {

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public <T extends BasicDO> T toDomain() {
        return null;
    }

}
