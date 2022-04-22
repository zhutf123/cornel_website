package cornel.dao;

import com.demai.cornel.util.DateFormatUtils;
import com.demai.cornel.util.JacksonUtils;
import cornel.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author tfzhu
 * @Date: 2019-11-05    15:05
 */
@Slf4j
public class TaskInfoTest extends BaseTest {
    @Resource
    private TaskInfoDao taskInfoDao;


    @Test
    public void insertTask() throws ParseException {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTitle("2000吨优质大米大连到连云港");
        taskInfo.setTaskId(UUID.randomUUID().toString());
        taskInfo.setProduct("大米");
        taskInfo.setWeight(new BigDecimal(2000));
        taskInfo.setUnitWeight("KG");
        taskInfo.setUnacceptWeight(new BigDecimal(2000));
        String s = "2019-11-08 23:59:59";
        java.util.Date d1 = DateFormatUtils.parseDateTime(s);
        taskInfo.setEndTime(DateFormatUtils.formatDateTime(new Date(System.currentTimeMillis())));
        taskInfo.setDep("大连");
        taskInfo.setArr("连云港");
        taskInfo.setDistance(new BigDecimal(600));
        //taskInfo.setUnitPrice(new BigDecimal(5.6));
        taskInfo.setLevel(1);
        List<TaskInfo.SubTaskTime> subTaskTimes = new ArrayList<>();
        TaskInfo.SubTaskTime subTaskTime = new TaskInfo.SubTaskTime();
        subTaskTime.setTime("2019-08-29 12:00-14:00");
        subTaskTime.setCount(1);
        TaskInfo.SubTaskTime subTaskTime2 = new TaskInfo.SubTaskTime();
        subTaskTime2.setTime("2019-08-29 14:00-16:00");
        subTaskTime2.setCount(1);
        subTaskTimes.add(subTaskTime2);
        subTaskTimes.add(subTaskTime);

        //taskInfo.setSubTaskTime(subTaskTimes);
        taskInfo.setSubTaskTimeString(JacksonUtils.obj2String(subTaskTimes));
        taskInfoDao.save(taskInfo);
        taskInfoDao.getTaskUnacceptWeight(taskInfo.getTaskId());
    }

}
