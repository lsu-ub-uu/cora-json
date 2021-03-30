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

package se.uu.ub.cora.json.parser.org;

import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import se.uu.ub.cora.json.parser.JsonArray;
import se.uu.ub.cora.json.parser.JsonObject;
import se.uu.ub.cora.json.parser.JsonParseException;
import se.uu.ub.cora.json.parser.JsonParser;
import se.uu.ub.cora.json.parser.JsonValue;
import se.uu.ub.cora.json.parser.JsonValueType;

public class OrgJsonParser implements JsonParser {

	private static final String UNABLE_TO_PARSE_JSON_STRING = "Unable to parse json string";
	private static final Pattern UNICODE_PATTERN = Pattern.compile("\\\\u([0-9A-Fa-f]{4})");

	@Override
	public JsonValue parseString(String jsonString) {
		try {
			JsonValue jsonValue = tryToParseJsonString(jsonString);
			validateEntireStringWasParsed(jsonString, jsonValue);
			return jsonValue;
		} catch (Exception e) {
			throw new JsonParseException(UNABLE_TO_PARSE_JSON_STRING, e);
		}
	}

	private JsonValue tryToParseJsonString(String jsonString) {
		if (stringIsBelivedToContainAnObject(jsonString)) {
			return tryToParseJsonStringAsObject(jsonString);
		}
		return tryToParseJsonStringAsArray(jsonString);
	}

	private boolean stringIsBelivedToContainAnObject(String jsonString) {
		return jsonString.trim().startsWith("{");
	}

	private JsonValue tryToParseJsonStringAsObject(String jsonString) {
		JSONObject jsonObject = new JSONObject(jsonString);
		return OrgJsonValueFactory.createFromOrgJsonObject(jsonObject);
	}

	private JsonValue tryToParseJsonStringAsArray(String jsonString) {
		JSONArray jsonArray = new JSONArray(jsonString);
		return OrgJsonValueFactory.createFromOrgJsonObject(jsonArray);
	}

	private void validateEntireStringWasParsed(String jsonString, JsonValue jsonValue) {
		String originalJsonWithoutWhitespace = removeWiteSpaceAndUnescapeUnicodeFromString(
				jsonString);
		String recreatedJsonString = recreateJsonStringFromJsonValue(jsonValue);
		String recreatedJsonWithoutWhitespace = removeWiteSpaceAndUnescapeUnicodeFromString(
				recreatedJsonString);
		if (originalJsonWithoutWhitespace.length() != recreatedJsonWithoutWhitespace.length()) {
			throw new JsonParseException("Unable to fully parse json string");
		}
	}

	private String unescapeUnicode(String unescaped) {
		return UNICODE_PATTERN.matcher(unescaped)
				.replaceAll(r -> String.valueOf((char) Integer.parseInt(r.group(1), 16)));
	}

	private String removeWiteSpaceAndUnescapeUnicodeFromString(String jsonString) {
		String allWhitespace = "\\s";
		String replaced = jsonString.replaceAll(allWhitespace, "");
		return unescapeUnicode(replaced);
	}

	private String recreateJsonStringFromJsonValue(JsonValue jsonValue) {
		String recreatedJsonString = "";
		if (JsonValueType.OBJECT == jsonValue.getValueType()) {
			recreatedJsonString = ((JsonObject) jsonValue).toJsonFormattedString();
		} else {
			recreatedJsonString = ((JsonArray) jsonValue).toJsonFormattedString();
		}
		return recreatedJsonString;
	}

	@Override
	public JsonObject parseStringAsObject(String jsonString) {
		try {
			return (JsonObject) tryToParseJsonStringAsObject(jsonString);
		} catch (Exception e) {
			throw new JsonParseException(UNABLE_TO_PARSE_JSON_STRING, e);
		}
	}

	@Override
	public JsonArray parseStringAsArray(String jsonString) {
		try {
			return (JsonArray) tryToParseJsonStringAsArray(jsonString);
		} catch (Exception e) {
			throw new JsonParseException(UNABLE_TO_PARSE_JSON_STRING, e);
		}
	}

}
