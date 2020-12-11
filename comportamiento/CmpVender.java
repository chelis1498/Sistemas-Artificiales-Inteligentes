/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comportamiento;

import agentes.AGvendedor;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author OGChelis
 */
public class CmpVender extends CyclicBehaviour{
    AGvendedor bsAgent;

    public CmpVender(AGvendedor a) {
        bsAgent = a;
    }


    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
        ACLMessage msg = bsAgent.receive(mt);

        if(msg != null) {
            String title = msg.getContent();
            ACLMessage reply = msg.createReply();
            Integer price = (Integer)bsAgent.getCatalogue().remove(title);
            if(price != null) {
                reply.setPerformative(ACLMessage.INFORM);
                System.out.println(title + " Vendido a: " + msg.getSender().getName());
            } else {
                reply.setPerformative(ACLMessage.FAILURE);
                reply.setContent("not-available");
            }
            bsAgent.send(reply);
        } else {
            block();
        }
    }
}
