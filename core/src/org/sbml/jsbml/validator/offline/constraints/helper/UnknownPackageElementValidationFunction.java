/*
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2017 jointly by the following organizations:
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
package org.sbml.jsbml.validator.offline.constraints.helper;

import java.util.List;

import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.util.TreeNodeWithChangeSupport;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.ValidationFunction;
import org.sbml.jsbml.xml.XMLNode;


/**
 * Class used to check if any unknown XML elements where found.
 * 
 * @author rodrigue
 */
public class UnknownPackageElementValidationFunction<T extends TreeNodeWithChangeSupport> implements ValidationFunction<T> {

  /**
   * the package label for which we need to check for invalid elements.
   */
  private String shortLabel;
  
  /**
   * Creates a new instance of {@link UnknownPackageElementValidationFunction}
   */
  public UnknownPackageElementValidationFunction(String packageLabel) {
    this.shortLabel = packageLabel;
  }
  

  @Override
  public boolean check(ValidationContext ctx, T t) {

    if (t.isSetUserObjects() && t.getUserObject(JSBML.UNKNOWN_XML) != null)
    {
      XMLNode unknownNode = (XMLNode) t.getUserObject(JSBML.UNKNOWN_XML);

      List<XMLNode> childElements = unknownNode.getChildElements(null, null);

      if (childElements.size() > 0) {
        
        SBMLDocument doc = (SBMLDocument) t.getRoot();
        String packageNamespace = doc.getEnabledPackageNamespace(shortLabel);

        for (XMLNode child : childElements) {
          String childURI = child.getURI();
          
          if (childURI.equals(packageNamespace)) {
            // TODO - create the proper SBMLError that contain useful information for the user.
            return false;
          }
        }
        
        return true;
      }
    }

    return true;
  }
}