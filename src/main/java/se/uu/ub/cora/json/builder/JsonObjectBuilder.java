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

import se.uu.ub.cora.json.parser.JsonObject;

/**
 * JsonObjectBuilder is an interface for classes that build {@link JsonObject}
 */
public interface JsonObjectBuilder {

	/**
	 * addKeyString adds a String value to this JsonObjectBuilder using the specified key (name)
	 * <p>
	 * If the value is null, then the key will be removed from the JSONObject if it is present.
	 * 
	 * @param key
	 *            A String name to store the value under in the builder
	 * @param value
	 *            A String value to store under the given key (name)
	 */
	void addKeyString(String key, String value);

	/**
	 * addKeyJsonObjectBuilder adds a {@link JsonObjectBuilder} value to this JsonObjectBuilder
	 * using the specified key (name)
	 * <p>
	 * If the value is null, then the key will be removed from the JSONObject if it is present.
	 * 
	 * @param key
	 *            A String name to store the value under in the builder
	 * @param value
	 *            A JsonObjectBuilder to store under the given key (name)
	 */
	void addKeyJsonObjectBuilder(String key, JsonObjectBuilder jsonObjectBuilder);

	/**
	 * addKeyJsonArrayBuilder adds a {@link JsonArrayBuilder} value to this JsonObjectBuilder using
	 * the specified key (name)
	 * <p>
	 * If the value is null, then the key will be removed from the JSONObject if it is present.
	 * 
	 * @param key
	 *            A String name to store the value under in the builder
	 * @param value
	 *            A JsonArrayBuilder to store under the given key (name)
	 */
	void addKeyJsonArrayBuilder(String key, JsonArrayBuilder jsonArrayBuilder);

	/**
	 * toJsonObject return the {@link JsonObject} that is beeing constructed using this builder.
	 * 
	 * @return A {@link JsonObject} constructed using methods in this builder
	 */
	JsonObject toJsonObject();

	/**
	 * toJsonCompactFormat return a String with a json String representation of the object that is
	 * beeing constructed using this builder using as little whitespace as possible.
	 * 
	 * @return A String with the json representation of the object.
	 */
	String toJsonFormattedString();

	/**
	 * toJsonFormattedPrettyString return a String with a json String representation of the object
	 * that is beeing constructed using this builder using a text representation that is as readable
	 * as possible, using whitespace and indentation to make the json more readable.
	 * 
	 * @return A String with the json representation of the object.
	 */
	String toJsonFormattedPrettyString();
}
