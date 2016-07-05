package org.xcolab.client.admin.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

public class ConfigurationAttribute implements Serializable {
    public static final TypeProvider<ConfigurationAttribute> TYPES =
            new TypeProvider<>(ConfigurationAttribute.class,
                    new ParameterizedTypeReference<List<ConfigurationAttribute>>() {
                    });

	private static final long serialVersionUID = -2085597890;

	private String name;
	private Long   additionalid;
	private Long   numericvalue;
	private String stringvalue;
	private Double realvalue;

	public ConfigurationAttribute() {}

	public ConfigurationAttribute(ConfigurationAttribute value) {
		this.name = value.name;
		this.additionalid = value.additionalid;
		this.numericvalue = value.numericvalue;
		this.stringvalue = value.stringvalue;
		this.realvalue = value.realvalue;
	}

	public ConfigurationAttribute(
		String name,
		Long   additionalid,
		Long   numericvalue,
		String stringvalue,
		Double realvalue
	) {
		this.name = name;
		this.additionalid = additionalid;
		this.numericvalue = numericvalue;
		this.stringvalue = stringvalue;
		this.realvalue = realvalue;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAdditionalId() {
		return this.additionalid;
	}

	public void setAdditionalId(Long additionalid) {
		this.additionalid = additionalid;
	}

	public Long getNumericValue() {
		return this.numericvalue;
	}

	public void setNumericValue(Long numericvalue) {
		this.numericvalue = numericvalue;
	}

	public String getStringValue() {
		return this.stringvalue;
	}

	public void setStringValue(String stringvalue) {
		this.stringvalue = stringvalue;
	}

	public Double getRealValue() {
		return this.realvalue;
	}

	public void setRealValue(Double realvalue) {
		this.realvalue = realvalue;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ConfigurationAttribute (");

		sb.append(name);
		sb.append(", ").append(additionalid);
		sb.append(", ").append(numericvalue);
		sb.append(", ").append(stringvalue);
		sb.append(", ").append(realvalue);

		sb.append(")");
		return sb.toString();
	}
}
