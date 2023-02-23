package cn.webestar.sams.microsvc.oms.domain;

import cn.webestar.sams.basic.beans.domain.CommonDO;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName(value = "user", autoResultMap = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User extends CommonDO {

    private String avatarUrl;
    @Schema(name = "用户名")
    private String username;
    private String phoneNum;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String password;
    private LocalDateTime regtime;

    private Integer status;

}
