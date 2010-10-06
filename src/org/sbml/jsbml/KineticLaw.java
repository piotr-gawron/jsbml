/*
 * $Id$
 * $URL$
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

package org.sbml.jsbml;

import java.util.HashMap;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.ListOf.Type;
import org.sbml.jsbml.util.filters.NameFilter;

/**
 * Represents the kineticLaw XML element of a SBML file.
 * 
 * @author Andreas Dr&auml;ger
 * @author marine
 * 
 * @opt attributes
 * @opt types
 * @opt visibility
 * 
 * @composed 0..* ListOf 1 LocalParameter
 */
public class KineticLaw extends MathContainer {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = 7528194464711501708L;
	/**
	 * Represents the listOfLocalParameters or listOfParameters sub-element of a
	 * kineticLaw element.
	 */
	private ListOf<LocalParameter> listOfParameters;
	/**
	 * Represents the 'substanceUnits' XML attribute of this KineticLaw.
	 * 
	 * @deprecated
	 */
	@Deprecated
	private String substanceUnitsID;
	/**
	 * Represents the 'timeUnits' XML attribute of this KineticLaw.
	 * 
	 * @deprecated
	 */
	@Deprecated
	private String timeUnitsID;

	/**
	 * Creates a KineticLaw instance. By default, this listOfParameters, the
	 * timeUnitsID and substanceUnitsID are null.
	 */
	public KineticLaw() {
		super();
		initDefaults();
	}

	/**
	 * Creates a KineticLaw instance from a level and version. By default, this
	 * listOfParameters, the timeUnitsID and substanceUnitsID are null.
	 * 
	 * @param level
	 * @param version
	 */
	public KineticLaw(int level, int version) {
		super(level, version);
		initDefaults();
	}

	/**
	 * Creates a KineticLaw instance from a given KineticLaw.
	 * 
	 * @param kineticLaw
	 */
	public KineticLaw(KineticLaw kineticLaw) {
		super(kineticLaw);
		initDefaults();
		if (kineticLaw.isSetListOfParameters()) {
			setListOfLocalParameters((ListOf<LocalParameter>) kineticLaw
					.getListOfParameters().clone());
		}
		if (kineticLaw.isSetTimeUnits()) {
			this.timeUnitsID = new String(kineticLaw.getTimeUnits());
		} else {
			timeUnitsID = null;
		}
		if (kineticLaw.isSetSubstanceUnits()) {
			this.substanceUnitsID = new String(kineticLaw.getSubstanceUnits());
		} else {
			substanceUnitsID = null;
		}
	}

