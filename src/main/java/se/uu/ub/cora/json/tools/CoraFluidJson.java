package se.uu.ub.cora.json.tools;

import org.json.JSONObject;

import java.util.Collection;

public class CoraFluidJson {
    private static final String NAME_IN_DATA = "name";
    private static final String VALUE = "value";
    private static final String CHILDREN = "children";
    private static final String REPEAT_ID = "repeatId";
    private static final String ATTRIBUTES = "attributes";

    private FluidJson baseJson;

    private CoraFluidJson(FluidJson base) {
        baseJson = base;
    }

    public static CoraFluidJson atomic(String nameInData, String value) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData, null, null).property(VALUE, value));
    }

    private static FluidJson getDataElementJson(String nameInData, String repeatId, CoraAttributesFluidJson attributes) throws FluidJsonException {
        var fluidJson = getFluidJsonWithNameInData(nameInData);
        possiblyAddRepeatId(repeatId, fluidJson);
        possiblyAddAttributes(attributes, fluidJson);
        return fluidJson;
    }

    private static FluidJson getFluidJsonWithNameInData(String nameInData) throws FluidJsonException {
        return FluidJson.json().property(NAME_IN_DATA, nameInData);
    }

    private static void possiblyAddRepeatId(String repeatId, FluidJson fluidJson) throws FluidJsonException {
        if(repeatId != null) {
            fluidJson.property(REPEAT_ID, repeatId);
        }
    }

    private static void possiblyAddAttributes(CoraAttributesFluidJson attributes, FluidJson fluidJson) throws FluidJsonException {
        if(attributes != null) {
            fluidJson.property(ATTRIBUTES, attributes.build());
        }
    }

    public static CoraFluidJson atomic(String nameInData, String value, String repeatId) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData, repeatId, null).property(VALUE, value));
    }

    public static CoraFluidJson group(String nameInData) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData,null, null).array(CHILDREN));
    }

    public static CoraFluidJson group(String nameInData, CoraAttributesFluidJson attributes) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData, null, attributes).array(CHILDREN));
    }

    public static CoraFluidJson group(String nameInData, String repeatId) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData, repeatId, null).array(CHILDREN));
    }

    public static CoraFluidJson group(String nameInData, String repeatId, CoraAttributesFluidJson attributes) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData, repeatId, attributes).array(CHILDREN));
    }

    public static CoraFluidJson group(String nameInData, CoraFluidJson children) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData, null, null).array(CHILDREN, children.baseJson));
    }

    public static CoraFluidJson group(String nameInData, CoraFluidJson children, CoraAttributesFluidJson attributes) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData,null, attributes).array(CHILDREN, children.baseJson));
    }

    public static CoraFluidJson group(String nameInData, String repeatId, CoraFluidJson children, CoraAttributesFluidJson attributes) throws FluidJsonException {
        return new CoraFluidJson(getDataElementJson(nameInData,repeatId, attributes).array(CHILDREN, children.baseJson));
    }

    public static CoraAttributesFluidJson attribute(String name, String value) throws FluidJsonException {
        return CoraAttributesFluidJson.attributes().attribute(name,value);
    }

    public JSONObject build() {
        return baseJson.build();
    }
}
