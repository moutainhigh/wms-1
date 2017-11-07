package com.teeny.wms.app.model;

public class StringMapEntity implements KeyValue<String, String> {

    private String key;
    private String value;

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
<<<<<<< HEAD
        return value;
    }
}
=======
        return this.value;
    }
}
>>>>>>> 7929634cea6564bc710d0b1045a48cb725b1eb05
