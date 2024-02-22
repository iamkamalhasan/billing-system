import java.util.ArrayList;

class Products {
  int productId;
  String productName;
  float buyingPrice;
  float sellingPrice;
  int quantity;

  public Products(int productId, String productName, float buyingPrice, float sellingPrice, int quantity) {
    this.productId = productId;
    this.productName = productName;
    this.buyingPrice = buyingPrice;
    this.sellingPrice = sellingPrice;
    this.quantity = quantity;
  }
}

public class Main {
  private static final ArrayList<Products> product = new ArrayList<>();
  public static void main(String[] args) {
    addProduct(1, "Sugar", 20.50f, 25.00f, 20);
    addProduct(2, "Salt", 8.75f, 12.50f, 15);
    displayProducts();
  }

  private static void addProduct(int productId, String productName, float buyingPrice, float sellingPrice, int quantity) {
    Products newProduct = new Products(productId, productName, buyingPrice, sellingPrice, quantity);
    product.add(newProduct);
  }

  private static void displayProducts() {
    System.out.println("List of Products:");
    for (Products p : product) {
      System.out.printf("Product ID: %d%n", p.productId);
      System.out.printf("Product Name: %s%n", p.productName);
      System.out.printf("Buying Price: $%s%n", p.buyingPrice);
      System.out.printf("Selling Price: $%s%n", p.sellingPrice);
      System.out.printf("Quantity: %d%n", p.quantity);
      System.out.println("---------------------------");
    }
  }
}
