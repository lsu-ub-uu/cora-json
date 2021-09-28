/*
 * Copyright 2015, 2021 Uppsala University Library
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

package se.uu.ub.cora.json.parser;

/**
 * JsonParser parses string to JsonValues (JsonObject or JsonArray)
 * <p>
 * Implementations of JsonParser MUST be threadsafe.
 */
public interface JsonParser {
	/**
	 * parseString parses a String with json data to a {@link JsonValue}. If the input string can
	 * not be fully parsed to a JsonValue MUST an {@link JsonParseException} be thrown.
	 * 
	 * @param jsonString
	 *            A String with json to convert to a JsonValue
	 * @return A JsonValue created from the entered json string
	 */
	JsonValue parseString(String jsonString);

	/**
	 * parseStringAsObject parses a String with json data to a {@link JsonObject}. If the input
	 * string can not be fully parsed to a JsonObject MUST an {@link JsonParseException} be thrown.
	 * 
	 * @param jsonString
	 *            A String with json to convert to a JsonObject
	 * @return A JsonObject created from the entered json string
	 */
	JsonObject parseStringAsObject(String jsonString);

	/**
	 * parseStringAsArray parses a String with json data to a {@link JsonArray}. If the input string
	 * can not be fully parsed to a JsonArray MUST an {@link JsonParseException} be thrown.
	 * 
	 * @param jsonString
	 *            A String with json to convert to a JsonArray
	 * @return A JsonArray created from the entered json string
	 */
	JsonArray parseStringAsArray(String jsonString);

}
