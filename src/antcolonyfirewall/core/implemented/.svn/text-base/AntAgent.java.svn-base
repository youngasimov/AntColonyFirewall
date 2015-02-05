/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyfirewall.core.implemented;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author camilovera
 */
public class AntAgent extends Agent {
    
    private Random random;
    private List<AntColonyRule> detectionUnit;
    private List<Integer> tabuList;
    private int steps;
    private int P;
    private int N;
    private int Z;
    private int group1Z;
    private int group2Z;
    private int pheromone;
    private int energy;
    
    public AntAgent(List<AntColonyRule> detectionUnit){
        super(detectionUnit);
        random = new Random();
        this.detectionUnit = detectionUnit;
        tabuList = new ArrayList<Integer>();
        steps = 0;
        P = -1;
        N = detectionUnit.size();
        Z = 0;
    }
    
    public AntColonyRule findRuleByPacket(AntColonyPacket packet){
        if(detectionUnit.isEmpty()){
            return null;
        }
        int diference = N - P;     
        pheromone = P+1+((diference>1)?random.nextInt(diference-1):0);
        AntColonyRule rule = detectionUnit.get(pheromone);
        if(packet.getValueOfMainColumn()>rule.getValueOfMainColumn()){
            steps++;
            energy = 1;
            P = pheromone;
        }else if(packet.getValueOfMainColumn()<rule.getValueOfMainColumn()){
            steps++;
            energy = -1;
            N = pheromone;
        }else{
            steps++;
            if(packet.equals(rule)){
                steps = steps + 3;
                return rule;
            }
            energy = 0;
            Z = pheromone;
            tabuList.add(Z);
        }
        diference = N - P;
        while(energy != 0 && diference != 1){
            pheromone = P+1+((diference>1)?random.nextInt(diference-1):0);
            rule = detectionUnit.get(pheromone);
            if(packet.getValueOfMainColumn()>rule.getValueOfMainColumn()){
                steps++;
                energy = 1;
                P = pheromone;
            }else if(packet.getValueOfMainColumn()<rule.getValueOfMainColumn()){
                steps++;
                energy = -1;
                N = pheromone;
            }else{
                steps++;
                if(packet.equals(rule)){
                    steps = steps + 3;
                    return rule;
                }
                energy = 0;
                Z = pheromone;
                tabuList.add(Z);
            }
            diference = N-P;
        }
        if(energy == 0){
            group1Z = Z;
            group2Z = Z;
            diference = group1Z - P;
            while(diference != 1){
                pheromone = P+1+((diference>1)?random.nextInt(diference-1):0);
                rule = detectionUnit.get(pheromone);
                if(packet.getValueOfMainColumn()>rule.getValueOfMainColumn()){
                    steps++;
                    energy = 1;
                    P = pheromone;
                }else{
                    steps++;
                    if(packet.equals(rule)){
                        steps = steps + 3;
                        return rule;
                    }
                    energy = 0;
                    group1Z = pheromone;
                    tabuList.add(group1Z);
                }
                diference = group1Z - P;
            }
            Collections.sort(tabuList);
            for(int i=tabuList.get(0);i<=tabuList.get(tabuList.size()-1);i++){
                if(!tabuList.contains(i)){
                    steps = steps + 3;;
                    rule = detectionUnit.get(i);
                    if(packet.equals(rule)){
                        return rule;
                    }
                }
            }
            tabuList.clear();
            tabuList.add(group2Z);
            diference = N-group2Z;
            while(diference != 1){
                pheromone = group2Z+1+((diference>1)?random.nextInt(diference-1):0);
                rule = detectionUnit.get(pheromone);
                if(packet.getValueOfMainColumn()<rule.getValueOfMainColumn()){
                    steps++;
                    energy = -1;
                    N = pheromone;
                }else{
                    steps++;
                    if(packet.equals(rule)){
                        steps= steps + 3;
                        return rule;
                    }
                    energy = 0;
                    group2Z = pheromone;
                    tabuList.add(group2Z);
                }
                diference = N - group2Z ;
            }
            Collections.sort(tabuList);
            for(int i=tabuList.get(0);i<=tabuList.get(tabuList.size()-1);i++){
                if(!tabuList.contains(i)){
                    steps = steps + 3;
                    rule = detectionUnit.get(i);
                    if(packet.equals(rule)){
                        return rule;
                    }
                }
            }
        }else if((N-P) == 1){
            return null;
        }
        return null;
    }

    public int getSteps() {
        return steps;
    }
    
    
    public void restartAgent(){
        tabuList.clear();
        steps = 0;
        P = 0;
        N = detectionUnit.size()+1;
        Z = 0;
    }
    
    
    
}
