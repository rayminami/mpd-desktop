/*
 * Copyright Vladimir Petrenko. All rights reserved
 * Contact: 
 *   Mitteldamm 48a
 *   32429 Minden
 *   Germany
 *   vladipetrenko@gmx.de 
 * Developer: Vladimir Petrenko
 * License: BSD
 */

package virtualkeyboard.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Locale;
import javax.swing.text.JTextComponent;

/**
 * Dialog for VirtualKeyboard
 * @author Vladimir Petrenko
 */
public class DialogVirtualKeyboardReal extends javax.swing.JDialog {

    /** Creates new form DialogVirtualKeyboard */
    public DialogVirtualKeyboardReal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        panelVirtualKeyboard.setWindow(this);
    }

    /** Creates new form DialogVirtualKeyboard */
    public DialogVirtualKeyboardReal(java.awt.Frame parent, boolean modal, JTextComponent textComponent) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();

        panelVirtualKeyboard.setWindow(this);
        int neutral_pos = (int) textComponent.getLocationOnScreen().getX()*-1;
        this.setTextComponent(textComponent);
        this.show(textComponent,neutral_pos+((parent.getWidth()-this.getWidth())/2) , textComponent.getHeight());
    }

    public boolean isPoitToUp() {
        return panelVirtualKeyboard.isPoitToUp();
    }

    public void setPoitToUp(boolean poitToUp) {
        panelVirtualKeyboard.setPoitToUp(poitToUp);
    }

    public boolean isShiftBs() {
        return panelVirtualKeyboard.isShiftBs();
    }

    public void setShiftBs(boolean shiftBs) {
        panelVirtualKeyboard.setShiftBs(shiftBs);
    }

    /**
     * Get the reference of JTextComponent
     *
     * @return JTextComponent
     */
    public JTextComponent getTextComponent() {
        return panelVirtualKeyboard.getTextComponent();
    }

    /**
     *  Set the reference of
     *
     * @param textComponent
     */
    public void setTextComponent(JTextComponent textComponent) {
        panelVirtualKeyboard.setTextComponent(textComponent);
    }

    /**
     * Displays the popup menu at the position x,y in the coordinate
     * space of the component invoker.
     *
     * @param invoker the component in whose space the popup menu is to appear
     * @param x the x coordinate in invoker's coordinate space at which
     * the popup menu is to be displayed
     * @param y the y coordinate in invoker's coordinate space at which
     * the popup menu is to be displayed
     */
    public void show(Component invoker, int x, int y) {
	Point invokerOrigin;
	if (invoker != null) {
	    invokerOrigin = invoker.getLocationOnScreen();

            // To avoid integer overflow
            long lx, ly;
            lx = ((long) invokerOrigin.x) +
                 ((long) x);
            ly = ((long) invokerOrigin.y) +
                 ((long) y);
            if(lx > Integer.MAX_VALUE) lx = Integer.MAX_VALUE;
            if(lx < Integer.MIN_VALUE) lx = Integer.MIN_VALUE;
            if(ly > Integer.MAX_VALUE) ly = Integer.MAX_VALUE;
            if(ly < Integer.MIN_VALUE) ly = Integer.MIN_VALUE;

            boolean valueX=false, valueY=false;
            int yl = 0, xl = 0;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //Value of Y-Axis
            if((int)(ly+this.getHeight())>=screenSize.getHeight()){
                valueY=true;
                yl = (int) (ly-(long)invoker.getHeight()-this.getHeight());
            }
            //Value of X-Axis
            if((int)(lx+this.getWidth())>=screenSize.getWidth()){
                valueX=true;
                xl = (int) (lx-(long)this.getWidth());
            }

            if(valueX && valueY) setLocation((int) xl, (int) yl);
            else if(valueX) setLocation((int) xl, (int) ly);
            else if(valueY) setLocation((int) lx, (int) yl);
            else setLocation((int) lx, (int) ly);
	} else {
	    setLocation(x, y);
	}
        setVisible(true);
        panelVirtualKeyboard.setFocusToEXE();
        panelVirtualKeyboard.updateGUI();
    }

    /**
     * Get the value of locale
     *
     * @return the value of locale
     */
    public Locale getLocaleL() {
        return panelVirtualKeyboard.getLocaleL();
    }

    /**
     * Set the value of locale
     *
     * @param locale new value of locale
     */
    public void setLocaleL(Locale localeL) {
        panelVirtualKeyboard.setLocaleL(localeL);
    }

    public int getGap() {
        return panelVirtualKeyboard.getGap();
    }

    public void setGap(int gap) {
        panelVirtualKeyboard.setGap(gap);
    }

    public int getTab() {
        return panelVirtualKeyboard.getTab();
    }

    public void setTab(int tab) {
        panelVirtualKeyboard.setTab(tab);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVirtualKeyboard = new virtualkeyboard.gui.PanelVirtualKeyboardReal();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        panelVirtualKeyboard.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelVirtualKeyboard.setAlignmentX(0.0F);
        panelVirtualKeyboard.setAlignmentY(0.0F);
        panelVirtualKeyboard.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                panelVirtualKeyboardFocusLost(evt);
            }
        });
        getContentPane().add(panelVirtualKeyboard, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        if(!this.isModal()){
            this.setVisible(false);
            this.dispose();
        }
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_formWindowLostFocus

    private void panelVirtualKeyboardFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelVirtualKeyboardFocusLost
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_panelVirtualKeyboardFocusLost

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogVirtualKeyboardReal dialog = new DialogVirtualKeyboardReal(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private virtualkeyboard.gui.PanelVirtualKeyboardReal panelVirtualKeyboard;
    // End of variables declaration//GEN-END:variables
}
