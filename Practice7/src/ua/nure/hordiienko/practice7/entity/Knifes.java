package ua.nure.hordiienko.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Knifes {

    protected List<KnifeDesc> knif;

    public List<KnifeDesc> getKnife() {
        if (knif == null) {
            knif = new ArrayList<>();
        }
        return knif;
    }
    
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	for (KnifeDesc knife : knif) {
    		builder.append(knife + System.lineSeparator());
    	}
    	return builder.toString();
    }

}
