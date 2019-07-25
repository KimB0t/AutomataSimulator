/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import _diverse.PointAndCell;
import _diverse.Neighbours;
import cells.Cell;
import cells.CellDiffusionClass;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import static _diverse.Params.RAND;

/**
 *
 * @author Karim
 */
public class AutomatonDiffusionClass extends Automaton{

    private CellDiffusionClass[][] matrix;
    
    public AutomatonDiffusionClass() {
        super();
        this.matrix = new CellDiffusionClass[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
    }
    
    public AutomatonDiffusionClass(CellDiffusionClass[][] mx) {
        super();
        this.matrix = mx;
    }
    
    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        
        // Calcul de la classe
        int cl = RAND.nextInt(getNB_CLASSES());
        
        this.matrix[i][j].setNbAgents(nb);
        this.matrix[i][j].setCouleur(getCOLOR_at(cl));
        this.matrix[i][j].setWall(wl);
        this.matrix[i][j].setClasse(cl);
    }

    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }

    @Override
    public void init_matrix() {
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                this.matrix[i][j] = new CellDiffusionClass(getP(), 0, getCOLOR_DEFAULT(), i, j, false);
            }
        }
        //applay boundaries if they are enabled
        if(isBOUNDARIESequalTo("Free")) makeBoundaries();
    }

    @Override
    public void randomConfig() {
        
        this.init_matrix();
        int rn_x, rn_y;
        int nbr_cell = (int)((getMATRIX_LENGTH()) * (getMATRIX_LENGTH()) * getDENSITY() / 100);
        
        System.out.println("nbr_cell (nb agents) = "+nbr_cell);
        reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){

            // Calcul des coordonnées
            rn_x = getRANDcoordinate();
            rn_y = getRANDcoordinate();
            
            // Créer l'agent (le dernier param c'est l'id et cet agent n'as pas besoins d'id )
            this.setAgent(rn_x, rn_y, 1, null, false);
        }
    }

    @Override
    public void step() {
        
        CellDiffusionClass[][] new_matrix_class = new CellDiffusionClass[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
        
        //For every cell calculate the next state
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                
                if(!this.matrix[i][j].isWall()){
                    //Déplacement des agents
                    //if there is an agent in this cell sa classe est forcément != -1
                    Neighbours nghbrs;
                    if (this.matrix[i][j].getNbAgents() > 0) {
                        //Depl des agents
                        nghbrs = countNeighbours(this.matrix[i][j], this.matrix[i][j].getClasse());
                        PointAndCell f = this.matrix[i][j].nextPoint(getP(), nghbrs);
                        reserve(f.point);
                        new_matrix_class[i][j] = f.cell;
    //                    if(f.cell == null) System.out.println("PPPPPPPPPPPPP");
    //                    else System.out.println("OOOOOOOOOOOOO: " + f.cell.getI());
    //                    ((CellDiffusionClass)f.cell).printCell("MMMMMMMMMM: ");
                    }
                    else{
                        CellDiffusionClass new_cell = new CellDiffusionClass(getP(), this.matrix[i][j].getNbAgents(), this.matrix[i][j].getCouleur(),
                                    this.matrix[i][j].getI(), this.matrix[i][j].getJ(), false, this.matrix[i][j].getState(), this.matrix[i][j].getDi(), this.matrix[i][j].getDj(),
                                    this.matrix[i][j].getClasse(), this.matrix[i][j].isReserved());
                        new_matrix_class[i][j] = new_cell;
                    }
                    this.matrix[i][j].printCell("11111111111111");
                    //this variables assures that waves are displayed correctly
                    //if the first vague is diplayed, other do not
                    //if the first is not, the second will and the rest will not
                    //and so on...
                    setVAGUE(false);

                    //Expantion de la Vague
                    //for all classes
    //                System.out.println("8979879879879564621322165784561");
                    for (int k = 0; k < getNB_CLASSES(); k++) {
                        //count excited neighbours for this class
                        nghbrs = countNeighbours(new_matrix_class[i][j], k);
    //                    new_matrix_class[i][j] = new_matrix_class[i][j].expandWave(nghbrs, k);
                        boolean new_VAGUE = new_matrix_class[i][j].expandWave(getP(), nghbrs, k);
                        setVAGUE(new_VAGUE);

                        this.matrix[i][j].printCell("222222222222222 ===== " + k);
                    }
                }
                else new_matrix_class[i][j] = makeWall(i, j);
            }
        }

        //For every cell check if there are additional steps to make:
        //updating agent locations and cell colors
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
//                new_matrix_class = new_matrix_class[i][j].additional_step(new_matrix_class);
//                CellDiffusionClass c = new_matrix_class[i][j];
                
                int di = new_matrix_class[i][j].getDi();
                int dj = new_matrix_class[i][j].getDj();
                
                if (di != -1 && dj != -1){
                    //moving agents
                    new_matrix_class[i][j].decreaseNbAgents(1);
                    new_matrix_class[di][dj].doStuff(getP(), new_matrix_class[i][j].getClasse());
                }
                if (new_matrix_class[i][j].getNbAgents() == 1)
                    new_matrix_class[i][j].setCouleur(getCOLOR_at(new_matrix_class[i][j].getClasse()));
                else if (new_matrix_class[i][j].getNbAgents() > 1)
                    new_matrix_class[i][j].setCouleur(getCOLOR_at(new_matrix_class[i][j].getClasse(), 255));
                else //means this class became empty, so we make it a neutral class
                    new_matrix_class[i][j].setClasse(-1);
                //always un-reserve the cell
                new_matrix_class[i][j].setReserved(false);
                new_matrix_class[i][j].setDi(-1);
                new_matrix_class[i][j].setDj(-1);
                new_matrix_class[i][j].printCell("33333333333 ");
            }
        }
        this.matrix = new_matrix_class;
        increaseNBGeneration(1);
    }
    
    @Override
    public Neighbours countNeighbours(Cell cell, int k) {
        
        Neighbours nb = new Neighbours(0, new ArrayList<>(), new ArrayList<>());

        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                
                int ii = (cell.getI() + di + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                int jj = (cell.getJ() + dj + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                
                //if this neighbours isn't me or isn't a wall
                if(!(cell.getI()==ii && cell.getJ()==jj) && !this.matrix[ii][jj].isWall()){
                    
                    //if his state is excited
//                    System.out.println("kkkkkk: " + k);
                    if (this.matrix[ii][jj].isMaxStateAtK(getP(), k)) {
                        //I count it
                        nb.increaseEFCount(1);
                        //if nb agents on this neighbour is < 2
                        // and the neighbour is not already reserved
                        // and (the neighbour is of same class or of neutral class)
                        if (this.matrix[ii][jj].getNbAgents() < 2 
                                && !this.matrix[ii][jj].isReserved()
                                && (this.matrix[ii][jj].getClasse() == k
                                    || this.matrix[ii][jj].getClasse() == -1))
                            //then I add it to neighbours set
                            nb.addFreeExcitedCells(this.matrix[ii][jj]);
                    }
                    //For PA
                    //Si cette cellule contient un agent && si sont voisin est libre
                    if (cell.getNbAgents() > 0 && this.matrix[ii][jj].getNbAgents() < 2
                            && !this.matrix[ii][jj].isReserved()
                                && (this.matrix[ii][jj].getClasse() == k
                                    || this.matrix[ii][jj].getClasse() == -1)) 
                        nb.addFreeCells(this.matrix[ii][jj]);
                }
            }
        }
        return nb;
    }
    
    public void reserve(Point p){
        this.matrix[(int)p.getX()][(int)p.getY()].setReserved(true);
    }
    
    @Override
    public void makeBoundaries(){
        
        for (int k = 0; k < getMATRIX_LENGTH(); k++) {
            this.matrix[k][0] = makeWall(k, 0);
            this.matrix[k][getMATRIX_LENGTH()-1] = makeWall(k, getMATRIX_LENGTH()-1);
            this.matrix[0][k] = makeWall(0, k);
            this.matrix[getMATRIX_LENGTH()-1][k] = makeWall(getMATRIX_LENGTH()-1, k);
        }
    }

    @Override
    public CellDiffusionClass makeWall(int i, int j) {
        return new CellDiffusionClass(getCOLOR_OBSTACLE(), i, j, true);
    }

    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellDiffusionClass(getCOLOR_OBSTACLE(), i, j, true);
    }
}
