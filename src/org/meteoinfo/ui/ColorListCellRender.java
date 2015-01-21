/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meteoinfo.ui;

import org.meteoinfo.global.colors.ColorTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author wyq
 */
public class ColorListCellRender extends JPanel implements ListCellRenderer {

    // <editor-fold desc="Variables">
    private ColorTable colorTable;
    private boolean isSelected = false;

    // </editor-fold>
    // <editor-fold desc="Constructor">
    /**
     * Constructor
     */
    public ColorListCellRender() {
        setOpaque(true);
    }
    
    /**
     * Constructor
     * @param isSelected Is selected
     */
    public ColorListCellRender(boolean isSelected) {
        setOpaque(true);
        this.isSelected = isSelected;
    }

    // </editor-fold>
    // <editor-fold desc="Get Set Methods">
    // </editor-fold>
    // <editor-fold desc="Methods">
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //Get the selected index. (The index param isn't
        //always valid, so just use the value.)
        this.colorTable = (ColorTable) value;
        this.isSelected = isSelected;

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.colorTable != null) {
            Graphics2D g2 = (Graphics2D) g;
            int n = this.colorTable.getColorCount();
            int xshift = 2;
            int yshift = 2;
            int width = this.getWidth() - xshift;
            int w = width / n;
            if (w == 0)
                w = 1;
            int h = this.getHeight() - yshift;
            int x = xshift / 2;
            int y = yshift / 2;
            Color c;
            for (int i = 0; i < n; i++) {
                c = this.colorTable.getColor(i);
                g2.setColor(c);
                if (x + w > width) {
                    g2.fillRect(x, y, width - x, h);
                    break;
                } else {
                    if (i == n - 1)
                        g2.fillRect(x, y, width - x, h);
                    else
                        g2.fillRect(x, y, w, h);
                }
                
                x += w;
            }
            
            if (this.isSelected){
                FontMetrics metrics = g2.getFontMetrics(this.getFont());
                String name = this.colorTable.getName();
                x = this.getWidth() / 2 - metrics.stringWidth(name) / 2;
                y = this.getHeight() / 2 + metrics.getHeight() / 3;
                //y = this.getHeight() / 2;
                g2.setColor(Color.black);
                g2.drawString(name, x, y);
            }
        }
    }
    // </editor-fold>    

}
