/*
 * $Id: SpeciesReferenceGlyphTest.java 1708 2014-05-06 01:00:00Z yvazirabad $
 * $URL: https://svn.code.sf.net/p/jsbml/code/trunk/extensions/layout/test/org/sbml/jsbml/ext/layout/SpeciesReferenceGlyphTest.java $
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
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.ext.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SpeciesReference;

public class SpeciesReferenceGlyphTest {

  @Test
  public void testGetChildAt() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
  }


  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#getChildCount()}.
   */
  @Test
  public void testGetChildCount() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph();
    assertTrue(glyph.getChildCount() == 0);
  }

/**
 * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#getCurve()}.
 */
  @Test
  public void testGetCurve() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.createCurve();
    assertTrue(glyph.getCurve() instanceof Curve);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#getSpeciesGlyph()}.
   */
  @Test
  public void testGetSpeciesGlyph() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.setSpeciesGlyph("reaction");
    assertEquals("error","reaction",glyph.getSpeciesGlyph());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#GetSpeciesRefereceInstance()}.
   */
  @Test
  public void testGetSpeciesReferenceInstance()  {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.setSpeciesReference("testName");
    assertTrue(glyph.getSpeciesReferenceInstance()==null);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#getSpeciesReference()}.
   */
  @Test
  public void testGetSpeciesReference() {
    String str="reaction";
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.setSpeciesReference(str);
    assertEquals("getSpeciesReferenceError",glyph.getSpeciesReference(),str);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#getSpeciesGlyphInstance()}.
   */
  @Test
  public void testGetSpeciesGlyphInstance(){
    SBMLDocument d = new SBMLDocument(3,1);
    Model model = d.createModel("extensionModel");

    LayoutModelPlugin lModel = new LayoutModelPlugin(model);
    Layout layout = lModel.createLayout("layout");
    ReactionGlyph rg = new ReactionGlyph("react_r1", model.getLevel(), model.getVersion());
    layout.addReactionGlyph(rg);

    SpeciesReferenceGlyph srg1 = rg.createSpeciesReferenceGlyph("srg_r1_s1", "SPG1");
    srg1.setRole(SpeciesReferenceRole.SUBSTRATE);
    srg1.setSpeciesGlyph("STUFF");
    assertTrue(srg1.isSetSpeciesGlyph());
    assertTrue(srg1.getSpeciesGlyphInstance() ==null);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#getSpeciesReferenceRole}.
   */
  @Test
  public void testGetSpeciesReferenceRole() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    SpeciesReferenceRole role = null;
    role=SpeciesReferenceRole.ACTIVATOR;
    glyph.setRole(role);
    assertEquals("role error",glyph.getSpeciesReferenceRole(),role);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#isSetCurve()}.
   */
  @Test
  public void testIsSetCurve() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.createCurve();
    assertTrue(glyph.isSetCurve());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#isSetSpeciesGlyph()}.
   */
  @Test
  public void testIsSetSpeciesGlyph() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.setSpeciesGlyph("reaction");
    assertTrue(glyph.isSetSpeciesGlyph());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#isSetSpeciesReference()}.
   */
  @Test
  public void testIsSetSpeciesReference() {
    String str="reaction";
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.setSpeciesReference(str);
    assertTrue(glyph.isSetSpeciesReference());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#isSetSpeciesReferenceRole()}.
   */
  @Test
  public void testIsSetSpeciesReferenceRole() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    SpeciesReferenceRole role = null;
    role=SpeciesReferenceRole.ACTIVATOR;
    glyph.setRole(role);
    assertTrue(glyph.isSetSpeciesReferenceRole());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#getSpeciesGlyph()}.
   */
  @Test
  public void testSetCurve() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    Curve curvy=glyph.createCurve();
    glyph.setCurve(curvy);
    assertEquals("CurveError",glyph.getCurve(),curvy);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#testSetRole()}.
   */
  @Test
  public void testSetRole() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    SpeciesReferenceRole role = null;
    role=SpeciesReferenceRole.ACTIVATOR;
    glyph.setRole(role);
    assertEquals("role error",glyph.getSpeciesReferenceRole(),role);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#testSetSpeciesGlyph()}.
   */
  @Test
  public void testSetSpeciesGlyph() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    String speciesGlyph="speciesglyph";
    glyph.setSpeciesGlyph(speciesGlyph);
    assertEquals("setSpeciesGlyphError",speciesGlyph,glyph.getSpeciesGlyph());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#testSetSpeciesReference(SimpleSpeciesReference)}.
   */
  @Test
  public void testSetSpeciesReferenceSimpleSpeciesReference() {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("newGlyph",3,1);
    SpeciesReference simpleSpecReference=new SpeciesReference("speciesReference",3,1);
    glyph.setSpeciesReference(simpleSpecReference);
    assertEquals("SetSpeciesReferenceSimpleSpeciesReferenceError","speciesReference",glyph.getSpeciesReference());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph#setSpeciesReferenceString()}.
   */
  @Test
  public void testSetSpeciesReferenceString() {
    String str="reaction";
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph("glyph",3,1);
    glyph.setSpeciesReference(str);
    assertEquals("setSpeciesReferenceStringError",glyph.getSpeciesReference(),str);
  }
}
