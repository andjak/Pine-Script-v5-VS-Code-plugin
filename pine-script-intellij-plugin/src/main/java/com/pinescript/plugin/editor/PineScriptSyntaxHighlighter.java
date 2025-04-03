package com.pinescript.plugin.editor;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.pinescript.plugin.language.PineScriptLexer;
import com.pinescript.plugin.psi.PineScriptTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class PineScriptSyntaxHighlighter extends SyntaxHighlighterBase {
    // TradingView-like colors
    private static final Color KEYWORD_COLOR = new Color(86, 156, 214);      // Blue for keywords
    private static final Color STRING_COLOR = new Color(206, 145, 120);      // Orange-brown for strings
    private static final Color NUMBER_COLOR = new Color(181, 206, 168);      // Light green for numbers
    private static final Color COMMENT_COLOR = new Color(106, 153, 85);      // Green for comments
    private static final Color FUNCTION_COLOR = new Color(220, 220, 170);    // Light yellow for functions
    private static final Color OPERATOR_COLOR = new Color(180, 180, 180);    // Light gray for operators
    private static final Color IDENTIFIER_COLOR = new Color(156, 220, 254);  // Light blue for identifiers
    private static final Color TYPE_COLOR = new Color(78, 201, 176);         // Teal for types
    private static final Color NAMESPACE_COLOR = new Color(78, 201, 176);    // Teal for namespaces

    // Create TextAttributes with TradingView-like colors
    private static final TextAttributes KEYWORD_ATTRIBUTES = new TextAttributes(KEYWORD_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes STRING_ATTRIBUTES = new TextAttributes(STRING_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes NUMBER_ATTRIBUTES = new TextAttributes(NUMBER_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes COMMENT_ATTRIBUTES = new TextAttributes(COMMENT_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes FUNCTION_ATTRIBUTES = new TextAttributes(FUNCTION_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes OPERATOR_ATTRIBUTES = new TextAttributes(OPERATOR_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes IDENTIFIER_ATTRIBUTES = new TextAttributes(IDENTIFIER_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes TYPE_ATTRIBUTES = new TextAttributes(TYPE_COLOR, null, null, null, Font.PLAIN);
    private static final TextAttributes NAMESPACE_ATTRIBUTES = new TextAttributes(NAMESPACE_COLOR, null, null, null, Font.PLAIN);

    // Create TextAttributesKey with custom attributes
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("PINE_SCRIPT_KEYWORD", KEYWORD_ATTRIBUTES);
    public static final TextAttributesKey STRING = createTextAttributesKey("PINE_SCRIPT_STRING", STRING_ATTRIBUTES);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("PINE_SCRIPT_NUMBER", NUMBER_ATTRIBUTES);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("PINE_SCRIPT_COMMENT", COMMENT_ATTRIBUTES);
    public static final TextAttributesKey FUNCTION = createTextAttributesKey("PINE_SCRIPT_FUNCTION", FUNCTION_ATTRIBUTES);
    public static final TextAttributesKey OPERATOR = createTextAttributesKey("PINE_SCRIPT_OPERATOR", OPERATOR_ATTRIBUTES);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("PINE_SCRIPT_IDENTIFIER", IDENTIFIER_ATTRIBUTES);
    public static final TextAttributesKey TYPE = createTextAttributesKey("PINE_SCRIPT_TYPE", TYPE_ATTRIBUTES);
    public static final TextAttributesKey NAMESPACE = createTextAttributesKey("PINE_SCRIPT_NAMESPACE", NAMESPACE_ATTRIBUTES);
    
    // Use default colors for these
    public static final TextAttributesKey PARENTHESES = createTextAttributesKey("PINE_SCRIPT_PARENTHESES", OPERATOR_ATTRIBUTES);
    public static final TextAttributesKey BRACKETS = createTextAttributesKey("PINE_SCRIPT_BRACKETS", OPERATOR_ATTRIBUTES);
    public static final TextAttributesKey BRACES = createTextAttributesKey("PINE_SCRIPT_BRACES", OPERATOR_ATTRIBUTES);
    public static final TextAttributesKey COMMA = createTextAttributesKey("PINE_SCRIPT_COMMA", OPERATOR_ATTRIBUTES);
    public static final TextAttributesKey DOT = createTextAttributesKey("PINE_SCRIPT_DOT", OPERATOR_ATTRIBUTES);
    public static final TextAttributesKey SEMICOLON = createTextAttributesKey("PINE_SCRIPT_SEMICOLON", OPERATOR_ATTRIBUTES);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("PINE_SCRIPT_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new PineScriptLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType == null) return TextAttributesKey.EMPTY_ARRAY;

        if (tokenType.equals(PineScriptTokenTypes.KEYWORD)) {
            return pack(KEYWORD);
        } else if (tokenType.equals(PineScriptTokenTypes.IDENTIFIER)) {
            return pack(IDENTIFIER);
        } else if (tokenType.equals(PineScriptTokenTypes.COMMENT)) {
            return pack(COMMENT);
        } else if (tokenType.equals(PineScriptTokenTypes.STRING)) {
            return pack(STRING);
        } else if (tokenType.equals(PineScriptTokenTypes.NUMBER)) {
            return pack(NUMBER);
        } else if (tokenType.equals(PineScriptTokenTypes.OPERATOR)) {
            return pack(OPERATOR);
        } else if (tokenType.equals(PineScriptTokenTypes.FUNCTION)) {
            return pack(FUNCTION);
        } else if (tokenType.equals(PineScriptTokenTypes.NAMESPACE)) {
            return pack(NAMESPACE);
        } else if (tokenType.equals(PineScriptTokenTypes.TYPE)) {
            return pack(TYPE);
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return pack(BAD_CHARACTER);
        }

        // Handle punctuation
        if (tokenType.equals(PineScriptTokenTypes.LPAREN) || tokenType.equals(PineScriptTokenTypes.RPAREN)) {
            return pack(PARENTHESES);
        } else if (tokenType.equals(PineScriptTokenTypes.LBRACKET) || tokenType.equals(PineScriptTokenTypes.RBRACKET)) {
            return pack(BRACKETS);
        } else if (tokenType.equals(PineScriptTokenTypes.LBRACE) || tokenType.equals(PineScriptTokenTypes.RBRACE)) {
            return pack(BRACES);
        } else if (tokenType.equals(PineScriptTokenTypes.COMMA)) {
            return pack(COMMA);
        } else if (tokenType.equals(PineScriptTokenTypes.DOT)) {
            return pack(DOT);
        } else if (tokenType.equals(PineScriptTokenTypes.SEMICOLON)) {
            return pack(SEMICOLON);
        }

        return TextAttributesKey.EMPTY_ARRAY;
    }
} 