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
package org.sbml.jsbml.ext.spatial;

import java.text.MessageFormat;
import java.util.Map;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;


/**
 * 
 * @author Alex Thomas
 * @version $Rev$
 * @since 0.8
 */
public class OrdinalMapping extends AbstractSBase {

  /**
   * 
   */
  private static final long serialVersionUID = -7174553771288567408L;

  String geometryDefinition;
  Integer ordinal;




  public OrdinalMapping() {
    super();
  }


  /**
   * @param node
   */
  public OrdinalMapping(OrdinalMapping om) {
    super(om);

    if (om.isSetGeometryDefinition()) {
      setGeometryDefinition(new String(om.getGeometryDefinition()));
    }
    if (om.isSetOrdinal()) {
      setOrdinal(om.getOrdinal());
    }


  }


  /**
   * 
   * @param level
   * @param version
   */
  public OrdinalMapping(int level, int version) {
    super(level, version);
  }


  @Override
  public OrdinalMapping clone() {
    return new OrdinalMapping(this);
  }


  @Override
  public boolean equals(Object object) {
    boolean equal = super.equals(object);
    if (equal) {
      OrdinalMapping om = (OrdinalMapping) object;

      equal &= om.isSetOrdinal() == isSetOrdinal();
      if (equal && isSetOrdinal()) {
        equal &= om.getOrdinal() == getOrdinal();
      }

      equal &= om.isSetGeometryDefinition() == isSetGeometryDefinition();
      if (equal && isSetGeometryDefinition()) {
        equal &= om.getGeometryDefinition().equals(getGeometryDefinition());
      }
    }
    return equal;
  }

  /**
   * Returns the value of ordinal
   *
   * @return the value of ordinal
   */
  public int getOrdinal() {
    if (isSetOrdinal()) {
      return ordinal;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.ordinal, this);
  }


  /**
   * Returns whether ordinal is set
   *
   * @return whether ordinal is set
   */
  public boolean isSetOrdinal() {
    return this.ordinal != null;
  }


  /**
   * Sets the value of ordinal
   */
  public void setOrdinal(int ordinal) {
    int oldOrdinal = this.ordinal;
    this.ordinal = ordinal;
    firePropertyChange(SpatialConstants.ordinal, oldOrdinal, this.ordinal);
  }


  /**
   * Unsets the variable ordinal
   *
   * @return {@code true}, if ordinal was set before,
   *         otherwise {@code false}
   */
  public boolean unsetOrdinal() {
    if (isSetOrdinal()) {
      int oldOrdinal = this.ordinal;
      this.ordinal = null;
      firePropertyChange(SpatialConstants.ordinal, oldOrdinal, this.ordinal);
      return true;
    }
    return false;
  }

  /**
   * Returns the value of geometryDefinition
   *
   * @return the value of geometryDefinition
   */
  public String getGeometryDefinition() {
    if (isSetGeometryDefinition()) {
      return geometryDefinition;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.geometryDefinition, this);
  }


  /**
   * Returns whether geometryDefinition is set
   *
   * @return whether geometryDefinition is set
   */
  public boolean isSetGeometryDefinition() {
    return this.geometryDefinition != null;
  }


  /**
   * Sets the value of geometryDefinition
   */
  public void setGeometryDefinition(String geometryDefinition) {
    String oldGeometryDefinition = this.geometryDefinition;
    this.geometryDefinition = geometryDefinition;
    firePropertyChange(SpatialConstants.geometryDefinition, oldGeometryDefinition, this.geometryDefinition);
  }


  /**
   * Unsets the variable geometryDefinition
   *
   * @return {@code true}, if geometryDefinition was set before,
   *         otherwise {@code false}
   */
  public boolean unsetGeometryDefinition() {
    if (isSetGeometryDefinition()) {
      String oldGeometryDefinition = this.geometryDefinition;
      this.geometryDefinition = null;
      firePropertyChange(SpatialConstants.geometryDefinition, oldGeometryDefinition, this.geometryDefinition);
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 983;//Change this prime number
    int hashCode = super.hashCode();
    if (isSetOrdinal()) {
      hashCode += prime * getOrdinal();
    }
    if (isSetGeometryDefinition()) {
      hashCode += prime * getGeometryDefinition().hashCode();
    }
    return hashCode;
  }


  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetOrdinal()) {
      attributes.remove("ordinal");
      attributes.put(SpatialConstants.shortLabel + ":ordinal", String.valueOf(getOrdinal()));
    }
    if (isSetGeometryDefinition()) {
      attributes.remove("geometryDefinition");
      attributes.put(SpatialConstants.shortLabel + ":geometryDefinition",
        getGeometryDefinition());
    }
    return attributes;
  }


  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = (super.readAttribute(attributeName, prefix, value))
        && (SpatialConstants.shortLabel == prefix);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.ordinal)) {
        try {
          setOrdinal(StringTools.parseSBMLInt(value));
        } catch (Exception e) {
          MessageFormat.format(
            SpatialConstants.bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.ordinal);
        }
      }
      else if (attributeName.equals(SpatialConstants.geometryDefinition)) {
        try {
          setGeometryDefinition(value);
        } catch (Exception e) {
          MessageFormat.format(SpatialConstants.bundle.getString("COULD_NOT_READ"), value, SpatialConstants.geometryDefinition);
        }
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }


  @Override
  public TreeNode getChildAt(int childIndex) {
    return null;
  }


  @Override
  public int getChildCount() {
    return 0;
  }


  @Override
  public boolean getAllowsChildren() {
    return false;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OrdinalMapping [geometryDefinition=");
    builder.append(geometryDefinition);
    builder.append(", ordinal=");
    builder.append(ordinal);
    builder.append("]");
    return builder.toString();
  }

}