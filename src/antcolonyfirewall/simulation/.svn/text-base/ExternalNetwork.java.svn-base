package antcolonyfirewall.simulation;

import antcolonyfirewall.core.implemented.Packet;
import antcolonyfirewall.input.ExcelListener;
import antcolonyfirewall.input.ExcelReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Asimov
 */
public class ExternalNetwork extends Network implements ExcelListener, Runnable {

    private ExcelReader reader;
    private Packet incomingPacket;
    private List<Packet> generator;
    private Random r;
    private int repeticiones;
    private int maxTimeBetweenPackets;
    private boolean ready;
    private boolean aleatorio;
    
    /**
     * simula una red de internet enviando paquetes a un firewall
     * @param file: archivo que contiene los paquetes de ejemplo que seran enviados
     * @param repeticiones: el numero de veces que se van a reenviar los paquetes originales
     */
    public ExternalNetwork(boolean aleatorio,int maxTimeBetweenPackets, int repeticiones, String file){
        reader = new ExcelReader(file);
        reader.setListener(this);
        generator = new ArrayList<Packet>();
        r = new Random();
        this.repeticiones = repeticiones;
        this.maxTimeBetweenPackets = maxTimeBetweenPackets;
        this.aleatorio = aleatorio;
        ready = false;
    }

    public void start(){
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try{
            reader.read();
            int size = generator.size();
            System.out.println("numero de paquetes de ejemplo: "+size);
            for(int i=0;i<repeticiones; i++){
                for(int j=0;j<size;j++){
                    if(aleatorio){
                        int packetId = Math.abs(r.nextInt())%generator.size();
                        getContext().getFirewall().incomingPacket(generator.get(packetId));
                        generator.remove(packetId);
                    }else{
                        getContext().getFirewall().incomingPacket(generator.get(j));
                    }
                    Thread.sleep(Math.abs(r.nextLong())%maxTimeBetweenPackets);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onReadCell(int column, int row, String data) {
        if(!ready){
            return;
        }
        if(incomingPacket == null){
            incomingPacket = new Packet();
        }
        if(column == 0){
            incomingPacket.setIpFuente(data.trim());
        }else if(column == 1){
            if(data.contains(".")){
                data = data.substring(0,data.indexOf("."));
            }
            incomingPacket.setPtFuente(data.trim());
        }else if(column == 2){
            incomingPacket.setIpDestino(data.trim());
        }else if(column == 3){
            if(data.contains(".")){
                data = data.substring(0,data.indexOf("."));
            }
            incomingPacket.setPtDestino(data.trim());
        }
    }

    public void onReadRow(int row, List<String> data){
        if(!ready){
            ready = true;
        }else{
            try{
                generator.add(incomingPacket);
                incomingPacket = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void SheetReaded(int sheetId) {
    }
}
