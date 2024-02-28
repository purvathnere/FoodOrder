
package com.myproject.foodorderapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FoodOrderApp extends JFrame implements ActionListener {

    private ArrayList<JCheckBox> foodItems;
    private JTextArea orderDetails;
    private JButton calculateButton;
    private JPanel menuPanel;
    private JScrollPane menuScrollPane;

    public FoodOrderApp() {
        // Frame initialization with title
        super("Food Ordering App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //  background color
        getContentPane().setBackground(new Color(235, 234, 232));

        // Menu Panel with ScrollPane
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(235, 234, 232)); // Set the background color

        // Adding JScrollPane to the menuPanel
        menuScrollPane = new JScrollPane(menuPanel);
        menuScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(menuScrollPane, BorderLayout.WEST);

        // Food items
        foodItems = new ArrayList<>();
        addFoodItems(); 

     
        orderDetails = new JTextArea(15, 25);
        orderDetails.setEditable(false);
        add(new JScrollPane(orderDetails), BorderLayout.CENTER);

        // Calculate Button
calculateButton = new JButton("Calculate Total");
calculateButton.addActionListener(this);

// color
calculateButton.setBackground(new Color(0,250,0)); 

calculateButton.setPreferredSize(new Dimension(50, 30)); 

add(calculateButton, BorderLayout.SOUTH);

    }

    private void addFoodItems() {
        String[] items = {
            "Pizza - $10", "Burger - $5", "Pasta - $8", "Salad - $7", "Sushi - $12", 
            "Fried Chicken - $9", "Vegan Bowl - $11", "Tacos - $6", "Steak - $15",
            "Sandwich - $4", "Bagel - $3", "Donut - $2", "Coffee - $2", "Tea - $1.5", 
            "Smoothie - $5", "Ice Cream - $4", "Cake Slice - $4", "Pie Slice - $3", 
            "Cookies - $1", "Brownie - $2"
        };

        for (String item : items) {
            JCheckBox checkBox = new JCheckBox(item);
            checkBox.setBackground(new Color(235, 234, 232)); 
            foodItems.add(checkBox);
            menuPanel.add(checkBox);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            StringBuilder details = new StringBuilder();
            int total = 0;
            for (JCheckBox item : foodItems) {
                if (item.isSelected()) {
                    String text = item.getText();
                    details.append(text).append("\n");
                    total += Integer.parseInt(text.replaceAll("[^0-9]", ""));
                }
            }
            details.append("Total: $").append(total);
            orderDetails.setText(details.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FoodOrderApp app = new FoodOrderApp();
            app.setVisible(true);
        });
    }
}
