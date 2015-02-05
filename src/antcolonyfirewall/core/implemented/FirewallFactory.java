/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core.implemented;

import antcolonyfirewall.core.Firewall;
import antcolonyfirewall.core.MessageListener;
import antcolonyfirewall.core.PacketReceiver;
import antcolonyfirewall.core.ProcessListener;
import java.util.List;

/**
 *
 * @author Asimov
 */
public class FirewallFactory {

    public static final String ANT_COLONY_ALGORITM = "ant_colony_algoritm";
    public static final String FAIR_ANT_COLONY_ALGORITM = "fair_ant_colony_algoritm";
    public static final String SECUENCIAL_ALGORITM = "secuencial_algoritm";


    public static synchronized Firewall buidFirewall(String algoritm,int buffer,int processingSpeed, RuleList initialRules, PacketReceiver receiver, List<ProcessListener> processListeners, List<MessageListener> messageListener){
        FirewallSimulator firewall = new FirewallSimulator();
        firewall.setBufferSize(buffer);
        firewall.setProcessingSpeed(processingSpeed);
        firewall.setProcessListeners(processListeners);
        firewall.setMessageListeners(messageListener);
        firewall.setPacketReceiver(receiver);
        firewall.setRules(initialRules);
        if(algoritm.equals(ANT_COLONY_ALGORITM)){
            firewall.setAlgoritm(new AntColonyAlgoritm(firewall,new AntAgentFactory(AntAgentFactory.RANDOM_AGENT)));
            firewall.setReady();
        }else if(algoritm.equals(FAIR_ANT_COLONY_ALGORITM)){
            firewall.setAlgoritm(new AntColonyAlgoritm(firewall,new AntAgentFactory(AntAgentFactory.FAIR_AGENT)));
            firewall.setReady();
        }else if(algoritm.equals(SECUENCIAL_ALGORITM)){
            firewall.setAlgoritm(new SecuancialAlgoritm(firewall));
            firewall.setReady();
        }
        return firewall;
    }
}