/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import _diverse.Neighbours;
import cells.CellInfluenceClass;
import _diverse.Params.Directions;
import _diverse.Prm;
import cells.Cell;

/**
 *
 * @author Karim
 */
public class AgentTurmite extends Agent{
    
    private Directions orientation = Directions.DEFAULT;
    
    /**
     *
     * @param i
     * @param j
     * @param d
     * @param p
     * @param id
     */
        
    public AgentTurmite(int i, int j, Directions d, int p, int id) {
        super(i, j, p, id);
        this.orientation = d;
    }
    
    @Override
    public AgentTurmite move(Prm param, int s){
        
        // d_: means delta -> movement -> the amout of movement to be added
        int d_i = 0;
        int d_j = 0;
        int d_Position = 0;
        Directions d_Direction = Directions.DEFAULT;
        
        if(s == 0){
            switch(this.orientation){
                case NORTH:
                    d_i = 0;
                    d_j = 1;
                    d_Direction = Directions.WEST;
                    d_Position = -1;
                    break;
                case WEST:
                    d_i = 1;
                    d_j = 0;
                    d_Direction = Directions.SOUTH;
                    d_Position = param.MATRIX_LENGTH;
                    break;
                case SOUTH:
                    d_i = 0;
                    d_j = -1;
                    d_Direction = Directions.EAST;
                    d_Position = +1;
                    break;
                case EAST:
                    d_i = -1;
                    d_j = 0;
                    d_Direction = Directions.NORTH;
                    d_Position = -param.MATRIX_LENGTH;
                    break;
                default:
                    break;
            }
        }
        if(s == 1){
            switch(this.orientation){
                case NORTH:
                    d_i = 0;
                    d_j = -1;
                    d_Direction = Directions.EAST;
                    d_Position = 1;
                    break;
                case WEST:
                    d_i = -1;
                    d_j = 0;
                    d_Direction = Directions.NORTH;
                    d_Position = -param.MATRIX_LENGTH;
                    break;
                case SOUTH:
                    d_i = 0;
                    d_j = 1;
                    d_Direction = Directions.WEST;
                    d_Position = -1;
                    break;
                case EAST:
                    d_i = 1;
                    d_j = 0;
                    d_Direction = Directions.SOUTH;
                    d_Position = param.MATRIX_LENGTH;
                    break;
                default:
                    break;
            }
        }
        
        int new_i = (this.getI() + d_i + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
        int new_j = (this.getJ() + d_j + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
        int new_Position = (this.getPosition() + d_Position + (param.MATRIX_LENGTH*param.MATRIX_LENGTH)) % (param.MATRIX_LENGTH*param.MATRIX_LENGTH);
        
        return new AgentTurmite(new_i, new_j, d_Direction, new_Position, this.getId());
    }

    @Override
    public Agent move(Prm param, Neighbours nghbrs, Cell new_cell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
