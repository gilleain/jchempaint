/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-01-04 17:26:00 +0000 (Thu, 04 Jan 2007) $
 *  $Revision: 7634 $
 *
 *  Copyright (C) 1997-2008 Stefan Kuhn
 *
 *  Contact: cdk-jchempaint@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *  All we ask is that proper credit is given for our work, which includes
 *  - but is not limited to - adding the above copyright notice to the beginning
 *  of your source code files, and to any copyright notice that you may distribute
 *  with programs based on this work.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.jchempaint.dialog.editor;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.interfaces.IPseudoAtom;
import org.openscience.cdk.isomorphism.matchers.RGroupQuery;

import org.openscience.jchempaint.GT;

/**
 */
public class PseudoAtomEditor extends ChemObjectEditor {
    
    private static final long serialVersionUID = 7785262423262705152L;
    
    JTextField labelField;
    
	public PseudoAtomEditor() {
        super(false);
        constructPanel();
	}
    
    private void constructPanel() {
        labelField = new JTextField(20);
        addField(GT.get("Label"), labelField, this);
    }
    
    public void setChemObject(IChemObject object) {
        if (object instanceof IPseudoAtom) {
            source = object;
            // update table contents
            labelField.setText(((IPseudoAtom)object).getLabel());
        } else {
            throw new IllegalArgumentException("Argument must be an PseudoAtom");
        }
    }
	
    public void applyChanges() {
    	String label=labelField.getText();
    	if (label!=null && label.startsWith("R")&&label.length()>1 && !RGroupQuery.isValidRgroupQueryLabel(label) ) {
    		JOptionPane.showMessageDialog(null, GT.get("This is not a valid R-group label.\nPlease label in range R1 .. R32"));
		}
    	else {
    		IPseudoAtom atom = (IPseudoAtom)source;
    		atom.setLabel(labelField.getText());
    	}
    }
}


