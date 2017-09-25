package com.sampathsl.sms.entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * Base entity class
 * @author SAMPATH
 *
 */

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 15235215235123543L;
	
	@Id
    private String id;

	protected BaseEntity() {}
	
	protected BaseEntity(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

}
