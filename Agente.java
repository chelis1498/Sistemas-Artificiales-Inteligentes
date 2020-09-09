/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

/**
 *
 * @author OGChelis
 */
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
public class Agente extends Agent
{
    protected void setup()
    {
        /*this.addBehaviour(new Behaviour()
        {
            public void action()
            {
                System.out.println("Hola munod de agentes, yo soy el agente --> " + getAID().getName());
            }
            
            public boolean done()
            {
                return true;
            }
        });  */
        
        Comportamiento cmp = new Comportamiento();
        addBehaviour(cmp);
    }
    
}
