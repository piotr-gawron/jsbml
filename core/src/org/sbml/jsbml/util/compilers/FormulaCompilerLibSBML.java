/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2016 jointly by the following organizations:
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
package org.sbml.jsbml.util.compilers;

import java.util.List;

import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.SBMLException;

/**
 * This class creates C-like infix formula {@link String}s that represent the
 * content of {@link ASTNode}s and try to reproduce the output generated by the LibSBML L3 parser. 
 *
 * <p>This class give an output that is closer to the output produced by the libSBML
 * <a href="http://sbml.org/Software/libSBML/docs/java-api/org/sbml/libsbml/libsbml.html#formulaToL3String(org.sbml.libsbml.ASTNode)">formulaToL3String</a>
 * method.
 * 
 * @author Nicolas Rodriguez
 * @since 1.0
 * @version $Rev$
 * 
 */

public class FormulaCompilerLibSBML extends FormulaCompiler {

  
  /**
   * 
   */
  public FormulaCompilerLibSBML() {
    INVERSE_TRIGONOMETRIC_PREFIX = "arc";
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#and(java.util.List)
   */
  @Override
  public ASTNodeValue and(List<ASTNode> nodes) throws SBMLException {
    return logicalOperation(" && ", nodes);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#eq(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue eq(ASTNode left, ASTNode right) throws SBMLException {
    if (left != null && left.getParent().getChildCount() > 2 ||
        left != null && left.getParent().getChildCount() < 2) 
    {
      return function("eq", ((ASTNode) left.getParent()).getListOfNodes());
    }
    
    return new ASTNodeValue(relation(left, "==", right), this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#factorial(org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue factorial(ASTNode node) {
    return function("factorial", node);
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#geq(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue geq(ASTNode left, ASTNode right) throws SBMLException {
    if (left != null && left.getParent().getChildCount() > 2 ||
        left != null && left.getParent().getChildCount() < 2) 
    {
      return function("geq", ((ASTNode) left.getParent()).getListOfNodes());
    }
    
    return new ASTNodeValue(relation(left, ">=", right), this);
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#gt(org.sbml.jsbml.ASTNode,  org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue gt(ASTNode left, ASTNode right) throws SBMLException {
    if (left != null && left.getParent().getChildCount() > 2 ||
        left != null && left.getParent().getChildCount() < 2) 
    {
      return function("gt", ((ASTNode) left.getParent()).getListOfNodes());
    }
    
    return new ASTNodeValue(relation(left, ">", right), this);
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#leq(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue leq(ASTNode left, ASTNode right) throws SBMLException {
    if (left != null && left.getParent().getChildCount() > 2 ||
        left != null && left.getParent().getChildCount() < 2) 
    {
      return function("leq", ((ASTNode) left.getParent()).getListOfNodes());
    }
    
    return new ASTNodeValue(relation(left, "<=", right), this);
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#lt(org.sbml.jsbml.ASTNode,  org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue lt(ASTNode left, ASTNode right) throws SBMLException {
    if (left != null && left.getParent().getChildCount() > 2 ||
        left != null && left.getParent().getChildCount() < 2) 
    {
      return function("lt", ((ASTNode) left.getParent()).getListOfNodes());
    }
    
    return new ASTNodeValue(relation(left, "<", right), this);
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#neq(org.sbml.jsbml.ASTNode,  org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue neq(ASTNode left, ASTNode right) throws SBMLException {
    return new ASTNodeValue(relation(left, "!=", right), this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#not(org.sbml.jsbml.ASTNode)
   */
  @Override
  public ASTNodeValue not(ASTNode node) throws SBMLException {
    return function("!", node);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#or(java.util.List)
   */
  @Override
  public ASTNodeValue or(List<ASTNode> nodes) throws SBMLException {
    return logicalOperation(" || ", nodes);
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#xor(java.util.List)
   */
  @Override
  public ASTNodeValue xor(List<ASTNode> nodes) throws SBMLException {
    // TODO
    return function(" xor ", nodes);
  }
  


}
