/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyfirewall.core.implemented;

import antcolonyfirewall.core.SearchRulesAlgoritm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author camilovera
 */
public class AntColonyAlgoritm implements SearchRulesAlgoritm {
    
    private List<AntColonyRule> sourceAddressDetectionUnit;
    private List<AntColonyRule> destinationAddressDetectionUnit;
    private List<AntColonyRule> sourcePortDetectionUnit;
    private List<AntColonyRule> destinationPortDetectionUnit;
    private List<AntColonyRule> networkAddressDetectionUnit;
    private FirewallSimulator firewall;
    private AntAgentFactory agentFactory;
    private int order;
    
    public AntColonyAlgoritm(FirewallSimulator firewall,AntAgentFactory agentFactory){
        this.firewall = firewall;
        this.agentFactory = agentFactory;
        sourceAddressDetectionUnit = new ArrayList<AntColonyRule>();
        destinationAddressDetectionUnit = new ArrayList<AntColonyRule>();
        sourcePortDetectionUnit = new ArrayList<AntColonyRule>();
        destinationPortDetectionUnit = new ArrayList<AntColonyRule>();
        order = AntColonyRule.ASCENDENT;
        populateDetectionUnits();
    }

    public Result applied(Packet packet) {
        int totalSteps=0;
        Agent agent = agentFactory.newAgent(sourceAddressDetectionUnit);
        AntColonyPacket antPacket = new AntColonyPacket(packet,AntColonyRule.COLUMN_SOURCE_ADDRESS);
        AntColonyRule rule = agent.findRuleByPacket(antPacket);
        totalSteps = agent.getSteps();
        if(rule == null){
            agent = agentFactory.newAgent(sourcePortDetectionUnit);
            antPacket = new AntColonyPacket(packet,AntColonyRule.COLUMN_SOURCE_PORT);
            rule = agent.findRuleByPacket(antPacket);
            totalSteps = totalSteps + agent.getSteps();
        }
        if(rule == null){
            agent = agentFactory.newAgent(destinationAddressDetectionUnit);
            antPacket = new AntColonyPacket(packet,AntColonyRule.COLUMN_DESTINATION_ADDRESS);
            rule = agent.findRuleByPacket(antPacket);
            totalSteps = totalSteps + agent.getSteps();
        }
        if(rule == null){
            agent = agentFactory.newAgent(destinationPortDetectionUnit);
            antPacket = new AntColonyPacket(packet,AntColonyRule.COLUMN_DESTINATION_PORT);
            rule = agent.findRuleByPacket(antPacket);
            totalSteps = totalSteps + agent.getSteps();
        }
        if(rule == null){
            return new Result(packet, new DefaultPolicyRule(firewall.getDefaultPolicy()), totalSteps);
        }else{
            return new Result(packet, rule, totalSteps);
        }
    }
    
    
    private void populateDetectionUnits(){
        RuleList rules = firewall.getRules();
        for(Rule rule:rules){
            if(rule.getSourceAddress()!= null && !rule.getSourceAddress().equalsIgnoreCase(Rule.ANY)){
                sourceAddressDetectionUnit.add(new AntColonyRule(rule,AntColonyRule.COLUMN_SOURCE_ADDRESS,order));
            }
            if(rule.getDestinationAddress()!= null && !rule.getDestinationAddress().equalsIgnoreCase(Rule.ANY)){
                destinationAddressDetectionUnit.add(new AntColonyRule(rule,AntColonyRule.COLUMN_DESTINATION_ADDRESS,order));
            }
            if(rule.getSourcePort()!= null && !rule.getSourcePort().equalsIgnoreCase(Rule.ANY)){
                sourcePortDetectionUnit.add(new AntColonyRule(rule,AntColonyRule.COLUMN_SOURCE_PORT,order));
            }
            if(rule.getDestinationPort()!= null && !rule.getDestinationPort().equalsIgnoreCase(Rule.ANY)){
                destinationPortDetectionUnit.add(new AntColonyRule(rule,AntColonyRule.COLUMN_DESTINATION_PORT,order));
            }
            //falta agregar reglas a la unidad de deteccion de redes, cuando la entienda la haré
        }
        Collections.sort(sourceAddressDetectionUnit);
        Collections.sort(destinationAddressDetectionUnit);
        Collections.sort(sourcePortDetectionUnit);
        Collections.sort(destinationPortDetectionUnit);
    }
    
    public static int transformAddress(String address){
        int ipNumerica=0;
        if(!address.equalsIgnoreCase(Rule.ANY)){
            String[] subnetworks = address.split(".");
            int[] subnets = new int[subnetworks.length];
            for(int i=0;i<subnetworks.length;i++){
                String subnet = subnetworks[i];
                try{
                    subnets[i]=(int) (Integer.parseInt(subnet)*Math.pow(1000.0, subnetworks.length-(i+1)));
                }catch(Exception e){
                    subnets[i]=0;
                }
                ipNumerica += subnets[i];
            }
        }
        return ipNumerica;
    }
    
}
