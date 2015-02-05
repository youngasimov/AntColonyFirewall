/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.simulation;

import antcolonyfirewall.core.Firewall;
import antcolonyfirewall.core.MessageListener;
import antcolonyfirewall.core.ProcessListener;
import antcolonyfirewall.core.implemented.FirewallFactory;
import antcolonyfirewall.input.ConfigReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
	    try {
		ConfigReader cr = new ConfigReader(args[0]);
		RulesReader rr;
		Simulation simulation = new Simulation();
		cr.read();
		rr = new RulesReader(cr.getRulesFile());
                rr.read();
		ExternalNetwork extNetwork = new ExternalNetwork(cr.isAleatorio(),cr.getMaxTimeBetweenPackets(), cr.getRepeticiones(), cr.getExamplesFile());
		InternalNetwork intNetwork = new InternalNetwork(cr.getAlgoritm());
		List<MessageListener> messages = new ArrayList<MessageListener>();
		List<ProcessListener> process = new ArrayList<ProcessListener>();
		messages.add(intNetwork);
		process.add(intNetwork);
		Firewall firewall  = FirewallFactory.buidFirewall(cr.getAlgoritm(),cr.getBuffer() , cr.getProcessingSpeed(), rr.getRules(), intNetwork, process, messages);
		simulation.setExtNetwork(extNetwork);
		simulation.setIntNetwork(intNetwork);
		simulation.setFirewall(firewall);
		simulation.start();
	    } catch (ArrayIndexOutOfBoundsException e) {
		    System.out.println("Ingrese parámetros de simulación. Error: " + e.toString());
	    }
        
    }

}
