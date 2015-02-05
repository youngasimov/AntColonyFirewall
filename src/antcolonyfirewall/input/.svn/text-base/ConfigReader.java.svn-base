/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Asimov
 */
public class ConfigReader implements Reader{

    public static final String ALGORITM = "algoritmo";
    public static final String BUFFER = "buffer";
    public static final String PROCESSING_SPEED = "processingSpeed";
    public static final String RULES_FILE = "rules";
    public static final String PACKETS_FILE = "packets";
    public static final String MAX_TIME_BETWEEN_PACKETS = "maxTimeBetweenPackets";
    public static final String REPETICIONES = "repeticiones";
    public static final String ALEATORIO = "aleatorio";

    private File file;
    private String algoritm;
    private int buffer;
    private int processingSpeed;
    private int maxTimeBetweenPackets;
    private int repeticiones;
    private boolean aleatorio;
    private String rulesFile;
    private String examplesFile;

    public ConfigReader(String file){
        this.file = new File(file);
    }

    public String getAlgoritm() {
        return algoritm;
    }

    public void setAlgoritm(String algoritm) {
        this.algoritm = algoritm;
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    public int getMaxTimeBetweenPackets() {
        return maxTimeBetweenPackets;
    }

    public void setMaxTimeBetweenPackets(int maxTimeBetweenPackets) {
        this.maxTimeBetweenPackets = maxTimeBetweenPackets;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getExamplesFile() {
        return examplesFile;
    }

    public void setExamplesFile(String examplesFile) {
        this.examplesFile = examplesFile;
    }

    public int getProcessingSpeed() {
        return processingSpeed;
    }

    public void setProcessingSpeed(int processingSpeed) {
        this.processingSpeed = processingSpeed;
    }

    public String getRulesFile() {
        return rulesFile;
    }

    public void setRulesFile(String rulesFile) {
        this.rulesFile = rulesFile;
    }

    public boolean isAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(boolean aleatorio) {
        this.aleatorio = aleatorio;
    }
    
    public void read() {
        FileReader fr = null;
        BufferedReader br = null;
        String line;
        String[] data;
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while(br.ready()){
                line = br.readLine();
                if(line==null || line.equals("")){
                    continue;
                }if(line.startsWith("*")){
                    continue;
                }if(line.contains("=")){
                    data = line.split("=");
                    if(data[0].equals(ALGORITM)){
                        algoritm = data[1];
                    }else if(data[0].equals(BUFFER)){
                        buffer = Integer.parseInt(data[1]);
                    }else if(data[0].equals(PROCESSING_SPEED)){
                        processingSpeed = Integer.parseInt(data[1]);
                    }else if(data[0].equals(RULES_FILE)){
                        rulesFile = data[1];
                    }else if(data[0].equals(PACKETS_FILE)){
                        examplesFile = data[1];
                    }else if(data[0].equals(MAX_TIME_BETWEEN_PACKETS)){
                        maxTimeBetweenPackets = Integer.parseInt(data[1]);
                    }else if(data[0].equals(REPETICIONES)){
                        repeticiones = Integer.parseInt(data[1]);
                    }else if(data[0].equals(ALEATORIO)){
                        aleatorio = (data[1].contains("1"))?true:false;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}