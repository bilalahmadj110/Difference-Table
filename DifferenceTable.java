
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.SwingUtilities.updateComponentTreeUI;

public class Test extends JFrame {
    int added = 0;
    JPanel container = new JPanel();
    JButton addXYFields = new JButton("+");
    JButton minusXYFields = new JButton("−");
    JButton resetButton = new JButton("Reset");
    JButton calculate = new JButton("Calculate");
    ArrayList<TextField> valueX = new ArrayList<>();
    ArrayList<TextField> valueY = new ArrayList<>();
    ArrayList<JLabel> entryLabels = new ArrayList<>();
    ArrayList<JLabel> otherLabels = new ArrayList<>();
    ArrayList<Integer> differenceTable = new ArrayList<>();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<JLabel> totalLabels = new ArrayList<>();

    Test() {
        calculate.setBounds(20, 100, 120, 22);
        resetButton.setBounds(150, 100, 60, 22);
        JScrollPane jsp = new JScrollPane(container);
        container.setPreferredSize(new Dimension(3000, 3000));
        container.setLayout(null);
        addEntry();
        jsp.setPreferredSize(new Dimension(12, 12));
        addXYFields.setBounds((added + 2) * 38, 39, 40, 22);
        minusXYFields.setBounds((added + 2) * 38, 64, 40, 22);
        container.add(addXYFields);
        container.add(minusXYFields);
        JLabel xValue = new JLabel("x");
        JLabel yValue = new JLabel("f(x)");
        xValue.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        yValue.setFont(new Font("Times New Roman", Font.ITALIC, 15));

        xValue.setBounds(20, 40, 50, 20);
        yValue.setBounds(20, 65, 50, 20);

        getContentPane().add(jsp);

        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        container.add(xValue);
        container.add(yValue);

        container.add(calculate);
        container.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidEntry()) {
                    clear();
                    drawDifferenceTable();
                } else
                    JOptionPane.showMessageDialog(null, "Please enter valid entries!");
            }
        });

        addXYFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
            }
        });
        minusXYFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEntry();
            }
        });

    }

    public void update() {
        try {
            updateComponentTreeUI(this);
        } catch (Exception e) {}
    }

    public boolean removeEntry() {
        if (added > 1) {
            container.remove(valueY.get(valueY.size() - 1));
            container.remove(valueX.get(valueX.size() - 1));
            container.remove(entryLabels.get(entryLabels.size() - 1));
            valueY.remove(valueY.size() - 1);
            valueX.remove(valueX.size() - 1);
            entryLabels.remove(entryLabels.size() - 1);

            added--;
            addXYFields.setBounds(((added) * 55) + 60, 39, 40, 22);
            minusXYFields.setBounds(((added) * 55) + 60, 64, 40, 22);
            update();
            return true;
        } else
            return false;

    }

    public void addEntry() {
        TextField x = new TextField();
        TextField y = new TextField();
        JLabel lbl = new JLabel("Entry " + (added + 1));
        valueX.add(x);
        valueY.add(y);
        entryLabels.add(lbl);
        x.setFont(new Font("Segeo UI Semibold", Font.PLAIN, 11));
        y.setFont(new Font("Segeo UI Semibold", Font.PLAIN, 11));

        x.setBounds((added + 1) * 55, 40, 50, 20);
        y.setBounds((added + 1) * 55, 65, 50, 20);
        lbl.setBounds((added + 1) * 55 + 10, 20, 50, 20);

        added++;

        container.add(x);
        container.add(y);
        container.add(lbl);
        addXYFields.setBounds(((added) * 55) + 60, 39, 40, 22);
        minusXYFields.setBounds(((added) * 55) + 60, 64, 40, 22);
        update();
    }

    public void showEntry() {
        JLabel xLbl = new JLabel("x");
        JLabel yLbl = new JLabel("y");
        int y = calculate.getY() + 50;
        for (int i = 0; i < valueX.size(); i++) {
            JLabel a = new JLabel(valueX.get(i).getText());
        }
    }

    public void clear() {
        for (JLabel iter : otherLabels) {
            container.remove(iter);
            update();
        }
        for (JLabel iter : totalLabels) {
            container.remove(iter);
            update();
        }
        totalLabels.clear();
        otherLabels.clear();
        update();
    }

    public void reset() {
        while (true) {
            if (removeEntry() == false)
                break;
        }
        clear();
        valueX.get(0).setText("");
        valueY.get(0).setText("");
        update();
    }

    public void drawDifferenceTable() {
        showEntry();
        int increase = 0;
        JLabel X = new JLabel("x");
        otherLabels.add(X);
        X.setBounds(50, calculate.getY() + 20, 50, 50);
        JLabel f_x = new JLabel("f(x)");
        otherLabels.add(f_x);
        f_x.setBounds(120, calculate.getY() + 20, 50, 50);
        f_x.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 15));
        X.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 15));
        container.add(X);
        container.add(f_x);
        for (int i = 0; i < valueX.size(); i++) {
            JLabel l = new JLabel("" + valueX.get(i).getText());
            l.setFont(new Font("arial", Font.PLAIN, 11));

            l.setBounds(50, calculate.getY() + 50 + increase, 50, 50);
            otherLabels.add(l);
            container.add(l);
            increase += 30;
        }
        increase = 0;
        for (int i = 0; i < valueY.size(); i++) {
            JLabel l = new JLabel("" + valueY.get(i).getText());
            l.setBounds(120, calculate.getY() + 50 + increase, 50, 50);
            l.setFont(new Font("arial", Font.PLAIN, 11));
            otherLabels.add(l);
            container.add(l);
            increase += 30;
        }
        increase = 0;
        int xIncrement = 190;
        for (int i = 1; i < valueY.size(); i++) {
            drawOrder(i, increase, xIncrement);
            increase += 30;
            xIncrement += 70;
        }

        update();

    }

    public void drawOrder(int index, int incre, int xincre) {
        JLabel l = new JLabel("Δ" + index);
        otherLabels.add(l);
        l.setBounds(xincre, calculate.getY() + 20, 50, 50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 15));
        container.add(l);
        int self = 0;
        if (index == 1) {
            for (int i = 1; i < valueY.size(); i++) {
                DecimalFormat df = new DecimalFormat("#.###");
                double val = (Double.parseDouble(valueY.get(i).getText()) -
                        Double.parseDouble(valueY.get(i - 1).getText()));
                JLabel lb = new JLabel();
                if (val > 0)
                    lb.setText("" + df.format(val));
                else
                    lb.setText("-" + Math.abs(val));
                lb.setBounds(xincre, calculate.getY() + 50 + self + 13, 50, 50);
                lb.setFont(new Font("arial", Font.PLAIN, 11));
                self += 30;
                container.add(lb);
                totalLabels.add(lb);
            }
        } else {
            self = 0;
            int totallbl = 1;
            for (int i = 1; i < index - 1; i++) {
                totallbl += valueX.size() - i;
            }


            for (int i = totallbl; i < totallbl + (valueY.size() - index); i++) {
                DecimalFormat df = new DecimalFormat("#.###");
                JLabel lk = new JLabel("" + df.format(Double.parseDouble(totalLabels.get(i).getText()) -
                        Double.parseDouble(totalLabels.get(i - 1).getText())));
                lk.setBounds(xincre, totalLabels.get(i).getY() - 17, 70, 50);
                lk.setFont(new Font("arial", Font.PLAIN, 11));
                self += 30;
                container.add(lk);
                totalLabels.add(lk);
            }

        }

    }

    public boolean isValidEntry() {
        boolean f = true;
        for (int i = 0; i < valueX.size(); i++) {
            try {
                double a = Double.parseDouble(valueX.get(i).getText());
            } catch (Exception e) {
                f = false;
            }
        }
        for (int i = 0; i < valueY.size(); i++) {
            try {
                double a = Double.parseDouble(valueY.get(i).getText());
            } catch (Exception e) {
                f = false;
            }
        }
        return f;
    }

    public static void main(String[] args) {
        Test gui = new Test();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            updateComponentTreeUI(gui);
        } catch (Exception e) { }
    }
}