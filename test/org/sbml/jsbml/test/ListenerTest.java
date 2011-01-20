/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2011 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.test;

import org.sbml.jsbml.CVTerm;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBO;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.SBaseChangedEvent;
import org.sbml.jsbml.SBaseChangedListener;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.CVTerm.Qualifier;
import org.sbml.jsbml.xml.stax.SBMLWriter;

/**
 * @author Andreas Dr&auml;ger
 * @date 2010-11-14
 */
public class ListenerTest implements SBaseChangedListener {

	public ListenerTest() {
		SBMLDocument doc = new SBMLDocument(1, 2);
		doc.addChangeListener(this);
		Model model = doc.createModel("test_model");
		Parameter p1 = model.createParameter("p1");
		p1.setId("p2");
		model.removeParameter(p1);

		Compartment c = model.createCompartment("c");
		c.setSize(4.3);
		c.setSBOTerm(SBO.getPhysicalCompartment());

		Species s1 = model.createSpecies("s1", c);
		s1.addCVTerm(new CVTerm(CVTerm.Type.BIOLOGICAL_QUALIFIER,
				Qualifier.BQB_IS, "urn:miriam:kegg.compound:C12345"));
		s1.setValue(23.7);
		model.removeSpecies(s1);

		try {
			System.out.println();
			SBMLWriter.write(doc, System.out);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ListenerTest();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.SBaseChangedListener#sbaseAdded(org.sbml.jsbml.SBase)
	 */
	public void sbaseAdded(SBase sb) {
		System.out.printf("Added:\t%s\n", sb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.SBaseChangedListener#sbaseRemoved(org.sbml.jsbml.SBase)
	 */
	public void sbaseRemoved(SBase sb) {
		System.out.printf("Removed:\t%s\n", sb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.sbml.jsbml.SBaseChangedListener#stateChanged(org.sbml.jsbml.
	 * SBaseChangedEvent)
	 */
	public void stateChanged(SBaseChangedEvent ev) {
		System.out.printf("Change:\t%s\n", ev.toString());
	}

}
