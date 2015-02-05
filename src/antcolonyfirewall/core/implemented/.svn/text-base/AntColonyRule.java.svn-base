/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyfirewall.core.implemented;

/**
 *
 * @author camilovera
 */
public class AntColonyRule extends Rule implements Comparable{
        
    public static final String COLUMN_SOURCE_ADDRESS = "source_address";
    public static final String COLUMN_DESTINATION_ADDRESS = "destination_address";
    public static final String COLUMN_SOURCE_PORT = "source_port";
    public static final String COLUMN_DESTINATION_PORT = "destination_port";
    public static final String COLUMN_NETWORK_ADDRESS = "network_address";

    public static final int ASCENDENT = 1;
    public static final int DESCENDENT =-1;

    public int sourceAddress;
    public int sourcePort;
    public int destinationAddress;
    public int destinationPort;
    public int order;
    private String mainColumn;

    public AntColonyRule(Rule r,String mainColumn, int order){
        super(r.getRulePosition());
        super.setAction(r.getAction());
        super.setDestinationAddress(r.getDestinationAddress());
        super.setDestinationPort(r.getDestinationPort());
        super.setSourceAddress(r.getSourceAddress());
        super.setSourcePort(r.getSourcePort());
        this.mainColumn = mainColumn;
        sourceAddress = AntColonyAlgoritm.transformAddress(getSourceAddress());
        destinationAddress = AntColonyAlgoritm.transformAddress(getDestinationAddress());
        try{
            sourcePort = Integer.parseInt(getSourcePort());
        }catch(Exception e){
            sourcePort = 0;
        }
        try{
            destinationPort = Integer.parseInt(getDestinationPort());
        }catch(Exception e){
            destinationPort = 0;
        }
        this.order = order;
    }
    
    public int getValueOfMainColumn(){
        if(mainColumn.equals(COLUMN_SOURCE_ADDRESS)){
            return sourceAddress;
        }else if(mainColumn.equals(COLUMN_SOURCE_PORT)){
            return sourcePort;
        }else if(mainColumn.equals(COLUMN_DESTINATION_ADDRESS)){
            return destinationAddress;
        }else if(mainColumn.equals(COLUMN_DESTINATION_PORT)){
            return destinationPort;
        }else if(mainColumn.equals(COLUMN_NETWORK_ADDRESS)){
            return 0; //modificar cuando sepa que es network address
        }else{
            return 0;
        }
    }

    public String getMainColumn() {
        return mainColumn;
    }

    public void setMainColumn(String mainColumn) {
        this.mainColumn = mainColumn;
    }

    public int compareTo(Object t) {
        if(t instanceof AntColonyRule){
            AntColonyRule rule = (AntColonyRule) t;
            if(mainColumn.equals(COLUMN_SOURCE_ADDRESS)){
                return (sourceAddress>rule.sourceAddress)?order:((sourceAddress<rule.sourceAddress)?-order:0);
            }
            else if(mainColumn.equals(COLUMN_DESTINATION_ADDRESS)){
                return (destinationAddress>rule.destinationAddress)?order:((destinationAddress<rule.destinationAddress)?-order:0);
            }
            else if(mainColumn.equals(COLUMN_SOURCE_PORT)){
                return (sourcePort>rule.sourcePort)?order:((sourcePort<rule.sourcePort)?-order:0);
            }
            else if(mainColumn.equals(COLUMN_DESTINATION_PORT)){
                return (destinationPort>rule.destinationPort)?order:((destinationPort<rule.destinationPort)?-order:0);
            }
            else{
                return -order;
            }
        }else{
            return -order;
        }
    }


}
