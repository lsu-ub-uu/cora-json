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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.json.parser.JsonArray;
import se.uu.ub.cora.json.parser.JsonObject;
import se.uu.ub.cora.json.parser.JsonParseException;
import se.uu.ub.cora.json.parser.JsonParser;
import se.uu.ub.cora.json.parser.JsonString;
import se.uu.ub.cora.json.parser.JsonValue;
import se.uu.ub.cora.json.parser.JsonValueType;

public class OrgJsonParserTest {
	private JsonParser jsonParser;

	@BeforeMethod
	public void beforeMethod() {
		jsonParser = new OrgJsonParser();
	}

	@Test(expectedExceptions = JsonParseException.class)
	public void testFactorOnJsonStringNullJson() {
		String json = null;
		jsonParser.parseString(json);
	}

	@Test(expectedExceptions = JsonParseException.class)
	public void testFactorOnJsonStringEmptyJson() {
		String json = "";
		jsonParser.parseString(json);
	}

	@Test(expectedExceptions = JsonParseException.class)
	public void testFactorOnJsonStringBrokenJson() {
		String json = "{";
		jsonParser.parseString(json);
	}

	@Test
	public void testObjectCreate() {
		JsonValue jsonValue = jsonParser.parseString("{\"id\":\"value\"}");
		assertTrue(jsonValue instanceof JsonObject);
	}

	@Test
	public void testObjectCreateWithSpaceInValue() {
		JsonValue jsonValue = jsonParser.parseString("{\"id\":\"This is a value with space\"}");
		assertTrue(jsonValue instanceof JsonObject);
	}

	@Test
	public void testObjectCreateWithProblematicValue() {
		String enDash = "–";
		JsonValue jsonValue = jsonParser.parseString("{\"id\":\"" + enDash + "\"}");
		assertTrue(jsonValue instanceof JsonObject);
		assertEquals(((JsonObject) jsonValue).getValueAsJsonString("id").getStringValue(), enDash);
	}

	@Test(enabled = false)
	public void testObjectCreateWithProblematicValue2() {
		JsonValue jsonValue = jsonParser.parseString("{\"id\":\"\\u2013\"}");
		assertTrue(jsonValue instanceof JsonObject);
		JsonString valueAsJsonString = ((JsonObject) jsonValue).getValueAsJsonString("id");
		assertEquals(valueAsJsonString.getStringValue(), "\\u2013");
	}

	@Test
	public void testObjectGetValueType() {
		JsonValue jsonValue = jsonParser.parseString("{\"id\":\"value\"}");
		assertTrue(JsonValueType.OBJECT.equals(jsonValue.getValueType()));
	}

	@Test(expectedExceptions = JsonParseException.class)
	public void testParseWrongJsonExtraKeyValuePairTopLevel() {
		String json = "{\"id\":{}},{\"id2\":\"value2\"}";
		jsonParser.parseString(json);
	}

	@Test(expectedExceptions = JsonParseException.class)
	public void testParseWrongJsonDuplicateKeyValuePair() {
		String json = "{\"id\":{},\"id\":\"value2\"}";
		jsonParser.parseString(json);
	}

	@Test
	public void testAsObjectGetValueType() {
		JsonValue jsonValue = jsonParser.parseStringAsObject("{\"id\":\"value\"}");
		assertTrue(JsonValueType.OBJECT.equals(jsonValue.getValueType()));
	}

	@Test(expectedExceptions = JsonParseException.class)
	public void testWrongAsObjectGetValueType() {
		jsonParser.parseStringAsObject("[\"id\",\"value\"]");
	}

	@Test
	public void testArrayCreate() {
		JsonValue jsonValue = jsonParser.parseString("[\"id\",\"value\"]");
		assertTrue(jsonValue instanceof JsonArray);
	}

	@Test
	public void testArrayGetValueType() {
		JsonValue jsonValue = jsonParser.parseString("[\"id\",\"value\"]");
		assertTrue(JsonValueType.ARRAY.equals(jsonValue.getValueType()));
	}

	@Test
	public void testAsArrayGetValueType() {
		JsonValue jsonValue = jsonParser.parseStringAsArray("[\"id\",\"value\"]");
		assertTrue(JsonValueType.ARRAY.equals(jsonValue.getValueType()));
	}

	@Test(expectedExceptions = JsonParseException.class)
	public void testWrongAsArrayGetValueType() {
		JsonValue jsonValue = jsonParser.parseStringAsArray("{\"id\":\"value\"}");
		assertTrue(JsonValueType.ARRAY.equals(jsonValue.getValueType()));
	}
}
