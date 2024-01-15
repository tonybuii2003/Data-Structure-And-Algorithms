import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {
    public static void main(String[] args) {
        Windows windows = new Windows();
    }
}

class Windows extends JFrame {
    private JPanel mailBox = new JPanel();
    private JPanel content = new JPanel();

    Windows() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mailBox.setPreferredSize(new Dimension(250, 250));
        content.setPreferredSize(new Dimension(100, 100));
        this.setSize(500, 500);
        this.mailBox.setBackground(Color.decode("#D3D3D3"));
        this.add(mailBox, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
