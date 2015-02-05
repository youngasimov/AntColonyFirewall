/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core.implemented;

import antcolonyfirewall.core.SearchRulesAlgoritm;

/**
 *
 * @author Asimov
 */

class SecuancialAlgoritm implements SearchRulesAlgoritm{

    private FirewallSimulator firewall;

    public SecuancialAlgoritm(FirewallSimulator firewall){
        this.firewall = firewall;
    }

    public Result applied(Packet packet) {
        Result r;
        RuleList rules;
        Rule rule = null;
	int index;
        int count = 0;
	rules = firewall.getRules();
	for(int i=0;i<rules.size();i++){
            rule = rules.get(i);
            count++;
            if(rule.getSourcePort().equals(packet.getPtFuente())){
                if(packet.equals(rule)){
                    count = count + 3;
                    break;
                }
            }
        }
	if (rule != null){
                r = new Result(packet,rule,count);
		return r;
        }else{	
		return new Result(packet,new DefaultPolicyRule(firewall.getDefaultPolicy()),count);
        }
    }
}
