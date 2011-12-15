/*
 * $Id$
 * $URL$
 * ---------------------------------------------------------------------
 * This file is part of the SBMLeditor API library.
 *
 * Copyright (C) 2011 by the University of Tuebingen, Germany.
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation. A copy of the license
 * agreement is provided in the file named "LICENSE.txt" included with
 * this software distribution and also available online as
 * <http://www.gnu.org/licenses/lgpl-3.0-standalone.html>.
 * ---------------------------------------------------------------------
 */
package org.sbml.jsbml.cdplugin;

import java.beans.PropertyChangeEvent;

import javax.swing.tree.TreeNode;

import jp.sbi.celldesigner.SpeciesAlias;
import jp.sbi.celldesigner.plugin.CellDesignerPlugin;
import jp.sbi.celldesigner.plugin.PluginAlgebraicRule;
import jp.sbi.celldesigner.plugin.PluginAntiSenseRNA;
import jp.sbi.celldesigner.plugin.PluginAssignmentRule;
import jp.sbi.celldesigner.plugin.PluginCompartment;
import jp.sbi.celldesigner.plugin.PluginCompartmentType;
import jp.sbi.celldesigner.plugin.PluginConstraint;
import jp.sbi.celldesigner.plugin.PluginDoSthAbstractAction;
import jp.sbi.celldesigner.plugin.PluginEvent;
import jp.sbi.celldesigner.plugin.PluginEventAssignment;
import jp.sbi.celldesigner.plugin.PluginFunctionDefinition;
import jp.sbi.celldesigner.plugin.PluginInitialAssignment;
import jp.sbi.celldesigner.plugin.PluginKineticLaw;
import jp.sbi.celldesigner.plugin.PluginListOf;
import jp.sbi.celldesigner.plugin.PluginModel;
import jp.sbi.celldesigner.plugin.PluginParameter;
import jp.sbi.celldesigner.plugin.PluginProtein;
import jp.sbi.celldesigner.plugin.PluginRateRule;
import jp.sbi.celldesigner.plugin.PluginReaction;
import jp.sbi.celldesigner.plugin.PluginRule;
import jp.sbi.celldesigner.plugin.PluginSimpleSpeciesReference;
import jp.sbi.celldesigner.plugin.PluginSpecies;
import jp.sbi.celldesigner.plugin.PluginSpeciesAlias;
import jp.sbi.celldesigner.plugin.PluginSpeciesReference;
import jp.sbi.celldesigner.plugin.PluginSpeciesType;
import jp.sbi.celldesigner.plugin.PluginUnit;
import jp.sbi.celldesigner.plugin.PluginUnitDefinition;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.AlgebraicRule;
import org.sbml.jsbml.Annotation;
import org.sbml.jsbml.AssignmentRule;
import org.sbml.jsbml.CVTerm;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.CompartmentType;
import org.sbml.jsbml.Constraint;
import org.sbml.jsbml.Creator;
import org.sbml.jsbml.Delay;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.EventAssignment;
import org.sbml.jsbml.FunctionDefinition;
import org.sbml.jsbml.History;
import org.sbml.jsbml.InitialAssignment;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.LocalParameter;
import org.sbml.jsbml.MathContainer;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Priority;
import org.sbml.jsbml.RateRule;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBO;
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.SpeciesType;
import org.sbml.jsbml.StoichiometryMath;
import org.sbml.jsbml.Trigger;
import org.sbml.jsbml.Unit;
import org.sbml.jsbml.UnitDefinition;
import org.sbml.jsbml.util.TreeNodeChangeEvent;
import org.sbml.jsbml.util.TreeNodeChangeListener;
import org.sbml.libsbml.ListOfCompartments;
import org.sbml.libsbml.SBase;
import org.sbml.libsbml.XMLNode;
import org.sbml.libsbml.libsbmlConstants;

/**
 * @author Alexander Peltzer
 * @author Andreas Dr&auml;ger
 * @version $Rev$
 * @date 10:50:22
 */
@SuppressWarnings("deprecation")
public class PluginChangeListener implements TreeNodeChangeListener {

	/**
   * 
   */
	private CellDesignerPlugin plugin;

	/**
	 * 
	 */
	private static final transient Logger logger = Logger
			.getLogger(PluginChangeListener.class);

	/**
	 * 
	 */
	private PluginModel plugModel;

