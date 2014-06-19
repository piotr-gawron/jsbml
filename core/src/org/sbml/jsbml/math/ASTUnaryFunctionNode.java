/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2014  jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 6. The University of Toronto, Toronto, ON, Canada
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.math;

/**
 * An Abstract Syntax Tree (AST) node representing a function with only one
 * parameter
 * 
 * @author Victor Kofia
 * @version $Rev$
 * @since 1.0
 * @date May 30, 2014
 */
public class ASTUnaryFunctionNode extends ASTFunction {

  /**
   * Creates a new {@link ASTUnaryFunctionNode}.
   */
  public ASTUnaryFunctionNode() {
    super();
  }

  /**
   * Copy constructor; Creates a deep copy of the given {@link ASTUnaryFunctionNode}.
   * 
   * @param node
   *            the {@link ASTUnaryFunctionNode} to be copied.
   */
  public ASTUnaryFunctionNode(ASTUnaryFunctionNode node) {
    super(node);
  }

  /**
   * Get the child of this node
   * 
   * @return {@link ASTNode2} child
   */
  public ASTNode2 getChild() {
    return getChildAt(0);
  }

  /**
   * Set the child of this node
   * 
   * @param {@link ASTNode2} child
   */
  public void setChild(ASTNode2 child) {
    replaceChild(0, child);
  }

  // TODO: Override clone method with specific return type.

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ASTUnaryFunctionNode [strict=");
    builder.append(strict);
    builder.append(", type=");
    builder.append(type);
    builder.append("]");
    return builder.toString();
  }

}
