/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _GUInterfaces;

import automata.Automaton;
import automata.AutomatonLife;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static _diverse.Params.*;
import _diverse.Painter;
import automata.AutomatonDiffusionClass;
import automata.AutomatonDiffusionGathering;
import automata.AutomatonInfluenceClass;
import automata.AutomatonInfluenceRepulsionClass;
import automata.AutomatonTurmites;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

/**
 *
 * @author Karim
 */
public class SimulatorInterface extends javax.swing.JFrame implements Runnable {

    private Automaton ac;
    private Thread t;
    private String threadName = "AAA";
    private boolean shutdown = true;
    
    /**
     * Creates new form Main
     * @param var
     */
    public SimulatorInterface(Variante var) {
        
        initComponents();
        initParams();
        setListeners();
        
        switch(var){
            case LIFE:
                ac = new AutomatonLife();
                break;
            case TURMITES:
                ac = new AutomatonTurmites();
                break;
            case DIFFUSION_CLASSIFICATION:
                ac = new AutomatonDiffusionClass();
                break;
            case DIFFUSION_GATHERING:
                ac = new AutomatonDiffusionGathering();
                break;
            case INFLUENCE_CLASSIFICATION:
                ac = new AutomatonInfluenceClass();
                break;
            case INFLUENCE_REPULSION:
                ac = new AutomatonInfluenceRepulsionClass();
                break;
            default:
                break;
        }
        
        ac.init_matrix();
        initScreen();
        
        
    }
    
//<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    
    //Setters and Getters
    
    /** ***********************************************************************/
    /* ************** GETTERS
    /** ***********************************************************************/
    //JComboboxes
    public JComboBox getjComboBox_shape() {
        return jComboBox_shape;
    }

    public JComboBox getjComboBox_speed() {
        return jComboBox_speed;
    }

    public JComboBox getjComboBox_policy() {
        return jComboBox_policy;
    }
    
    //JPanels

    public JPanel getjPanel_screen() {
        return jPanel_screen;
    }
    
    //JTextFields

    public JTextField getjTextField_density() {
        return jTextField_density;
    }

    public JTextField getjTextField_fire() {
        return jTextField_fire;
    }

    public JTextField getjTextField_mlevel() {
        return jTextField_mlevel;
    }

    public JTextField getjTextField_obstacleLength() {
        return jTextField_obstacleLength;
    }

    public JTextField getjTextField_obstaclesNbr() {
        return jTextField_obstaclesNbr;
    }

    public JTextField getjTextField_pa() {
        return jTextField_pa;
    }
    
    //JLabels

    public JLabel getjLabel_coordinates() {
        return jLabel_coordinates;
    }

    public JLabel getjLabel_nbrGenerations() {
        return jLabel_nbrGenerations;
    }
    
    
    //JButtons

    public JButton getjButton_color() {
        return jButton_color;
    }
    
    //JCheckBoxes

    public JCheckBox getjCheckBox_obstacles() {
        return jCheckBox_obstacles;
    }

    public JCheckBox getjCheckBox_grid() {
        return jCheckBox_grid;
    }

    public JCheckBox getjCheckBox_handDrawObstacles() {
        return jCheckBox_handDrawObstacles;
    }

    public JCheckBox getjCheckBox_uncover() {
        return jCheckBox_uncover;
    }

    public JRadioButton getjRadioButton_both() {
        return jRadioButton_both;
    }

    public JRadioButton getjRadioButton_horizontal() {
        return jRadioButton_horizontal;
    }

    public JRadioButton getjRadioButton_vertical() {
        return jRadioButton_vertical;
    }
    
    
    
    
    /** ***********************************************************************/
    /* ************** SETTERS
    /** ***********************************************************************/

    //JLabels
    public void setjLabel_coordinates(String text) {
        this.jLabel_coordinates.setText(text);
    }

    public void setjLabel_nbrGenerations(String text) {
        this.jLabel_nbrGenerations.setText(text);
    }

    public void setjLabel_variante(String text) {
        this.jLabel_variante.setText(text);
    }
    
    //JButtons

    public void setjButton_start(ImageIcon imgIcn) {
        this.jButton_start.setIcon(imgIcn);
    }

    public void setjButton_color(ImageIcon imgIcn) {
        this.jButton_color.setIcon(imgIcn);
    }

    public void setjButton_ObstaclesReset(boolean state) {
        this.jButton_ObstaclesReset.setEnabled(state);
    }

