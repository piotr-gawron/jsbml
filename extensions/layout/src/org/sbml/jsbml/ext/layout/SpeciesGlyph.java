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
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.layout;

import java.util.Map;

import org.sbml.jsbml.NamedSBase;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.util.TreeNodeChangeEvent;

/**
 * @author Nicolas Rodriguez
 * @author Sebastian Fr&ouml;lich
 * @author Andreas Dr&auml;ger
 * @author Clemens Wrzodek
 * @since 1.0
 * @version $Rev$
 */
public class SpeciesGlyph extends AbstractReferenceGlyph {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = 1077785483575936434L;

  /**
   * 
   */
  public SpeciesGlyph() {
    super();
  }

  /**
   * 
   * @param level
   * @param version
   */
  public SpeciesGlyph(int level, int version) {
    super(level, version);
  }

  /**
   * 
   * @param speciesGlyph
   */
  public SpeciesGlyph(SpeciesGlyph speciesGlyph) {
    super(speciesGlyph);
  }

  /**
   * 
   * @param id
   */
  public SpeciesGlyph(String id) {
    super(id);
  }

  /**
   * 
   * @param id
   * @param level
   * @param version
   */
  public SpeciesGlyph(String id, int level, int version) {
    super(id, level, version);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.layout.AbstractReferenceGlyph#clone()
   */
  @Override
  public SpeciesGlyph clone() {
    return new SpeciesGlyph(this);
  }

  /**
   * 
   * @return
   */
  public String getSpecies() {
    return getReference();
  }

  /**
   * Note that the return type of this method is {@link NamedSBase} because it
   * could be possible to link some element from other packages to this glyph.
   * 
   * @return
   */
  public NamedSBase getSpeciesInstance() {
    return getNamedSBaseInstance();
  }

  /**
   * @return the {@link #speciesId}
   */
  public boolean isSetSpecies() {
    return isSetReference();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractNamedSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix,
    String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix,
      value);

    if (!isAttributeRead)
    {
      if (attributeName.equals(LayoutConstants.species)) {
        setSpecies(value);
      }
      else
      {
        return false;
      }
    }
    return true;
  }

  /**
   * 
   * @param species
   */
  public void setSpecies(Species species) {
    setSpecies(species.getId());
  }

  /**
   * 
   * @param species
   */
  public void setSpecies(String species) {
    setReference(species, TreeNodeChangeEvent.species);
  }

  /**
   * 
   */
  public void unsetSpecies() {
    unsetReference();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.layout.GraphicalObject#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (isSetSpecies()) {
      attributes.put(LayoutConstants.shortLabel + ':' + LayoutConstants.species, getSpecies());
    }

    return attributes;
  }

}
