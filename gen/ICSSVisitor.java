// Generated from C:/Users/heili/OneDrive/Workspace/Her23/App/Icss_Compiler/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variable_reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_reference(ICSSParser.Variable_referenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ICSSParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty_name(ICSSParser.Property_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolean_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_literal(ICSSParser.Boolean_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#pixel_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPixel_literal(ICSSParser.Pixel_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#percentage_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentage_literal(ICSSParser.Percentage_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#scalar_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalar_literal(ICSSParser.Scalar_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#color_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor_literal(ICSSParser.Color_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#tag_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_selector(ICSSParser.Tag_selectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#id_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_selector(ICSSParser.Id_selectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#class_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_selector(ICSSParser.Class_selectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#if_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_clause(ICSSParser.If_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#else_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_clause(ICSSParser.Else_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(ICSSParser.ConditionContext ctx);
}