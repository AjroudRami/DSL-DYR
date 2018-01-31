package com.polytech.dsl.ssl.core;

import java.util.Map;

import groovy.lang.Binding;
import groovy.lang.Script;

public class GroovyBinding extends Binding {

    private Script script;

    private GroovyModel model;

    public GroovyBinding() {
        super();
    }

    @SuppressWarnings("rawtypes")
    public GroovyBinding(Map variables) {
        super(variables);
    }

    public GroovyBinding(Script script) {
        super();
        this.script = script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void setGroovyModel(GroovyModel model) {
        this.model = model;
    }

    public Object getVariable(String name) {
        if ("scriptback".equals(name)) {
            return script;
        }

        return super.getVariable(name);
    }

    public void setVariable(String name, Object value) {
        super.setVariable(name, value);
    }

    public GroovyModel getGroovyModel() {
        return this.model;
    }

}
