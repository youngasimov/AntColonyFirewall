/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.simulation;

import antcolonyfirewall.core.implemented.Rule;
import antcolonyfirewall.core.implemented.RuleList;
import antcolonyfirewall.input.ExcelListener;
import antcolonyfirewall.input.ExcelReader;
import java.util.List;

/**
 *
 * @author Asimov
 */
public class RulesReader implements ExcelListener{

    private boolean ready;
    private Rule rule;
    private RuleList rules;
    private ExcelReader excelReader;

    public RulesReader(String file){
        excelReader = new ExcelReader(file);
        excelReader.setListener(this);
        ready = false;
        rules = new RuleList();  
    }

    public RuleList getRules() {
        return rules;
    }

    public void setRules(RuleList rules) {
        this.rules = rules;
    }

    public void onReadCell(int column, int row, String data) {
        if(rule == null){
            rule = new Rule(row-1);
        }
        if(column == 0){
            rule.setSourceAddress(data);
        }else if(column == 1){
            if(data.contains(".")){
                data = data.substring(0,data.indexOf("."));
            }
            rule.setSourcePort(data);
        }else if(column == 2){
            rule.setDestinationAddress(data);
        }else if(column == 3){
            if(data.contains(".")){
                data = data.substring(0,data.indexOf("."));
            }
            rule.setDestinationPort(data);
        }else if(column == 4){
            rule.setAction(data);
        }
    }

    public void onReadRow(int row, List<String> data) {
        if(!ready){
            ready = true;
        }else if(rule != null && rule.getSourceAddress() != null && rule.getSourcePort() != null && rule.getDestinationAddress() != null && rule.getDestinationPort() != null){
            rules.add(rule);
        }
        rule = null;
    }

    public void SheetReaded(int sheetId) {
    }
    
    public void read(){
        excelReader.read();
    }
}