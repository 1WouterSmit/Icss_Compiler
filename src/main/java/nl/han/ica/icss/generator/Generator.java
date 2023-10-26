package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.expressions.literals.*;

public class Generator {

	private static final String BRACKET_OPEN = "{";
	private static final String BRACKET_CLOSE = "}";
	private static final String CARRIAGE_RETURN = "\n";
	// GE02
	private static final String INDENT = "  ";
	private static final String NBSP = " ";
	private static final String SEMICOLON = ";";
	private static final String TRUE = "TRUE";
	private static final String FALSE = "FALSE";
	private static final String PIXEL = "px";
	private static final String PERCENTAGE = "%";
	private static final String COLON = ":";

	String css;
	int scopeLevel;
	public Generator() {
		css = "";
		scopeLevel = 0;
	}
	public String generate(AST ast) {
		traverse(ast.root);
        return css;
	}

	// GE01
	private void traverse(ASTNode node) {

		if( node instanceof Stylerule ) {
			// configure the right selector string
			String selectorString = ((Stylerule) node).selectors.get(0).toString();

			newLine();
			addString(selectorString);
			space();
			addString(BRACKET_OPEN);
			scopeLevel++;

			for(ASTNode child : ((Stylerule) node).body) {
				traverse(child);
			}

			scopeLevel--;
			newLine();
			addString(BRACKET_CLOSE);
		} else if( node instanceof Declaration ) {
			newLine();
			addString(((Declaration) node).property.name);
			addString(COLON);
			space();
			addString(getExpressionAsString(((Declaration) node).expression));
			addString(SEMICOLON);
		} else {
			for( ASTNode child : node.getChildren()) {
				traverse(child);
			}
		}

	}

	private String getExpressionAsString(Expression expression) {
		if( expression instanceof ScalarLiteral ) {
			return String.valueOf(((ScalarLiteral) expression).value);
		}
		if( expression instanceof BoolLiteral ) {
			if( ((BoolLiteral) expression).value ) return TRUE;
			return FALSE;
		}
		if( expression instanceof ColorLiteral ) {
			return ((ColorLiteral) expression).value;
		}
		if( expression instanceof PercentageLiteral ) {
			return ((PercentageLiteral) expression).value+PERCENTAGE;
		}
		if( expression instanceof PixelLiteral ) {
			return ((PixelLiteral) expression).value+PIXEL;
		}
		return null;
	}

	// GE02 - Indent of 2 spaces (nbps) per scopeLevel
	private void newLine() {
		String indent = "";
		for(int i=0; i<scopeLevel; i++) {
			indent = indent.concat(INDENT);
		}
		css = css.concat(CARRIAGE_RETURN).concat(indent);
	}

	private void addString(String addition) {
		css = css.concat(addition);
	}

	private void space() {
		addString(NBSP);
	}
}
