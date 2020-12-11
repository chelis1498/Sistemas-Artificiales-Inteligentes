/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import comportamiento.CmpOfertar;//
import comportamiento.CmpVender;//
import interfaz.IntfzVendedor;//
import jade.core.Agent;//
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.HashMap;//
import javax.swing.JOptionPane;//

/**
 *
 * @author OGChelis
 */
public class AGvendedor extends Agent{
    private HashMap catalogo;
    private IntfzVendedor gui;
	

    protected void setup() {
        catalogo = new HashMap();

        gui = new IntfzVendedor(this);
        gui.showGui();

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("book-selling");
        sd.setName("book-trading");
        dfd.addServices(sd);

        try {
            DFService.register(this, dfd);
        }catch(FIPAException fe) {
            JOptionPane.showMessageDialog(gui, fe);
        }
        addBehaviour(new CmpOfertar(this));
        addBehaviour(new CmpVender(this));
    }

    protected void takeDown() {
        try {
            DFService.deregister(this);
        }catch(FIPAException fe) {
            JOptionPane.showMessageDialog(gui, fe,"Exception",1);
        }
        gui.dispose();
        System.out.println("Agente vendedor: " + getAID().getName() + "Terminando");
    }

    public void updateCatalogue(final String title, final int price) {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                catalogo.put(title, price);
                JOptionPane.showMessageDialog(gui,"Libro "+title + " Agregado a: $" + price,"Vendedor",1);
            }
        });
    }

    public HashMap getCatalogue() {
        return catalogo;
    }
}
