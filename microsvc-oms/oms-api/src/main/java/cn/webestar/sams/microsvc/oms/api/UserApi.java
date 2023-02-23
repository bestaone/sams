package cn.webestar.sams.microsvc.oms.api;

import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.microsvc.oms.domain.User;
import cn.webestar.sams.microsvc.oms.dto.user.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.webestar.sams.microsvc.oms.dto.user.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zgs
 */
@FeignClient(name="microsvc-oms")
public interface UserApi {

    @PostMapping("/api/user/create")
    R<User> create(@RequestBody UserCreateBody body);

    @PostMapping("/delete")
    R<Boolean> delete(@RequestParam("id") Long id);

    @PostMapping("/update")
    R<User> update(@RequestBody UserUpdateBody body);

    @PostMapping("/api/user/findById")
    R<User> findById(@RequestParam("id") Long id);

    @PostMapping("/findByIds")
    R<Map<Long, User>> findByIds(@RequestBody Set<Long> ids);

    @PostMapping("/page")
    R<IPage<User>> page(@RequestBody UserPageBody body);

    @PostMapping("/api/user/findOne")
    R<User> findOne(@RequestBody UserSimpleBody body);

    @PostMapping("/limitList")
    R<List<User>> limitList(@RequestBody UserLimitBody body);

}
