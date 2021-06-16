// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class Parser {

    static public Formula parse3(String input) {
	Parser parser = new Parser(false);
	return parser.parse2(input);
    }

     public Variable getVariable(String name) {
	return (Variable) Symbol.getSymbol(name);
    }
     public Variable fetchVariable(String name) {
	Variable out = (Variable) Symbol.getSymbol(name);
	if ( null == out ) { 
	    out = new Variable(name);
	    Symbol.addSymbol(name, out);
	}
	return out;
    }
    private boolean traceb = false;

    public Parser (boolean traceb) { this.traceb = traceb; }

    public Formula parse2(String input) { 
	Formula out = null;
	try { out = parse(input); }
	catch (Exception ex) { return null; }
	return out;
    }

    public Formula parse(String input) throws ParserException, Exception {
	input = input.trim();
	if ( traceb ) System.out.println("parse input: " + input);
	int lng = input.length();
	if ( lng < 4 ) throw new ParserException("No input: " + input);
	if ( !input.endsWith(")") )
	    throw new ParserException("Cant find ) at end of conjunction");
	if ( input.startsWith("&&(") ) {
	    String body = input.substring(3, lng-1);
	    return parseList("&&", body.trim()); 
	}
	if ( input.startsWith("||(") ) {
	    String body = input.substring(3, lng-1);
	    return parseList("||", body.trim());
	}
	if ( input.startsWith("->(") ) {
	    String body = input.substring(3, lng-1);
	    return parseList("->", body.trim());
	}
	if ( input.startsWith("<->(") ) {
	    String body = input.substring(4, lng-1);
	    return parseList("<->", body.trim());
	}
	if ( input.startsWith("~(") ) {
	    String body = input.substring(2, lng-1);
	    return parseList("~", body.trim());
	}
	if ( input.startsWith("==(") ) {
	    String body = input.substring(3, lng-1).trim();
	    Atom atom = parseArgs(Symbol.dummy, body);
	    return new Equal(atom.getArgs());
	}
	if ( input.startsWith("!=(") ) {
	    String body = input.substring(3, lng-1).trim();
	    Atom atom = parseArgs(Symbol.dummy, body);
	    return new Unequal(atom.getArgs());
	}
	if ( input.startsWith("<(") ) {
	    String body = input.substring(2, lng-1).trim();
	    Atom atom = parseArgs(Symbol.dummy, body);
	    return new Less(atom.getArgs());
	}
	if ( input.startsWith(">(") ) {
	    String body = input.substring(2, lng-1).trim();
	    Atom atom = parseArgs(Symbol.dummy, body);
	    return new Greater(atom.getArgs());
	}
	if ( input.startsWith("<=(") ) {
	    String body = input.substring(3, lng-1).trim();
	    Atom atom = parseArgs(Symbol.dummy, body);
	    return new LessOrEqual(atom.getArgs());
	}
	if ( input.startsWith(">=(") ) {
	    String body = input.substring(3, lng-1).trim();
	    Atom atom = parseArgs(Symbol.dummy, body);
	    return new GreaterOrEqual(atom.getArgs());
	}
	// Here == < > <= >= !=
	// Here (uq  (eq
	if ( input.startsWith("(uq") ) {
	    Vector out = quantified(input); 
	    Variable var = (Variable) out.elementAt(0);
	    Formula bodyFormula = (Formula) out.elementAt(1);
	    return new Universal(var, bodyFormula);
	}

	if ( input.startsWith("(eq") ) {
	    Vector out = quantified(input); 
	    Variable var = (Variable) out.elementAt(0);
	    Formula bodyFormula = (Formula) out.elementAt(1);
	    return new Existential(var, bodyFormula);
	}

	if ( input.startsWith("(") ) {
	    int end = findEnd(input, 0);
	    if ( end <= 0 )
		throw new ParserException("Formula: cant find ) character");
	    return parse(input.substring(1, end));
	}


	if ( !Character.isUpperCase(input.charAt(0)) )
	    throw new ParserException("Formula: expect uppercase character");
	int start = input.indexOf('(');
	if ( start < 0 )
	    throw new ParserException("Formula: cant find ( character");
	int end = findEnd(input, start);
	if ( end < start )
	    throw new ParserException("Formula: cant find ) character");
	String head = input.substring(0, start).trim();
	String tail = input.substring(start+1, end);
	if ( traceb ) {
	    System.out.println("head: " + head.length() + " " + head);
	    System.out.println("tail: " + tail.length() + " " + tail);
	}
	Symbol headSymbol = Symbol.fetchSymbol(head);
	try { return parseArgs(headSymbol, tail); }
	catch (ParserException ex) {
	    throw new ParserException("Formula: " + ex.getMessage());
	}

	// ++++++++
	// return null;
    }
    private Atom parseArgs(Symbol headSymbol, String body) 
	throws ParserException, Exception {
	if ( traceb )
	    System.out.println("head: " + headSymbol.html() +
			       " body: " + body);
	int lng = body.length();
	if ( 0 == lng ) 
	    throw new ParserException("parseArgs: head= " + 
				       headSymbol.html() + " no body");
	Vector formulas = new Vector();
	while ( 0 < lng ) {
	    if (traceb) System.out.println("lng: " + lng + " body: " + body);
	    if ( body.startsWith("?") ) { // variable
		if ( lng <=1 )
		    throw new ParserException(
                          "parseArgs: cant find variable in: " + body);
		int end = findLast(body, 1) + 1;
		if ( end < 1 )
		    throw new ParserException(
                          "parseArgs: cant find variable in: " + body);
		Variable var = fetchVariable(body.substring(1, end));
		formulas.addElement(var);
		int tail = end;
		if ( tail == lng ) break;
		body = body.substring(tail).trim();
		lng = body.length();
		continue;
	    }
	    char c = body.charAt(0);
	    if ( Character.isDigit(c) || '-' == c || '+' == c) { // should be an int
		boolean signSet = false;
		if ( '-' == c || '+' == c ) { signSet = true; }
		int end = ( signSet ? findLast(body, 1) + 1 : findLast(body, 0) + 1 );
		if ( end < 0 )
		    throw new ParserException(
			 "parseArgs: cant find int in: " + body);
		String intString = body.substring(0, end);
		try { int x = Integer.parseInt(intString); }
		catch (NumberFormatException ex) {
		    throw new ParserException(
                        "parseArgs: cant find int in: " + intString);
		}
		Symbol sym = Symbol.getSymbol(intString); 
		if ( null == sym ) sym = new IntSymbol(intString);
		formulas.addElement(sym);
		body = body.substring(end).trim();
		lng = body.length();
		if ( 0 == lng ) break;
		continue;
	    }
	    if ( !(Character.isLetter(c) ) )
		throw new ParserException(
		   "parseArgs: expect lower- or upper-case letter: " + 
		   body);
	    int end = findLast(body, 0) + 1;
	    if ( end < 0 )
		throw new ParserException(
		   "parseArgs: cant find symbol in: " + body);
	    Symbol sym = Symbol.fetchSymbol(body.substring(0, end));
	    body = body.substring(end).trim();
	    lng = body.length();
	    if ( 0 == lng ) {
		formulas.addElement(sym);
		break;
	    }
	    if ( !body.startsWith("(") ) {
		formulas.addElement(sym);
		continue;
	    }
	    end = findEnd(body, 0);
	    if ( end < 0 )
		throw new ParserException("parseArgs: cant find ) in: " + body);
	    Atom term = parseArgs(sym, body.substring(1, end).trim());
	    FTerm fterm = new FTerm(term.getPredicate(), term.getArgs());
	    formulas.addElement(fterm);
	    if (traceb) System.out.println("atom term: " + term.html());
	    body = body.substring(end + 1).trim();
	    lng = body.length();
	}

	int numberOfArgs = formulas.size();
	if ( 0 == numberOfArgs )
	    throw new ParserException("parseArgs:  no arguments");
	if (traceb)
	    for (int i = 0; i < numberOfArgs; i++) {
		Term term = (Term) formulas.elementAt(i);
		System.out.println("parseArgs Term: " + term.html());
	    }
	return new Atom(headSymbol, formulas);
	// 
    }

    private Formula parseList(String operand, String body) 
	throws ParserException, Exception {
	if (traceb) 
	    System.out.println("operand: " + operand +
			       " body: " + body);
	int lng = body.length();
	if ( 0 == lng ) {
	    if ( operand.equals("&&") ) return Symbol.TRUE;
	    if ( operand.equals("||") ) return Symbol.FALSE;
	    throw new ParserException("parseList: operand= " + 
				       operand + " no body");
	}
	Vector formulas = new Vector();
	while ( 0 < lng ) {
	    if (traceb) 
		System.out.println("lng: " + lng + " body: " + body);

	    if ( body.startsWith("True()") ) {
		formulas.addElement(Symbol.TRUE);
		body = body.substring(6).trim();
		lng = body.length();
		continue;
	    }
	    if ( body.startsWith("False()") ) {
		formulas.addElement(Symbol.FALSE);
		body = body.substring(7).trim();
		lng = body.length();
		continue;
	    }
	    // System.out.println("2lng: " + lng + " body: " + body);
	    int start = body.indexOf("(");
	    int end = findEnd(body, start) + 1 ;
	    if ( end <= start ) 
		throw new ParserException(
			    "parseList: operand= " + 
			    operand + " no end bracket body= " +
			    body);
	    formulas.addElement(parse(body.substring(0, end)));
	    // System.out.println("YY " + end + " " + body.length());
	    if ( end == body.length() ) break;
	    body = body.substring(end+1).trim();
	    // System.out.println("XX " + body);
	    lng = body.length();
	}
	int numberOfFormulas = formulas.size();
	if ( 0 == numberOfFormulas )
	    throw new ParserException("parseList: operand= " + 
				       operand + " no formulas");
	if ( operand.equals("&&") ) {
	    if ( 1 == numberOfFormulas ) 
		return (Formula) formulas.elementAt(0);
	    return new Conjunction(formulas);
	}
	if ( operand.equals("||") ) {
	    if ( 1 == numberOfFormulas ) 
		return (Formula) formulas.elementAt(0);
	    return new Disjunction(formulas);
	}
	if ( operand.equals("->") ) {
	    if ( 2 != numberOfFormulas ) 
	    throw new ParserException("parseList: operand= ->" + 
				       " not 2 formulas");
	    return new Implication(formulas);
	}
	if ( operand.equals("<->") ) {
	    if ( 2 != numberOfFormulas ) 
	    throw new ParserException("parseList: operand= <->" + 
				       " not 2 formulas");
	    return new Equivalence(formulas);
	}
	if ( operand.equals("~") ) {
	    if ( 1 != numberOfFormulas ) 
	    throw new ParserException("parseList: operand= ~" + 
				       " not 1 formula");
	    return new Negation((Formula)formulas.elementAt(0));
	}

	throw new ParserException("parseList: operand= " + 
				  operand + " is unknown");
    }

    private int findEnd(String body, int start) {
	// index start has an open bracket
	// findEnd returns the corresponding close bracket, if any
	int lng = body.length();
	int level = 0;
	char c;
	for ( int i = start+1; i < lng; i++ ) {
	    c = body.charAt(i);
	    if ( c == ')' ) {
		if ( 0 == level ) return i;
		level--;
	    } else 
	    if ( c == '(' ) level++;
	}
	return -1;
    }
    private int findLast(String body, int start) {
	// index start begins a symbol
	// findLast returns the position of the last character, if any
	int lng = body.length();
	char c;
	for ( int i = start; i < lng; i++ ) {
	    c = body.charAt(i);
	    if ( Character.isLetterOrDigit(c) ) continue;
	    return i-1;
	}
	return lng - 1;
    }

    private Vector quantified(String input) 
        throws Exception, ParserException {
	int lng = input.length();
	String body = input.substring(3, lng-1).trim();
	lng = body.length();
	if ( lng < 5 ) 
	    throw new ParserException(
		"Formula: cant find body in quantified expression");
	if ( !body.startsWith("?") ) 
		throw new ParserException("Formula: expect variable");
	int end = findLast(body, 1) + 1;
	if ( end < 1 )
	    throw new ParserException(
		  "parseArgs: cant find variable in: " + body);
	Variable var = fetchVariable(body.substring(1, end));
	body = body.substring(end).trim();
	lng = body.length();
	if ( 0 == lng )
	    throw new ParserException(
	          "Formula: cant find body in quantified expression");
	Formula bodyFormula = parse(body);
	Vector out = new Vector();
	out.addElement(var);
	out.addElement(bodyFormula);
	return out;
    }
}
