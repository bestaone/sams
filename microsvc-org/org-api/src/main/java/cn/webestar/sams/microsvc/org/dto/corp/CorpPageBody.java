package cn.webestar.sams.microsvc.org.dto.corp;

import cn.webestar.sams.basic.beans.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class CorpPageBody extends PageBody {

    @Override
    public <T> QueryWrapper<T> toParamsQueryWapper() {
        return null;
    }

}
