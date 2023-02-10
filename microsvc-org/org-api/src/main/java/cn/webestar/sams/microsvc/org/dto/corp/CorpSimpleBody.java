package cn.webestar.sams.microsvc.org.dto.corp;

import cn.webestar.sams.basic.beans.api.SimpleBody;
import cn.webestar.sams.microsvc.org.domain.Corp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author zgs
 */
@Data
public class CorpSimpleBody extends SimpleBody {

    private String name;
    private String address;

    @Override
    public QueryWrapper<Corp> toParamsQueryWapper() {
        QueryWrapper<Corp> queryWrapper = new QueryWrapper();
        if (StringUtils.hasText(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.hasText(address)) {
            queryWrapper.eq("address", address);
        }
        return queryWrapper;
    }

}
