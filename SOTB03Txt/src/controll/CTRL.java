package controll;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CTRL {
    public String acharFeriado(String holidayName, String filePath) {
        try {
            BufferedReader ler = new BufferedReader(new FileReader(filePath));
            String linha;
            while ((linha = ler.readLine()) != null) {
                if (linha.contains(holidayName)) {
                    int dateIndex = linha.indexOf("\"date\":\"") + 8;
                    int endIndex = linha.indexOf("\"", dateIndex);
                    return linha.substring(dateIndex, endIndex);
                }
            }
            ler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Feriado não encontrado: " + holidayName);
    }
}
