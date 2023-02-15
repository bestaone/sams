package cn.webestar.sams.microsvc.org;

import cn.webestar.sams.microsvc.org.domain.Corp;
import cn.webestar.sams.microsvc.org.service.CorpService;
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

@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OrgStarter.class)
class CorpServiceTests {

	@Autowired
	private CorpService service;

	@Test
	public void CRUDTest() {

		//add
		Corp o = new Corp();
		o.setName("宇宙_564");
		o.setAddress("13433334444");
		o.setUpdaterId(1L);
		o.setCreaterId(1L);
		service.save(o);
		Assert.notNull(o.getId(), "添加失败");

		//get
		o = service.getById(o.getId());
		Assert.notNull(o.getId(), "主键查询失败");

		//update
		o.setName("火星_345334");
		service.updateById(o);

		//page
		Page<Corp> page = new Page<>(1, 2, true);
		IPage<Corp> oPage = service.page(page);
		Assert.isTrue(oPage.getTotal() > 0,"分页查询失败");

		//delete
		service.removeById(o.getId());
		o = service.getById(o.getId());
		Assert.isNull(o, "删除失败");

	}
}
