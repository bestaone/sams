package cn.webestar.sams.microsvc.oms.dto.user;

import cn.webestar.sams.basic.beans.api.LimitBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class UserLimitBody extends LimitBody {

    @Override
    public <T> QueryWrapper<T> toParamsQueryWapper() {
        return null;
    }
    
}
