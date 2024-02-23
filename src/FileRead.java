import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.Objects;

class Product{
    int stocks = 10;
    private int ProductID;
    private String ProductName;
    private double CP;
    private double SP;
    private int soldOut;
    private double profit;

    public Product(){}
    public Product(int productID, String productName, double cP, double sP) {
        ProductID = productID;
        ProductName = productName;
        CP = cP;
        SP = sP;
    }
    public void setStocks(int stocks) { this.stocks = stocks; }
    public int getStocks() { return this.stocks; }

    public void setProductID(int ProductID) { this.ProductID = ProductID; }
    public int getProductID() { return this.ProductID; }

    public void setProductName(String ProductName) { this.ProductName = ProductName; }
    public String getProductName() { return this.ProductName; }

    public void setCP(double CP) { this.CP = CP; }
    public double getCP() { return this.CP; }

    public void setSP(double SP) { this.SP = SP; }
    public double getSP() { return this.SP; }

    public double getProfit() { 
        this.profit = (this.SP - this.CP)*this.soldOut;
        return this.profit; }

    public boolean equals(Object o)
    {
        if(this==o)
            return true;
        if(o==null || getClass()!=o.getClass())
            return false;
        Product product = (Product)o;
        return ProductID == product.ProductID && Double.compare(product.CP, CP)==0 && Double.compare(product.SP, SP)==0 && Objects.equals(ProductName , product.ProductName);
        
    }
    public int hashCode(){
        return Objects.hash(ProductID,ProductName,CP,SP);
    }
    public String toString(){
        return "Product{"+"ID="+ProductID+", name="+ProductName+", CP="+CP+", SP="+SP+"}";
    }
}
public class FileRead {
    
    private static boolean addToFile(ArrayList<Product> al) throws IOException
    {
        String file = "src\\File.csv";
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            al = removeDuplicates(al);

            for(Object product : al)
            {
                int id = ((Product)product).getProductID();
                String name = ((Product)product).getProductName();
                double cp = ((Product)product).getCP();
                double sp = ((Product)product).getSP();

                String s = id + ","+name+","+cp+","+sp+"\n";
                fw.append(s);
                System.out.println("File Written Successfully");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Updation failed");
            return false;
        } catch (IOException e) {
            System.out.println("Updation failed");
            return false;
        }
        finally{
            fw.close();
        }
        return true;
    }

    private static ArrayList<Product> removeDuplicates(ArrayList<Product> al)
    {
        HashSet<Product> ts = new HashSet<>(al);
        return new ArrayList<Product>(ts);
    }
    public static void main(String[] args) {
        ArrayList<Product> al = new ArrayList<>();
        Product p = new Product(1,"Rice", 30, 50);
        Product p1 = new Product(2,"Sugar", 30, 40);
        Product p2 = new Product(1,"Rice", 30, 50);
        al.add(p);
        al.add(p1);
        al.add(p2);
        
        try {
            addToFile(al);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}