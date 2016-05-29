package org.sbml.jsbml.validator.factory;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;

public abstract class AbstractFactoryManager implements FactoryManager {
    /**
     * Log4j logger
     */
    protected static final transient Logger logger = Logger.getLogger(AbstractFactoryManager.class);
    
    @Override
    public List<Integer> getIdsForClass(Class<?> clazz, CheckCategory category) {
	List<Integer> list = new ArrayList<Integer>();
//	System.out.println("Get Ids for " + clazz.getName());
	
	if(clazz.equals(SBMLDocument.class))
	{
	    list.add(ConstraintFactory.ID_VALIDATE_DOCUMENT_TREE);
	} else if (clazz.equals(Model.class))
	{
	    list.add(ConstraintFactory.ID_VALIDATE_MODEL_TREE);
	}
	
	try{
	    // Capitalize category name
	    char[] chars = category.toString().toLowerCase().toCharArray();
	    chars[0] = Character.toUpperCase(chars[0]);
	    String categoryName = new String(chars);
	    
	    Method m = this.getClass().getMethod("add" + categoryName + clazz.getSimpleName() + "Ids", List.class);
	    m.invoke(this, list);
	}
	catch(Exception e)
	{
	    logger.debug(e.getMessage());
	}
	
	
	return list;
    }
    
    protected static void addRangeToList(List<Integer> list, int from, int to) {
	for (int i = from; i <= to; i++) {
	    list.add(i);
	}
    }
}
