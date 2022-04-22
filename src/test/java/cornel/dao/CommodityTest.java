package cornel.dao;

import com.demai.cornel.controller.quota.CommodityController;
import cornel.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author tfzhu
 * @Date: 2019-12-27    17:11
 */
public class CommodityTest extends BaseTest {
    @Resource
    private CommodityDao commodityDao;
    @Resource
    private CommodityService commodityService;

    @Resource
    private CommodityController commodityController;
    @Test
    public void testInsertCommodity(){
        Commodity commodity = new Commodity();
        commodity.setName("aaa");
        Map<String, String> commodityProperties=new HashMap<>();
        commodityProperties.put("q","djsa");
        commodityProperties.put("qwe","sad");
        commodity.setCommodityProperties(commodityProperties);
        commodityDao.save(commodity);
    }





}
