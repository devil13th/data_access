package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.util.TaskManager;
import com.thd.dataaccess.util.TransformModel;
import com.thd.dataaccess.util.impl.TaskManagerImpl;
import com.thd.dataaccess.util.impl.TransformModelImpl;

public class ImportByXmlTest extends TestCase{
	private TaskManager tm;
	private TransformModel tfm;
	private Map<String,Task> r;
	@Before
	public void setUp() throws Exception{
		tm = new TaskManagerImpl();
		tfm = new TransformModelImpl();
		File f = new File("E://thdsvn//java_project//data_access//xml//importXml.xml");
		InputStream is = new FileInputStream(f);
		r = tfm.transformTaskToMap(is);
	}
	@Test
	public void test_deleteme01() throws Exception{
		Task task = r.get("test_deleteme01");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
	
	@Test
	public void test_deleteme02() throws Exception{
		Task task = r.get("test_deleteme02");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
	
	@Test
	public void test_deleteme03() throws Exception{
		Task task = r.get("test_deleteme03");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
	@Test
	public void test_deleteme04() throws Exception{
		Task task = r.get("test_deleteme04");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
}
