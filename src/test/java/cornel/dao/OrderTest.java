package cornel.dao;

import com.demai.cornel.vo.task.GetOrderListResp;
import cornel.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * @Author tfzhu
 * @Date: 2019-12-21    22:12
 */
public class OrderTest extends BaseTest {

    @Resource private OrderInfoDao orderInfoDao;

//    @Test public void insertTask() throws ParseException {
//        List<GetOrderListResp> orderListResp = orderInfoDao.getOrderInfoBySupplier("hubin.hu", 1L,null,10);
//       System.out.println("ok");
//    }

    @Test public void insertTask() throws ParseException {
        List<GetOrderListResp> orderListResp = orderInfoDao.getOrderInfoBySupplier("hubin.hu", 1L,null,10);
//        OrderInfo orderInfo = orderInfoDao.getOrderInfoByOrderId("12345678909876543");
        GetOrderListResp getOrderListResp = orderInfoDao.getOrderInfoByUserAndOrderId("hubin.hu","12345678909876543");
        List<GetOrderListResp>  getOrderListResps = orderInfoDao.getOrderInfoByOrderTypeAndUserId("hubin.hu",1L,null,10);
        System.out.println("ok");
    }



}
