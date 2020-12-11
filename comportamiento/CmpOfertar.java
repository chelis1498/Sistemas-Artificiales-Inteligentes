/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comportamiento;

import agentes.AGvendedor;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import javax.swing.JOptionPane;

/**
 *
 * @author OGChelis
 */
public class CmpOfertar extends CyclicBehaviour{
    AGvendedor bsAgent;

    public CmpOfertar(AGvendedor a) {
        bsAgent = a;
    }


    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
        ACLMessage msg = bsAgent.receive(mt);

        if(msg != null) {
            String title = msg.getContent();
            ACLMessage reply = msg.createReply();

            Integer price = (Integer) bsAgent.getCatalogue().get(title);

            if(price != null) {
                reply.setPerformative(ACLMessage.PROPOSE);
                reply.setContent(String.valueOf(price.intValue()));
            } else {
                reply.setPerformative(ACLMessage.REFUSE);
                reply.setContent("not-available");
            }

            bsAgent.send(reply);
        } else {
            block();
        }
    }
}
