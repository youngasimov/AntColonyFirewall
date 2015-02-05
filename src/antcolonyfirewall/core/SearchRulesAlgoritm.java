/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core;

import antcolonyfirewall.core.implemented.Packet;
import antcolonyfirewall.core.implemented.Result;

/**
 *
 * @author Asimov
 */
public interface SearchRulesAlgoritm {

    public Result applied(Packet packet);

}
