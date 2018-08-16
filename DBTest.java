import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class DBTest {
	
	
	public static void main(String args[]) throws SQLException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("myConn", "myStmt", "myRs");
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			
			// 3. Execute SQL query
			String s = "select first_name, last_name, email from employees LIMIT 1";
		    myRs = myStmt.executeQuery(s);
			

			// 4. Process the result set
			while (myRs.next()) {
				String username = myRs.getString("first_name");
				String password = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				driver.findElement(By.name("firstname")).sendKeys(username);
				driver.findElement(By.name("lastname")).sendKeys(password);
//				driver.findElement(By.xpath("//*[@id='reg_form_box']/div[2]")).sendKeys(email);
//				driver.findElement(By.xpath("//*[@id='password_field']/div[1]")).sendKeys("12345678");
//				driver.findElement(By.name("sex")).isSelected();
				driver.findElement(By.name("websubmit")).click();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
		}

		}
	}
}
