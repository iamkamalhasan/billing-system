import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    public void fileRead(String file)
    {
        BufferedReader reader = null;
        String line=" ";
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                for(String index: row)
                {
                    System.out.print(index+"    ");
                }
                System.out.println();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                reader.close();
            }catch(IOException io){
                io.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        String file = "src\\File.csv";
        FileRead fr = new FileRead();
        fr.fileRead(file);
    }
}