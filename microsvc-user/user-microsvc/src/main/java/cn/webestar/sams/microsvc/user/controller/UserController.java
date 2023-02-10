package cn.webestar.sams.microsvc.user.controller;

import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.microsvc.user.domain.User;
import cn.webestar.sams.microsvc.user.dto.user.*;
import cn.webestar.sams.microsvc.user.service.UserService;
import cn.webestar.sams.starter.apisvc.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Tag(name = "用户", description = "用户")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService service;

    @Override
    public IService<User> getService() {
        return service;
    }

    @Operation(summary = "info")
    public R<User> info(@RequestParam("id") Long id) {
        User user = this.getService().getById(id);
        return R.success(user);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestParam("id") Long id) {
        return super._delete(id);
    }

    @PostMapping("/findById")
    public R<User> findById(@RequestParam("id") Long id) {
        return super._findById(id);
    }

    @PostMapping("/findByIds")
    public R<Map<Long, User>> findByIds(@RequestBody Set<Long> ids) {
        return super._findByIds(ids);
    }

    @PostMapping("/create")
    public R<User> create(@RequestBody UserCreateBody body) {
        return super._create(body);
    }

    @PostMapping("/update")
    public R<User> update(@RequestBody UserUpdateBody body) {
        return super._update(body.toDomain());
    }

    @PostMapping("/page")
    public R<IPage<User>> page(@RequestBody UserPageBody body) {
        return super._page(body);
    }

    @PostMapping("/findOne")
    public R<User> findOne(@RequestBody UserSimpleBody body) {
        return super._findOne(body);
    }

    @PostMapping("/limitList")
    public R<List<User>> limitList(@RequestBody UserLimitBody body) {
        return super._limitList(body);
    }

}
