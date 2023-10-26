package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.expressions.Operation;
import nl.han.ica.icss.ast.expressions.VariableReference;
import nl.han.ica.icss.ast.expressions.literals.*;
import nl.han.ica.icss.ast.expressions.operations.AddOperation;
import nl.han.ica.icss.ast.expressions.operations.MultiplyOperation;
import nl.han.ica.icss.ast.expressions.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;


public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        recursiveCheck(ast.root);
    }

    private void recursiveCheck(ASTNode node) {
        variableTypes.addFirst(new HashMap<>());

        actOnNode(node);

        for(ASTNode child: node.getChildren()) {
            recursiveCheck(child);
        }

        variableTypes.removeFirst();
    }

    private void actOnNode(ASTNode node) {

        if( node instanceof VariableAssignment ) addVariable((VariableAssignment) node);

        // CH01 - Check use of undefined variables.
        // CH06 - Check if referenced variable is within scope.
        if( node instanceof VariableReference ) checkVariableScopes((VariableReference) node);

        // CH03 - Check if operation's operands are not ColorLiterals.
        if( node instanceof Operation ) checkOperation((Operation) node);

        // CH04 - Check declaration for invalid property-value combinations.
        if( node instanceof Declaration ) checkDeclaration((Declaration) node);

        // CH05 - Check if condition's expression retrieves a boolean value.
        if( node instanceof IfClause ) checkCondition((IfClause) node);
    }

    // CH01+CH06 - Sets an error on the reference node if variable can not be found (within scope).
    private ExpressionType checkVariableScopes(VariableReference reference) {
        String variableName = reference.name;
        ExpressionType variableExpressionType = null;

        // Remember expressionType when the variable name is found.
        for(int i=0; i<variableTypes.getSize(); i++) {
            if( variableTypes.get(i).containsKey(variableName) ) {
                variableExpressionType = variableTypes.get(i).get(variableName);
            }
        }

        // CH01
        if( variableExpressionType == null ) {
            reference.setError("Variable name "+variableName+" not found in scope.");
        }
        return variableExpressionType;
    }

    private void addVariable(VariableAssignment assignment) {
        variableTypes.get(1).put(assignment.name.name, getExpressionType(assignment.expression));
    }

    // CH03 - If either child of the operation is a color literal, set error on the node.
    private void checkOperation(Operation operation) {
        ExpressionType leftType = getExpressionType(operation.lhs);
        ExpressionType rightType = getExpressionType(operation.rhs);

        if(leftType == ExpressionType.COLOR || rightType == ExpressionType.COLOR) {
            operation.setError("Operations cannot be made on literals of type Color");
            //return ExpressionType.UNDEFINED;
        }
    }

    // CH04 - If property names are being assigned invalid values, set error on the node.
    private void checkDeclaration(Declaration declaration) {
        String propertyName = declaration.property.name;
        ExpressionType valueType = getExpressionType(declaration.expression);

        if( (propertyName.equals("width") || propertyName.equals("height")) &&
                valueType != ExpressionType.PIXEL && valueType != ExpressionType.PERCENTAGE) {
            declaration.setError("Property "+propertyName+" requires literal value of type pixel or percentage");
        }
        else if( (propertyName.equals("color") || propertyName.equals("`background-color`")) &&
                valueType != ExpressionType.COLOR) {
            declaration.setError("Property "+propertyName+" requires literal value of type color");
        }
    }

    // CH05 - Check if expression of if-clause returns boolean value.
    private void checkCondition(IfClause ifClause) {
        ExpressionType expressionType = getExpressionType(ifClause.conditionalExpression);
        if( expressionType != ExpressionType.BOOL ) {
            ifClause.setError("Expression within if-clause must contain a boolean value.");
        }
    }

    // Method to retrieve the type returned by the expression.
    // CH02 - in case of an operation where the combination of operands is invalid, an error is set on the node
    private ExpressionType getExpressionType(Expression expression) {
        // If literal, return type
        if( expression instanceof ScalarLiteral ) return ExpressionType.SCALAR;
        if( expression instanceof BoolLiteral) return ExpressionType.BOOL;
        if( expression instanceof PixelLiteral ) return ExpressionType.PIXEL;
        if( expression instanceof PercentageLiteral ) return ExpressionType.PERCENTAGE;
        if( expression instanceof ColorLiteral ) return ExpressionType.COLOR;


        // If operation, recursively get type
        if( expression instanceof Operation ) {
            ExpressionType leftType = getExpressionType(((Operation) expression).lhs);
            ExpressionType rightType = getExpressionType(((Operation) expression).rhs);

            if( expression instanceof AddOperation || expression instanceof SubtractOperation ) {
                // valid types for operation
                if( leftType.equals(rightType) ) {
                    return leftType;
                }
                // invalid types for operation
                else {
                    expression.setError("Add or Subtract operation performed on unequal expressiontypes");
                    return ExpressionType.UNDEFINED;
                }
            } else if( expression instanceof MultiplyOperation ) {
                // valid types for operation
                if( leftType == ExpressionType.SCALAR ) return rightType;
                if( rightType == ExpressionType.SCALAR ) return leftType;
                // invalid types for operation
                else {
                    expression.setError("Multiply operation performed without at least 1 scalar literal type");
                    return ExpressionType.UNDEFINED;
                }
            }
        }

        // If variable reference, look up type
        if( expression instanceof VariableReference ) {
            ExpressionType variableType = checkVariableScopes((VariableReference) expression);
            if(variableType == null) variableType = ExpressionType.UNDEFINED;
            return variableType;
        }
        expression.setError("expression has unknown type");
        return ExpressionType.UNDEFINED;
    }
}
