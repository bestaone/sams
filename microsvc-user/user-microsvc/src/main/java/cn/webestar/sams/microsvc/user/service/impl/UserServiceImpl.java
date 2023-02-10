package cn.webestar.sams.microsvc.user.service.impl;

import cn.webestar.sams.microsvc.user.domain.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webestar.sams.microsvc.user.mapper.UserMapper;
import cn.webestar.sams.microsvc.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
