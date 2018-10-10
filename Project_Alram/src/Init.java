import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Init {
	public Init() throws IOException {
		StringTokenizer token;

		try {
			BufferedReader in = new BufferedReader(new FileReader("alram"));

			String temp, tempData[][] = new String[100][100];
			int row = 0;
			while ((temp = in.readLine()) != null) {
				token = new StringTokenizer(temp, "|");

				for (int i = 0; token.hasMoreTokens(); i++) {
					tempData[row][i] = token.nextToken();
				}
				row++;
			}
			MainFrame.data = new Object[row][4];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < 3; j++) {
					MainFrame.data[i][j] = tempData[i][j];
				}
				if (tempData[i][3] == "true")
					MainFrame.data[i][3] = true;
				else
					MainFrame.data[i][3] = false;
			}
			MainFrame.row = row;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}