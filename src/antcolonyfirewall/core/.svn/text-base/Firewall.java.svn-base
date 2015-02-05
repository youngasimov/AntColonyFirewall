/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core;

import antcolonyfirewall.core.implemented.Packet;
import antcolonyfirewall.core.implemented.Rule;

/**
 *
 * @author Asimov
 *
 * representación de un firewall
 */
public interface Firewall {

    //recibe un nuevo paquete de datos desde internet
    public void incomingPacket(Packet packet);

    public boolean turnOn();

    public void turnOff();

    public void addRule(Rule rule);

}