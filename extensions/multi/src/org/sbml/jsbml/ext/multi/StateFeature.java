/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2014 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.multi;

import java.text.MessageFormat;
import java.util.Map;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.AbstractNamedSBase;
import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.UniqueNamedSBase;
import org.sbml.jsbml.util.filters.NameFilter;

/**
 * A species type ({@link SpeciesType}) can carry any number of state features
 * ({@link StateFeature}), which are characteristic properties specific
 * for this type of species ({@link Species}). The element {@link StateFeature}
 * of SBML Level 3 Version 1 multi Version 1
 * corresponds to the "state variable" of the SBGN Entity Relationship language.
 * A {@link StateFeature} is identified by an id and an optional name. A
 * {@link StateFeature} is linked to a list of PossibleValues.
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 */
public class StateFeature extends AbstractNamedSBase implements UniqueNamedSBase {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -1796119514784158560L;
  /**
   * 
   */
  ListOf<PossibleValue> listOfPossibleValues;


  @Override
  public boolean isIdMandatory() {
    return false;
  }

  @Override
  public AbstractSBase clone() {
    // TODO
    return null;
  }

  public StateFeature() {
    super();
    initDefaults();
  }

  /**
   * Returns the listOfPossibleValues
   * 
   * @return the listOfPossibleValues
   */
  public ListOf<PossibleValue> getListOfPossibleValues() {
    if (listOfPossibleValues == null) {
      listOfPossibleValues = new ListOf<PossibleValue>();
      listOfPossibleValues.setNamespace(MultiConstants.namespaceURI);
      registerChild(listOfPossibleValues);
      listOfPossibleValues.setSBaseListType(ListOf.Type.other);
    }

    return listOfPossibleValues;
  }

  /**
   * Adds a PossibleValue.
   * 
   * @param possibleValue the PossibleValue to add
   */
  public void addPossibleValue(PossibleValue possibleValue) {
    getListOfPossibleValues().add(possibleValue);
  }

  /**
   * Creates a new {@link PossibleValue} inside this {@link StateFeature} and returns it.
   * <p>
   * 
   * @return the {@link PossibleValue} object created
   *         <p>
   * @see #addPossibleValue(PossibleValue r)
   */
  public PossibleValue createPossibleValue() {
    return createPossibleValue(null);
  }

  /**
   * Creates a new {@link PossibleValue} inside this {@link StateFeature} and returns it.
   * 
   * @param id
   *        the id of the new element to create
   * @return the {@link PossibleValue} object created
   */
  public PossibleValue createPossibleValue(String id) {
    PossibleValue possibleValue = new PossibleValue();
    possibleValue.setId(id);
    addPossibleValue(possibleValue);

    return possibleValue;
  }

  /**
   * Gets the ith {@link PossibleValue}.
   * 
   * @param i
   * 
   * @return the ith {@link PossibleValue}
   * @throws IndexOutOfBoundsException if the index is invalid.
   */
  public PossibleValue getPossibleValue(int i) {
    return getListOfPossibleValues().get(i);
  }

  /**
   * Gets the {@link PossibleValue} that has the given id.
   * 
   * @param id
   * @return the {@link PossibleValue} that has the given id or null if
   * no {@link PossibleValue} are found that match {@code id}.
   */
  public PossibleValue getPossibleValue(String id) {
    if (isSetListOfPossibleValues()) {
      return listOfPossibleValues.firstHit(new NameFilter(id));
    }
    return null;
  }

  /**
   * Returns {@code true} if the listOfPossibleValue is set.
   * 
   * @return {@code true} if the listOfPossibleValue is set.
   */
  public boolean isSetListOfPossibleValues() {
    if ((listOfPossibleValues == null) || listOfPossibleValues.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Sets the listOfPossibleValues to null
   * 
   * @return {@code true} is successful
   */
  public boolean unsetListOfPossibleValues() {
    if (isSetListOfPossibleValues()) {
      // unregister the ids if needed.
      listOfPossibleValues.fireNodeRemovedEvent();
      listOfPossibleValues = null;
      return true;
    }
    return false;
  }


  /**
   * 
   */
  public void initDefaults() {
    setNamespace(MultiConstants.namespaceURI);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#getChildAt(int)
   */
  @Override
  public TreeNode getChildAt(int index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException(index + " < 0");
    }

    int count = super.getChildCount(), pos = 0;
    if (index < count) {
      return super.getChildAt(index);
    } else {
      index -= count;
    }
    if (isSetListOfPossibleValues()) {
      if (pos == index) {
        return getListOfPossibleValues();
      }
      pos++;
    }

    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}",
      index, +Math.min(pos, 0)));
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#getChildCount()
   */
  @Override
  public int getChildCount() {
    int count = super.getChildCount();

    if (isSetListOfPossibleValues()) {
      count++;
    }

    return count;
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractNamedSBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (isSetId()) {
      attributes.remove("id");
      attributes.put(MultiConstants.shortLabel+ ":id", getId());
    }
    if (isSetName()) {
      attributes.remove("name");
      attributes.put(MultiConstants.shortLabel+ ":name", getName());
    }

    return attributes;
  }

  // TODO: equals, hashCode, toString, more constructors, ...
}