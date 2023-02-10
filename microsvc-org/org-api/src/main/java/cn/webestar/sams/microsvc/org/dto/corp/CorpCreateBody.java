package cn.webestar.sams.microsvc.org.dto.corp;

import cn.webestar.sams.basic.beans.api.CreateBody;
import cn.webestar.sams.microsvc.org.domain.Corp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CorpCreateBody extends CreateBody {

    private String name;
    private String address;
    private Integer status;
    private Long updaterId;
    private Long createrId;

    @Override
    public Corp toDomain() {
        Corp o = new Corp();
        o.setName(name);
        o.setAddress(address);
        o.setCreaterId(createrId);
        o.setUpdaterId(updaterId);
        o.setStatus(status);
        return o;
    }

}