    public void setjButton_okLength(boolean state) {
        this.jButton_okLength.setEnabled(state);
    }

    public void setjButton_okNumber(boolean state) {
        this.jButton_okNumber.setEnabled(state);
    }

    public void setjButton_randObstacle(boolean state) {
        this.jButton_randObstacle.setEnabled(state);
    }
    
    //JTextFields

    public void setjTextField_obstacleLength(boolean state) {
        this.jTextField_obstacleLength.setEnabled(state);
    }

    public void setjTextField_obstaclesNbr(boolean state) {
        this.jTextField_obstaclesNbr.setEnabled(state);
    }
    
    //JRadioButtons

    public void setjRadioButton_both(boolean state) {
        this.jRadioButton_both.setEnabled(state);
    }

    public void setjRadioButton_horizontal(boolean state) {
        this.jRadioButton_horizontal.setEnabled(state);
    }

    public void setjRadioButton_vertical(boolean state) {
        this.jRadioButton_vertical.setEnabled(state);
    }
    
    //JCheckBoxes

    public void setjCheckBox_handDrawObstacles(boolean state) {
        this.jCheckBox_handDrawObstacles.setEnabled(state);
    }
    
    //JPanels
    
//</editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jFrame1 = new javax.swing.JFrame();
        jColorChooser1 = new javax.swing.JColorChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel_modelParams = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_fire = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_density = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_mlevel = new javax.swing.JTextField();
        jButton_okFire = new javax.swing.JButton();
        jButton_okDensity = new javax.swing.JButton();
        jButton_okMlevel = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField_pa = new javax.swing.JTextField();
        jButton_okPA = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jComboBox_policy = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_repulsion = new javax.swing.JTextField();
        jButton_repulsion = new javax.swing.JButton();
        jPanel_screen = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton_random = new javax.swing.JButton();
        jButton_start = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_stop = new javax.swing.JButton();
        jButton_clear = new javax.swing.JButton();
        jButton_nextPlus = new javax.swing.JButton();
        jButton_next1000 = new javax.swing.JButton();
        jLabel_coordinates = new javax.swing.JLabel();
        jPanel_displayOptions = new javax.swing.JPanel();
        jCheckBox_grid = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_speed = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_shape = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton_color = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jButton_screenSave = new javax.swing.JButton();
        jPanel_obstacles = new javax.swing.JPanel();
        jTextField_obstaclesNbr = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_obstacleLength = new javax.swing.JTextField();
        jCheckBox_obstacles = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jRadioButton_vertical = new javax.swing.JRadioButton();
        jRadioButton_horizontal = new javax.swing.JRadioButton();
        jRadioButton_both = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jButton_randObstacle = new javax.swing.JButton();
        jButton_okLength = new javax.swing.JButton();
        jButton_okNumber = new javax.swing.JButton();
        jButton_ObstaclesReset = new javax.swing.JButton();
        jCheckBox_handDrawObstacles = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jCheckBox_uncover = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel_variante = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_nbrGenerations = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 41, Short.MAX_VALUE)
                    .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 42, Short.MAX_VALUE)))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 56, Short.MAX_VALUE)
                    .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 57, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Automate");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel_modelParams.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Firing rate %:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jLabel6, gridBagConstraints);

        jTextField_fire.setText("0.1");
        jTextField_fire.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jTextField_fire, gridBagConstraints);

        jLabel7.setText("Density %:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jLabel7, gridBagConstraints);

        jTextField_density.setText("10");
        jTextField_density.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jTextField_density, gridBagConstraints);

        jLabel8.setText("Mlevel:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jLabel8, gridBagConstraints);

        jTextField_mlevel.setText("2");
        jTextField_mlevel.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jTextField_mlevel, gridBagConstraints);

        jButton_okFire.setText("OK");
        jButton_okFire.setBorder(null);
        jButton_okFire.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_okFire.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton_okFire.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_okFire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okFireActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jButton_okFire, gridBagConstraints);

        jButton_okDensity.setText("OK");
        jButton_okDensity.setBorder(null);
        jButton_okDensity.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_okDensity.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton_okDensity.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_okDensity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okDensityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jButton_okDensity, gridBagConstraints);

        jButton_okMlevel.setText("OK");
        jButton_okMlevel.setBorder(null);
        jButton_okMlevel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_okMlevel.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton_okMlevel.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_okMlevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okMlevelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jButton_okMlevel, gridBagConstraints);

        jLabel16.setText("Pa :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jLabel16, gridBagConstraints);

        jTextField_pa.setText("0.0");
        jTextField_pa.setPreferredSize(new java.awt.Dimension(22, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jTextField_pa, gridBagConstraints);

        jButton_okPA.setText("Ok");
        jButton_okPA.setBorder(null);
        jButton_okPA.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton_okPA.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_okPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okPAActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jButton_okPA, gridBagConstraints);

        jLabel15.setText("Policy:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jLabel15, gridBagConstraints);

        jComboBox_policy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Synch", "Cyclic", "Random" }));
        jComboBox_policy.setSelectedIndex(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jComboBox_policy, gridBagConstraints);

        jLabel17.setText("Simulation Settings");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jLabel17, gridBagConstraints);

        jLabel1.setText("<html>Repulsion<br>rate %:</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jLabel1, gridBagConstraints);

        jTextField_repulsion.setText("0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jTextField_repulsion, gridBagConstraints);

        jButton_repulsion.setText("OK");
        jButton_repulsion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_repulsionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_modelParams.add(jButton_repulsion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(jPanel_modelParams, gridBagConstraints);

        jPanel_screen.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_screen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_screen.setMaximumSize(new java.awt.Dimension(540, 540));
        jPanel_screen.setMinimumSize(new java.awt.Dimension(540, 540));
        jPanel_screen.setPreferredSize(new java.awt.Dimension(540, 540));

        javax.swing.GroupLayout jPanel_screenLayout = new javax.swing.GroupLayout(jPanel_screen);
        jPanel_screen.setLayout(jPanel_screenLayout);
        jPanel_screenLayout.setHorizontalGroup(
            jPanel_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        jPanel_screenLayout.setVerticalGroup(
            jPanel_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.8;
        getContentPane().add(jPanel_screen, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton_random.setText("<html>Rand<br>Config</html>");
        jButton_random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_randomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton_random, gridBagConstraints);

        jButton_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play_30.png"))); // NOI18N
        jButton_start.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_startActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton_start, gridBagConstraints);

        jButton_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next_30.png"))); // NOI18N
        jButton_next.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton_next, gridBagConstraints);

        jButton_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stop_30.png"))); // NOI18N
        jButton_stop.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_stopActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton_stop, gridBagConstraints);

        jButton_clear.setText("Clear");
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jButton_clear, gridBagConstraints);

        jButton_nextPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nextPlus_30.png"))); // NOI18N
        jButton_nextPlus.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_nextPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextPlusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton_nextPlus, gridBagConstraints);

        jButton_next1000.setText("x1000");
        jButton_next1000.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_next1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_next1000ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton_next1000, gridBagConstraints);

        jLabel_coordinates.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel_coordinates.setText("<html>X=<br>Y=");
        jLabel_coordinates.setMinimumSize(new java.awt.Dimension(40, 30));
        jLabel_coordinates.setPreferredSize(new java.awt.Dimension(40, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel_coordinates, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel_displayOptions.setMinimumSize(new java.awt.Dimension(128, 0));
        jPanel_displayOptions.setLayout(new java.awt.GridBagLayout());

        jCheckBox_grid.setText("Grid");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jCheckBox_grid, gridBagConstraints);

        jLabel2.setText("Speed:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jLabel2, gridBagConstraints);

        jComboBox_speed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "x0.25", "x0.5", "x0.75", "x1", "x1.5", "x2", "x4" }));
        jComboBox_speed.setSelectedIndex(3);
        jComboBox_speed.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jComboBox_speed, gridBagConstraints);

        jLabel5.setText("Form:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jLabel5, gridBagConstraints);

        jComboBox_shape.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Oval", "Square", "Triangle" }));
        jComboBox_shape.setSelectedIndex(1);
        jComboBox_shape.setPreferredSize(new java.awt.Dimension(60, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jComboBox_shape, gridBagConstraints);

        jLabel4.setText("Color:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jLabel4, gridBagConstraints);

        jButton_color.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_color.setPreferredSize(new java.awt.Dimension(22, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jButton_color, gridBagConstraints);

        jLabel19.setText("Display Settings :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jLabel19, gridBagConstraints);

        jButton_screenSave.setText("Screensave");
        jButton_screenSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_screenSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_displayOptions.add(jButton_screenSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(jPanel_displayOptions, gridBagConstraints);

        jPanel_obstacles.setMinimumSize(new java.awt.Dimension(120, 0));
        jPanel_obstacles.setLayout(new java.awt.GridBagLayout());

        jTextField_obstaclesNbr.setText("10");
        jTextField_obstaclesNbr.setEnabled(false);
        jTextField_obstaclesNbr.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jTextField_obstaclesNbr, gridBagConstraints);

        jLabel12.setText("Length");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jLabel12, gridBagConstraints);

        jTextField_obstacleLength.setText("10");
        jTextField_obstacleLength.setEnabled(false);
        jTextField_obstacleLength.setPreferredSize(new java.awt.Dimension(30, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jTextField_obstacleLength, gridBagConstraints);

        jCheckBox_obstacles.setText("Use obstacles");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jCheckBox_obstacles, gridBagConstraints);

        jLabel11.setText("Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jLabel11, gridBagConstraints);

        buttonGroup1.add(jRadioButton_vertical);
        jRadioButton_vertical.setText("|");
        jRadioButton_vertical.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jRadioButton_vertical, gridBagConstraints);

        buttonGroup1.add(jRadioButton_horizontal);
        jRadioButton_horizontal.setEnabled(false);
        jRadioButton_horizontal.setLabel("─");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jRadioButton_horizontal, gridBagConstraints);

        buttonGroup1.add(jRadioButton_both);
        jRadioButton_both.setSelected(true);
        jRadioButton_both.setEnabled(false);
        jRadioButton_both.setLabel("┼");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jRadioButton_both, gridBagConstraints);

        jLabel13.setText("Shape");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jLabel13, gridBagConstraints);

        jButton_randObstacle.setText("Draw Random");
        jButton_randObstacle.setEnabled(false);
        jButton_randObstacle.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_randObstacle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_randObstacleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jButton_randObstacle, gridBagConstraints);

        jButton_okLength.setText("OK");
        jButton_okLength.setBorder(null);
        jButton_okLength.setEnabled(false);
        jButton_okLength.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_okLength.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_okLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okLengthActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jButton_okLength, gridBagConstraints);

        jButton_okNumber.setText("OK");
        jButton_okNumber.setBorder(null);
        jButton_okNumber.setEnabled(false);
        jButton_okNumber.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_okNumber.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_okNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okNumberActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jButton_okNumber, gridBagConstraints);

        jButton_ObstaclesReset.setText("Reset");
        jButton_ObstaclesReset.setEnabled(false);
        jButton_ObstaclesReset.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_ObstaclesReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ObstaclesResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jButton_ObstaclesReset, gridBagConstraints);

        jCheckBox_handDrawObstacles.setText("HandDrawing");
        jCheckBox_handDrawObstacles.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jCheckBox_handDrawObstacles, gridBagConstraints);

        jLabel18.setText("Obstacles");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel_obstacles.add(jLabel18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(jPanel_obstacles, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jCheckBox_uncover.setText("Uncover");
        jPanel4.add(jCheckBox_uncover, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel_variante.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel_variante.setForeground(new java.awt.Color(0, 0, 204));
        jLabel_variante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_variante.setText("Classification");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel_variante, gridBagConstraints);

        jLabel3.setText("Générations:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel_nbrGenerations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_nbrGenerations.setForeground(new java.awt.Color(0, 0, 204));
        jLabel_nbrGenerations.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel_nbrGenerations, gridBagConstraints);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        getContentPane().add(jPanel2, gridBagConstraints);

        jMenu1.setText("File");

        jMenuItem1.setText("Nouveau Jeu de la vie");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Nouvea Jeu de la guerre");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        ac.clear();
        shutdown();
        jButton_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play_30.png")));
        updateScreen();
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jButton_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_startActionPerformed
        //Start Simulation
        if (jButton_start.getIcon().toString().contains("play_30")){
            start();
            jButton_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pause_30.png")));
        }else {
            shutdown();
            jButton_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play_30.png")));
        }
    }//GEN-LAST:event_jButton_startActionPerformed

    private void jButton_randomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_randomActionPerformed
        ac.randomConfig();
        updateScreen();
    }//GEN-LAST:event_jButton_randomActionPerformed

    private void jButton_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_stopActionPerformed
        ac.reInitNBGeneration();
        shutdown();
        jButton_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play_30.png")));
    }//GEN-LAST:event_jButton_stopActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        runXSteps(1);
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_okFireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okFireActionPerformed
        ac.setLAMBDA(Float.parseFloat(jTextField_fire.getText()));
    }//GEN-LAST:event_jButton_okFireActionPerformed

    private void jButton_okDensityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okDensityActionPerformed
        ac.setDENSITY(Float.parseFloat(jTextField_density.getText()));
    }//GEN-LAST:event_jButton_okDensityActionPerformed

    private void jButton_okMlevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okMlevelActionPerformed
       ac.setMLEVEL(Integer.parseInt(jTextField_mlevel.getText()));
    }//GEN-LAST:event_jButton_okMlevelActionPerformed

    private void jButton_nextPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextPlusActionPerformed
        runXSteps(100);
    }//GEN-LAST:event_jButton_nextPlusActionPerformed

    private void jButton_randObstacleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_randObstacleActionPerformed
        
    }//GEN-LAST:event_jButton_randObstacleActionPerformed

    private void jButton_okLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okLengthActionPerformed
        ac.setOBSTACLES_LENGTH(Integer.parseInt(jTextField_obstacleLength.getText()));
    }//GEN-LAST:event_jButton_okLengthActionPerformed

    private void jButton_okNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okNumberActionPerformed
        ac.setOBSTACLES_NBR(Integer.parseInt(jTextField_obstaclesNbr.getText()));
    }//GEN-LAST:event_jButton_okNumberActionPerformed

    private void jButton_ObstaclesResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ObstaclesResetActionPerformed
        
    }//GEN-LAST:event_jButton_ObstaclesResetActionPerformed

    private void jButton_next1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_next1000ActionPerformed
        runXSteps(1000);
    }//GEN-LAST:event_jButton_next1000ActionPerformed

    private void jButton_okPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okPAActionPerformed
        ac.setPA(Double.valueOf(jTextField_pa.getText()));
    }//GEN-LAST:event_jButton_okPAActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        shutdown();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton_repulsionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_repulsionActionPerformed
        ac.setREPULSION(Double.valueOf(jTextField_repulsion.getText()));
    }//GEN-LAST:event_jButton_repulsionActionPerformed

    private void jButton_screenSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_screenSaveActionPerformed
        saveImage();
    }//GEN-LAST:event_jButton_screenSaveActionPerformed
    
    private void initParams(){
        
        MAIN_COEF_SPEED = Float.parseFloat(this.getjComboBox_speed().getSelectedItem().toString().substring(1));
        
        MAIN_PANEL_LENGTH = (int)this.getjPanel_screen().getPreferredSize().getWidth();
        
        MAIN_LAMBDA = Double.valueOf(this.jTextField_fire.getText());
        MAIN_DENSITY = Double.valueOf(this.jTextField_density.getText());
        MAIN_MLEVEL = Integer.valueOf(this.jTextField_mlevel.getText());
        MAIN_OBSTACLES_LENGTH = Integer.valueOf(this.jTextField_obstacleLength.getText());
        MAIN_OBSTACLES_NBR = Integer.valueOf(this.jTextField_obstaclesNbr.getText());
        MAIN_PA = Double.valueOf(this.jTextField_pa.getText());
        MAIN_REPULSION = Double.valueOf(this.jTextField_repulsion.getText());
        
        switch(this.getjComboBox_policy().getSelectedItem().toString()){
            case "Synch":
                MAIN_POLICY = Policy.SYNCHRO;
                break;
            case "Cyclic":
                MAIN_POLICY = Policy.CYCLIC;
                break;
            case "Random":
                MAIN_POLICY = Policy.RANDOM;
                break;
            default: 
                MAIN_POLICY = Policy.DEFAULT;
                break;
        }
    }
    
    private void initScreen(){
        
        jPanel_screen = new Painter(ac);
        
        jPanel_screen.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_screen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_screen.setMaximumSize(new java.awt.Dimension(540, 540));
        jPanel_screen.setMinimumSize(new java.awt.Dimension(540, 540));
        jPanel_screen.setPreferredSize(new java.awt.Dimension(540, 540));

        javax.swing.GroupLayout jPanel_screenLayout = new javax.swing.GroupLayout(jPanel_screen);
        jPanel_screen.setLayout(jPanel_screenLayout);
        jPanel_screenLayout.setHorizontalGroup(
            jPanel_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        jPanel_screenLayout.setVerticalGroup(
            jPanel_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        java.awt.GridBagConstraints gridBagConstraints;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.8;
        getContentPane().add(jPanel_screen, gridBagConstraints);
        
        JPanel jp = jPanel_screen;
        jp.setEnabled(false);
        //To display coordiantes on the screen
        jp.addMouseMotionListener(new MouseAdapter() {
            //To display coordiantes on the screen
            @Override
            public void mouseMoved(MouseEvent e){
                jLabel_coordinates.setText("<html>X="+e.getX()/ac.getCELL_DIM()
                        +"<br>Y="+e.getY()/ac.getCELL_DIM()+"</html>");
            }
        });
        
//        jPanel_screen.setBackground(Color.WHITE);
//        jPanel_screen.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                int i = e.getX()/ac.getCELL_DIM();
//                int j = e.getY()/ac.getCELL_DIM();
//                if(e.getX()>=0 && e.getX()<ac.getPANEL_LENGTH() && 
//                        e.getY()>=0 && e.getY()<ac.getPANEL_LENGTH()){
//
//                    //DRAW
//                    ac.setAgent(i, j, 1, ac.getCOLOR_AGENT1(), false);
//                    repaint();
//                }
//            }
//
////            //To display coordiantes on the screen
////            @Override
////            public void mouseMoved(MouseEvent e){
////                jLabel_coordinates.setText("<html>X="+e.getX()+"<br>Y="+e.getY()+"</html>");
////            }
//
//        });
//        jPanel_screen.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int i = e.getX()/ac.getCELL_DIM();
//                int j = e.getY()/ac.getCELL_DIM();
//                if(e.getX()>=0 && e.getX()<ac.getPANEL_LENGTH() && 
//                        e.getY()>=0 && e.getY()<ac.getPANEL_LENGTH()){
//
//                    //DRawing agents
//                    ac.setAgent(i, j, 1, ac.getCOLOR_AGENT1(), false);
//                    repaint();
//                }
//            }
//        });
    }
    
    private void updateScreen(){
        
        ((Painter)jPanel_screen).setAutomaton(ac);
        jPanel_screen.repaint();
    }
    
    private void runXSteps(int x){
               
        if (isRunning()) {
            shutdown();
            try {
                Thread.sleep(40);
                for(int i=0; i<x; i++) ac.step();
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulatorInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            start();
        }
        else for(int i=0; i<x; i++) ac.step();
        updateScreen();
    }
    
    //Runnable
    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            while (!shutdown) {
                
                ac.step();
                jPanel_screen.repaint();
                Thread.sleep(ac.sleepTime());
                jLabel_nbrGenerations.setText(ac.stringNBGeneration());
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
//        Thread.currentThread().interrupt();
        System.out.println("Thread " +  threadName + " exiting.");
        
    }
   
    public void start () {
        System.out.println("Starting " +  threadName );
        shutdown = false;
        t = new Thread (this, threadName);
        t.start ();
    }
   
    public void shutdown() {
        shutdown = true;
    }
    
    public boolean isRunning() {
        return !shutdown;
    }
    
    private void setListeners(){
        
//        BufferedImage colorSample = new BufferedImage(
//            16,16,BufferedImage.TYPE_INT_RGB);
        Dimension size = jButton_color.getSize();
        BufferedImage colorSample = new BufferedImage(
            size.width/2,size.height/2,BufferedImage.TYPE_INT_RGB);
        jButton_color.setIcon(new ImageIcon(colorSample));
        
        //When speed is changed
        jComboBox_speed.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ac.setCOEF_SPEED(Float.parseFloat(jComboBox_speed.getSelectedItem().toString().substring(1)));
            }
        });
        
        //when shape is changed
        jComboBox_shape.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ac.setSHAPE(jComboBox_shape.getSelectedItem().toString());
            }
        });
        
        //Color chooser to change color
        jButton_color.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Color c = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
                if (c!=null) {
                    ac.setCOLOR_AGENT1(c);
                    Graphics2D g = colorSample.createGraphics();
                    g.setColor(ac.getCOLOR_AGENT1());
                    g.fillRect(0, 0, colorSample.getWidth(), colorSample.getHeight());
                    g.dispose();
                    jButton_color.setIcon(new ImageIcon(colorSample));
                    jButton_color.repaint();
                    System.out.println("couleur: " + c);
                }
            }
        });
        
        //When obstacles are checked/unchecked
        jCheckBox_obstacles.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    for(Component c : jPanel_obstacles.getComponents()){
                        if(c != jCheckBox_obstacles)
                            c.setEnabled(true);
                    }
