/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.simulation;

import antcolonyfirewall.core.MessageListener;
import antcolonyfirewall.core.PacketReceiver;
import antcolonyfirewall.core.ProcessListener;
import antcolonyfirewall.core.implemented.Packet;
import antcolonyfirewall.core.implemented.Result;
import antcolonyfirewall.input.ExcelWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asimov
 */
public class InternalNetwork extends Network implements PacketReceiver, MessageListener, ProcessListener {
    
    private ExcelWriter writer;
    private List<String> row;
    private int time;
    
    
    public InternalNetwork(String algoritm){
        writer = new ExcelWriter(algoritm);
        time = 0;
    }
    
    public void onPacketRejected(Result result) {
        row = new ArrayList<String>();
        Packet packet = result.getPacket();
        time = time+result.getSteps();
        System.out.println(packet.getIpFuente()+"\t"+packet.getPtFuente()+"\t"+packet.getIpDestino()+"\t"+packet.getPtDestino()+"\trejected\ttime:"+result.getSteps());
        row.add(packet.getIpFuente());
        row.add(packet.getPtFuente());
        row.add(packet.getIpDestino());
        row.add(packet.getPtDestino());
        row.add("rejected");
        row.add(Integer.toString(result.getMatchedRule().getRulePosition()));
        row.add(Integer.toString(result.getSteps()));
        writer.addRow(row);
    }

    public void onPacketAccepted(Result result) {
        row = new ArrayList<String>();
        Packet packet = result.getPacket();
        time = time+result.getSteps();
        System.out.println(packet.getIpFuente()+"\t"+packet.getPtFuente()+"\t"+packet.getIpDestino()+"\t"+packet.getPtDestino()+"\taccepted\ttime:"+result.getSteps());
        row.add(packet.getIpFuente());
        row.add(packet.getPtFuente());
        row.add(packet.getIpDestino());
        row.add(packet.getPtDestino());
        row.add("accepted");
        row.add(Integer.toString(result.getMatchedRule().getRulePosition()));
        row.add(Integer.toString(result.getSteps()));
        writer.addRow(row);
    }

    public void onMessageReceived(String message, String from) {
        System.out.println("From "+from+" : "+message);
    }

    public void onProcessStart(String process) {
        row = new ArrayList<String>();
        row.add("ip fuente");
        row.add("puerto fuente");
        row.add("ip destino");
        row.add("puerto destino");
        row.add("estado");
        row.add("posición de regla");
        row.add("pasos de busqueda de regla");
        writer.addRow(row);
        System.out.println("Internal Network:ProcessStart");
    }

    public void onProcessFinish(String process) {
        writer.addRow(new ArrayList<String>());
        row = new ArrayList<String>();
        row.add("pasos totales de simulación");
        row.add(Integer.toString(time));
        writer.addRow(row);
        writer.write();
        System.out.println("total time: "+time);
    }

    public void onProcessFail(String process, String fail) {
        System.out.println("Internal Network:ProcessFail");
    }

    public void onTaskStart(String process, String task) {
        System.out.println("Internal Network:TaskStart");
    }

    public void onTaskFinish(String process, String task) {
        System.out.println("Internal Network:TaskFinish");
    }

    public void onTaskFail(String process, String task, String fail) {
        System.out.println("Internal Network:TaskFail");
    }

}
