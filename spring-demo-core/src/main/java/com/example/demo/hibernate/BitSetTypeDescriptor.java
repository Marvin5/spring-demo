package com.example.demo.hibernate;

import com.google.common.base.Strings;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;

import java.util.BitSet;

public class BitSetTypeDescriptor extends AbstractTypeDescriptor<BitSet> {
    public static final BitSetTypeDescriptor INSTANCE = new BitSetTypeDescriptor();
    private static final String DELIMITER = ",";

    public BitSetTypeDescriptor() {
        super(BitSet.class);
    }

    @Override
    public String toString(BitSet value) {
        StringBuilder sb = new StringBuilder();
        for (long token : value.toLongArray()) {
            if (sb.length() > 0) {
                sb.append(DELIMITER);
            }
            sb.append(Long.toString(token, 2));
        }
        return sb.toString();
    }

    @Override
    public BitSet fromString(String string) {
        if (Strings.isNullOrEmpty(string)) {
            return null;
        }
        String[] tokens = string.split(DELIMITER);
        long[] values = new long[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            values[i] = Long.valueOf(tokens[i], 2);
        }
        return BitSet.valueOf(values);
    }


    @Override
    public <X> X unwrap(BitSet value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (BitSet.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) toString(value);
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> BitSet wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (String.class.isInstance(value)) {
            return fromString((String) value);
        }
        if (BitSet.class.isInstance(value)) {
            return (BitSet) value;
        }
        throw unknownWrap(value.getClass());
    }
}
