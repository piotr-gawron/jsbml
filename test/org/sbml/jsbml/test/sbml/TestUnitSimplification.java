/*
 * $Id$
 * $URL$
 *
 * ---------------------------------------------------------------------------- 
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML> 
 * for the latest version of JSBML and more information about SBML. 
 * 
 * Copyright (C) 2009-2011 jointly by the following organizations: 
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
package org.sbml.jsbml.test.sbml;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.sbml.jsbml.Unit;
import org.sbml.jsbml.Unit.Kind;
import org.sbml.jsbml.UnitDefinition;


/**
 * @author Andreas Dr&auml;ger
 * @version $Rev$
 * @since 1.0
 * @date 21.03.2012
 */
public class TestUnitSimplification {

  private static final int level = 3, version = 1;
  /**
   * mole per litre
   */
  private UnitDefinition ud1;
  /**
   * micro litre
   */
  private UnitDefinition ud2;
  /**
   * hours
   */
  private UnitDefinition ud3;
  /**
   * minutes
   */
  private UnitDefinition ud4;
  /**
   * micro mole
   */
  private UnitDefinition ud5;
  /**
   * milli litre
   */
  private UnitDefinition ud6;
  /**
   * seconds
   */
  private UnitDefinition ud7;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    Locale.setDefault(Locale.ENGLISH);
    
    ud1 = new UnitDefinition("ud1", level, version);
    ud2 = new UnitDefinition("ud2", level, version);
    ud3 = new UnitDefinition("ud3", level, version);
    ud4 = new UnitDefinition("ud4", level, version);
    ud5 = new UnitDefinition("ud5", level, version);
    ud6 = new UnitDefinition("ud6", level, version);
    ud7 = new UnitDefinition("ud7", level, version);
    
    // mole per litre
    ud1.addUnit(Kind.MOLE);
    ud1.addUnit(new Unit(Kind.LITRE, -1d, level, version));
    
    // micro litre
    ud2.addUnit(new Unit(-6, Kind.LITRE, level, version));
    
    // hours
    ud3.addUnit(new Unit(3600d, 0, Kind.SECOND, 1d, level, version));
    
    // minutes
    ud4.addUnit(new Unit(60d, 0, Kind.SECOND, 1d, level, version));
    
    // micro mole
    ud5.addUnit(new Unit(-6, Kind.MOLE, level, version));
    
    // milli litre
    ud6.addUnit(new Unit(-3, Kind.LITRE, level, version));
    
