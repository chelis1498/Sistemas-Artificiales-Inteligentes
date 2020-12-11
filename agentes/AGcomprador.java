/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import interfaz.IntfzComprador;
import jade.core.Agent;
import comportamiento.CmpComprar;
import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import javax.swing.JOptionPane;

/**
 *
 * @author OGChelis
 */
public class AGcomprador extends Agent {
    private String titulo;
    public IntfzComprador intfz;
    private AID[] aVendedores;
    private int ticker_timer = 10000;
    private AGcomprador thisAgent = this;

    protected void setup() {
      
    intfz = new IntfzComprador(this);
    intfz.showGui();
    System.out.println("Agente Comprador: " + getAID().getName() + " listo");
    }
  
    public void comprarLibro(final String libro) {

        if(libro != null && libro.length() > 0) {
            titulo = libro;
            addBehaviour(new TickerBehaviour(this, ticker_timer) {
            @Override
            protected void onTick() {
                System.out.println("Intentando comprar: " + titulo);
                intfz.msg.setText("Intentando comprar: " + titulo);
                DFAgentDescription template = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType("book-selling");
                template.addServices(sd);

                try {
                    intfz.msg.setText("Espere un momento...");
                    DFAgentDescription[] result = DFService.search(myAgent, template);
                    String msgVendedores="";
                    aVendedores = new AID[result.length];
                    for(int i = 0; i < result.length; i++) {
                        aVendedores[i] = result[i].getName();
                        msgVendedores = msgVendedores+aVendedores[i].getName()+"/n";
                    }
                    intfz.msg.setText(msgVendedores);
                }catch(FIPAException fe) {
                    JOptionPane.showMessageDialog(intfz, fe,"Exception",JOptionPane.ERROR_MESSAGE);
                }

                myAgent.addBehaviour(new CmpComprar(thisAgent));
            }
            });
        } else {
            JOptionPane.showMessageDialog(intfz,"No hay vendedores para el libro: "+titulo,"Comprador",JOptionPane.INFORMATION_MESSAGE);
            doDelete();
        }
    }


    protected void takeDown() {
        intfz.dispose();
        System.out.println("Agente comprador " + getAID().getName() + " Terminado");
    }

    public AID[] getSellerAgents() {
        return aVendedores;
    }

    public String getBookTitle() {
        return titulo;
    }
}
