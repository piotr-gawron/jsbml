/*
 * $Id: SBMLReader.java 38 2009-11-05 15:50:38Z niko-rodrigue $
 * $URL: https://jsbml.svn.sourceforge.net/svnroot/jsbml/trunk/src/org/sbml/jsbml/SBMLReader.java $
 *
 * 
 *==================================================================================
 * Copyright (c) 2009 The jsbml team.
 *
 * This file is part of jsbml, the pure java SBML library. Please visit
 * http://sbml.org for more information about SBML, and http://jsbml.sourceforge.net/
 * to get the latest version of jsbml.
 *
 * jsbml is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jsbml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsbml.  If not, see <http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html>.
 *
 *===================================================================================
 *
 */

package org.sbml.jsbml.io;

import java.util.Date;
import java.util.List;

import org.sbml.jsbml.element.CVTerm;
import org.sbml.jsbml.element.Compartment;
import org.sbml.jsbml.element.EventAssignment;
import org.sbml.jsbml.element.FunctionDefinition;
import org.sbml.jsbml.element.InitialAssignment;
import org.sbml.jsbml.element.KineticLaw;
import org.sbml.jsbml.element.Model;
import org.sbml.jsbml.element.ModifierSpeciesReference;
import org.sbml.jsbml.element.Parameter;
import org.sbml.jsbml.element.Reaction;
import org.sbml.jsbml.element.Rule;
import org.sbml.jsbml.element.SBMLException;
import org.sbml.jsbml.element.Species;
import org.sbml.jsbml.element.SpeciesReference;
import org.sbml.jsbml.element.SpeciesType;
import org.sbml.jsbml.element.StoichiometryMath;
import org.sbml.jsbml.element.Unit;
import org.sbml.jsbml.element.UnitDefinition;

/**
 * @author Andreas Dr&auml;ger <a
 *         href="mailto:andreas.draeger@uni-tuebingen.de">
 *         andreas.draeger@uni-tuebingen.de</a>
 * 
 */
public interface SBMLReader {

	/**
	 * 
	 * @param d
	 * @return
	 */
	public Date convertDate(Object d);

	/**
	 * 
	 * @return
	 */
	public int getNumErrors();

	/**
	 * 
	 * @param sbmlDocument
	 * @return
	 */
	public List<SBMLException> getWarnings();

	/**
	 * 
	 * @param compartment
	 * @return
	 */
	public Compartment readCompartment(Object compartment);

	/**
	 * 
	 * @param term
	 * @return
	 */
	public CVTerm readCVTerm(Object term);

	/**
	 * 
	 * @param eventAssignment
	 * @return
	 */
	public EventAssignment readEventAssignment(Object eventAssignment);

	/**
	 * 
	 * @param functionDefinition
	 * @return
	 */
	public FunctionDefinition readFunctionDefinition(Object functionDefinition);

	/**
	 * 
	 * @param initialAssignment
	 * @return
	 */
	public InitialAssignment readInitialAssignment(Object initialAssignment);

	/**
	 * 
	 * @param kineticLaw
	 * @return
	 */
	public KineticLaw readKineticLaw(Object kineticLaw);

	/**
	 * 
	 * @param model
	 * @return
	 */
	public Model readModel(Object model);

	/**
	 * 
	 * @param plumod
	 * @return
	 */
	public ModifierSpeciesReference readModifierSpeciesReference(
			Object modifierSpeciesReference);

	/**
	 * 
	 * @param parameter
	 * @return
	 */
	public Parameter readParameter(Object parameter);

	/**
	 * 
	 * @param reaction
	 * @return
	 */
	public Reaction readReaction(Object reaction);

	/**
	 * 
	 * @param rule
	 * @return
	 */
	public Rule readRule(Object rule);

	/**
	 * 
	 * @param species
	 * @return
	 */
	public Species readSpecies(Object species);

	/**
	 * 
	 * @param speciesReference
	 * @return
	 */
	public SpeciesReference readSpeciesReference(Object speciesReference);

	/**
	 * 
	 * @param speciesType
	 * @return
	 */
	public SpeciesType readSpeciesType(Object speciesType);

	/**
	 * 
	 * @param stoichiometryMath
	 * @return
	 */
	public StoichiometryMath readStoichiometricMath(Object stoichiometryMath);

	/**
	 * 
	 * @param unit
	 * @return
	 */
	public Unit readUnit(Object unit);

	/**
	 * 
	 * @param unitDefinition
	 * @return
	 */
	public UnitDefinition readUnitDefinition(Object unitDefinition);
}
