package com.jrmapp.service.base;

import org.springframework.stereotype.Service;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Oct 9, 2010 2:49:26 PM
 * @类说明
 */
@Service("baseService")
/**
 Spring 2.5 中除了提供 @Component 注释外，还定义了几个拥有特殊语义的注释，它们分别是：@Repository、@Service 
和 @Controller。在目前的 Spring 版本中，这 3 个注释和 @Component 是等效的，但是从注释类的命名上，很容易看出这 
3 个注释分别和持久层、业务层和控制层（Web 层）相对应。
 */
public class BaseServiceImpl implements BaseService {
	
}
