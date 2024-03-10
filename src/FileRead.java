import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Map.Entry;


class Product{
    int stocks = 10;
    private int ProductID;
    private String ProductName;
    private double CP;
    private double SP;
    private int soldOut;
    private double profit;

    //Assigning values to variables;
    public Product(){}
    public Product(int productID, String productName, double cP, double sP) {
        ProductID = productID;
        ProductName = productName;
        CP = cP;
        SP = sP;
    }
    public Product(int productID, String productName, double cP, double sP,int Stocks) {
        ProductID = productID;
        ProductName = productName;
        CP = cP;
        SP = sP;
        stocks = Stocks;
        
    }
    //getter setters to assign & retrieve values
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


    /* equals(Object obj) -> boolean : This method checks that two product objects contains same values or not  if same values present 
     * It returns true otherwise it returns false
    */
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj==null || getClass()!=obj.getClass())
            return false;
        Product product = (Product)obj;
        return ProductID == product.ProductID && Double.compare(product.CP, CP)==0 && Double.compare(product.SP, SP)==0 && Objects.equals(ProductName , product.ProductName) && stocks==product.stocks;
        
    }
    /*
     * hashCode returns the hash value for the given values.
     * Generates a hash code for a sequence of input values. The hash code is generated as if all the input values were placed into an array, 
     * and that array were hashed by calling Arrays.hashCode(Object []).
     * This method is useful for implementing Object.hashCode() on objects containing multiple fields.
     */
    @Override
    public int hashCode(){
        return Objects.hash(ProductID,ProductName,CP,SP,stocks);
    }
    @Override
    public String toString(){
        return "Product{"+"ID="+ProductID+", name="+ProductName+", CP="+CP+", SP="+SP+", Stocks="+stocks+"}";
    }
}
public class FileRead {
    private static ArrayList<Product> updatedProductList(ArrayList<Product> al)
    {
        HashMap<Integer, Product> hm = new HashMap<Integer,Product>();
        HashMap<Integer, Integer> hmUpdate = new HashMap<Integer,Integer>();
        
        for(Product i: al){
        	if(hm.containsKey(i.getProductID())) {
        		hmUpdate.put(i.getProductID(), i.stocks);
        		continue;
        	}
        	hm.put(i.getProductID(), i);
        }

        for(Entry<Integer, Product> entry : hm.entrySet())
        {
        	for(Entry<Integer, Integer> entry1 : hmUpdate.entrySet())
            {
        		if(hm.containsKey(entry1.getKey()) && entry.getKey()==entry1.getKey()) {
            		entry.getValue().setStocks(entry1.getValue());
            	}
            }
        }
        return al;
    }
    /*
     * This addToFile method is used to add the contents of ArrayList to an existing file.
     * It contains file operations so it throws IOException.
     * it contains removeDuplicates method to get unique objects of Product.
     * return true if all operations done successfully
     * otherwise returns
     */
    
    private static String getFileName() {
    	Scanner s = new Scanner(System.in);
    	System.out.println("Default type of file is .csv");
    	System.out.println("Enter File Name: ");
    	String name = s.nextLine();
    	name  = name +".csv";
    	return name;
    }

    private static boolean addToFile(ArrayList<Product> al) throws IOException
    {
        String file = getFileName();
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            al = updatedProductList(al);
            al = removeDuplicates(al);

            for(Object product : al)
            {
                int id = ((Product)product).getProductID();
                String name = ((Product)product).getProductName();
                double cp = ((Product)product).getCP();
                double sp = ((Product)product).getSP();
                int stocks = ((Product)product).getStocks();

                String s = id + ","+name+","+cp+","+sp+","+stocks+"\n";
                fw.append(s);
                System.out.println("Record Written Successfully");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Updation failed");
            //e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("Updation failed");
            return false;
        }
        finally{
            if(fw!=null)
                fw.close();
        }
        return true;
    }
    /*
     * remove duplicates
     */
    private static ArrayList<Product> removeDuplicates(ArrayList<Product> al)
    {
        HashSet<Product> ts = new HashSet<>(al);
        return new ArrayList<Product>(ts);
    }
    public static void main(String[] args) {
        ArrayList<Product> al = new ArrayList<>();
        Product p = new Product(1,"Rice", 30, 50,20);
        Product p1 = new Product(2,"Sugar", 30, 40,25);
        Product p2 = new Product(1,"Rice", 30, 50,15);
        al.add(p);
        al.add(p1);
        al.add(p2);
        System.out.println(al);
        try {
            addToFile(al);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}