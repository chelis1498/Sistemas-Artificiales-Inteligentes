package agentes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author OGChelis
 */
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class EjemploMultiAgente extends Agent 
{
    private String titulo;
    
    protected void setup()
    {
        System.out.println("Hola soy el agente comprador: " + getAID().getName());
        Object [] args = getArguments();
        
        if(args != null && args.length > 0)
        {
            titulo = (String) args [0];
            System.out.println("Vamos a comprar el libro: " + titulo);
            addBehaviour(new TickerBehaviour(this, 10000)
                    {
                        protected void onTick()
                        {
                            System.out.println("enviando peticion");
                        }
                    });
        }
        else
        {
            System.out.println("No se especifico ningun libro");
            doDelete();
        }
    }
    
    protected void takeDown()
    {
        System.out.println("Adios " + getAID().getName());
    }
    
}
