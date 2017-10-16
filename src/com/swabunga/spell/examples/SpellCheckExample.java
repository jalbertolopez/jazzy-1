package com.swabunga.spell.examples;

import java.io.*;
import java.util.*;
import com.swabunga.spell.event.*;
import com.swabunga.spell.engine.*;

public class SpellCheckExample implements SpellCheckListener {

	private static String corpusRAE = "C:\\System\\Codes\\Java\\jazzy-1\\dict\\corpusRAE.txt";
	private static String nHombres = "C:\\System\\Codes\\Java\\jazzy-1\\dict\\nombreH.txt";
	private static String nMujeres = "C:\\System\\Codes\\Java\\jazzy-1\\dict\\nombreM.txt";
	//private static String dictFile = "C:\\System\\Codes\\Java\\jazzy-1\\dict\\corpusRAE.txt";
	private SpellChecker spellCheck = null;

	public SpellCheckExample() {
		try {
			//SpellDictionary dictionary = new SpellDictionary(new File(corpusRAE));
			SpellDictionary dictionary = new SpellDictionary(new File(nHombres));

			spellCheck = new SpellChecker(dictionary);
			spellCheck.addSpellCheckListener(this);
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("ORACIÓN A SER ANALIZADA: ");
				String line = in.readLine();

				if (line.length() <= 0)
					break;
				spellCheck.checkSpelling(new StringWordTokenizer(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void spellingError(SpellCheckEvent event) {
		List suggestions = event.getSuggestions();
		if (suggestions.size() > 0) {
			System.out.println("PALABRA MAL ESCRITA: " + event.getInvalidWord());
			for (Iterator suggestedWord = suggestions.iterator(); suggestedWord.hasNext();) {
				System.out.println("SUGERENCIA: =" + suggestedWord.next());
			}
		}
		// NINGUNA ACCION EN CASO DE ELSE
	}

	public static void main(String[] args) {
		new SpellCheckExample();
	}
}
