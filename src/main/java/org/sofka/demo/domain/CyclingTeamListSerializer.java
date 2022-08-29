package org.sofka.demo.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


/**
 * [
 *  clase  CyclingTeamListSerializer  contiene metodos para serializar
 * ]
 * @version [1,0.0]
 *
 * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
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
