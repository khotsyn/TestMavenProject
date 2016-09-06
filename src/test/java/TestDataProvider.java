import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	@DataProvider
	public static Iterator<Object[]> dateForLog() {
		List<Object[]> list = new ArrayList<Object[]>();
		File file = new File("accDataCSV.txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			while(str != null) {
				String[] part = str.split(",");
				list.add(new Object[]{part[0], part[1], part[2], part[3]});
				str = br.readLine();
			}
			br.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return list.iterator();
	}
}
