package org.sbml.jsbml.validator.offline.constraints;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.Assignment;
import org.sbml.jsbml.ExplicitRule;
import org.sbml.jsbml.InitialAssignment;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.util.filters.Filter;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.helper.CycleDetectionTreeNode;

public abstract class AssignmentConstraints
  extends AbstractConstraintDeclaration {

  @Override
  public void addErrorCodesForCheck(Set<Integer> set, int level, int version,
    CHECK_CATEGORY category) {

    switch (category) {
    case GENERAL_CONSISTENCY:
      break;
    case IDENTIFIER_CONSISTENCY:
      break;
    case MATHML_CONSISTENCY:
      break;
    case MODELING_PRACTICE:
      break;
    case OVERDETERMINED_MODEL:
      break;
    case SBO_CONSISTENCY:
      break;
    case UNITS_CONSISTENCY:
      break;
    }
  }


  @Override
  public void addErrorCodesForAttribute(Set<Integer> set, int level,
    int version, String attributeName) {
    // TODO Auto-generated method stub

  }


  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode) {
    ValidationFunction<Assignment> func = null;

    switch (errorCode) {
//    case CORE_20906:
//      func = new ValidationFunction<Assignment>() {
//
//        private Filter                      isName  = new Filter() {
//
//                                                      @Override
//                                                      public boolean accepts(
//                                                        Object o) {
//                                                        ASTNode n = (ASTNode) o;
//                                                        return n.isName();
//                                                      }
//                                                    };
//
//        Map<String, CycleDetectionTreeNode> nodeMap =
//          new HashMap<String, CycleDetectionTreeNode>();
//        Queue<Object>                   queue   =
//          new LinkedList<Object>();
//
//
//        @Override
//        public boolean check(ValidationContext ctx, Assignment a) {
//
//          queue.offer(a);
//
//          while (!queue.isEmpty()) {
//            Object obj = queue.poll();
//            CycleDetectionTreeNode node = getNode(as);
//
//          }
//
//          queue.clear();
//          nodeMap.clear();
//          return false;
//        }
//
//
//        private boolean addChildren(CycleDetectionTreeNode node, Assignment a) {
//
//        }
//
//
//        private boolean addChildrenOfRule(Model m, CycleDetectionTreeNode node,
//          ExplicitRule er) {
//          
//          
//          if (er.isSetMath())
//          {
//            for (ASTNode var:er.getMath().getListOfNodes(isName))
//            {
//              String name = var.getName();
//          
//              Reaction reac = m.getReaction(name);
//              Assignment child;
//              
//              if (reac != null)
//              {
//                
//              }
//              
//              
//            }
//          }
//          
//          return true;
//        }
//
//
//        private CycleDetectionTreeNode getNode(Object o) {
//          String name = getName(o);
//          CycleDetectionTreeNode node = nodeMap.get(name);
//
//          if (node == null) {
//            node = new CycleDetectionTreeNode(name);
//            nodeMap.put(name, node);
//          }
//
//          return node;
//        }
//
//
//        private String getName(Object o) {
//          if (o instanceof ExplicitRule) {
//            return ((ExplicitRule) o).getVariable();
//          } else if (o instanceof InitialAssignment) {
//            return ((InitialAssignment) o).getSymbol();
//          } else if (o instanceof Reaction) {
//            return ((Reaction) o).getId();
//          }
//
//          return "";
//        }
//      };
//
//      break;

    default:
      break;
    }

    return func;
  }

}