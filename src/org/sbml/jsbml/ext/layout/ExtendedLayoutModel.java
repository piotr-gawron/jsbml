/*
 * $Id$
 * $URL$
 *
 *
 *==================================================================================
 * Copyright (c) 2009 the copyright is held jointly by the individual
 * authors. See the file AUTHORS for the list of authors.
 *
 * This file is part of jsbml, the pure java SBML library. Please visit
 * http://sbml.org for more information about SBML, and http://jsbml.sourceforge.net/
 * to get the latest version of jsbml.
 *
 * jsbml is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jsbml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsbml.  If not, see <http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html>.
 *
 *===================================================================================
 *
 */

package org.sbml.jsbml.ext.layout;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBaseChangedListener;

/**
 * 
 * @author
 *
 */
public class ExtendedLayoutModel extends Model {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -6666014348571697514L;
	/**
	 * 
	 */
	protected ListOf<Layout> listOfLayouts = new ListOf<Layout>();
	/**
	 * 
	 */
	protected Model model;

	/**
	 * 
	 */
	public ExtendedLayoutModel() {

	}

	/**
	 * 
	 * @param level
	 * @param version
	 */
	public ExtendedLayoutModel(int level, int version) {
		// TODO : add package version as well
		super(level, version);
	}

	/**
	 * 
	 * @param model
	 */
	public ExtendedLayoutModel(Model model) {
		this.model = model;
		model.setThisAsParentSBMLObject(this);
	}

	/**
	 * 
	 * @param layout
	 */
	public void add(Layout layout) {
		addLayout(layout);
	}

	/**
	 * 
	 * @param layout
	 */
	public void addLayout(Layout layout) {
		if (layout != null) {
			setThisAsParentSBMLObject(layout);
			listOfLayouts.add(layout);
		}
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public Layout getLayout(int i) {
		if (i >= 0 && i < listOfLayouts.size()) {
			return listOfLayouts.get(i);
		}

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public ListOf<Layout> getListOfLayouts() {
		return listOfLayouts;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSetListOfLayouts() {
		if ((listOfLayouts == null) || listOfLayouts.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param listOfLayouts
	 */
	public void setListOfLayouts(ListOf<Layout> listOfLayouts) {
		unsetListOfLayouts();
		if (listOfLayouts == null) {
			this.listOfLayouts = new ListOf<Layout>();
		} else {
			this.listOfLayouts = listOfLayouts;
		}
		if ((this.listOfLayouts != null) && (this.listOfLayouts.getSBaseListType() != ListOf.Type.other)) {
			this.listOfLayouts.setSBaseListType(ListOf.Type.other);
		}
		setThisAsParentSBMLObject(listOfLayouts);
	}
	
	/**
	 * Removes the {@link #listOfLayouts} from this {@link Model} and notifies
	 * all registered instances of {@link SBaseChangedListener}.
	 * 
	 * @return <code>true</code> if calling this method lead to a change in this
	 *         data structure.
	 */
	public boolean unsetListOfLayouts() {
		if (this.listOfLayouts != null) {
			ListOf<Layout> oldListOfLayouts = this.listOfLayouts;
			this.listOfLayouts = null;
			oldListOfLayouts.fireSBaseRemovedEvent();
			return true;
		}
		return false;
	}

}
