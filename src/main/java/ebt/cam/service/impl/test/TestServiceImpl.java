package ebt.cam.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebt.cam.service.test.TestServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("testService")
@Transactional
public class TestServiceImpl extends CommonServiceImpl implements TestServiceI {
	
}