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

package se.uu.ub.cora.json.builder.org;

import se.uu.ub.cora.json.builder.JsonArrayBuilder;
import se.uu.ub.cora.json.builder.JsonObjectBuilder;
import se.uu.ub.cora.json.parser.JsonArray;
import se.uu.ub.cora.json.parser.org.OrgJsonArrayAdapter;

public class OrgJsonArrayBuilderAdapter implements JsonArrayBuilder {

	private static final int TEXT_INDENT = 4;
	private org.json.JSONArray orgJsonArray = new org.json.JSONArray();

	@Override
	public void addString(String value) {
		orgJsonArray.put(value);
	}

	@Override
	public void addJsonObjectBuilder(JsonObjectBuilder jsonObjectBuilder) {
		OrgJsonObjectBuilderAdapter objectBuilderAdapter = (OrgJsonObjectBuilderAdapter) jsonObjectBuilder;
		orgJsonArray.put(objectBuilderAdapter.getWrappedBuilder());
	}

	@Override
	public void addJsonArrayBuilder(JsonArrayBuilder jsonArrayBuilder) {
		OrgJsonArrayBuilderAdapter arrayBuilderAdapter = (OrgJsonArrayBuilderAdapter) jsonArrayBuilder;
		orgJsonArray.put(arrayBuilderAdapter.getWrappedBuilder());
	}

	org.json.JSONArray getWrappedBuilder() {
		return orgJsonArray;
	}

	@Override
	public JsonArray toJsonArray() {
		return OrgJsonArrayAdapter.usingOrgJsonArray(orgJsonArray);
	}

	@Override
	public String toJsonFormattedString() {
		return orgJsonArray.toString();
	}

	@Override
	public String toJsonFormattedPrettyString() {
		return orgJsonArray.toString(TEXT_INDENT);
	}
}
