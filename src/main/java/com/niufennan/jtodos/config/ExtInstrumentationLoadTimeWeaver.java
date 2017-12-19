package com.niufennan.jtodos.config;

import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

import java.lang.instrument.ClassFileTransformer;

public class ExtInstrumentationLoadTimeWeaver extends
        InstrumentationLoadTimeWeaver {

    @Override
    public void addTransformer(ClassFileTransformer transformer) {
        try {
            super.addTransformer(transformer);
        } catch (Exception e) {}
    }
}
