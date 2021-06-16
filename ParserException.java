// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ParserException extends Exception {

    ParserException(String message) throws Exception {
	String m2 = "##: " + message;
	System.out.println(m2);
	throw new Exception(m2);
    }
}
