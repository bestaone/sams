package cn.webestar.sams.microsvc.oms.dto.user;

import cn.webestar.sams.basic.beans.api.CreateBody;
import cn.webestar.sams.microsvc.oms.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserCreateBody extends CreateBody {

    private String avatarUrl;
    private String username;
    private String phoneNum;
    private String password;
    private LocalDateTime regtime;
    private Long updaterId;
    private Long createrId;
    private Integer status;

    @Override
    public User toDomain() {
        User o = new User();
        o.setAvatarUrl(avatarUrl);
        o.setUsername(username);
        o.setPhoneNum(phoneNum);
        o.setPassword(password);
        o.setRegtime(regtime);
        o.setUpdaterId(updaterId);
        o.setCreaterId(createrId);
        o.setStatus(status);
        return o;
    }

}
