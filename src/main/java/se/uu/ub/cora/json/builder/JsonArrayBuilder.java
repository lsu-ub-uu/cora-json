/*
 * Copyright 2015 Uppsala University Library
 * Copyright 2016 Olov McKie
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.json.builder;

import se.uu.ub.cora.json.parser.JsonArray;

/**
 * JsonArrayBuilder is an interface for classes that build {@link JsonArray}
 */
public interface JsonArrayBuilder {

	/**
	 * addKeyString adds a String value to this JsonObjectBuilder using the specified key (name)
	 * 
	 * @param key
	 *            A String name to store the value under in the builder
	 * @param value
	 *            A String value to store under the given key (name)
	 */
	void addString(String value);

	/**
	 * addKeyJsonObjectBuilder adds a {@link JsonObjectBuilder} value to this JsonArrayBuilder using
	 * the specified key (name)
	 * 
	 * @param key
	 *            A String name to store the value under in the builder
	 * @param value
	 *            A JsonObjectBuilder to store under the given key (name)
	 */
	void addJsonObjectBuilder(JsonObjectBuilder jsonObjectBuilder);

	/**
	 * addKeyJsonArrayBuilder adds a {@link JsonArrayBuilder} value to this JsonArrayBuilder using
	 * the specified key (name)
	 * 
	 * @param key
	 *            A String name to store the value under in the builder
	 * @param value
	 *            A JsonArrayBuilder to store under the given key (name)
	 */
	void addJsonArrayBuilder(JsonArrayBuilder jsonArrayBuilder);

	/**
	 * toJsonArray return the {@link JsonArray} that is beeing constructed using this builder.
	 * 
	 * @return A {@link JsonArray} constructed using methods in this builder
	 */
	JsonArray toJsonArray();

	/**
	 * toJsonCompactFormat return a String with a json String representation of the array that is
	 * beeing constructed using this builder using as little whitespace as possible.
	 * 
	 * @return A String with the json representation of the array.
	 */
	String toJsonFormattedString();

	/**
	 * toJsonFormattedPrettyString return a String with a json String representation of the array
	 * that is beeing constructed using this builder using a text representation that is as readable
	 * as possible, using whitespace and indentation to make the json more readable.
	 * 
	 * @return A String with the json representation of the array.
	 */
	String toJsonFormattedPrettyString();
}
