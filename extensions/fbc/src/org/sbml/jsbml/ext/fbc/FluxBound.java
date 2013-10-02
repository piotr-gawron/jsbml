/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2013 jointly by the following organizations:
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
package org.sbml.jsbml.ext.fbc;

import java.util.Map;

import org.sbml.jsbml.AbstractNamedSBase;
import org.sbml.jsbml.LevelVersionError;
import org.sbml.jsbml.UniqueNamedSBase;
import org.sbml.jsbml.util.StringTools;

/**
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 * @date 27.10.2011
 */
public class FluxBound extends AbstractNamedSBase implements UniqueNamedSBase {


  /**
   * 
   */
  private static final long serialVersionUID = -8885319163985464653L;
  private String reaction;
  private String operation; // TODO: make it an Enumeration
  private double value;

  private boolean isSetValue = false;

  /**
   * 
   * @param fb
   */
  public FluxBound(FluxBound fb) {
    super(fb);
    this.operation = new String(fb.getOperation());
    this.reaction = new String(fb.getReaction());
    this.value = fb.getValue();
    this.isSetValue = fb.isSetValue();
  }

  /**
   * Creates an instance of FluxBound.
   */
  public FluxBound() {
    super();
    initDefaults();
  }

  /**
   * Creates a FluxBound instance with an id. 
   * 
   * @param id
   */
  public FluxBound(String id) {
    super(id);
    initDefaults();
  }

  /**
   * Creates a FluxBound instance with a level and version. 
   * 
   * @param level
   * @param version
   */
  public FluxBound(int level, int version) {
    this(null, null, level, version);
  }

  /**
   * Creates a FluxBound instance with an id, level, and version. 
   * 
   * @param id
   * @param level
   * @param version
   */
  public FluxBound(String id, int level, int version) {
    this(id, null, level, version);
  }

  /**
   * Creates a FluxBound instance with an id, name, level, and version. 
   * 
   * @param id
   * @param name
   * @param level
   * @param version
   */
  public FluxBound(String id, String name, int level, int version) {
    super(id, name, level, version);
    if (getLevelAndVersion().compareTo(
      Integer.valueOf(FBCConstants.MIN_SBML_LEVEL),
      Integer.valueOf(FBCConstants.MIN_SBML_VERSION)) < 0) {
      throw new LevelVersionError(getElementName(), level, version);
    }
    initDefaults();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#clone()
   */
  public FluxBound clone() {
    return new FluxBound(this);
  }

  /**
   * Initializes the default values using the namespace.
   */
  public void initDefaults() {
    addNamespace(FBCConstants.namespaceURI);
  }

  // TODO: re-write the getters and setters using the new template to get the change events launched.

  /**
   * Returns the reaction id
   * 
   * @return the reaction id
   */
  public String getReaction() {
    return reaction;
  }

  /**
   * Sets the the reaction id
   * 
   * @param reaction the reaction id to set
   */
  public void setReaction(String reaction) {
    this.reaction = reaction;
  }

  /**
   * Returns the operation
   * 
   * @return the operation
   */
  public String getOperation() {
    return operation;
  }

  /**
   * @param operation the operation to set
   */
  public void setOperation(String operation) {
    this.operation = operation;
  }

  /**
   * Returns the value
   * 
   * @return the value
   */
  public double getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(double value) {
    this.value = value;
    isSetValue = true;
  }

  /**
   * 
   * @return
   */
  public boolean isSetValue() {
    return isSetValue;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#isIdMandatory()
   */
  public boolean isIdMandatory() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.element.SBase#readAttribute(String attributeName, String prefix, String value)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix,
      value);

    if (!isAttributeRead) {
      isAttributeRead = true;

      if (attributeName.equals("reaction")) {
        setReaction(value);
      } else if (attributeName.equals("operation")) {
        setOperation(value);
      } else if (attributeName.equals("value")) {
        setValue(StringTools.parseSBMLDouble(value));
      } else {
        isAttributeRead = false;
      }

    }

    return isAttributeRead;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.element.SBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (reaction != null) {
      attributes.put(FBCConstants.shortLabel + ":reaction", getReaction());			
    }
    if (operation != null) {
      attributes.put(FBCConstants.shortLabel + ":operation", getOperation());
    }
    if (isSetValue()) {
      attributes.put(FBCConstants.shortLabel + ":value", StringTools.toString(getValue()));
    }
    if (isSetId()) {
      attributes.remove("id");
      attributes.put(FBCConstants.shortLabel + ":id", getId());
    }

    return attributes;
  }

  /**
   * 
   * @param operation
   */
  public void setOperation(Operation operation) {
    setOperation(operation.name());
  }

}
