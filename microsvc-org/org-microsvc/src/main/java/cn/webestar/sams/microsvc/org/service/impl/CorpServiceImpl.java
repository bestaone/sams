package cn.webestar.sams.microsvc.org.service.impl;

import cn.webestar.sams.microsvc.org.domain.Corp;
import cn.webestar.sams.microsvc.org.mapper.CorpMapper;
import cn.webestar.sams.microsvc.org.service.CorpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CorpServiceImpl extends ServiceImpl<CorpMapper, Corp> implements CorpService {

}
