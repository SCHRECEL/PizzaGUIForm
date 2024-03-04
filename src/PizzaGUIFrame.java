import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame {

    private JRadioButton thinCrustRadio, regularCrustRadio, deepDishCrustRadio;
    private JRadioButton smallSizeRadio, mediumSizeRadio, largeSizeRadio, superSizeRadio;
    private JCheckBox topping1, topping2, topping3, topping4, topping5, topping6;
    private JTextArea orderTextArea;

    public PizzaGUIFrame() {
        setTitle("Pizza Order System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel crustPanel = createTitledPanel("Type of Crust", createCrustPanel());
        JPanel sizePanel = createTitledPanel("Size", createSizePanel());
        JPanel toppingsPanel = createTitledPanel("Toppings", createToppingsPanel());
        JPanel orderPanel = createTitledPanel("Order Details", createOrderPanel());
        JPanel buttonPanel = createButtonPanel();
        add(crustPanel, BorderLayout.NORTH);
        add(sizePanel, BorderLayout.WEST);
        add(toppingsPanel, BorderLayout.CENTER);
        add(orderPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createCrustPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        thinCrustRadio = new JRadioButton("Thin");
        regularCrustRadio = new JRadioButton("Regular");
        deepDishCrustRadio = new JRadioButton("Deep-dish");
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinCrustRadio);
        crustGroup.add(regularCrustRadio);
        crustGroup.add(deepDishCrustRadio);
        panel.add(thinCrustRadio);
        panel.add(regularCrustRadio);
        panel.add(deepDishCrustRadio);

        return panel;
    }

    private JPanel createSizePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        smallSizeRadio = new JRadioButton("Small");
        mediumSizeRadio = new JRadioButton("Medium");
        largeSizeRadio = new JRadioButton("Large");
        superSizeRadio = new JRadioButton("Super");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallSizeRadio);
        sizeGroup.add(mediumSizeRadio);
        sizeGroup.add(largeSizeRadio);
        sizeGroup.add(superSizeRadio);
        panel.add(smallSizeRadio);
        panel.add(mediumSizeRadio);
        panel.add(largeSizeRadio);
        panel.add(superSizeRadio);

        return panel;
    }

    private JPanel createToppingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        topping1 = new JCheckBox("More Cheese");
        topping2 = new JCheckBox("Boot Leather");
        topping3 = new JCheckBox("Banana");
        topping4 = new JCheckBox("Chicken");
        topping5 = new JCheckBox("Pineapple");
        topping6 = new JCheckBox("Sausage");
        panel.add(topping1);
        panel.add(topping2);
        panel.add(topping3);
        panel.add(topping4);
        panel.add(topping5);
        panel.add(topping6);

        return panel;
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        orderTextArea = new JTextArea();
        orderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton orderButton = new JButton("Order");
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOrderButtonClick();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClearButtonClick();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleQuitButtonClick();
            }
        });
        panel.add(orderButton);
        panel.add(clearButton);
        panel.add(quitButton);
        return panel;
    }

    private JPanel createTitledPanel(String title, JPanel contentPanel) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
        panel.setLayout(new BorderLayout());
        panel.add(contentPanel, BorderLayout.CENTER);
        return panel;
    }
    private void handleOrderButtonClick() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("=========================================\n");
        orderDetails.append("Type of Crust & Size\t\tPrice\n");
        orderDetails.append("Ingredient \t\t\tPrice\n\n");

        if (thinCrustRadio.isSelected()) {
            orderDetails.append("Crust: Thin\n");
        } else if (regularCrustRadio.isSelected()) {
            orderDetails.append("Crust: Regular\n");
        } else if (deepDishCrustRadio.isSelected()) {
            orderDetails.append("Crust: Deep-dish\n");
        }
        String selectedSize = "";
        if (smallSizeRadio.isSelected()) {
            selectedSize = "Small";
        } else if (mediumSizeRadio.isSelected()) {
            selectedSize = "Medium";
        } else if (largeSizeRadio.isSelected()) {
            selectedSize = "Large";
        } else if (superSizeRadio.isSelected()) {
            selectedSize = "Super";
        }
        orderDetails.append("Size: ").append(selectedSize).append("\t\t");
        orderDetails.append("Toppings:\n");
        if (topping1.isSelected()) {
            orderDetails.append("\t- More Cheese\n");
        }
        if (topping2.isSelected()) {
            orderDetails.append("\t- Boot Leather\n");
        }
        if (topping3.isSelected()) {
            orderDetails.append("\t- Banana\n");
        }
        if (topping4.isSelected()) {
            orderDetails.append("\t- Chicken\n");
        }
        if (topping5.isSelected()) {
            orderDetails.append("\t- Pineapple\n");
        }
        if (topping6.isSelected()) {
            orderDetails.append("\t- Sausage\n");
        }
        double subTotal = calculateSubTotal();
        double tax = subTotal * 0.07;
        double total = subTotal + tax;

        orderDetails.append("\nSub-total: \t\t\t").append(String.format("$%.2f", subTotal)).append("\n");
        orderDetails.append("Tax: \t\t\t\t").append(String.format("$%.2f", tax)).append("\n");
        orderDetails.append("---------------------------------------------------------------------\n");
        orderDetails.append("Total: \t\t\t\t").append(String.format("$%.2f", total)).append("\n");
        orderDetails.append("=========================================\n");

        orderTextArea.setText(orderDetails.toString());
    }

    private void handleClearButtonClick() {
        thinCrustRadio.setSelected(false);
        regularCrustRadio.setSelected(false);
        deepDishCrustRadio.setSelected(false);
        smallSizeRadio.setSelected(false);
        mediumSizeRadio.setSelected(false);
        largeSizeRadio.setSelected(false);
        superSizeRadio.setSelected(false);
        topping1.setSelected(false);
        topping2.setSelected(false);
        topping3.setSelected(false);
        topping4.setSelected(false);
        topping5.setSelected(false);
        topping6.setSelected(false);

        orderTextArea.setText("");
    }

    private void handleQuitButtonClick() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private double calculateSubTotal() {
        double baseCost = 0.0;

        String selectedSize = "";
        if (smallSizeRadio.isSelected()) {
            selectedSize = "Small";
        } else if (mediumSizeRadio.isSelected()) {
            selectedSize = "Medium";
        } else if (largeSizeRadio.isSelected()) {
            selectedSize = "Large";
        } else if (superSizeRadio.isSelected()) {
            selectedSize = "Super";
        }

        switch (selectedSize) {
            case "Small":
                baseCost = 8.00;
                break;
            case "Medium":
                baseCost = 12.00;
                break;
            case "Large":
                baseCost = 16.00;
                break;
            case "Super":
                baseCost = 20.00;
                break;
        }
        int toppingsCount = 0;
        if (topping1.isSelected()) toppingsCount++;
        if (topping2.isSelected()) toppingsCount++;
        if (topping3.isSelected()) toppingsCount++;
        if (topping4.isSelected()) toppingsCount++;
        if (topping5.isSelected()) toppingsCount++;
        if (topping6.isSelected()) toppingsCount++;

        return baseCost + (toppingsCount * 1.00);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaGUIFrame());
    }
}
