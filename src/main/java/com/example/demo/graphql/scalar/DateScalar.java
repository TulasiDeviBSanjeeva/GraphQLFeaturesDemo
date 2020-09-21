package com.example.demo.graphql.scalar;

import com.example.demo.utils.DateUtils;
import graphql.language.StringValue;
import graphql.schema.*;

import java.util.Date;

public class DateScalar extends GraphQLScalarType {

    private static final String NAME = "Date";

    public DateScalar() {
        super(NAME, "Date type", new Coercing<Date, String>() {

            @Override
            public String serialize(Object input) {
                if (input instanceof Date) {
                    return DateUtils.toString((Date) input);
                }
                throw new CoercingSerializeException("Invalid Date: " + input);
            }

            @Override
            public Date parseValue(Object input) {
                if (input instanceof String) {
                    Date dt = DateUtils.toDate((String) input);
                    if(dt != null) {
                        return dt;
                    }
                }
                throw new CoercingParseValueException("Invalid Date: " + input);
            }

            @Override
            public Date parseLiteral(Object input) {
                if (!(input instanceof StringValue)) return null;
                String s = ((StringValue) input).getValue();
                Date dt = DateUtils.toDate(s);
                if(dt != null) {
                    return dt;
                }
                return null;
            }
        });
    }
}

