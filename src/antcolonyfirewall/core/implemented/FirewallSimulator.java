/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core.implemented;

import antcolonyfirewall.core.Firewall;
import antcolonyfirewall.core.MessageListener;
import antcolonyfirewall.core.PacketReceiver;
import antcolonyfirewall.core.ProcessListener;
import antcolonyfirewall.core.SearchRulesAlgoritm;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Asimov
 */
class FirewallSimulator implements Firewall, Runnable {

    public static final String FROM = "firewall";

    private ArrayBlockingQueue<Packet> buffer;
    private List<MessageListener> messageListeners;
    private List<ProcessListener> processListeners;
    private PacketReceiver receiver;
    private SearchRulesAlgoritm algoritm;
    private RuleList rules;
    private String defaultPolicy;
    private int processingSpeed;
    private int countEmptyBuffer;
    private int maxEmptyBufferCount;
    private boolean operative;
    private boolean ready;
    
    public FirewallSimulator(){
        messageListeners = new ArrayList<MessageListener>();
        processListeners = new ArrayList<ProcessListener>();
        operative = false;
        countEmptyBuffer = 0;
        maxEmptyBufferCount = 50;
        defaultPolicy = Rule.DROP;
    }

    public String getDefaultPolicy() {
        return defaultPolicy;
    }

    public void setDefaultPolicy(String defaultPolicy) {
        this.defaultPolicy = defaultPolicy;
    }

    public int getProcessingSpeed() {
        return processingSpeed;
    }

    public void setProcessingSpeed(int processingSpeed) {
        this.processingSpeed = processingSpeed;
    }

    public int getBufferSize(){
        return buffer.size();
    }

    public void setBufferSize(int bufferSize) {
        this.buffer = new ArrayBlockingQueue<Packet>(bufferSize);
    }

    public RuleList getRules(){
        return rules;
    }

    public void setRules(RuleList rules){
        this.rules = rules;
    }

    public void addRule(Rule rule){
        rules.add(rule);
    }

    public void setAlgoritm(SearchRulesAlgoritm algoritm) {
        this.algoritm = algoritm;
    }

    public void setPacketReceiver(PacketReceiver receiver) {
        this.receiver = receiver;
    }


    public void incomingPacket(Packet packet) {
        if(!this.getOperative()){
            return;
        }
        boolean isFull = !buffer.add(packet);
        if(isFull){
           receiver.onPacketRejected(new Result(packet, new DefaultPolicyRule(Rule.DROP),0));
        }
    }

    public void setProcessListeners(List<ProcessListener> listeners) {
        this.processListeners = listeners;
    }

    public void setMessageListeners(List<MessageListener> listeners) {
        messageListeners = listeners;
    }

    public void setReady(){
        this.ready = true;
    }

    public boolean turnOn() {
        if(!ready){
            return false;
        }
        this.setOperative(true);
        Thread t = new Thread(this);
        t.start();
        return true;
    }

    public void turnOff() {
        this.setOperative(false);

    }

    public void run() {
        if(!this.getOperative()){
            sendMessage("el firewall esta apagado");
            return;
        }
        try{
            Packet p;
            sendProcessStart();
            while(this.getOperative()){
                Thread.sleep(processingSpeed);
                if(!buffer.isEmpty()){
                    countEmptyBuffer = 0;
                    p = buffer.poll();
                    Result r = algoritm.applied(p);
                    if(r.isAccepted()){
                        receiver.onPacketAccepted(r);
                    }else{
                        receiver.onPacketRejected(r);
                    }
                }else{
                    countEmptyBuffer++;
                }
                if(countEmptyBuffer>=maxEmptyBufferCount){
                    this.turnOff();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            sendMessage("ocurrio un error en al ejecución del firewall");
            sendProcessFail(e.getMessage());
        }finally{
            sendProcessFinish();
        }
    }

    private void sendMessage(String message){
        for(MessageListener listener:messageListeners){
            listener.onMessageReceived(message, FirewallSimulator.FROM);
        }
    }

    private void sendProcessStart(){
        for(ProcessListener listener:processListeners){
            listener.onProcessStart(FirewallSimulator.FROM);
        }
    }

    private void sendProcessFinish(){
        for(ProcessListener listener:processListeners){
            listener.onProcessFinish(FirewallSimulator.FROM);
        }
    }

    private void sendProcessFail(String fail){
        for(ProcessListener listener:processListeners){
            listener.onProcessFail(FirewallSimulator.FROM, fail);
        }
    }

    private void sendTaskStart(String task){
        for(ProcessListener listener:processListeners){
            listener.onTaskStart(FirewallSimulator.FROM,task);
        }
    }

    private void sendTaskFinish(String task){
        for(ProcessListener listener:processListeners){
            listener.onTaskFinish(FirewallSimulator.FROM,task);
        }
    }

    private void sendTaskFail(String task,String fail){
        for(ProcessListener listener:processListeners){
            listener.onTaskFail(FirewallSimulator.FROM, task, fail);
        }
    }

    private synchronized void setOperative(boolean operative){
        this.operative = operative;
    }

    private synchronized boolean getOperative(){
        return this.operative;
    }
}