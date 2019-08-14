package ua.nure.hordiienko.practice7.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nure.hordiienko.practice7.entity.KnifeDesc;

/**
 * 
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 * This method contains static methods for sorting.
 *
 */

public final class Sorter {
	
	private Sorter() {
		
	}
	
	/**
	 * Sorts knifes by type value
	 */
	
	public static final Comparator<KnifeDesc> SORT_KNIFE_BY_TYPE = new Comparator<KnifeDesc>() {

		@Override
		public int compare(KnifeDesc k1, KnifeDesc k2) {
			return k1.getKnifeType().compareTo(k2.getKnifeType());
		}
	};
	
	/**
	 * Sorts knifes by origin value
	 */
	
	public static final Comparator<KnifeDesc> SORT_KNIFE_BY_ORIGIN = new Comparator<KnifeDesc>() {

		@Override
		public int compare(KnifeDesc k1, KnifeDesc k2) {
			return k1.getKnifeOrigin().compareTo(k2.getKnifeOrigin());
		}
	};
	/**
	 * Sorts knifes by origin value
	 */
	public static final Comparator<KnifeDesc> SORT_KNIFE_BY_NAME = new Comparator<KnifeDesc>() {

		@Override
		public int compare(KnifeDesc k1, KnifeDesc k2) {
			return k1.getName().compareTo(k2.getName());
		}
	};
	
	/***
	 * This method takes Knife object and sorts it with according comparator.
	 */
	
	public static void sortKnifesByType(List<KnifeDesc> knifes) {
		Collections.sort(knifes, SORT_KNIFE_BY_TYPE);
	}
	
	/***
	 * This method takes Knife object and sorts it with according comparator.
	 */
	
	public static void sortKnifesByName(List<KnifeDesc> knifes) {
		Collections.sort(knifes, SORT_KNIFE_BY_NAME);
	}
	
	/***
	 * This method takes Knife object and sorts it with according comparator.
	 */
	
	public static void sortKnifesByOrigin(List<KnifeDesc> knifes) {
		Collections.sort(knifes, SORT_KNIFE_BY_ORIGIN);
	}
}
