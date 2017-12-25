package net.udevs.hashqr;

import java.util.TreeMap;

import lombok.Data;

@Data
public class Seed implements Flattenable, Readable, Cloneable{
	
	public static final String CODE="CODE";
	
	TreeMap<String,String> metadata;
	
	@Override
	public String flatten() {
		return metadata.toString().replaceAll("(^\\[|\\]|\\{|\\}$)", "");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Seed clone=new Seed();
		clone.metadata=new TreeMap<>(this.metadata);
		return clone;
	}
	
	@Override
	public void read(String flattendedString) {
		this.metadata=new TreeMap<>();
		String keyValues[]=flattendedString.split(",");
		for(String keyValue:keyValues) {
			String keyValuePair[]=keyValue.split("=");
			metadata.put(keyValuePair[0].trim(), keyValuePair[1].trim());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seed other = (Seed) obj;
		if (metadata == null) {
			if (other.metadata != null)
				return false;
		}
		return this.metadata.equals(other.metadata);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
		return result;
	}
	
	
	
}