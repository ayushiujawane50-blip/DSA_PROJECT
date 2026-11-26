
import java.util.*;

class Product {
    int id, quantity;
    String name;
    double price;

    Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    void display() {
        System.out.println(id + "  " + name + "  " +
                           quantity + "  " + price);
    }
}

public class InventoryManagement {
    static ArrayList<Product> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int search(int id) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).id == id)
                return i;
        return -1;
    }

    static void add() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        if (search(id) != -1) {
            System.out.println("ID already exists");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        if (qty < 0 || price < 0) {
            System.out.println("Invalid values");
            return;
        }

        list.add(new Product(id, name, qty, price));
        System.out.println("Product Added");
    }

    static void display() {
        if (list.isEmpty()) {
            System.out.println("No products found");
            return;
        }
        for (Product p : list)
            p.display();
    }

    static void update() {
        System.out.print("Enter ID: ");
        int i = search(sc.nextInt());

        if (i == -1) {
            System.out.println("Not Found");
            return;
        }

        Product p = list.get(i);
        sc.nextLine();
        System.out.print("New Name: ");
        p.name = sc.nextLine();
        System.out.print("New Quantity: ");
        p.quantity = sc.nextInt();
        System.out.print("New Price: ");
        p.price = sc.nextDouble();

        System.out.println("Updated Successfully");
    }

    static void delete() {
        System.out.print("Enter ID: ");
        int i = search(sc.nextInt());

        if (i == -1)
            System.out.println("Not Found");
        else {
            list.remove(i);
            System.out.println("Deleted Successfully");
        }
    }

    static void sort() {
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size()-i-1; j++)
                if (list.get(j).price > list.get(j+1).price) {
                    Product temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }

        display();
    }

    static void lowStock() {
        for (Product p : list)
            if (p.quantity < 5)
                p.display();
    }

    public static void main(String[] args) {
        int ch;
        do {
            System.out.println("\n1.Add  2.Display  3.Search");
            System.out.println("4.Update  5.Delete  6.Sort");
            System.out.println("7.Low Stock  0.Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1: add(); break;
                case 2: display(); break;
                case 3:
                    System.out.print("Enter ID: ");
                    int i = search(sc.nextInt());
                    if (i == -1) System.out.println("Not Found");
                    else list.get(i).display();
                    break;
                case 4: update(); break;
                case 5: delete(); break;
                case 6: sort(); break;
                case 7: lowStock(); break;
                case 0: System.out.println("Thank You!"); break;
                default: System.out.println("Invalid Choice");
            }
        } while (ch != 0);
    }
}
