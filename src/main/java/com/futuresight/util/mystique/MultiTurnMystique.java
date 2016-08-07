/*
 * Copyright (c) Balajee TM 2016.
 * All rights reserved.
 */

/*
 * Created on 7 Aug, 2016 by balajeetm
 */
package com.futuresight.util.mystique;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * The Class MultiTurnMystique.
 *
 * @author balajmoh
 */
@Component
public class MultiTurnMystique implements Mystique {

	/** The factory. */
	@Autowired
	private MystiqueFactory factory;

	/* (non-Javadoc)
	 * @see com.futuresight.util.mystique.Mystique#transform(java.util.List, com.google.gson.JsonObject, com.google.gson.JsonElement, com.google.gson.JsonElement)
	 */
	@Override
	public JsonElement transform(List<JsonElement> source, JsonObject deps, JsonElement turn, JsonObject resultWrapper) {
		JsonArray turnArray = turn.getAsJsonArray();
		JsonElement transform = null;
		for (JsonElement turnObject : turnArray) {
			Mystique mystique = factory.getMystique(turnObject);
			transform = mystique.transform(source, deps, turnObject, resultWrapper);
			if (null != transform && !transform.isJsonNull()) {
				break;
			}
		}
		return transform;
	}

}
