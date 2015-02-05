/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.simulation;

import antcolonyfirewall.core.Firewall;

/**
 *
 * @author Asimov
 */
public class Simulation {

    private ExternalNetwork extNetwork;
    private InternalNetwork intNetwork;
    private Firewall firewall;

    public ExternalNetwork getExtNetwork() {
        return extNetwork;
    }

    public void setExtNetwork(ExternalNetwork extNetwork) {
        this.extNetwork = extNetwork;
    }

    public Firewall getFirewall() {
        return firewall;
    }

    public void setFirewall(Firewall firewall) {
        this.firewall = firewall;
    }

    public InternalNetwork getIntNetwork() {
        return intNetwork;
    }

    public void setIntNetwork(InternalNetwork intNetwork) {
        this.intNetwork = intNetwork;
    }

    public void start(){
        firewall.turnOn();
        intNetwork.setContext(this);
        extNetwork.setContext(this);
        extNetwork.start();
    }
}
