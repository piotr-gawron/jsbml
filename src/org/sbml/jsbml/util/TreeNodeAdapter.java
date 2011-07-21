/*
 * $Id:  TreeNodeAdapter.java 08:35:34 draeger $
 * $URL: TreeNodeAdapter.java $
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
package org.sbml.jsbml.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.sbml.jsbml.AbstractTreeNode;
import org.sbml.jsbml.ListOf;

/**
 * <p>
 * This class is a wrapper element for any {@link Object} that should be linked
 * into a tree data structure as represented by {@link AbstractTreeNode}, but
 * that might by itself not be an instance of {@link TreeNode}, i.e., not
 * compatible with the remaining tree data structure. In analogy to
 * {@link MutableTreeNode} we call this {@link Object} <code>userObject</code>
 * (see {@link #getUserObject()} and {@link #setUserObject(Object)}).
 * </p>
 * <p>
 * This wrapper distinguishes the following special cases depending on the type
 * of <code>userObject</code> when accessing the i-th child of this
 * {@link TreeNode}. The <code>userObject</code> is an instance of
 * <ul>
 * <li>{@link TreeNode}: recursive operations are continued at this element</li>
 * <li>{@link Collection}: recursion leads to the i-th element in the
 * {@link Iterator}</li>
 * <li>{@link Map}: the key set is sorted (only possible if the keys implement
 * the {@link Comparable} interface) and recursion continues at the i-th key.</li>
 * </ul>
 * </p>
 * 
 * @author Andreas Dr&auml;ger
 * @version $Rev$
 * @since 0.8
 * @date 19.07.2011
 */
public class TreeNodeAdapter extends AbstractTreeNode {
	
	/**
	 * 
	 */
	private Object userObject;

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -6818272462908634740L;

	/**
	 * 
	 * @param userObject
	 */
	public TreeNodeAdapter(Object userObject) {
		super();
		this.userObject = userObject;
	}

	/**
	 * @param node
	 */
	public TreeNodeAdapter(TreeNodeAdapter node) {
		super(node);
		this.userObject = node.getUserObject();
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractTreeNode#clone()
	 */
	public TreeNode clone() {
		return new TreeNodeAdapter(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractTreeNode#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof TreeNodeAdapter) {
			TreeNodeAdapter node = (TreeNodeAdapter) o;
			return super.equals(node)
					&& userObject.equals(node.getUserObject());
		}
		return false;
	}


	/**
	 * 
	 * @param object
	 */
	public void setUserObject(Object object) {
		this.userObject = object;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		if (isSetUserObject() && (userObject instanceof Collection<?>)) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public Object getUserObject() {
		return userObject;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		if (childIndex < 0) {
			throw new IndexOutOfBoundsException(childIndex + " < 0");
		}
		if (isSetUserObject()) {
			if (userObject instanceof TreeNode) {
				return ((TreeNode) userObject).getChildAt(childIndex);
			}
			Object child = null;
			if (userObject instanceof Map<?, ?>) {
				Map<?, ?> map = (Map<?, ?>) userObject;
				Object keys[] = map.keySet().toArray();
				Arrays.sort(keys);
				child = map.get(keys[childIndex]);
			}
			if ((userObject instanceof List<?>)) {
				child = ((List<?>) userObject).get(childIndex);
			} else if ((userObject instanceof Collection<?>)) {
				Iterator<?> iterator = ((Collection<?>) userObject).iterator();
				for (int pos = 0; pos < childIndex - 1; pos++) {
					iterator.next();
				}
				child = iterator.next();
			}
			if (child != null) {
				if (child instanceof TreeNode) {
					return (TreeNode) child;
				} else {
					return new TreeNodeAdapter(child);
				}
			}
		}
		throw new IndexOutOfBoundsException(String.format("Index %d >= %d",
				childIndex, getChildCount()));
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		if (isSetUserObject()) {
			if (userObject instanceof Collection<?>) {
				return ((Collection<?>) userObject).size();
			}
			if (userObject instanceof Map<?, ?>) {
				return ((Map<?, ?>) userObject).size();
			}
		}
		return 0;
	}
	
	

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractTreeNode#toString()
	 */
	@Override
	public String toString() {
		if (isSetUserObject()) {
			if (userObject instanceof Collection<?>) {
				Collection<?> collection = (Collection<?>) userObject;
				if (ListOf.isDebugMode()) {
					return collection.toString();
				} else {
					if (collection.size() > 0) {
						String name = collection.iterator().next().getClass()
								.getSimpleName();
						if (!name.endsWith("s")) {
							name += "s";
						}
						String type = collection instanceof List<?> ? "listOf"
								: "collectionOf";
						return type + name;
					}
					return collection.getClass().getSimpleName();
				}
			} else if (userObject instanceof Map<?, ?>) {
				Map<?, ?> map = (Map<?, ?>) userObject;
				if (ListOf.isDebugMode()) {
					return map.toString();
				} else {
					if (map.size() > 0) {
						Map.Entry<?, ?> entry = map.entrySet().iterator()
								.next();
						String name = entry.getKey().getClass().getSimpleName()
								+ "To"
								+ entry.getValue().getClass().getSimpleName();
						return "mapOf" + name;
					}
				}
			}
			return userObject.toString();
		}
		return super.toString();
	}

	/**
	 * @return
	 */
	public boolean isSetUserObject() {
		return userObject != null;
	}

}
