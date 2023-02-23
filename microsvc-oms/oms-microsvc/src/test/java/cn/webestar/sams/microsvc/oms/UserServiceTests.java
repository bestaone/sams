package cn.webestar.sams.microsvc.oms;

import cn.webestar.sams.microsvc.oms.domain.User;
import cn.webestar.sams.microsvc.oms.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OmsMicrSvcStarter.class)
class UserServiceTests {

	@Autowired
	private UserService service;

	@Test
	public void CRUDTest() {

		//add
		User o = new User();
		o.setUsername("宇宙_564");
		o.setPhoneNum("13433334444");
		o.setPassword("1111");
		o.setRegtime(LocalDateTime.now());
		o.setUpdaterId(1L);
		o.setCreaterId(1L);
		service.save(o);
		Assert.notNull(o.getId(), "添加失败");

		//get
		o = service.getById(o.getId());
		Assert.notNull(o.getId(), "主键查询失败");

		//update
		o.setUsername("火星_345334");
		service.updateById(o);

		//page
		Page<User> page = new Page<>(1, 2, true);
		IPage<User> oPage = service.page(page);
		Assert.isTrue(oPage.getTotal() > 0,"分页查询失败");

		//delete
		service.removeById(o.getId());
		o = service.getById(o.getId());
		Assert.isNull(o, "删除失败");

	}
}
