package weaver.interfaces.workflow.action;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 管理数据库连接对象和资源释放的类
 * @author Administrator
 *
 */
//数据迁移--附件--数据库连接
public class DBHelper {

	private final static String className = "oracle.jdbc.driver.OracleDriver";
	
	private final static String url = "jdbc:oracle:thin:@172.18.180.87:1521:SZXX";
	
	private final static String uname = "syoa";
	
	private final static String upass = "pass4DB";
	
	/**
	 * 获取数据库连接对象
	 * @return 数据库连接对象
	 */
	public static Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(className);
			conn = DriverManager.getConnection(url, uname, upass);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}

	public static void closeConn(Connection conn){
		try{
			if(conn!=null || !conn.isClosed()){
				conn.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void closeStmt(Statement stmt){
		try{
			if(stmt!=null){
				stmt.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void closePstmt(PreparedStatement pstmt){
		try{
			if(pstmt!=null){
				pstmt.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void closeCstmt(CallableStatement cstmt){
		try{
			if(cstmt!=null){
				cstmt.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void closeRs(ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}