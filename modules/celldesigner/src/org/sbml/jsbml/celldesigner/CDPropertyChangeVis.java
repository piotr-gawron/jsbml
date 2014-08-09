/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2014  jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 6. Marquette University, Milwaukee, WI, USA
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.celldesigner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


/**
 * @author Ibrahim Vazirabad
 * @version $Rev$
 * @since 1.0
 * @date Jul 1, 2014
 */
public class CDPropertyChangeVis extends JFrame implements ActionListener {
  /** Generated serial version identifier */
  private static final long serialVersionUID = -6800051247041441688L;
  Container contentPane = getContentPane();
  private final JTextArea viewerArea = new JTextArea(30,75);

  public CDPropertyChangeVis() {
    super("SBML/CD Test");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    contentPane.setLayout(new BorderLayout());
    contentPane.add(new JScrollPane(viewerArea), BorderLayout.CENTER);
    //creating Button and listening for click
    JButton clearConsole = new JButton("Clear Display");
    clearConsole.addActionListener(this);
    clearConsole.setIcon(new ImageIcon("C:\\Users\\Yusef-PC\\workspace\\JSBML\\modules\\celldesigner\\resources\\org\\sbml\\jsbml\\celldesigner\\clear_display.png"));
    clearConsole.setVerticalTextPosition(SwingConstants.BOTTOM);
    clearConsole.setHorizontalTextPosition(SwingConstants.CENTER);

    JToolBar toolBar = new JToolBar("Clear Display");
    //toolBar.setFloatable(false);
    toolBar.add(clearConsole);
    toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
    contentPane.add(toolBar, BorderLayout.PAGE_START);
    pack();
    setLocationRelativeTo(null);
    setAlwaysOnTop(true);
    setVisible(true);
  }

  public void addSBase(String sbase)
  {
    String sbaseAdded = "\nSBase_Added:\t"+sbase;
    viewerArea.append(sbaseAdded);
  }

  public void modelSelectChanged(String sbase)
  {
    String sbaseAdded = "\nModel_SelectChanged:\t"+sbase;
    viewerArea.append(sbaseAdded);
  }

  public void modelOpened(String sbase)
  {
    String modelOpened = "\nmodel_Opened:\t"+sbase;
    viewerArea.append(modelOpened);
  }

  public void modelClosed(String sbase)
  {
    String modelClosed = "\nmodel_Closed:\t"+sbase;
    viewerArea.append(modelClosed);
  }

  public void deleteSBase(String sbase)
  {
    String deleteSBase="\nSBase_Deleted:\t"+sbase;
    viewerArea.append(deleteSBase);
  }

  public void changeSBase(String sbase)
  {
    String changeSBase="\nSBase_Changed:\t"+sbase;
    viewerArea.append(changeSBase);
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    viewerArea.setText("");
  }
}
