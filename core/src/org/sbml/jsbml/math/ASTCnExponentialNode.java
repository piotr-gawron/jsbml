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

import org.apache.log4j.Logger;
import org.sbml.jsbml.ASTNode.Type;
import org.sbml.jsbml.MathContainer;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.math.compiler.ASTNode2Compiler;
import org.sbml.jsbml.math.compiler.ASTNode2Value;
import org.sbml.jsbml.math.compiler.FormulaCompiler;
import org.sbml.jsbml.math.compiler.LaTeXCompiler;
import org.sbml.jsbml.math.compiler.MathMLXMLStreamCompiler;
import org.sbml.jsbml.util.TreeNodeChangeEvent;
import org.sbml.jsbml.util.ValuePair;


/**
 * An Abstract Syntax Tree (AST) node representing an exponent
 * in a mathematical expression
 * 
 * @author Victor Kofia
 * @version $Rev$
 * @since 1.0
 * @date May 30, 2014
 */
public class ASTCnExponentialNode extends ASTCnNumberNode<ValuePair<Integer,Integer>> {

  /**
   * 
   */
  private static final long serialVersionUID = 2454959490592406140L;

  /**
   * A {@link Logger} for this class.
   */
  private static final Logger logger = Logger.getLogger(ASTCnExponentialNode.class);

  /**
   * Creates a new {@link ASTCnExponentialNode} that lacks a pointer
   * to its containing {@link MathContainer}.
   */
  public ASTCnExponentialNode() {
    super();
    setType(Type.REAL_E);
  }

  /**
   * Copy constructor; Creates a deep copy of the given
   * {@link ASTCnExponentialNode}.
   * 
   * @param node
   *            the {@link ASTCnExponentialNode} to be copied.
   */
  public ASTCnExponentialNode (ASTCnExponentialNode node) {
    super(node);
    setType(Type.REAL_E);
  }
  
  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCnNumberNode#clone()
   */
  @Override
  public ASTCnExponentialNode clone() {
    return new ASTCnExponentialNode(this);
  }
  
  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTNode2#compile(org.sbml.jsbml.util.compilers.ASTNode2Compiler)
   */
  @Override
  public ASTNode2Value<?> compile(ASTNode2Compiler compiler) {
    ASTNode2Value<?> value = compiler.compile(getMantissa(), getExponent(),
      isSetUnits() ? getUnits() : null);
    return processValue(value);
  }

  /**
   * Get the exponent value of this node.
   * 
   * @return int exponent
   */
  public int getExponent() {
    if (isSetExponent()) {
      return number.getL();
    }
    PropertyUndefinedError error = new PropertyUndefinedError("exponent", this);
    if (isStrict()) {
      throw error;
    }
    logger.warn(error);
    return 0;
  }

  /**
   * Get the mantissa value of this node. 
   * 
   * @return int mantissa
   */
  public int getMantissa() {
    if (isSetMantissa()) {
      return number.getV();
    }
    PropertyUndefinedError error = new PropertyUndefinedError("mantissa", this);
    if (isStrict()) {
      throw error;
    }
    logger.warn(error);
    return 0;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTCnNumberNode#isAllowableType(org.sbml.jsbml.ASTNode.Type)
   */
  @Override
  public boolean isAllowableType(Type type) {
    return type == Type.REAL_E;
  }

  /**
   * Returns True iff exponent has been set
   * 
   * @param null
   * @return boolean
   */
  public boolean isSetExponent() {
    return this.number == null ? false : this.number.getL() != null;
  }

  /**
   * Returns True iff mantissa has been set
   * 
   * @param null
   * @return boolean
   */
  public boolean isSetMantissa() {
    return this.number == null ? false : this.number.getV() != null;
  }

  /**
   * Set the exponent value of this node
   * 
   * @param int exponent
   */
  public void setExponent(int exponent) {
    if (! isSetNumber()) {
      setNumber(new ValuePair<Integer,Integer>());
    }
    Integer old = this.number.getL();
    this.number.setL(exponent);
    firePropertyChange(TreeNodeChangeEvent.exponent, old, this.number.getL());
  }

  /**
   * Get the mantissa value of this node
   * 
   * @param int mantissa
   */
  public void setMantissa(int mantissa) {
    if (! isSetNumber()) {
      setNumber(new ValuePair<Integer,Integer>());
    }
    Integer old = this.number.getV();
    this.number.setV(mantissa);
    firePropertyChange(TreeNodeChangeEvent.mantissa, old, this.number.getV());
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.AbstractASTNode#toFormula()
   */
  @Override
  public String toFormula() throws SBMLException {
    return compile(new FormulaCompiler()).toString();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.AbstractASTNode#toLaTeX()
   */
  @Override
  public String toLaTeX() throws SBMLException {
    return compile(new LaTeXCompiler()).toString();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.AbstractASTNode#toMathML()
   */
  @Override
  public String toMathML() {
    try {
      return MathMLXMLStreamCompiler.toMathML(this);
    } catch (RuntimeException e) {
      logger.error("Unable to create MathML");
      return null;
    }
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ASTCnExponentialNode [number=");
    builder.append(number);
    builder.append(", parentSBMLObject=");
    builder.append(parentSBMLObject);
    builder.append(", strict=");
    builder.append(strict);
    builder.append(", type=");
    builder.append(type);
    builder.append(", id=");
    builder.append(id);
    builder.append(", style=");
    builder.append(style);
    builder.append(", listOfListeners=");
    builder.append(listOfListeners);
    builder.append(", parent=");
    builder.append(parent);
    builder.append("]");
    return builder.toString();
  }

}
