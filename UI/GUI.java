package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends TitleFrame{
    public final int F_Width = 1000;
    public final int F_Height = 1000;

    JPanel Panel_Border,Panel1,Panel2,Panel3;

    public GUI()
    {
        super();
        this.setBounds(0, 0, F_Width, F_Height);

        Panel_Border = new JPanel();
        Panel_Border.setLayout(new BorderLayout());

        Panel1 = new JPanel();
        Panel2 = new JPanel();
        Panel3 = new JPanel();

        Panel1.setBackground(Color.red);
        Panel1.setPreferredSize(new Dimension(100,100));

        Panel2.setBackground(Color.blue);
        Panel2.setPreferredSize(new Dimension(500,500));

        Panel3.setBackground(Color.gray);
        Panel3.setPreferredSize(new Dimension(200,200));


        Panel_Border.add(Panel1, BorderLayout.NORTH);
        Panel_Border.add(Panel2, BorderLayout.EAST);
        Panel_Border.add(Panel3, BorderLayout.WEST);


        this.add(Panel_Border);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
