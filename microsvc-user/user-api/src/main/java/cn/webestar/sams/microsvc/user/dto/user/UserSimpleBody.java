package cn.webestar.sams.microsvc.user.dto.user;

import cn.webestar.sams.basic.beans.api.SimpleBody;
import cn.webestar.sams.microsvc.user.domain.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author zgs
 */
@Data
public class UserSimpleBody extends SimpleBody {

    private String username;
    private String phoneNum;

    @Override
    public QueryWrapper<User> toParamsQueryWapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        if (StringUtils.hasText(username)) {
            queryWrapper.eq("username", username);
        }
        if (StringUtils.hasText(phoneNum)) {
            queryWrapper.eq("phone_num", phoneNum);
        }
        return queryWrapper;
    }

}
