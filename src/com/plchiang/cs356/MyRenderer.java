/* MyRenderer
 * This class is used to make sure that UserGroupNodes use folder icons instead of file icons
 * when displayed in the JTree in the GUI.
 */

package com.plchiang.cs356;


import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.plchiang.cs356.composite.UserGroupNode;

public class MyRenderer extends DefaultTreeCellRenderer {

    public MyRenderer() {
        super();
    }

    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);
        //use directory icon for UserGroupNodes
        if (leaf && value instanceof UserGroupNode) {
        	ImageIcon icon = new ImageIcon(".../toolbarButtonGraphics/general/Open16.gif");
        	setIcon(UIManager.getIcon("FileView.directoryIcon"));
        } else {
            setToolTipText(null); //no tool tip
        } 

        return this;
    }
}
