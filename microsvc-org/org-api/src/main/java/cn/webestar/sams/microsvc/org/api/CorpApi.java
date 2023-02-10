package cn.webestar.sams.microsvc.org.api;

import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.microsvc.org.domain.Corp;
import cn.webestar.sams.microsvc.org.dto.corp.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
@FeignClient(name="microsvc-org")
public interface CorpApi {

    @PostMapping("/api/corp/create")
    R<Corp> create(@RequestBody CorpCreateBody body);

    @PostMapping("/delete")
    R<Boolean> delete(@RequestParam("id") Long id);

    @PostMapping("/update")
    R<Corp> update(@RequestBody CorpUpdateBody body);

    @PostMapping("/api/corp/findById")
    R<Corp> findById(@RequestParam("id") Long id);

    @PostMapping("/findByIds")
    R<Map<Long, Corp>> findByIds(@RequestBody Set<Long> ids);

    @PostMapping("/page")
    R<IPage<Corp>> page(@RequestBody CorpPageBody body);

    @PostMapping("/api/user/findOne")
    R<Corp> findOne(@RequestBody CorpSimpleBody body);

    @PostMapping("/limitList")
    R<List<Corp>> limitList(@RequestBody CorpLimitBody body);

}
