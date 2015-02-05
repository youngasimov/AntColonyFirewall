/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core.implemented;

/**
 *
 * @author Asimov
 * encapsula la información relevante de un paquete de datos
 */
public class Packet {

    private String ipDestino;
    private String ipFuente;
    private String ptDestino;
    private String ptFuente;
    private String macDestino;
    private String macFuente;

    public String getPtDestino() {
	    return ptDestino;
    }
    
    public void setPtDestino(String puerto){
        this.ptDestino = puerto;
    }
    
    public String getPtFuente() {
	    return ptFuente;
    }
    
    public void setPtFuente(String puerto){
        this.ptFuente = puerto;
    }
    
    public String getIpDestino() {
        return ipDestino;
    }

    public void setIpDestino(String ipDestino) {
        this.ipDestino = ipDestino;
    }

    public String getIpFuente() {
        return ipFuente;
    }

    public void setIpFuente(String ipFuente) {
        this.ipFuente = ipFuente;
    }

    public String getMacDestino() {
        return macDestino;
    }

    public void setMacDestino(String macDestino) {
        this.macDestino = macDestino;
    }

    public String getMacFuente() {
        return macFuente;
    }

    public void setMacFuente(String macFuente) {
        this.macFuente = macFuente;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Packet){
            Packet p = (Packet)o;
            if(((ipFuente == null && p.getIpFuente() == null) || ipFuente.equals(p.getIpFuente())) && 
                    ((ipDestino == null && p.getIpDestino() == null) || ipDestino.equals(p.getIpDestino())) && 
                    ((ptFuente == null && p.getPtFuente() == null) || ptFuente.equals(p.getPtFuente())) &&
                    ((ptDestino == null && p.getPtDestino() == null) || ptDestino.equals(p.getPtDestino())) &&
                    ((macFuente == null && p.getMacFuente() == null) || macFuente.equals(p.getMacFuente())) &&
                    ((macDestino == null && p.getMacDestino() == null) || macDestino.equals(p.getMacDestino()))
                    ){
                return true;
            }else{
                return false;
            }
        }else if(o instanceof Rule){
            Rule r = (Rule)o;
            String sourceAdress = r.getSourceAddress();
            String destinationAdress = r.getDestinationAddress();
            if(sourceAdress.contains("*")){
                sourceAdress = sourceAdress.substring(0,sourceAdress.indexOf("*"));
            }
            if(destinationAdress.contains("*")){
                destinationAdress = destinationAdress.substring(0,destinationAdress.indexOf("*"));
            }
            if(((ipFuente == null && r.getSourceAddress() == null) || ipFuente.startsWith(sourceAdress) || r.getSourceAddress().equalsIgnoreCase(Rule.ANY)) && 
                    ((ipDestino == null && r.getDestinationAddress() == null) || ipDestino.startsWith(destinationAdress) || r.getDestinationAddress().equalsIgnoreCase(Rule.ANY)) && 
                    ((ptFuente == null && r.getSourcePort() == null) || ptFuente.equals(r.getSourcePort()) || r.getSourcePort().equalsIgnoreCase(Rule.ANY)) &&
                    ((ptDestino == null && r.getDestinationPort() == null) || ptDestino.equals(r.getDestinationPort()) || r.getDestinationPort().equalsIgnoreCase(Rule.ANY))
                    ){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    
}
