/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import jade.core.behaviours.Behaviour;

public class Comportamiento extends Behaviour
{    
    int i = 0;
    
    public void action()
    {
        
        i++;
        System.out.println(i);
    }
    
    public boolean done()
    {
        if(i >= 100)
            return true;
        return false;
    }
}
