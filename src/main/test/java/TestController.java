import com.baizhi.Application;
import com.baizhi.bean.Product;
import com.baizhi.service.ProService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestController {

    @Autowired
    private ProService proService;
    /*测试添加*/
    @Test
    public void testAdd(){
        Product pro=new Product();
        pro.setId(UUID.randomUUID().toString());
        pro.setDesctory("描述");
        pro.setAddr("北京海淀");
        pro.setDate("2012-10-11");
        pro.setName("电饭煲");
        pro.setPrice(12.5);
        proService.addPro(pro);
    }
}
