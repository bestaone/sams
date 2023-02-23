package cn.webestar.sams.microsvc.oms.service.impl;

import cn.webestar.sams.microsvc.oms.domain.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webestar.sams.microsvc.oms.mapper.UserMapper;
import cn.webestar.sams.microsvc.oms.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