//                    OBSTACLES = true;
//                    _ac.setObstacles();
                }
                else {
                    for(Component c : jPanel_obstacles.getComponents()){
                        if(c != jCheckBox_obstacles)
                            c.setEnabled(false);
                    }
//                    OBSTACLES = false;
//                    _ac.removeObstacles();
                }
            }
        });
        
        jCheckBox_grid.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    ac.setGRID(true);
                else
                    ac.setGRID(false);
            }
        });
        
        jCheckBox_handDrawObstacles.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    ac.setHAND_DRAW_OBSTACLES(true);
                else
                    ac.setHAND_DRAW_OBSTACLES(false);
            }
        });
        
        //When policy is changed
        jComboBox_policy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch(jComboBox_policy.getSelectedItem().toString()){
                    case "Synch":
                        ac.setPOLICY(Policy.SYNCHRO);
                        break;
                    case "Cyclic":
                        ac.setPOLICY(Policy.CYCLIC);
                        break;
                    case "Random":
                        ac.setPOLICY(Policy.RANDOM);
                        break;
                    default: 
                        ac.setPOLICY(Policy.DEFAULT);
                        break;
                }
            }
        });
        
        jCheckBox_uncover.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    ac.setUNCOVER(true);
                else
                    ac.setUNCOVER(false);
            }
        });
        
    }
    
    private void saveImage(){
        BufferedImage imagebuf=null;
        try {
            imagebuf = new Robot().createScreenCapture(jPanel_screen.getBounds());
        } catch (AWTException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }  
         Graphics2D graphics2D = imagebuf.createGraphics();
         jPanel_screen.paint(graphics2D);
         try {
            JFileChooser chooser = new JFileChooser(); 
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choisir l'emplacement pour sauvegarder");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // disable the "All files" option.
            chooser.setAcceptAllFileFilterUsed(false);
            //    
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String outputPathFilename = chooser.getSelectedFile().toString()+"\\Save_"+VARIANTE.toString()+".png";
                ImageIO.write(imagebuf,"png", new File(outputPathFilename));
                System.out.println("SAved in : " + outputPathFilename);
            }
            else {
                System.out.println("Aucun emplacement n'a été choisit! Veuillez choisir un emplacement");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error");
        }
        
    }
    
