/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import agentes.AGvendedor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author OGChelis
 */
public class IntfzVendedor extends JFrame{
    private AGvendedor myAgent;
    private JTextField tituloF, precioF;

    public IntfzVendedor(AGvendedor a) {
        super(a.getLocalName());

        myAgent = a;
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2));
        p.add(new JLabel("Titulo de libro:"));
        tituloF = new JTextField(15);
        p.add(tituloF);
        p.add(new JLabel("Precio:"));
        precioF = new JTextField(15);
        p.add(precioF);
        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    String title = tituloF.getText().trim();
                    String price = precioF.getText().trim();

                    myAgent.updateCatalogue(title, Integer.parseInt(price));
                    tituloF.setText("");
                    precioF.setText("");
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(IntfzVendedor.this, e,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        p = new JPanel();
        p.add(addButton);
        getContentPane().add(p, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });

        setResizable(false);
    }

    public void showGui() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;

        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setVisible(true);
    }
}
