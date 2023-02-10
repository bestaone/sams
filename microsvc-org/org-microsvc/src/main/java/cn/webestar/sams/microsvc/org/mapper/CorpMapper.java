package cn.webestar.sams.microsvc.org.mapper;

import cn.webestar.sams.microsvc.org.domain.Corp;
import cn.webestar.sams.starter.mybatis.utils.CacheHelper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheNamespace(implementation = CacheHelper.class, eviction = CacheHelper.class)
public interface CorpMapper extends BaseMapper<Corp> {

}