//<editor-fold defaultstate="collapsed" desc="Var Declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_ObstaclesReset;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_color;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_next1000;
    private javax.swing.JButton jButton_nextPlus;
    private javax.swing.JButton jButton_okDensity;
    private javax.swing.JButton jButton_okFire;
    private javax.swing.JButton jButton_okLength;
    private javax.swing.JButton jButton_okMlevel;
    private javax.swing.JButton jButton_okNumber;
    private javax.swing.JButton jButton_okPA;
    private javax.swing.JButton jButton_randObstacle;
    private javax.swing.JButton jButton_random;
    private javax.swing.JButton jButton_repulsion;
    private javax.swing.JButton jButton_screenSave;
    private javax.swing.JButton jButton_start;
    private javax.swing.JButton jButton_stop;
    private javax.swing.JCheckBox jCheckBox_grid;
    private javax.swing.JCheckBox jCheckBox_handDrawObstacles;
    private javax.swing.JCheckBox jCheckBox_obstacles;
    private javax.swing.JCheckBox jCheckBox_uncover;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JComboBox jComboBox_policy;
    private javax.swing.JComboBox jComboBox_shape;
    private javax.swing.JComboBox jComboBox_speed;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_coordinates;
    private javax.swing.JLabel jLabel_nbrGenerations;
    private javax.swing.JLabel jLabel_variante;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel_displayOptions;
    private javax.swing.JPanel jPanel_modelParams;
    private javax.swing.JPanel jPanel_obstacles;
    private javax.swing.JPanel jPanel_screen;
    private javax.swing.JRadioButton jRadioButton_both;
    private javax.swing.JRadioButton jRadioButton_horizontal;
    private javax.swing.JRadioButton jRadioButton_vertical;
    private javax.swing.JTextField jTextField_density;
    private javax.swing.JTextField jTextField_fire;
    private javax.swing.JTextField jTextField_mlevel;
    private javax.swing.JTextField jTextField_obstacleLength;
    private javax.swing.JTextField jTextField_obstaclesNbr;
    private javax.swing.JTextField jTextField_pa;
    private javax.swing.JTextField jTextField_repulsion;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
    
}
