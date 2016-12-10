package com.aristoula.messages;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer.Tag;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ComplexClass {

    @Tag(1)
    private String myFate;

    @Tag(2)
    private List<AnotherClass> aList;

//    @Tag(3)
//    private List<AnotherClass> extraListTObeDeleted;


    public ComplexClass(final String myFate, final List<AnotherClass> aList) {
        this.myFate = myFate;
        this.aList = aList;
//        this.extraListTObeDeleted = extraListTObeDeleted;
    }

    public List<AnotherClass> getaList() {
        return aList;
    }

    public String getMyFate() {
        return myFate;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

//    public List<AnotherClass> getExtraListTObeDeleted() {
//        return extraListTObeDeleted;
//    }
}
