package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.expressions.Literal;
import nl.han.ica.icss.ast.expressions.Operation;
import nl.han.ica.icss.ast.expressions.VariableReference;
import nl.han.ica.icss.ast.expressions.literals.*;
import nl.han.ica.icss.ast.expressions.operations.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Evaluator implements Transform {

    private IHANLinkedList<HashMap<String, Literal>> variableValues;

    @Override
    public void apply(AST ast) {
        variableValues = new HANLinkedList<>();

        traverseAndApply(ast.root);
    }

    // TR01
    // TR02
    private void traverseAndApply(ASTNode parent) {
        variableValues.addFirst(new HashMap<>());
        ArrayList<ASTNode> children = parent.getChildren();

        for( int i=0; i<children.size(); i++ ) {
            ASTNode child = children.get(i);

            if( child instanceof VariableAssignment ) {
                Literal literalValue = calculateExpression(((VariableAssignment) child).expression);
                assignVariable(((VariableAssignment) child).name.name, literalValue);
                ((VariableAssignment) child).expression = literalValue;
            }

            if( child instanceof Declaration ) {
                ((Declaration) child).expression = calculateExpression(((Declaration) child).expression);
            }

            if( child instanceof Stylerule ) {
                traverseAndApply(child);
            }

            // Replace the IfClause node with the nodes from the IfClause or ElseClause body.
            if( child instanceof IfClause ) {
                replaceIfStatement(parent, (IfClause) child);

                // Since the if-statement at index i has been replaced, the new element is also at i.
                i--;

                // Changes were made directly to the parent's body. the getChildren method returns the body along
                // with selectors, conditionalstatements, else-clauses etc. Therefore the list of children must be
                // updated.
                children = parent.getChildren();
            }
        }

        variableValues.removeFirst();
    }

    // TR02 - Replace if-statements with the correct nodes from:
    //      1: if-clause.body       (condition == true)
    //      2: else-clause.body     (condition == false)
    //      3: none                 (condition == false && ifClause.Elseclause == null)
    private void replaceIfStatement(ASTNode parent, IfClause ifClause) {
        // Get the correct body from IfClause, a false if-statement without else-clause returns an empty list.
        ArrayList<ASTNode> body = getBodyFromIfClause(ifClause);

        // Variable reference to the parent's body where the if-clause's nodes are to be added.
        ArrayList<ASTNode> parentBody;

        // Set the parentBody to the correct body.
        if( parent instanceof Stylerule ) {
            parentBody = ((Stylerule) parent).body;
        } else if( parent instanceof IfClause) {
            BoolLiteral condition = (BoolLiteral) ((IfClause) parent).conditionalExpression;
            if (condition.value) {
                parentBody = ((IfClause) parent).body;
            } else {
                parentBody = ((IfClause) parent).elseClause.body;
            }
        } else return;

        // In parentBody, replace the if-clause with its body nodes.
        int posInParentBody = parentBody.indexOf(ifClause);
        parentBody.remove(posInParentBody);
        for( int i=0; i< body.size(); i++ ) {
            parentBody.add(posInParentBody+i, body.get(i));
        }
    }

    // TR02
    private ArrayList<ASTNode> getBodyFromIfClause(IfClause node) {
        BoolLiteral conditionValue = (BoolLiteral) calculateExpression(node.conditionalExpression);
        if( conditionValue.value ) {
            return node.body;
        }
        if( node.elseClause != null ) {
            return node.elseClause.body;
        }
        return new ArrayList<>();
    }

    // TR01 - Replace expressions with calculated Literal value
    private Literal calculateExpression(Expression expression) {
        if( expression instanceof VariableReference) {
            return calculateVariableReference((VariableReference) expression);
        }
        if( expression instanceof Literal ) return (Literal) expression;
        if( expression instanceof Operation) return calculateOperation((Operation) expression);
        return null;
    }

    // TR01
    private Literal calculateOperation(Operation operation) {
        Literal left = calculateExpression(operation.lhs);
        Literal right = calculateExpression(operation.rhs);

        if( operation instanceof AddOperation ) return add(left, right);
        if( operation instanceof SubtractOperation ) return subtract(left, right);
        if( operation instanceof MultiplyOperation ) return multiply(left, right);

        // UITBREIDING BOOLEAN OPERATORS
        if( operation instanceof NotOperation ) return not(left);
        if( operation instanceof EqualsOperation ) return equals(left, right);
        if( operation instanceof GreaterOperation ) return greaterThan(left, right);
        if( operation instanceof LesserOperation ) return lesserThan(left, right);
        return null;
    }

    // UITBREIDING EQUALS-OPERATOR
    private BoolLiteral equals(Literal left, Literal right) {
        boolean bool = false;
        if(left instanceof ScalarLiteral) {
            bool = ((ScalarLiteral) left).value == ((ScalarLiteral) right).value;
        }
        if(left instanceof PixelLiteral) {
            bool = ((PixelLiteral) left).value == ((PixelLiteral) right).value;
        }
        if(left instanceof PercentageLiteral) {
            bool = ((PercentageLiteral) left).value == ((PercentageLiteral) right).value;
        }
        if(left instanceof ColorLiteral) {
            bool = ((ColorLiteral) left).value.equals(((ColorLiteral) right).value);
        }
        if(left instanceof BoolLiteral) {
            bool = ((BoolLiteral) left).value == ((BoolLiteral) right).value;
        }
        return new BoolLiteral(bool);
    }

    // UITBREIDING GREATER-THAN-OPERATOR
    private BoolLiteral greaterThan(Literal left, Literal right) {
        boolean bool = false;
        if(left instanceof ScalarLiteral) {
            bool = ((ScalarLiteral) left).value > ((ScalarLiteral) right).value;
        }
        if(left instanceof PixelLiteral) {
            bool = ((PixelLiteral) left).value > ((PixelLiteral) right).value;
        }
        if(left instanceof PercentageLiteral) {
            bool = ((PercentageLiteral) left).value > ((PercentageLiteral) right).value;
        }
        if(left instanceof ColorLiteral) {
            bool = ((ColorLiteral) left).value.compareTo( ((ColorLiteral) right).value ) > 0;
        }
        if(left instanceof BoolLiteral) {
            return null;
        }
        return new BoolLiteral(bool);
    }

    // UITBREIDING LESSER-THAN-OPERATOR
    private BoolLiteral lesserThan(Literal left, Literal right) {
        boolean bool = false;
        if(left instanceof ScalarLiteral) {
            bool = ((ScalarLiteral) left).value < ((ScalarLiteral) right).value;
        }
        if(left instanceof PixelLiteral) {
            bool = ((PixelLiteral) left).value < ((PixelLiteral) right).value;
        }
        if(left instanceof PercentageLiteral) {
            bool = ((PercentageLiteral) left).value < ((PercentageLiteral) right).value;
        }
        if(left instanceof ColorLiteral) {
            bool = ((ColorLiteral) left).value.compareTo( ((ColorLiteral) right).value ) < 0;
        }
        if(left instanceof BoolLiteral) {
            return null;
        }
        return new BoolLiteral(bool);
    }

    // UITBREIDING NOT-OPERATOR
    private BoolLiteral not(Literal value) {
        boolean bool = !((BoolLiteral) value).value;
        return new BoolLiteral(bool);
    }

    // TR01
    private Literal add(Literal left, Literal right) {
        if( left instanceof ScalarLiteral ) {
            int result = ((ScalarLiteral) left).value + ((ScalarLiteral)right).value;
            return new ScalarLiteral(result);
        }
        if ( left instanceof PixelLiteral ) {
            int result = ((PixelLiteral) left).value + ((PixelLiteral) right).value;
            return new PixelLiteral(result);
        }
        if ( left instanceof PercentageLiteral) {
            int result = ((PercentageLiteral) left).value + ((PercentageLiteral) right).value;
            return new PercentageLiteral(result);
        }
        return null;
    }

    // TR01
    private Literal subtract(Literal left, Literal right) {
        if( left instanceof ScalarLiteral ) {
            int result = ((ScalarLiteral) left).value - ((ScalarLiteral)right).value;
            return new ScalarLiteral(result);
        }
        if ( left instanceof PixelLiteral ) {
            int result = ((PixelLiteral) left).value - ((PixelLiteral) right).value;
            return new PixelLiteral(result);
        }
        if ( left instanceof PercentageLiteral) {
            int result = ((PercentageLiteral) left).value - ((PercentageLiteral) right).value;
            return new PercentageLiteral(result);
        }
        return null;
    }

    // TR01
    private Literal multiply(Literal left, Literal right) {
        if( left instanceof ScalarLiteral ) {
            if (right instanceof ScalarLiteral) {
                return new ScalarLiteral(((ScalarLiteral) left).value * ((ScalarLiteral) right).value);
            }
            if (right instanceof PixelLiteral) {
                return new PixelLiteral(((ScalarLiteral) left).value * ((PixelLiteral) right).value);
            }
            if (right instanceof PercentageLiteral) {
                return new PercentageLiteral(((ScalarLiteral) left).value * ((PercentageLiteral) right).value);
            }
        }
        if( right instanceof ScalarLiteral ) {
            if( left instanceof PixelLiteral ) {
                return new PixelLiteral(((PixelLiteral) left).value * ((ScalarLiteral) right).value);
            }
            if( left instanceof PercentageLiteral ) {
                return new PercentageLiteral(((PercentageLiteral) left).value * ((ScalarLiteral) right).value);
            }
        }

        return null;
    }

    // TR01
    private Literal calculateVariableReference(VariableReference varReference) {
        for( int i=0; i<variableValues.getSize(); i++ ) {
            if( variableValues.get(i).containsKey(varReference.name) ) {
                return variableValues.get(i).get(varReference.name);
            }
        }
        return null;
    }

    // TR01
    private void assignVariable(String name, Literal literal) {
        int scopeLevel = -1;
        for( int i=0; i<variableValues.getSize(); i++ ) {
            if( variableValues.get(i).containsKey(name) ) {
                scopeLevel = i;
                break;
            }
        }
        if(scopeLevel == -1) {
            variableValues.getFirst().put(name, literal);
        } else {
            variableValues.get(scopeLevel).put(name, literal);
        }
    }
}
