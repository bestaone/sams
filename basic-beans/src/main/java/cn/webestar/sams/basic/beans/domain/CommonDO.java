package cn.webestar.sams.basic.beans.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import cn.webestar.sams.basic.common.domain.BasicDO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommonDO implements BasicDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long updaterId;
    private Long createrId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
