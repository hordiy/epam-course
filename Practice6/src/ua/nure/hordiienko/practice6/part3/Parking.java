package ua.nure.hordiienko.practice6.part3;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private List<Integer> value;

    public Parking(int size) {
        value = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            value.add(null);
        }
    }

    public boolean arrive(int ind) {
    	if (ind > value.size() - 1) {
			throw new IllegalArgumentException();
		}
    	if (!value.contains(null)) {
            return false;
        }
    	for (int i = ind, j = 0; i < value.size(); i++, j++) {
			if (value.get(i) == null) {
				value.set(i, 1);
				return true;
			}
			if ((value.get(value.size() - 1) != null)) {
				value.set(j, 1);
				return true;
			}
    	}
		return false;
    }
 
    
    public boolean depart(int ind) {
    	if (ind > value.size() - 1) {
			throw new IllegalArgumentException();
		}
        if (!value.contains(1)) {
            return true;
        }
        for (int i = ind; i < value.size(); i++) {
            if (1 == value.get(ind)) {
                value.set(i, null);
                return true;
            }
        }
		return false;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (Integer car : value) {
            sb = (car == null) ? sb.append("0") : sb.append(car);
        }
        System.out.println(sb.toString());
    }
}