	/**
	 * 
	 * @param plugin
	 */
	public PluginChangeListener(SBMLDocument doc, CellDesignerPlugin plugin) {
		this.plugin = plugin;
		this.plugModel = plugin.getSelectedModel();
		if (doc != null) {
			Model model = doc.getModel();
			if (model != null) {

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		Object eventsource = event.getSource();

		String prop = event.getPropertyName();
		if (prop.equals(TreeNodeChangeEvent.about)) {

		} else if (prop.equals(TreeNodeChangeEvent.addCVTerm)) {

		} else if (prop.equals(TreeNodeChangeEvent.addDeclaredNamespace)) {

		}
		// TODO Auto-generated method stub
		else if (prop.equals(TreeNodeChangeEvent.charge)) {
			Species species = (Species) event.getSource();
			PluginSpecies plugSpec = plugModel.getSpecies(species.getId());
			plugSpec.setCharge(species.getCharge());
			plugin.notifySBaseChanged(plugSpec);
		} else if (prop.equals(TreeNodeChangeEvent.math)) {
			MathContainer mathContainer = (MathContainer) event.getSource();
			// TODO check which corresponding element can be found in
			// CellDesigner
			if (mathContainer instanceof Constraint) {
				// TODO
				Constraint c = (Constraint) mathContainer;

			}
			// ...
			else if (mathContainer instanceof KineticLaw) {
				Reaction r = ((KineticLaw) mathContainer).getParent();
				PluginReaction plugReac = plugModel.getReaction(r.getId());
				if (plugReac != null) {
					PluginKineticLaw plugKl = plugReac.getKineticLaw();
					// plugKl.setMath(); // see PluginSBMLWriter

				}
			}
		}
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.util.TreeNodeChangeListener#nodeAdded(javax.swing.tree
	 * .TreeNode)
	 */
	public void nodeAdded(TreeNode node) {
		if (node instanceof CompartmentType) {
			CompartmentType ct = (CompartmentType) node;
			PluginCompartmentType pt = new PluginCompartmentType(ct.getId());
			if (ct.isSetName() && !pt.getName().equals(ct.getName())) {
				pt.setName(ct.getName());
				plugin.notifySBaseAdded(pt); 
			}
			logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
		} else if (node instanceof Species) {
			Species sp = (Species) node;
			PluginSpecies plugsp = new PluginSpecies(sp.getSpeciesType(), sp.getName());
			if (sp.isSetName() && !sp.getName().equals(plugsp.getName())) {
				plugin.notifySBaseAdded(plugsp);
			} else {
				logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
			}
		} else if (node instanceof Reaction) {
			Reaction react = (Reaction) node;
			PluginReaction plugreac = new PluginReaction();
			if (react.isSetName() && !react.getName().equals(plugreac.getName())){
				plugreac.setName(react.getName());
				plugin.notifySBaseAdded(plugreac);
			} else {
				logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
			}
		} else if (node instanceof SpeciesType) {
			SpeciesType speciestype = (SpeciesType) node;
			PluginSpeciesType plugspectype = new PluginSpeciesType(speciestype.getId());
			if (speciestype.isSetName() && !speciestype.getName().equals(plugspectype.getName())){
				plugspectype.setName(speciestype.getName());
				plugin.notifySBaseAdded(plugspectype);
			} else {
				logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
			}
		} else if (node instanceof org.sbml.jsbml.Parameter) {
			org.sbml.jsbml.Parameter param = (org.sbml.jsbml.Parameter) node;
			if (param.getParent() instanceof KineticLaw){
				PluginParameter plugparam = new PluginParameter((PluginKineticLaw) param.getParent());
				if (param.isSetName() && !param.getName().equals(plugparam.getName())){
					plugparam.setName(param.getName());
					plugin.notifySBaseAdded(plugparam);
				} else {
					logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
				}
			} else if (param.getParent() instanceof Model){
				PluginParameter plugparam = new PluginParameter((PluginModel) param.getParent());
				if (param.isSetName() && !param.getName().equals(plugparam.getName())){
					plugparam.setName(param.getName());
					plugin.notifySBaseAdded(plugparam);
				} else {
					logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
				}
			}
		} else if (node instanceof FunctionDefinition) {
			FunctionDefinition funcdef = (FunctionDefinition) node;
			PluginFunctionDefinition plugfuncdef = new PluginFunctionDefinition(funcdef.getId());
			if (funcdef.isSetName() && !plugfuncdef.getName().equals(funcdef.getName())) {
				plugfuncdef.setName(funcdef.getName());
				plugin.notifySBaseAdded(plugfuncdef);
			} else {
				logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
			}
		} else if (node instanceof Compartment) {
			Compartment comp = (Compartment) node;
			PluginCompartment plugcomp = new PluginCompartment(comp.getCompartmentType());
			if (comp.isSetName() && !plugcomp.getName().equals(comp.getName())) {
				plugcomp.setName(comp.getName());
				plugin.notifySBaseAdded(plugcomp);
			} else {
				logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
			}
		} else if (node instanceof SpeciesReference) {
			SpeciesReference specRef = (SpeciesReference) node;
      String type = SBO.convertSBO2Alias(specRef.getSBOTerm());
      if (type.length() == 0) {
        // use "unknown"
        int sbo = 285;
        type = SBO.convertSBO2Alias(sbo);
        logger.log(Level.DEBUG, String.format(
          "No SBO term defined for %s, using %d", 
          specRef.getElementName(), sbo));
      }
      // TODO: use SBML layout extension (later than JSBML 0.8)
      if (specRef.isSetSpecies()) {
        PluginSpeciesAlias alias = new PluginSpeciesAlias(plugModel
            .getSpecies(specRef.getSpecies()), type);
        PluginSpeciesReference plugspecRef = new PluginSpeciesReference(
          (PluginReaction) specRef.getParent(), alias);
        plugin.notifySBaseAdded(plugspecRef);
      } else {
        logger.log(Level.DEBUG, "Cannot create PluginSpeciesReference due to missing species annotation.");
      }
		} else if (node instanceof LocalParameter) {
			LocalParameter locparam = (LocalParameter) node;
			ListOf<LocalParameter> lop = locparam.getParentSBMLObject();
			KineticLaw kl = (KineticLaw) lop.getParentSBMLObject();
			Reaction r = kl.getParentSBMLObject();
			//TODO LocalParameter not available in CellDesigner?
		} else if (node instanceof SimpleSpeciesReference) {
			SimpleSpeciesReference simspec = (SimpleSpeciesReference) node;
			//PluginSimpleSpeciesReference plugsimspec = new PluginSimpleSpeciesReference(alias);
			// TODO Where to get the Species Alias ?
		} else if (node instanceof UnitDefinition) {
			UnitDefinition undef = (UnitDefinition) node;
			PluginUnitDefinition plugundef = new PluginUnitDefinition(undef.getId());
			if (undef.isSetName() && !plugundef.getName().equals(undef.getName())) {
				plugundef.setName(undef.getName());
				plugin.notifySBaseAdded(plugundef);
			} else {
				logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
			}
		} else if (node instanceof Event) {
			Event event = (Event) node;
			PluginEvent plugevent = new PluginEvent(event.getId());
			if (event.isSetName() && !event.getName().equals(plugevent.getName())){
				plugevent.setName(event.getName());
				plugin.notifySBaseAdded(plugevent);
			} else {
				logger.log(Level.DEBUG, "Cannot parse node" + node.getClass().getSimpleName());
			}
		} else if (node instanceof RateRule) {
			RateRule rule = (RateRule) node;
			PluginRateRule plugraterule = new PluginRateRule(plugModel);
			plugraterule.setFormula(rule.getFormula());
			plugraterule.setMath(convert(rule.getMath()));
			plugraterule.setVariable(rule.getVariable());
			//TODO Howto convert XMLNode elements from JSBML to libsbml ?
			//This isn't as well described in the PluginSBMLWriter class
			plugin.notifySBaseAdded(plugraterule);
			
		} else if (node instanceof AssignmentRule) {
			AssignmentRule assignRule = (AssignmentRule) node;
			PluginAssignmentRule plugassignRule = new PluginAssignmentRule(plugModel);
			plugassignRule.setFormula(assignRule.getFormula());
			plugassignRule.setL1TypeCode(assignRule.getLevel());
			plugassignRule.setMath(convert(assignRule.getMath()));
			plugassignRule.setVariable(assignRule.getVariable());
			//TODO Howto convert XMLNode elements from JSBML to libsbml ?
			//This isn't as well described in the PluginSBMLWriter class
			plugin.notifySBaseAdded(plugassignRule);
		} else if (node instanceof KineticLaw) {
			KineticLaw klaw = (KineticLaw) node;
			Reaction parentreaction = klaw.getParentSBMLObject();
			PluginKineticLaw plugklaw = plugModel.getReaction(
					parentreaction.getId()).getKineticLaw();
			PluginReaction plugreac = plugModel.getReaction(parentreaction.getId());
			plugreac.setKineticLaw(plugklaw);
			plugin.notifySBaseAdded(plugreac);
		} else if (node instanceof InitialAssignment) {
			InitialAssignment iAssign = (InitialAssignment) node;
			PluginInitialAssignment plugiassign = new PluginInitialAssignment(iAssign.getSymbol());
			plugiassign.setMath(iAssign.getMathMLString());
			//TODO Howto convert XMLNode elements from JSBML to libsbml ?
			//This isn't as well described in the PluginSBMLWriter class
			plugin.notifySBaseAdded(plugiassign);
		} else if (node instanceof EventAssignment) {
			EventAssignment eassign = (EventAssignment) node;
			//PluginEventAssignment plugeassign = new PluginEventAssignment(eassign.getParent());
			// TODO: This is a list of <EventAssignments> Are there always only one or more ? 
			// Do we have to parse them all ?
		} else if (node instanceof StoichiometryMath) {
			StoichiometryMath stoich = (StoichiometryMath) node;
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no class in CD for that ? What to do with such things ? Log-Message and thats it ?
		} else if (node instanceof Trigger) {
			Trigger trig = (Trigger) node;
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no class in CD for that ?
		} else if (node instanceof Rule) {
			Rule rule = (Rule) node;
			PluginRule plugrule = new PluginRule();
			plugin.notifySBaseAdded(plugrule);
		} else if (node instanceof AlgebraicRule) {
			AlgebraicRule alrule = (AlgebraicRule) node;
			PluginAlgebraicRule plugalrule = new PluginAlgebraicRule(plugModel);
			plugalrule.setFormula(alrule.getFormula());
			plugalrule.setMath(convert(alrule.getMath()));
			//TODO Howto convert XMLNode elements from JSBML to libsbml ?
			//This isn't as well described in the PluginSBMLWriter class
			plugin.notifySBaseAdded(plugalrule);
		} else if (node instanceof Constraint) {
			Constraint ct = (Constraint) node;
			PluginConstraint plugct = new PluginConstraint(ct.getMathMLString());
			plugct.setMessage(ct.getMessageString());
			//TODO XMLNode parsing as well here
			plugin.notifySBaseAdded(plugct);
		} else if (node instanceof Delay) {
			Delay dl = (Delay) node;
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no counter class in CD available
			// Therefore unnecessary to implement this?
		} else if (node instanceof Priority) {
			Priority prt = (Priority) node;
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no counter class in CD available
			// Therefore unnecessary to implement this?
		} else if (node instanceof Unit) {
			Unit ut = (Unit) node;
			//TODO Unclear how to continue with that information
			//PluginUnit plugunit = new PluginUnit(ut.getParent().get)
		} else if (node instanceof SBMLDocument) {
			SBMLDocument doc = (SBMLDocument) node;
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no counter class in CD available
			// Therefore unnecessary to implement this?
		} else if (node instanceof ListOf<?>) {
			ListOf<?> listOf = (ListOf<?>) node;
			switch (listOf.getSBaseListType()) {
			  case listOfCompartments:
				  ListOfCompartments ll = new ListOfCompartments();
				  
			    break;
			  case listOfCompartmentTypes:
			    break;
			  case listOfConstraints:
			    break;
			  case listOfEventAssignments:
			    break;
			  case listOfEvents:
			    break;
			  case listOfFunctionDefinitions:
          break;
        case listOfInitialAssignments:
          break;
        case listOfLocalParameters:
          break;
        case listOfModifiers:
          break;
        case listOfParameters:
          break;
        case listOfProducts:
          break;
        case listOfReactants:
          break;
        case listOfReactions:
          break;
        case listOfRules:
          break;
        case listOfSpecies:
          break;
        case listOfSpeciesTypes:
          break;
        case listOfUnitDefinitions:
          break;
        case listOfUnits:
          break;
        case other:
          // TODO for JSBML packages (later than 0.8).
        default:
          // unknown
          break;
			}
			PluginListOf pluglistof = new PluginListOf();
			pluglistof.setNotes(listOf.getNotesString());
			// TODO Parse all lists or what has to be done here?
		} else if (node instanceof CVTerm){
			CVTerm cv = (CVTerm) node;
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			//TODO This has to be done with the libsbml.CVTerm Class, fix this.
		} else if (node instanceof History){
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no counter class in CD available
						// Therefore unnecessary to implement this?
		} else if (node instanceof Annotation){
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no counter class in CD available
						// Therefore unnecessary to implement this?
		} else if (node instanceof Creator){
			logger.log(Level.DEBUG, "No counter class in CellDesigner" + node.getClass().getSimpleName());
			// TODO no counter class in CD available
						// Therefore unnecessary to implement this?
		
		} else {
			logger.warn(String.format("Could not process %s.", node.toString()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbml.jsbml.util.TreeNodeChangeListener#nodeRemoved(javax.swing.tree
	 * .TreeNode)
	 */
	public void nodeRemoved(TreeNode node) {
		if (node instanceof CompartmentType) {
			CompartmentType ct = (CompartmentType) node;
			PluginCompartmentType pt = plugModel.getCompartmentType(ct.getId());
			plugModel.removeCompartmentType(ct.getId());
			plugin.notifySBaseDeleted(pt);
		} else if (node instanceof Species) {
			Species sp = (Species) node;
			PluginSpecies ps = plugModel.getSpecies(sp.getId());
			plugModel.removeSpecies(sp.getId());
			plugin.notifySBaseDeleted(ps);
		} else if (node instanceof Reaction) {
			Reaction react = (Reaction) node;
			PluginReaction preac = plugModel.getReaction(react.getId());
			plugModel.removeReaction(react.getId());
			plugin.notifySBaseDeleted(preac);
		} else if (node instanceof SpeciesType) {
			SpeciesType speciestype = (SpeciesType) node;
			PluginSpeciesType pspec = plugModel.getSpeciesType(speciestype
					.getId());
			plugModel.removeSpeciesType(speciestype.getId());
			plugin.notifySBaseDeleted(pspec);
		} else if (node instanceof org.sbml.jsbml.Parameter) {
			org.sbml.jsbml.Parameter param = (org.sbml.jsbml.Parameter) node;
			PluginParameter plugParam = plugModel.getParameter(param.getId());
			plugModel.removeParameter(param.getId());
			plugin.notifySBaseDeleted(plugParam);
		} else if (node instanceof FunctionDefinition) {
			FunctionDefinition funcdef = (FunctionDefinition) node;
			PluginFunctionDefinition plugFuncdef = plugModel
					.getFunctionDefinition(funcdef.getId());
			plugModel.removeFunctionDefinition(funcdef.getId());
			plugin.notifySBaseDeleted(plugFuncdef);
		} else if (node instanceof Compartment) {
			Compartment comp = (Compartment) node;
			PluginCompartment plugComp = plugModel.getCompartment(comp.getId());
			plugModel.removeCompartment(comp.getId());
			plugin.notifySBaseDeleted(plugComp);
		} else if (node instanceof SpeciesReference) {
			SpeciesReference specRef = (SpeciesReference) node;
			SBase sbase = (SBase) specRef.getParent();
			// TODO What do we do with such an SBase Type ?

		} else if (node instanceof LocalParameter) {
			LocalParameter locparam = (LocalParameter) node;
			ListOf<LocalParameter> lop = locparam.getParentSBMLObject();
			KineticLaw kl = (KineticLaw) lop.getParentSBMLObject();
			Reaction r = kl.getParentSBMLObject();
			// PluginKineticLaw plugkl =
			// plugModel.getReaction(r.getId()).getKineticLaw();
			// plugin.notifySBaseDeleted(sbase)
			// TODO USE PARAMETER in PluginKineticLaw.
		} else if (node instanceof SimpleSpeciesReference) {
			SimpleSpeciesReference simspec = (SimpleSpeciesReference) node;
			// What to do with Treenode?
			// TODO Has no ID, crosscheck this
		} else if (node instanceof UnitDefinition) {
			UnitDefinition undef = (UnitDefinition) node;
			PluginUnitDefinition plugUndef = plugModel.getUnitDefinition(undef
					.getId());
			plugModel.removeUnitDefinition(undef.getId());
			plugin.notifySBaseDeleted(plugUndef);
		} else if (node instanceof Event) {
			Event event = (Event) node;
			PluginEvent plugEvent = plugModel.getEvent(event.getId());
			plugModel.removeEvent(event.getId());
			plugin.notifySBaseDeleted(plugEvent);
		} else if (node instanceof RateRule) {
			RateRule rule = (RateRule) node;
			// TODO This has to be hashed somehow
		} else if (node instanceof AssignmentRule) {
			AssignmentRule assignRule = (AssignmentRule) node;
			// TODO This has to be hashed somehow
		} else if (node instanceof KineticLaw) {
			KineticLaw klaw = (KineticLaw) node;
			Reaction parentreaction = klaw.getParentSBMLObject();
      PluginReaction plugReac = plugModel.getReaction(parentreaction.getId());
			PluginKineticLaw plugklaw = plugReac.getKineticLaw();
			//TODO test this, is null applicable here or not?
			plugReac.setKineticLaw(null);
			plugin.notifySBaseDeleted(plugklaw);
			// plugModel.removeR
			// Do we have to remove the whole Reaction here or only the
			// KineticLaw ?
			// TODO crosscheck, no ID available
		} else if (node instanceof InitialAssignment) {
			InitialAssignment iAssign = (InitialAssignment) node;
			// TODO This has to be hashed somehow.
		} else if (node instanceof EventAssignment) {
			EventAssignment eAssign = (EventAssignment) node;
			ListOf<EventAssignment> elist = eAssign.getParent();
			Event e = (Event) elist.getParentSBMLObject();
			PluginEventAssignment plugEventAssignment = plugModel.getEvent(
					e.getId()).getEventAssignment(eAssign.getIndex(node));
			plugin.notifySBaseDeleted(plugEventAssignment);
		} else if (node instanceof StoichiometryMath) {
			StoichiometryMath stoich = (StoichiometryMath) node;
			// TODO no class in CD for that ?
		} else if (node instanceof Trigger) {
			// TODO no class in CD for that ?
		} else if (node instanceof Rule) {
			Rule rule = (Rule) node;
			// TODO This has to be hashed somehow
		} else if (node instanceof AlgebraicRule) {
			AlgebraicRule alrule = (AlgebraicRule) node;
			// TODO This has to be hashed somehow
		} else if (node instanceof Constraint) {
			Constraint ct = (Constraint) node;
			// TODO This has to be hashed somehow
		} else if (node instanceof Delay) {
			Delay dl = (Delay) node;
			// TODO no counter class in CD available
			// Therefore unnecessary to implement this?
		} else if (node instanceof Priority) {
			Priority prt = (Priority) node;
			// TODO no counter class in CD available
			// Therefore unnecessary to implement this?
		} else if (node instanceof Unit) {
			Unit ut = (Unit) node;
			// TODO no counter class in CD available
			// Therefore unnecessary to implement this?
		} else if (node instanceof SBMLDocument) {
			SBMLDocument doc = (SBMLDocument) node;
			// TODO no counter class in CD available
			// Therefore unnecessary to implement this?
		} else if (node instanceof ListOf<?>) {
			ListOf<?> listof = (ListOf<?>) node;
			// PluginListOf pluglistof = plugModel.getListof???
			// TODO Parse all lists or what has to be done here?
		} else if (node instanceof CVTerm){
			CVTerm term = (CVTerm) node;
			
			//TODO
		} else if (node instanceof History){
			//TODO
		} else if (node instanceof Annotation){
			//TODO
		} else if (node instanceof Creator){
			//TODO
		}
	}
	
	/**
	 * This stuff can be written separately into another class, maybe called utils; since it is used by both kind of plugins.
	 * 
	 */
	
	/**
	 * 
	 * @param ast
	 * @return
	 */
	private org.sbml.libsbml.ASTNode convert(ASTNode astnode) {
		org.sbml.libsbml.ASTNode libAstNode;
		switch (astnode.getType()) {
		case REAL:
			libAstNode = new org.sbml.libsbml.ASTNode(libsbmlConstants.AST_REAL);
			libAstNode.setValue(astnode.getReal());
			break;
		case INTEGER:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_INTEGER);
			libAstNode.setValue(astnode.getInteger());
			break;
		case FUNCTION_LOG:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_LOG);
			break;
		case POWER:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_POWER);
			break;
		case PLUS:
			libAstNode = new org.sbml.libsbml.ASTNode(libsbmlConstants.AST_PLUS);
			break;
		case MINUS:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_MINUS);
			break;
		case TIMES:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_TIMES);
			break;
		case DIVIDE:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_DIVIDE);
			break;
		case RATIONAL:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_RATIONAL);
			break;
		case NAME_TIME:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_NAME_TIME);
			break;
		case FUNCTION_DELAY:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_DELAY);
			break;
		case NAME:
			libAstNode = new org.sbml.libsbml.ASTNode(libsbmlConstants.AST_NAME);
			libAstNode.setName(astnode.getName());
			break;
		case CONSTANT_PI:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_CONSTANT_PI);
			break;
		case CONSTANT_E:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_CONSTANT_E);
			break;
		case CONSTANT_TRUE:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_CONSTANT_TRUE);
			break;
		case CONSTANT_FALSE:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_CONSTANT_FALSE);
			break;
		case REAL_E:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_REAL_E);
			libAstNode.setValue(astnode.getMantissa(), astnode.getExponent());
			break;
		case FUNCTION_ABS:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ABS);
			break;
		case FUNCTION_ARCCOS:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCCOS);
			break;
		case FUNCTION_ARCCOSH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCCOSH);
			break;
		case FUNCTION_ARCCOT:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCCOT);
			break;
		case FUNCTION_ARCCOTH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCCOTH);
			break;
		case FUNCTION_ARCCSC:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCCSC);
			break;
		case FUNCTION_ARCCSCH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCCSCH);
			break;
		case FUNCTION_ARCSEC:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCSEC);
			break;
		case FUNCTION_ARCSECH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCSECH);
			break;
		case FUNCTION_ARCSIN:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCSIN);
			break;
		case FUNCTION_ARCSINH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCSINH);
			break;
		case FUNCTION_ARCTAN:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCTAN);
			break;
		case FUNCTION_ARCTANH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ARCTANH);
			break;
		case FUNCTION_CEILING:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_CEILING);
			break;
		case FUNCTION_COS:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_COS);
			break;
		case FUNCTION_COSH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_COSH);
			break;
		case FUNCTION_COT:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_COT);
			break;
		case FUNCTION_COTH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_COTH);
			break;
		case FUNCTION_CSC:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_CSC);
			break;
		case FUNCTION_CSCH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_CSCH);
			break;
		case FUNCTION_EXP:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_EXP);
			break;
		case FUNCTION_FACTORIAL:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_FACTORIAL);
			break;
		case FUNCTION_FLOOR:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_FLOOR);
			break;
		case FUNCTION_LN:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_LN);
			break;
		case FUNCTION_POWER:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_POWER);
			break;
		case FUNCTION_ROOT:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_ROOT);
			break;
		case FUNCTION_SEC:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_SEC);
			break;
		case FUNCTION_SECH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_SECH);
			break;
		case FUNCTION_SIN:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_SIN);
			break;
		case FUNCTION_SINH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_SINH);
			break;
		case FUNCTION_TAN:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_TAN);
			break;
		case FUNCTION_TANH:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_TANH);
			break;
		case FUNCTION:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION);
			libAstNode.setName(astnode.getName());
			break;
		case LAMBDA:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_LAMBDA);
			break;
		case LOGICAL_AND:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_LOGICAL_AND);
			break;
		case LOGICAL_XOR:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_LOGICAL_XOR);
			break;
		case LOGICAL_OR:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_LOGICAL_OR);
			break;
		case LOGICAL_NOT:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_LOGICAL_NOT);
			break;
		case FUNCTION_PIECEWISE:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_FUNCTION_PIECEWISE);
			break;
		case RELATIONAL_EQ:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_RELATIONAL_EQ);
			break;
		case RELATIONAL_GEQ:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_RELATIONAL_GEQ);
			break;
		case RELATIONAL_GT:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_RELATIONAL_GT);
			break;
		case RELATIONAL_NEQ:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_RELATIONAL_NEQ);
			break;
		case RELATIONAL_LEQ:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_RELATIONAL_LEQ);
			break;
		case RELATIONAL_LT:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_RELATIONAL_LT);
			break;
		default:
			libAstNode = new org.sbml.libsbml.ASTNode(
					libsbmlConstants.AST_UNKNOWN);
			break;
		}
		for (ASTNode child : astnode.getListOfNodes())
			libAstNode.addChild(convert(child));
		return libAstNode;
	}
}
