package nl.han.ica.icss.parser;


import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.expressions.*;
import nl.han.ica.icss.ast.expressions.literals.*;
import nl.han.ica.icss.ast.expressions.operations.*;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private final AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private final IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		currentContainer.push(new Stylesheet());
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.setRoot((Stylesheet) currentContainer.pop());
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		currentContainer.push(new Stylerule());
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		ASTNode stylerule = currentContainer.pop();
		currentContainer.peek().addChild(stylerule);
	}

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		currentContainer.push(new Declaration());
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		ASTNode declaration = currentContainer.pop();
		currentContainer.peek().addChild(declaration);
	}

	@Override
	public void enterProperty_name(ICSSParser.Property_nameContext ctx) {
		currentContainer.push(new PropertyName(ctx.getText()));
	}

	@Override
	public void exitProperty_name(ICSSParser.Property_nameContext ctx) {
		ASTNode propertyName = currentContainer.pop();
		currentContainer.peek().addChild(propertyName);
	}

	@Override
	public void enterTag_selector(ICSSParser.Tag_selectorContext ctx) {
		currentContainer.push(new TagSelector(ctx.getText()));
	}

	@Override
	public void exitTag_selector(ICSSParser.Tag_selectorContext ctx) {
		ASTNode tagSelector = currentContainer.pop();
		currentContainer.peek().addChild(tagSelector);
	}

	@Override
	public void enterId_selector(ICSSParser.Id_selectorContext ctx) {
		currentContainer.push(new IdSelector(ctx.getText()));
	}

	@Override
	public void exitId_selector(ICSSParser.Id_selectorContext ctx) {
		ASTNode idSelector = currentContainer.pop();
		currentContainer.peek().addChild(idSelector);
	}

	@Override
	public void enterClass_selector(ICSSParser.Class_selectorContext ctx) {
		currentContainer.push(new ClassSelector(ctx.getText()));
	}

	@Override
	public void exitClass_selector(ICSSParser.Class_selectorContext ctx) {
		ASTNode classSelector = currentContainer.pop();
		currentContainer.peek().addChild(classSelector);
	}

	@Override
	public void enterBoolean_literal(ICSSParser.Boolean_literalContext ctx) {
		currentContainer.peek().addChild(new BoolLiteral(ctx.getText()));
	}

	@Override
	public void enterPixel_literal(ICSSParser.Pixel_literalContext ctx) {
		currentContainer.peek().addChild(new PixelLiteral(ctx.getText()));
	}

	@Override
	public void enterPercentage_literal(ICSSParser.Percentage_literalContext ctx) {
		currentContainer.peek().addChild(new PercentageLiteral(ctx.getText()));
	}

	@Override
	public void enterScalar_literal(ICSSParser.Scalar_literalContext ctx) {
		currentContainer.peek().addChild(new ScalarLiteral(ctx.getText()));
	}

	@Override
	public void enterColor_literal(ICSSParser.Color_literalContext ctx) {
		currentContainer.peek().addChild(new ColorLiteral(ctx.getText()));
	}

	@Override
	public void enterVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
		currentContainer.push(new VariableAssignment());
	}

	@Override
	public void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
		ASTNode variableAssignment = currentContainer.pop();
		currentContainer.peek().addChild(variableAssignment);
	}

	@Override
	public void enterVariable_reference(ICSSParser.Variable_referenceContext ctx) {
		currentContainer.peek().addChild(new VariableReference(ctx.getText()));
	}

	@Override
	public void enterExpression(ICSSParser.ExpressionContext ctx) {
		int childCount = ctx.getChildCount();

		// Expression is a literal or variable
		if( childCount == 1 ) return;

		// Expression is an operation
		ASTNode operation;
		if( childCount == 3 ) {
			switch (ctx.getChild(1).getText()) {
				case "*":
					operation = new MultiplyOperation();
					break;
				case "+":
					operation = new AddOperation();
					break;
				case "-":
					operation = new SubtractOperation();
					break;
				// UITBREIDING_OPERATORS
				case "==":
					operation = new EqualsOperation();
					break;
				case ">":
					operation = new GreaterOperation();
					break;
				case "<":
					operation = new LesserOperation();
					break;
				default:
					return;
			}
			currentContainer.push(operation);
		} // UITBREIDING: Expression is a Not-operation
		else if( childCount == 2 && ctx.getChild(0).getText().equals("!")) {
			operation = new NotOperation();
			currentContainer.push(operation);
		}
	}

	@Override
	public void exitExpression(ICSSParser.ExpressionContext ctx) {
		if( ctx.getChildCount() == 3 || ctx.getChildCount() == 2) {
			ASTNode operation = currentContainer.pop();
			currentContainer.peek().addChild(operation);
		}
	}

	@Override
	public void enterIf_clause(ICSSParser.If_clauseContext ctx) {
		currentContainer.push(new IfClause());
	}

	@Override
	public void exitIf_clause(ICSSParser.If_clauseContext ctx) {
		ASTNode ifClause = currentContainer.pop();
		currentContainer.peek().addChild(ifClause);
	}

	@Override
	public void enterElse_clause(ICSSParser.Else_clauseContext ctx) {
		currentContainer.push(new ElseClause());
	}

	@Override
	public void exitElse_clause(ICSSParser.Else_clauseContext ctx) {
		ASTNode elseClause = currentContainer.pop();
		currentContainer.peek().addChild(elseClause);
	}
}