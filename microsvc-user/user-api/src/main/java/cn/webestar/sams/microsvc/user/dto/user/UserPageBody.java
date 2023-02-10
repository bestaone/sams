package cn.webestar.sams.microsvc.user.dto.user;

import cn.webestar.sams.basic.beans.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class UserPageBody extends PageBody {

    @Override
    public <T> QueryWrapper<T> toParamsQueryWapper() {
        return null;
    }

}