	/**
	 * Creates a KineticLaw instance from a given Reaction.
	 * 
	 * @param parentReaction
	 */
	public KineticLaw(Reaction parentReaction) {
		this(parentReaction.getLevel(), parentReaction.getVersion());
		parentReaction.setKineticLaw(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.sbml.jsbml.element.SBase#addChangeListener(org.sbml.squeezer.io.
	 * SBaseChangedListener )
	 */
	@Override
	public void addChangeListener(SBaseChangedListener l) {
		super.addChangeListener(l);
		if (isSetListOfParameters()) {
			listOfParameters.addChangeListener(l);
		}
	}

	/**
	 * Adds a copy of the given Parameter object to the list of local parameters
	 * in this KineticLaw.
	 * 
	 * @param parameter
	 */
	public void addLocalParameter(LocalParameter parameter) {
		if (!isSetListOfParameters()) {
			initListOfParameters();
		}
		if (!getListOfParameters().contains(parameter)) {
			listOfParameters.add(parameter);
			if (parameter.isSetId() && isSetMath()) {
				getMath().updateVariables();
			}
			stateChanged();
		}
	}

	/**
	 * Adds a copy of the given Parameter object to the list of local parameters
	 * in this KineticLaw.
	 * 
	 * @param p
	 * @deprecated use {@link #addLocalParameter(LocalParameter)}.
	 */
	@Deprecated
	public void addParameter(LocalParameter parameter) {
		addLocalParameter(parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public KineticLaw clone() {
		return new KineticLaw(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.element.MathContainer#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof KineticLaw) {
			KineticLaw kl = (KineticLaw) o;
			if ((!kl.isSetListOfParameters() && isSetListOfParameters())
					|| (kl.isSetListOfParameters() && !isSetListOfParameters())) {
				return false;
			}
			boolean equal = super.equals(o);
			if (kl.isSetListOfParameters() && isSetListOfParameters()) {
				equal &= kl.getListOfParameters().equals(getListOfParameters());
			}
			if ((!kl.isSetTimeUnits() && isSetTimeUnits())
					|| (kl.isSetTimeUnits() && !isSetTimeUnits())) {
				return false;
			}
			if (kl.isSetTimeUnits() && isSetTimeUnits()) {
				equal &= kl.getTimeUnits().equals(getTimeUnits());
			}
			if ((!kl.isSetSubstanceUnits() && isSetSubstanceUnits())
					|| (kl.isSetSubstanceUnits() && !isSetSubstanceUnits())) {
				return false;
			}
			if (kl.isSetSubstanceUnits() && isSetSubstanceUnits()) {
				equal &= kl.getSubstanceUnits().equals(getSubstanceUnits());
			}
			return equal;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.MathContainer#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int index) {
		int children = getChildCount();
		if (index >= children) {
			throw new IndexOutOfBoundsException(index + " >= " + children);
		}
		int pos = 0;
		if (isSetMath()) {
			if (pos == index) {
				return getMath();
			}
			pos++;
		}
		if (isSetListOfParameters()) {
			if (pos == index) {
				return getListOfParameters();
			}
			pos++;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.MathContainer#getChildCount()
	 */
	@Override
	public int getChildCount() {
		int children = super.getChildCount();
		if (isSetListOfParameters()) {
			children++;
		}
		return children;
	}

	/**
	 * 
	 * @return the listOfParameters of this KineticLaw. Return null if it is not
	 *         set.
	 */
	public ListOf<LocalParameter> getListOfParameters() {
		if (!isSetListOfParameters()) {
			initListOfParameters();
		}
		return listOfParameters;
	}

	/**
	 * 
	 * @return the number of local parameters in this KineticLaw instance.
	 */
	public int getNumParameters() {
		return isSetListOfParameters() ? listOfParameters.size() : 0;
	}

	/**
	 * 
	 * @param i
	 * @return the ith Parameter object in the list of local parameters in this
	 *         KineticLaw instance.
	 */
	public LocalParameter getParameter(int i) {
		return getListOfParameters().get(i);
	}

	/**
	 * 
	 * @param id
	 * @return a local parameter based on its identifier.
	 */
	public LocalParameter getParameter(String id) {
		return getListOfParameters().firstHit(new NameFilter(id));
	}

	/**
	 * This method is convenient when holding an object nested inside other
	 * objects in an SBML model. It allows direct access to the &lt;model&gt;
	 * 
	 * element containing it.
	 * 
	 * @return Returns the parent SBML object.
	 */
	@Override
	public Reaction getParentSBMLObject() {
		return (Reaction) parentSBMLObject;
	}

	/**
	 * 
	 * @return the substanceUnitsID of this KineticLaw. Return the empty String
	 *         if it is not set.
	 */
	@Deprecated
	public String getSubstanceUnits() {
		return isSetSubstanceUnits() ? this.substanceUnitsID : "";
	}

	/**
	 * 
	 * @return the UnitDefinition instance which has the substanceUnistID of
	 *         this KineticLaw as id. Return null if it doesn't exist.
	 */
	@Deprecated
	public UnitDefinition getSubstanceUnitsInstance() {
		if (getModel() == null) {
			return null;
		}
		return getModel().getUnitDefinition(this.substanceUnitsID);
	}

	/**
	 * 
	 * @return the timeUnitsID of this KineticLaw. Return the empty String if it
	 *         is not set.
	 */
	@Deprecated
	public String getTimeUnits() {
		return isSetTimeUnits() ? this.timeUnitsID : "";
	}

	/**
	 * 
	 * @return the UnitDefinition instance which has the timeUnistID of this
	 *         KineticLaw as id. Return null if it doesn't exist.
	 */
	@Deprecated
	public UnitDefinition getTimeUnitsInstance() {
		Model m = getModel();
		return m != null ? m.getUnitDefinition(this.timeUnitsID) : null;
	}

	/**
	 * 
	 */
	public void initDefaults() {
		// We cannot initialize this list here because maybe level and version
		// are set later, which will then cause inconsistencies.
		// initListOfParameters();
		this.timeUnitsID = null;
		this.substanceUnitsID = null;
	}

	/**
	 * 
	 */
	private void initListOfParameters() {
		this.listOfParameters = new ListOf<LocalParameter>(getLevel(),
				getVersion());
		setThisAsParentSBMLObject(this.listOfParameters);
		this.listOfParameters.setSBaseListType(Type.listOfParameters);
	}

	/**
	 * 
	 * @return true if the listOfParameters of this KineticLaw is not null and
	 *         not empty.
	 */
	public boolean isSetListOfParameters() {
		return (listOfParameters != null) && (listOfParameters.size() > 0);
	}

	/**
	 * 
	 * @return true if the substanceUnitsID of this KineticLaw is not null.
	 */
	@Deprecated
	public boolean isSetSubstanceUnits() {
		return this.substanceUnitsID != null;
	}

	/**
	 * 
	 * @return true if the UnistDefinition instance which has the
	 *         substanceUnitsID of this KineticLaw as id is not null.
	 * @deprecated
	 */
	@Deprecated
	public boolean isSetSubstanceUnitsInstance() {
		Model m = getModel();
		return m != null ? m.getUnitDefinition(this.substanceUnitsID) != null
				: false;
	}

	/**
	 * 
	 * @return true if the timeUnitsID of this KineticLaw is not null.
	 * @deprecated
	 */
	@Deprecated
	public boolean isSetTimeUnits() {
		return this.timeUnitsID != null;
	}

	/**
	 * 
	 * @return true if the UnistDefinition instance which has the timeUnitsID of
	 *         this KineticLaw as id is not null.
	 * @deprecated
	 */
	@Deprecated
	public boolean isSetTimeUnitsInstance() {
		Model m = getModel();
		return m != null ? m.getUnitDefinition(this.timeUnitsID) != null
				: false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.MathContainer#readAttribute(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = super.readAttribute(attributeName, prefix,
				value);

		if (!isAttributeRead) {
			if (attributeName.equals("timeUnits")
					&& ((getLevel() == 2 && getVersion() == 1) || getLevel() == 1)) {
				setTimeUnits(value);
			} else if (attributeName.equals("substanceUnits")
					&& ((getLevel() == 2 && getVersion() == 1) || getLevel() == 1)) {
				setSubstanceUnits(value);
			}
		}
		return isAttributeRead;
	}

	/**
	 * Removes the ith local parameter from this object.
	 * 
	 * @param i
	 */
	public void removeParameter(int i) {
		if (isSetListOfParameters()) {
			listOfParameters.remove(i).sbaseRemoved();
		}
	}

	/**
	 * Removes the parameter 'p' from the listOfParameters of this KineticLaw.
	 * 
	 * @param p
	 */
	public void removeParameter(Parameter p) {
		if (isSetListOfParameters()) {
			listOfParameters.remove(p);

		}
	}

	/**
	 * Removes the ith local parameter from this object based on its id.
	 * 
	 * @param i
	 */
	public void removeParameter(String id) {
		if (isSetListOfParameters()) {
			int i = 0;
			ListOf<LocalParameter> listOfParameters = this.listOfParameters;
			while (i < listOfParameters.size()) {
				if (listOfParameters.get(i) instanceof LocalParameter) {
					LocalParameter parameter = listOfParameters.get(i);
					if (parameter.isSetId()) {
						if (!parameter.getId().equals(id)) {
							i++;
						}
					} else if (parameter.isSetName()) {
						if (!parameter.getName().equals(id)) {
							i++;
						}
					} else {
						break;
					}
				} else {
					break;
				}
			}
			if (i < listOfParameters.size()) {
				listOfParameters.remove(i).sbaseRemoved();
			}
		}
	}

	/**
	 * Sets the listOfParameters of this KineticLaw to 'list'. It automatically
	 * sets this as parentSBML object of the listOfParameters as well as the
	 * Parameter instances in the list.
	 * 
	 * @param list
	 */
	public void setListOfLocalParameters(ListOf<LocalParameter> list) {
		if (list == null) {
			unsetListOfLocalParameters();
		}
		if (getLevel() != list.getLevel()) {
			throw new IllegalArgumentException(JSBML.levelMismatchMessage(this,
					list));
		}
		if (getVersion() != list.getVersion()) {
			throw new IllegalArgumentException(JSBML.versionMismatchMessage(
					this, list));
		}
		this.listOfParameters = list;
		setThisAsParentSBMLObject(this.listOfParameters);
		this.listOfParameters
				.setSBaseListType(ListOf.Type.listOfLocalParameters);
		if (isSetMath()) {
			getMath().updateVariables();
		}
	}

	/**
	 * Sets the substanceUnitsID of this KineticLaw.
	 * 
	 * @param substanceUnits
	 * @deprecated
	 */
	@Deprecated
	public void setSubstanceUnits(String substanceUnits) {
		this.substanceUnitsID = substanceUnits;
	}

	/**
	 * Sets the substanceUnitsID of this KineticLaw.
	 * 
	 * @param substanceUnits
	 * @deprecated
	 */
	@Deprecated
	public void setSubstanceUnitsInstance(UnitDefinition substanceUnits) {
		this.substanceUnitsID = substanceUnits.isSetId() ? substanceUnits
				.getId() : null;
	}

	/**
	 * Sets the timeUnitsID of this KineticLaw.
	 * 
	 * @param timeUnits
	 * @deprecated
	 */
	@Deprecated
	public void setTimeUnits(String timeUnits) {
		this.timeUnitsID = timeUnits;
	}

	/**
	 * Sets the timeUnitsID of this KineticLaw.
	 * 
	 * @param timeUnits
	 * @deprecated
	 */
	@Deprecated
	public void setTimeUnitsInstance(UnitDefinition timeUnits) {
		this.timeUnitsID = timeUnits.isSetId() ? timeUnits.getId() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.MathContainer#toString()
	 */
	@Override
	public String toString() {
		StringBuilder kineticLawStr = new StringBuilder();
		kineticLawStr.append("kineticLaw(");
		kineticLawStr.append(((Reaction) getParent()).getId());
		kineticLawStr.append(") : ");
		kineticLawStr.append(super.toString());
		return kineticLawStr.toString();
	}

	/**
	 * 
	 */
	public void unsetListOfLocalParameters() {
		if (listOfParameters != null) {
			listOfParameters.clear();
		}
		listOfParameters = null;
		stateChanged();
	}

	/**
	 * Unsets the sunbstanceUnistID of this KineticLaw.
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void unsetSubstanceUnits() {
		this.substanceUnitsID = null;
	}

	/**
	 * Unsets the timeUnitsID of this KineticLaw.
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void unsetTimeUnits() {
		this.timeUnitsID = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.MathContainer#writeXMLAttributes()
	 */
	@Override
	public HashMap<String, String> writeXMLAttributes() {
		HashMap<String, String> attributes = super.writeXMLAttributes();

		if (isSetTimeUnits()
				&& ((getLevel() == 2 && getVersion() == 1) || getLevel() == 1)) {
			attributes.put("timeUnits", getTimeUnits());
		} else if (isSetSubstanceUnits()
				&& ((getLevel() == 2 && getVersion() == 1) || getLevel() == 1)) {
			attributes.put("substanceUnits", getSubstanceUnits());
		}

		return attributes;
	}

}
