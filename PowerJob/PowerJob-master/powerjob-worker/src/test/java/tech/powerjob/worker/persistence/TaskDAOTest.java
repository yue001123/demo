package tech.powerjob.worker.persistence;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tech.powerjob.worker.common.constants.StoreStrategy;
import tech.powerjob.worker.common.constants.TaskStatus;
import tech.powerjob.worker.core.processor.TaskResult;

import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 任务持久化接口测试
 *
 * @author tjq
 * @since 2022/10/23
 */
@Slf4j
class TaskDAOTest {

    private static TaskDAO taskDAO;

    @BeforeAll
    static void initDAO() throws Exception {

        // 1. 创建对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.initDatasource(StoreStrategy.DISK);
        taskDAO = new TaskDAOImpl(connectionFactory);

        // 2. 初始化表
        taskDAO.initTable();
    }

    @Test
    @SneakyThrows
    void testUniqueKey() {

        TaskDO taskDO = buildTaskDO("2", 200000000000000L, TaskStatus.WORKER_PROCESS_FAILED);
        boolean firstSaveRet = taskDAO.save(taskDO);
        assert firstSaveRet;
        log.info("[testUniqueKey] first save result: {}", firstSaveRet);
        assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
            taskDAO.save(taskDO);
        });
    }

    @Test
    @SneakyThrows
    void testCRUD() {
        TaskDO oneTask = buildTaskDO("1", 1L, TaskStatus.WAITING_DISPATCH);
        TaskDO twoTask = buildTaskDO("2", 1L, TaskStatus.WAITING_DISPATCH);
        TaskDO threeTask = buildTaskDO("99", 1L, TaskStatus.WAITING_DISPATCH);

        boolean batchSave = taskDAO.batchSave(Lists.newArrayList(oneTask, twoTask, threeTask));
        log.info("[testCRUD] batchSave result: {}", batchSave);
        assert batchSave;

        SimpleTaskQuery query = new SimpleTaskQuery();
        query.setInstanceId(1L);
        List<TaskDO> simpleQueryRet = taskDAO.simpleQuery(query);
        log.info("[testCRUD] simple query by instanceId's result: {}", simpleQueryRet);
        assert simpleQueryRet.size() == 3;

        SimpleTaskQuery deleteQuery = new SimpleTaskQuery();
        deleteQuery.setTaskId("99");
        deleteQuery.setInstanceId(1L);
        boolean simpleDelete = taskDAO.simpleDelete(deleteQuery);
        log.info("[testCRUD] simpleDelete result: {}", simpleDelete);
        assert simpleDelete;

        query.setQueryContent("status, result");
        List<Map<String, Object>> simpleQueryPlusRet = taskDAO.simpleQueryPlus(query);
        log.info("[testCRUD] simple query plus by instanceId's result: {}", simpleQueryPlusRet);
        assert simpleQueryPlusRet.size() == 2;
        assert simpleQueryPlusRet.get(0).get("status") != null;
        assert simpleQueryPlusRet.get(0).get("instanceId") == null;

        boolean updateToSuccessRet = taskDAO.updateTaskStatus(1L, "1", TaskStatus.WORKER_PROCESS_SUCCESS.getValue(), System.currentTimeMillis(), "UPDATE_TO_SUCCESS");
        boolean updateToFailedRet = taskDAO.updateTaskStatus(1L, "2", TaskStatus.WORKER_PROCESS_FAILED.getValue(), System.currentTimeMillis(), "UPDATE_TO_FAILED");
        assert updateToSuccessRet;
        assert updateToFailedRet;

        List<TaskResult> allTaskResult = taskDAO.getAllTaskResult(1L, 1L);
        log.info("[testCRUD] allTaskResult: {}", allTaskResult);
        assert allTaskResult.size() == 2;
    }

    private static TaskDO buildTaskDO(String taskId, Long instanceId, TaskStatus taskStatus) {
        TaskDO taskDO = new TaskDO();
        taskDO.setTaskId(taskId);
        taskDO.setInstanceId(instanceId);
        taskDO.setSubInstanceId(instanceId);
        taskDO.setTaskName("TEST_TASK");
        taskDO.setTaskContent("TEST_CONTENT".getBytes(StandardCharsets.UTF_8));
        taskDO.setAddress("127.0.0.1:10086");
        taskDO.setStatus(taskStatus.getValue());
        taskDO.setResult("SUCCESS");
        taskDO.setFailedCnt(0);
        taskDO.setLastModifiedTime(System.currentTimeMillis());
        taskDO.setLastReportTime(System.currentTimeMillis());
        taskDO.setCreatedTime(System.currentTimeMillis());
        return taskDO;
    }


}