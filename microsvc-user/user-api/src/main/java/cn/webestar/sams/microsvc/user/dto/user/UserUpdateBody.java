package cn.webestar.sams.microsvc.user.dto.user;

import cn.webestar.sams.basic.beans.api.UpdateBody;
import cn.webestar.sams.basic.common.domain.BasicDO;
import cn.webestar.sams.microsvc.user.domain.User;

public class UserUpdateBody extends UpdateBody {

    private Long id;
    private String username;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public <T extends BasicDO> T toDomain() {
        User o = new User();
        o.setUsername(username);
        return (T) o;
    }

}
