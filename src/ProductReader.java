import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;


public class ProductReader {
    public static void main(String[] args) {


        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<Product> products = new ArrayList<>();

        /*

        Here is the data file we are reading:
        000001, Bilbo, Baggins, Esq., 1060
        000002, Frodo, Baggins, Esq., 1120
        000003, Samwise, Gamgee, Esq., 1125
        000004, Peregrin, Took, Esq., 1126
        000005, Meridoc, Brandybuck, Esq., 1126

        */

        final int FIELDS_LENGTH = 4;



        try {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.println("ID#   Name      Description      Cost");
                System.out.println("======================================================");

                while ((rec = reader.readLine()) != null) {
                    String[] fields = rec.split(",");
                    if (fields.length == FIELDS_LENGTH) {
                        String ID = fields[0].trim();
                        String name = fields[1].trim();
                        String description = fields[2].trim();
                        double cost = Double.parseDouble(fields[3].trim());

                        Product product = new Product(name, description, ID, cost);
                        products.add(product);
                    } else {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(rec);
                    }
                }
                reader.close();


                for (Product product : products) {
                    System.out.printf("\n%-8s%-25s%-25s%10.2f",
                            product.getID(), product.getName(), product.getDescription(), product.getCost());
                }
            } else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
