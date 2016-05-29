package org.sbml.jsbml.validator.factory;

import java.util.List;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.FunctionDefinition;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Species;

public class L3V1FactoryManager extends AbstractFactoryManager {

    public void addGeneralModelIds(List<Integer> list) {
	addRangeToList(list, 20204, 20232);
	list.add(20705);
    }

    public void addGeneralFunctionDefinitionIds(List<Integer> list) {
	list.add(20301);
	addRangeToList(list, 20303, 20307);
    }

    public void addGeneralCompartmentIds(List<Integer> list) {
	addRangeToList(list, 20507, 20509);
	list.add(20517);
    }

    public void addGeneralSpeciesIds(List<Integer> list) {
	list.add(20601);
	addRangeToList(list, 20608, 20611);
	list.add(20614);
	list.add(20617);
	list.add(20623);
    }
}
