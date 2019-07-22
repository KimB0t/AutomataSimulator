/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import _diverse.AllColors;
import _diverse.Neighbours;
import _diverse.Prm;
import java.awt.Color;

/**
 *
 * @author Karim
 */
public class CellTurmites extends Cell{

    private int state = 0;
    private int position = 0;
    
    public CellTurmites(int nbA, Color c, int i, int j, boolean w, int s, int p) {
        super(nbA, c, i, j, w);
        this.state = s;
        this.position = p;
    }

    public CellTurmites() {
        super();
        this.state = -1;
        this.position = -1;
    }

    public CellTurmites(int s, int p) {
        super();
        this.state = s;
        this.position = p;
    }
    
    public CellTurmites(int nbA, Color c, int i, int j, boolean w) {
        super(nbA, c, i, j, w);
        this.state = -1;
        this.position = -1;
    }
    
    public void setState(int state) {
        this.state = state;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public int getPosition() {
        return position;
    }
    
    public void changeState(Prm param){
        this.state = 1 - this.state;
        if(state == 0)
            this.setCouleur(param.COLORS.COLOR_DEFAULT);
        else
            this.setCouleur(param.COLORS.COLOR_EXCITED);
    }

    @Override
    public Cell nextState(Prm param, Neighbours nghbrs) {
        return null;
    }
    
//    public Agent nextStateTurmite(Neighbours nghbrs) {
//        
//        if(nghbrs.cCGet(this.position) != null) {
//            ArrayList<Agent> tempConcur = nghbrs.cCGet(this.position);
//            int oldi, oldj;
//            switch(POLICY){
//                case CYCLIC:
//                    //Normalement qlq soit le nombre d'agent on prendra tjrs le 1er et le reste
//                    //on les prends pas, ils ne vont pas changer dans _ag.
//                    oldi = _ag.get(tempConcur.get(0).getId()).getI();
//                    oldj = _ag.get(tempConcur.get(0).getId()).getJ();
//                    if(!this.matrix[tempConcur.get(0).getI()][tempConcur.get(0).getJ()].isWall()){
//                        this.matrix[oldi][oldj].changeState();
//                        //get(0) pour recupèrer le 1er agent
//                        _ag.set(tempConcur.get(0).getId(), tempConcur.get(0));
//                    }
//                    break;
//                case RANDOM:
//                    //Choisir 1 aléatoirement
//                    int i = RAND.nextInt(tempConcur.size());
//                    oldi = _ag.get(tempConcur.get(i).getId()).getI();
//                    oldj = _ag.get(tempConcur.get(i).getId()).getJ();
//                    if(!this.matrix[tempConcur.get(0).getI()][tempConcur.get(0).getJ()].isWall()){
//                        this.matrix[oldi][oldj].changeState();
//                        //get(0) pour recupèrer le 1er agent
//                        _ag.set(tempConcur.get(i).getId(), tempConcur.get(i));
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        return null;
//    }
}
