import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandProductSearch {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JPanel mainPanel;

    public RandProductSearch() {
        // Initialize components
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Enter Partial Product Name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Action listener for searchButton
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                searchProducts(searchTerm);
            }
        });
    }

    private void searchProducts(String searchTerm) {
        try (RandomAccessFile raf = new RandomAccessFile("products.dat", "r")) {
            boolean found = false;
            resultArea.setText("");
            while (raf.getFilePointer() < raf.length()) {
                String name = raf.readUTF();
                String description = raf.readUTF();
                String ID = raf.readUTF();
                double cost = raf.readDouble();

                if (name.contains(searchTerm)) {
                    resultArea.append("Name: " + name + ", Description: " + description + ", ID: " + ID + ", Cost: " + cost + "\n");
                    found = true;
                }
            }
            if (!found) {
                resultArea.append("No products found matching: " + searchTerm);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading file.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("RandProductSearch");
                frame.setContentPane(new RandProductSearch().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