    // seconds
    ud7.addUnit(new Unit(Kind.SECOND, level, version));
  }


  /**
   * Test method for {@link UnitDefinition#divideBy(UnitDefinition)}.
   */
  @Test
  public void testDivideBy() {
    assertTrue(UnitDefinition.printUnits(ud1.clone().divideBy(ud1), true).equals("mol*l^(-1)*mol^(-1)*l"));
    assertTrue(UnitDefinition.printUnits(ud1.clone().divideBy(ud2), true).equals("mol*l^(-1)*\u03BCl^(-1)"));
    assertTrue(UnitDefinition.printUnits(ud1.clone().divideBy(ud3), true).equals("mol*l^(-1)*(3600*s)^(-1)"));
  }


  /**
   * Test method for {@link UnitDefinition#multiplyWith(UnitDefinition)}.
   */
  @Test
  public void testMultiplyWith() {
    assertTrue(UnitDefinition.printUnits(ud1.clone().multiplyWith(ud1), true).equals("mol^2*l^(-2)"));
    assertTrue(UnitDefinition.printUnits(ud1.clone().multiplyWith(ud2), true).equals("mol*l^(-1)*\u03BCl"));
    assertTrue(UnitDefinition.printUnits(ud1.clone().multiplyWith(ud3), true).equals("mol*l^(-1)*3600*s"));
  }


  /**
   * Test method for {@link UnitDefinition#raiseByThePowerOf(double)}.
   */
  @Test
  public void testRaiseByThePowerOf() {
    assertTrue(UnitDefinition.printUnits(ud1.clone().raiseByThePowerOf(3d), true).equals("mol^3*l^(-3)"));
    assertTrue(UnitDefinition.printUnits(ud2.clone().raiseByThePowerOf(-2d), true).equals("\u03BCl^(-2)"));
    assertTrue(UnitDefinition.printUnits(ud3.clone().raiseByThePowerOf(3.4d), true).equals("(3600*s)^3.4"));
  }


  /**
   * Test method for {@link UnitDefinition#simplify()}.
   */
  @Test
  public void testSimplify() {
    // TODO: add more test cases!
    UnitDefinition udef = ud1.clone().divideBy(ud1).simplify();
    printTask('/', ud1, ud1, udef);
    assertTrue(UnitDefinition.printUnits(udef, true).equals("dimensionless"));
    
    udef = ud1.clone().divideBy(ud2).simplify();
    printTask('/', ud1, ud2, udef);
    assertTrue(UnitDefinition.printUnits(udef, true).equals("ml^(-2)*mol"));
    
    udef = ud1.clone().multiplyWith(ud2).simplify();
    printTask('*', ud1, ud2, udef);
    assertTrue(UnitDefinition.printUnits(udef, true).equals("\u03BCmol"));
    
    udef = ud1.clone().multiplyWith(ud2).divideBy(ud3).simplify();
    System.out.println(c(ud1) + '*' + c(ud2) + '/' + c(ud3) + " = " + c(udef));
    assertTrue(UnitDefinition.printUnits(udef, true).equals("\u03BCmol*(3600*s)^(-1)"));
    
    udef = ud3.clone().divideBy(ud4).simplify();
    printTask('/', ud3, ud4, udef);
    assertTrue(UnitDefinition.printUnits(udef, true).equals("60*dimensionless"));
    
    UnitDefinition u1 = new UnitDefinition(level, version);
    u1.addUnit(new Unit(-3, Unit.Kind.JOULE, level, version));
    u1.addUnit(new Unit(-3, Unit.Kind.MOLE, level, version));
    
    UnitDefinition u2 = new UnitDefinition(level, version);
    u2.addUnit(new Unit(Unit.Kind.JOULE, 5, level, version));
    u2.addUnit(new Unit(Unit.Kind.MOLE, 5, level, version));
    
    udef = u1.clone().divideBy(u2).simplify();
    printTask('/', u1, u2, udef);
    double pow = Math.pow(10, 3d/4d);
    assertTrue(UnitDefinition.printUnits(udef, true).equals('('+ Double.toString(pow) +"*J)^(-4)*(" + Double.toString(pow) + "*mol)^(-4)"));
    
    UnitDefinition u3 = new UnitDefinition(level, version);
    u3.addUnit(new Unit(Kind.MOLE, -4d, level, version));
    u3.addUnit(new Unit(Kind.SECOND, -1d, level, version));
    udef = ud5.clone().multiplyWith(u3).simplify();
    printTask('*', ud5, u3, udef);
    assertTrue(UnitDefinition.printUnits(udef, true).equals("hmol^(-3)*s^(-1)"));
    
    u1 = ud5.clone().raiseByThePowerOf(5d).divideBy(ud6.clone().raiseByThePowerOf(5d));
    u2 = ud5.clone().raiseByThePowerOf(-4d).multiplyWith(ud6.clone().raiseByThePowerOf(5d)).divideBy(ud7.clone());
    udef = u1.clone().multiplyWith(u2).simplify();
    printTask('*', u1, u2, udef);
    assertTrue(UnitDefinition.printUnits(udef, true).equals("\u03BCmol*s^(-1)"));
  }
  
  /**
   * Convenient helper method.
   * 
   * @param symbol
   * @param input1
   * @param input2
   * @param result
   */
  private static void printTask(char symbol, UnitDefinition input1,
    UnitDefinition input2, UnitDefinition result) {
    System.out.printf("%s %s %s = %s\n",
      UnitDefinition.printUnits(input1, true), Character.valueOf(symbol),
      UnitDefinition.printUnits(input2, true),
      UnitDefinition.printUnits(result, true));
  }
  
  /**
   * Compact {@link String} representation of a {@link UnitDefinition}.
   * 
   * @param udef
   * @return
   */
  private static String c(UnitDefinition udef) {
    return UnitDefinition.printUnits(udef, true);
  }

}
