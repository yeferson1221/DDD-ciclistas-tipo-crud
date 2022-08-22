package org.sofka.demo.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CyclingTeamListSerializer extends StdSerializer<List<CyclingTeam>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -709033919847200488L;

	public CyclingTeamListSerializer() {
		this(null);
	}
	
	public CyclingTeamListSerializer(Class<List<CyclingTeam>> t) {
		super(t);
	}
	
	@Override
	public void serialize(List<CyclingTeam> list, JsonGenerator gen, SerializerProvider provider) throws IOException {
		List<String> teamCodes = new ArrayList<>(); 
		list.forEach((team) -> teamCodes.add(team.getTeamCode()));
		gen.writeObject(teamCodes);
	}

}
