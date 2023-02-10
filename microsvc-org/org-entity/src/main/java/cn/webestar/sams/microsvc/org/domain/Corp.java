package cn.webestar.sams.microsvc.org.domain;

import cn.webestar.sams.basic.beans.domain.CommonDO;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName(value = "corp", autoResultMap = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Corp extends CommonDO {

    @Schema(name = "名称")
    private String name;
    private String address;
    private Integer status;

}
