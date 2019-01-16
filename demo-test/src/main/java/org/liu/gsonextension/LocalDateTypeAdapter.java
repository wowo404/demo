package org.liu.gsonextension;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
		return null == src ? null : new JsonPrimitive(formatter.format(src));
	}

	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return null == json ? null : LocalDate.parse(json.getAsString(), formatter);
	}

}
