package org.xcsp.common.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.xcsp.common.Constants;
import org.xcsp.common.Utilities;

public class TableSymbolic extends Table {
	@Override
	public TableSymbolic positive(Boolean positive) {
		this.positive = positive;
		return this;
	}

	private List<String[]> list = new ArrayList<>();

	/**
	 * Adds the specified symbolic tuple to the table.
	 * 
	 * @param tuple
	 *            a symbolic tuple
	 * @return this symbolic table
	 */
	public TableSymbolic add(String... tuple) {
		Utilities.control(tuple.length > 0, "A tuple of length 0 has been encoutered during parsing.\n" + TABLE_SYNTAX_PB);
		Utilities.control(list.size() == 0 || list.get(0).length == tuple.length, "The tuple has a different length from those already recorded");
		list.add(tuple);
		return this;
	}

	/**
	 * Adds the specified symbolic tuples to the table.
	 * 
	 * @param tuples
	 *            a sequence of tuples
	 * @return this symbolic table
	 */
	public TableSymbolic add(String[]... tuples) {
		Stream.of(tuples).forEach(t -> add(t));
		return this;
	}

	/**
	 * Adds all tuples of the specified stream to the table.
	 * 
	 * @param stream
	 *            a stream of tuples to be added to the table
	 * @return this symbolic table
	 */
	public TableSymbolic add(Stream<String[]> stream) {
		stream.forEach(t -> add(t));
		return this;
	}

	/**
	 * Adds the tuples obtained after parsing the specified string. The string must represent a sequence of tuples as defined in XCSP3. For
	 * example, it could be {@code "(a,a,b)(a,c,a)(b,a,b)(b,b,c)"}.
	 * 
	 * @param tuples
	 *            a string representing a sequence of tuples
	 * @return this symbolic table
	 */
	public TableSymbolic addSequence(String s) {
		boolean b = controlStringRepresentationOfTuples(s);
		Utilities.control(!b, "The specified string is not correct, as it does not correspond to a sequence of symbolic tuples");
		String[][] tuples = Stream.of(s.split(Constants.DELIMITER_LISTS)).skip(1).map(tok -> Stream.of(tok.split("\\s*,\\s*")).toArray(String[]::new))
				.toArray(String[][]::new);
		Stream.of(tuples).forEach(tuple -> add(tuple));
		return this;
	}

	/**
	 * Returns a 2-dimensional array corresponding to the collected tuples.
	 * 
	 * @return a 2-dimensional array corresponding to the collected tuples
	 */
	public String[][] toArray() {
		return list.stream().sorted(Utilities.lexComparatorString).distinct().toArray(String[][]::new);
	}

}