/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antcolonyfirewall.core;

/**
 *
 * @author Asimov
 *
 * la clase que implemente esta interfaz sera capaz de recibir notificaciones del estado actual de un proceso complejo
 */
public interface ProcessListener{

    public void onProcessStart(String process);
    public void onProcessFinish(String process);
    public void onProcessFail(String process,String fail);
    public void onTaskStart(String process, String task);
    public void onTaskFinish(String process,String task);
    public void onTaskFail(String process, String task,String fail);
}
