/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyfirewall.core.implemented;

import java.util.List;

/**
 *
 * @author camilovera
 */
public class AntAgentFactory {
    
    public static final int RANDOM_AGENT = 1;
    public static final int FAIR_AGENT = 2;
    
    private int tipo;
    
    public AntAgentFactory(int tipo){
        this.tipo = tipo;
    }
    
    
    public Agent newAgent(List<AntColonyRule> detectionUnit){
        if(tipo == RANDOM_AGENT){
            return new AntAgent(detectionUnit);
        }else if(tipo == FAIR_AGENT){
            return new FairAntAgent(detectionUnit);
        }else{
            return new AntAgent(detectionUnit);
        }
    }
    
}
