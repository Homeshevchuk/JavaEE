package dao.fields;

import java.lang.reflect.Field;

/**
 * Created by ustymchyk.nazar on 28.12.2016.
 */
public class FieldWrap {
    private Field field;
    private String string;

    public FieldWrap(Field field, String string) {
        this.field = field;
        this.string = string;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
