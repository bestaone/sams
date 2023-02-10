package cn.webestar.sams.microsvc.org.dto.corp;

import cn.webestar.sams.basic.beans.api.LimitBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class CorpLimitBody extends LimitBody {

    @Override
    public <T> QueryWrapper<T> toParamsQueryWapper() {
        return null;
    }
    
}
