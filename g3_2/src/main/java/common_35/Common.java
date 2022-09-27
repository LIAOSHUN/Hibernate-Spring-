package common_35;

public class Common {
	// MySQL 8之後連線URL需加上SSL與時區設定
		public final static String driver = "com.mysql.cj.jdbc.Driver";
		public final static String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
		// MySQL 8之前
		// public final static String url = "jdbc:mysql://localhost:3306/bookshop_jdbc";
		
		public final static String userid = "root";
		public final static String passwd = "Asdf1234";
}
