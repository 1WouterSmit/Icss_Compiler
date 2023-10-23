// Generated from C:/Users/heili/OneDrive/Workspace/Her23/App/icss2022-sep/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void enterVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variable_reference}.
	 * @param ctx the parse tree
	 */
	void enterVariable_reference(ICSSParser.Variable_referenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variable_reference}.
	 * @param ctx the parse tree
	 */
	void exitVariable_reference(ICSSParser.Variable_referenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void enterStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void exitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ICSSParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ICSSParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#property_name}.
	 * @param ctx the parse tree
	 */
	void enterProperty_name(ICSSParser.Property_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#property_name}.
	 * @param ctx the parse tree
	 */
	void exitProperty_name(ICSSParser.Property_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_literal(ICSSParser.Boolean_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_literal(ICSSParser.Boolean_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#pixel_literal}.
	 * @param ctx the parse tree
	 */
	void enterPixel_literal(ICSSParser.Pixel_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#pixel_literal}.
	 * @param ctx the parse tree
	 */
	void exitPixel_literal(ICSSParser.Pixel_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#percentage_literal}.
	 * @param ctx the parse tree
	 */
	void enterPercentage_literal(ICSSParser.Percentage_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#percentage_literal}.
	 * @param ctx the parse tree
	 */
	void exitPercentage_literal(ICSSParser.Percentage_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#scalar_literal}.
	 * @param ctx the parse tree
	 */
	void enterScalar_literal(ICSSParser.Scalar_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#scalar_literal}.
	 * @param ctx the parse tree
	 */
	void exitScalar_literal(ICSSParser.Scalar_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#color_literal}.
	 * @param ctx the parse tree
	 */
	void enterColor_literal(ICSSParser.Color_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#color_literal}.
	 * @param ctx the parse tree
	 */
	void exitColor_literal(ICSSParser.Color_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#tag_selector}.
	 * @param ctx the parse tree
	 */
	void enterTag_selector(ICSSParser.Tag_selectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#tag_selector}.
	 * @param ctx the parse tree
	 */
	void exitTag_selector(ICSSParser.Tag_selectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#id_selector}.
	 * @param ctx the parse tree
	 */
	void enterId_selector(ICSSParser.Id_selectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#id_selector}.
	 * @param ctx the parse tree
	 */
	void exitId_selector(ICSSParser.Id_selectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#class_selector}.
	 * @param ctx the parse tree
	 */
	void enterClass_selector(ICSSParser.Class_selectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#class_selector}.
	 * @param ctx the parse tree
	 */
	void exitClass_selector(ICSSParser.Class_selectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#if_clause}.
	 * @param ctx the parse tree
	 */
	void enterIf_clause(ICSSParser.If_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#if_clause}.
	 * @param ctx the parse tree
	 */
	void exitIf_clause(ICSSParser.If_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#else_clause}.
	 * @param ctx the parse tree
	 */
	void enterElse_clause(ICSSParser.Else_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#else_clause}.
	 * @param ctx the parse tree
	 */
	void exitElse_clause(ICSSParser.Else_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ICSSParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ICSSParser.ConditionContext ctx);
}