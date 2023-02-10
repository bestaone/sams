package cn.webestar.sams.microsvc.org.controller;

import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.microsvc.org.domain.Corp;
import cn.webestar.sams.microsvc.org.dto.corp.*;
import cn.webestar.sams.microsvc.org.service.CorpService;
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

@Tag(name = "公司", description = "公司")
@RestController
@RequestMapping("/api/corp")
public class CorpController extends BaseController<Corp> {

    @Autowired
    private CorpService service;

    @Override
    public IService<Corp> getService() {
        return service;
    }

    @Operation(summary = "info")
    public R<Corp> info(@RequestParam("id") Long id) {
        Corp o = this.getService().getById(id);
        return R.success(o);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestParam("id") Long id) {
        return super._delete(id);
    }

    @PostMapping("/findById")
    public R<Corp> findById(@RequestParam("id") Long id) {
        return super._findById(id);
    }

    @PostMapping("/findByIds")
    public R<Map<Long, Corp>> findByIds(@RequestBody Set<Long> ids) {
        return super._findByIds(ids);
    }

    @PostMapping("/create")
    public R<Corp> create(@RequestBody CorpCreateBody body) {
        return super._create(body);
    }

    @PostMapping("/update")
    public R<Corp> update(@RequestBody CorpUpdateBody body) {
        return super._update(body.toDomain());
    }

    @PostMapping("/page")
    public R<IPage<Corp>> page(@RequestBody CorpPageBody body) {
        return super._page(body);
    }

    @PostMapping("/findOne")
    public R<Corp> findOne(@RequestBody CorpSimpleBody body) {
        return super._findOne(body);
    }

    @PostMapping("/limitList")
    public R<List<Corp>> limitList(@RequestBody CorpLimitBody body) {
        return super._limitList(body);
    }

}
