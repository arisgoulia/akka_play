package com.aristoula.messages;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AnotherClass {
    
    @TaggedFieldSerializer.Tag(1)
    private String epic;

    @TaggedFieldSerializer.Tag(2)
    private String another;

    public AnotherClass(final String epic) {
        this.epic = epic;
    }

    public String getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("epic", epic)
                .toString();
    }
}
