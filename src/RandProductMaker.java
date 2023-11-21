import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandProductMaker {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField idField;
    private JTextField costField;
    private JButton addButton;
    private JPanel mainPanel;
    private JLabel recordCountLabel;
    private int recordCount = 0;

    public RandProductMaker() {
        // Initialize components
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2)); // Example layout

        nameField = new JTextField(20);
        descriptionField = new JTextField(20);
        idField = new JTextField(20);
        costField = new JTextField(20);
        addButton = new JButton("Add");
        recordCountLabel = new JLabel("Record Count: 0");

        // Add components to mainPanel
        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Description:"));
        mainPanel.add(descriptionField);
        mainPanel.add(new JLabel("ID:"));
        mainPanel.add(idField);
        mainPanel.add(new JLabel("Cost:"));
        mainPanel.add(costField);
        mainPanel.add(addButton);
        mainPanel.add(recordCountLabel);

        // Action listener for addButton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String id = idField.getText();
                    double cost = Double.parseDouble(costField.getText());

                    Product product = new Product(name, description, id, cost);

                    try (RandomAccessFile raf = new RandomAccessFile("products.dat", "rw")) {
                        raf.seek(raf.length());
                        raf.writeUTF(product.getName());
                        raf.writeUTF(product.getDescription());
                        raf.writeUTF(product.getID());
                        raf.writeDouble(product.getCost());
                        recordCount++;
                        recordCountLabel.setText("Record Count: " + recordCount);
                    }

                    // Clearing fields
                    nameField.setText("");
                    descriptionField.setText("");
                    idField.setText("");
                    costField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid cost. Please enter a valid number.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error writing to file.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("RandProductMaker");
                frame.setContentPane(new RandProductMaker().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
