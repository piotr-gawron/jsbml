/*
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2019 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.validator.offline.constraints;

import java.util.Set;

import org.sbml.jsbml.ext.comp.CompSBMLDocumentPlugin;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;;

/**
 * Defines validation rules (as {@link ValidationFunction} instances) for the {@link CompSBMLDocumentPlugin} class.
 *  
 * @author rodrigue
 * @since 1.5
 */
public class CompSBMLDocumentPluginConstraints extends AbstractConstraintDeclaration {

  /* (non-Javadoc)
   * @see org.sbml.jsbml.validator.offline.constraints.ConstraintDeclaration#addErrorCodesForAttribute(java.util.Set, int, int, java.lang.String)
   */
  @Override
  public void addErrorCodesForAttribute(Set<Integer> set, int level,
    int version, String attributeName, ValidationContext context) 
  {
    // no specific attribute, so nothing to do.

  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.validator.offline.constraints.ConstraintDeclaration#addErrorCodesForCheck(java.util.Set, int, int, org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY)
   */
  @Override
  public void addErrorCodesForCheck(Set<Integer> set, int level, int version,
    CHECK_CATEGORY category, ValidationContext context) {

    switch (category) {
    case GENERAL_CONSISTENCY:

      addRangeToSet(set, COMP_20201, COMP_20212 );
      
      set.add(COMP_20714);
      
      break;
    case IDENTIFIER_CONSISTENCY:
      break;
    case MATHML_CONSISTENCY:
      break;
    case MODELING_PRACTICE:
      break;
    case OVERDETERMINED_MODEL:
      break;
    case SBO_CONSISTENCY:
      break;
    case UNITS_CONSISTENCY:
      break;
    }
  }


  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode, ValidationContext context) {
    ValidationFunction<CompSBMLDocumentPlugin> func = null;

    switch (errorCode) {

    case COMP_20201: // 
    {
      // TODO
      break;
    }
    case COMP_20202: // 
    {
      // TODO
      break;
    }
    // case COMP_20203: // removed
    // case COMP_20204: // removed
    case COMP_20205: // 
    {
      // TODO
      break;
    }
    case COMP_20206: // 
    {
      // TODO
      break;
    }
    case COMP_20207: // 
    {
      // TODO
      break;
    }
    case COMP_20208: // 
    {
      // TODO
      break;
    }
    case COMP_20209: // 
    {
      // TODO
      break;
    }
    case COMP_20210: // 
    {
      // TODO
      break;
    }
    case COMP_20211: // 
    {
      // TODO
      break;
    }
    case COMP_20212: // 
    {
      // TODO
      break;
    }
    
    case COMP_20714: // Implemented in the CompSBMLDocumentPlugin
    {
      // 20714 - Any one SBML object may only be referenced in one of the following ways: referenced by a single
      // Port object; referenced by a single Deletion object; referenced by a single ReplacedElement;
      // be the parent of a single ReplacedBy child; be referenced by one ormore ReplacedBy objects;
      // or be referenced by one or more ReplacedElement objects all using the deletion attribute.
      // Essentially, once an object has been referenced in one of these ways it cannot be referenced
      // again.
      // TODO
      break;
    }
    }

    return func;
  }
}
