package com.Alex.database3.mappers;

public interface Mapper<A,B> {

    B mapTo(A a);
    A mapFrom(B b);
}
