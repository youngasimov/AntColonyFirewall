/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core.implemented;

/**
 *
 * @author Asimov
 */
public class Rule {
    
    public static final String ANY = "any";
    public static final String ACCEPT = "accept";
    public static final String DROP = "drop";
    
    private String sourceAddress, sourcePort, destinationAddress, destinationPort, ruleAction;
    private int rulePosition;

    public Rule(int rulePosition) {
        this.rulePosition = rulePosition;
        
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Rule){
		Rule rule = (Rule) o;
                if(this.rulePosition == rule.getRulePosition()){
                    return true;
                }
                if(this.destinationAddress.equals(rule.getDestinationAddress()) &&
                        this.destinationPort.equals(rule.getDestinationPort()) &&
                        this.sourceAddress.equals(rule.getSourceAddress()) &&
                        this.getSourcePort().equals(rule.getSourcePort()) &&
                        this.ruleAction.equals(rule.getAction())){
                    return true;
                }
                return false;
        }
        if(o instanceof Packet){
		Packet p = (Packet) o;
		// definir caract que definen un paquete
                if(((p.getIpFuente() == null && sourceAddress == null) || p.getIpFuente().equals(sourceAddress)) || sourceAddress.equals(Rule.ANY) && 
                    ((p.getIpDestino()) == null && destinationAddress == null) || p.getIpDestino().equals(destinationAddress) || destinationAddress.equals(Rule.ANY) && 
                    ((p.getPtFuente() == null && sourcePort == null) || p.getPtFuente().equals(sourcePort)) || sourcePort.equals(Rule.ANY) &&
                    ((p.getPtDestino() == null && destinationPort == null) || p.getPtDestino().equals(destinationPort)) || destinationPort.equals(Rule.ANY)
                    ){
                return true;
            }else{
                return false;
            }
		
        }
        return false;
    }
    
    public int getRulePosition(){
        return this.rulePosition;
    }
    
    public void setSourceAddress(String sourceAddress) {
	    this.sourceAddress = sourceAddress;
    }
    public String getSourceAddress() {
	    return sourceAddress;
    }
    public void setSourcePort(String sourcePort) {
	    this.sourcePort = sourcePort;
    }
    public String getSourcePort() {
	    return sourcePort;
    }
    public void setDestinationAddress(String destinationAddress) {
	    this.destinationAddress = destinationAddress;
    }
    public String getDestinationAddress() {
	    return destinationAddress;
    }
    public void setDestinationPort(String destinationPort) {
	    this.destinationPort = destinationPort;
    }
    public String getDestinationPort() {
	    return destinationPort;
    }
    public void setAction(String ruleAction) {
	    // ACCEPT : True; DROP: False.
	    this.ruleAction = ruleAction;
    }
    public String getAction() {
	    return ruleAction;
    }
    public boolean getActionB() {
	    if (ruleAction.toLowerCase().contains(Rule.ACCEPT))
		    return true;
	    else if (ruleAction.toLowerCase().contains(Rule.DROP))
		    return false;
	    else
		    return false;
    }

}
