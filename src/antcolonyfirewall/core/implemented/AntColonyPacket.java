/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyfirewall.core.implemented;

/**
 *
 * @author camilovera
 */
public class AntColonyPacket extends Packet{
    
    private int ipFuente;
    private int ipDestino;
    private int ptFuente;
    private int ptDestino;
    private int network;
    private String mainColumn;
    
    public AntColonyPacket(Packet packet,String mainColumn){
        super();
        super.setIpFuente(packet.getIpFuente());
        super.setPtFuente(packet.getPtFuente());
        super.setIpDestino(packet.getIpDestino());
        super.setPtDestino(packet.getPtDestino());
        ipFuente = AntColonyAlgoritm.transformAddress(getIpFuente());
        ipDestino = AntColonyAlgoritm.transformAddress(getIpDestino());
        try{
            ptFuente = Integer.parseInt(getPtFuente());
        }catch(Exception e){
            ptFuente = 0;
        }
        try{
            ptDestino = Integer.parseInt(getPtDestino());
        }catch(Exception e){
            ptDestino = 0;
        }
        this.mainColumn = mainColumn;
    }

    public String getMainColumn() {
        return mainColumn;
    }
    
    public int getValueOfMainColumn(){
        if(mainColumn.equals(AntColonyRule.COLUMN_SOURCE_ADDRESS)){
            return ipFuente;
        }else if(mainColumn.equals(AntColonyRule.COLUMN_SOURCE_PORT)){
            return ptFuente;
        }else if(mainColumn.equals(AntColonyRule.COLUMN_DESTINATION_ADDRESS)){
            return ipDestino;
        }else if(mainColumn.equals(AntColonyRule.COLUMN_DESTINATION_PORT)){
            return ptDestino;
        }else if(mainColumn.equals(AntColonyRule.COLUMN_NETWORK_ADDRESS)){
            return network; //modificar cuando sepa que es network address
        }else{
            return 0;
        }
    }
    
}
