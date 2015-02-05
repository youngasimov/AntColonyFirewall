/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyfirewall.core.implemented;

/**
 *
 * @author camilovera
 */
public class Result {
    
    private Packet packet;
    private Rule rule;
    private int steps;
    
    public Result(){
     this(null,null,0);
    };
    
    public Result(Packet packet){
        this(packet,null,0);
    }
    
    public Result(Packet packet, int steps){
        this(packet,null,steps);
    }
    
    public Result(Packet packet,Rule rule, int steps){
        this.packet = packet;
        this.rule = rule;
        this.steps = steps;
    }
    
    public Rule getMatchedRule(){
        return rule;
    }
    
    public void setMatchedRule(Rule rule){
        this.rule = rule;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public boolean isAccepted() {
        return rule.getActionB();
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
    
    
    
}
