/*
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2018 jointly by the following organizations:
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

package org.sbml.jsbml.validator.offline.constraints;

import org.sbml.jsbml.validator.offline.ValidationContext;

/**
 * Basic functional interface for a {@link ValidationFunction}. A object of this
 * class could be passed to a {@link ValidationConstraint}.
 * 
 * @author Roman
 * @since 1.2
 */
public interface ValidationFunction<T> {

  /**
   * Returns <code>true</code> if the constraint is valid and <code>false</code>
   * if it's broken.
   * 
   * @param ctx the context in which the validation is performed
   * @param t the object to check
   * @return <code>false</code> if the constraint is broken
   */
  public boolean check(ValidationContext ctx, T t);
    
}
