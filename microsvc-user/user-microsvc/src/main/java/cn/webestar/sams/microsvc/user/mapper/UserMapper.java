package cn.webestar.sams.microsvc.user.mapper;

import cn.webestar.sams.microsvc.user.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.webestar.sams.starter.mybatis.utils.CacheHelper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheNamespace(implementation = CacheHelper.class, eviction = CacheHelper.class)
public interface UserMapper extends BaseMapper<User> {

}
