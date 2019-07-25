package com.core.models;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class BasicModel implements Serializable {

	protected transient Logger log = LoggerFactory.getLogger(this.getClass());

	private long id;

	private int replyCode = 200;
	private String replyMessage = "message.success";
	/**
	 * payload associated with reply message localization
	 */
	private transient List<String> payLoadList = new ArrayList<String>();
	private String payload = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(int replyCode) {
		this.replyCode = replyCode;
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

	public List<String> getPayLoadList() {
		return payLoadList;
	}

	public void setPayLoadList(List<String> payLoadList) {
		this.payLoadList = payLoadList;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public void addLocalizationParameter(String param) {
		payLoadList.add(param);
		payload = payLoadList.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BasicModel)) {
			return false;
		}
		BasicModel other = (BasicModel) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		try {
			String json = json();
			// remove any password in the text, and don't log to any logger
			json = json.replaceAll("(?i)(\")([^\"]*password[^\"]*)(\":.*?,)", "");
			// json = json.replaceAll("(?i)(\")([^\"]*password[^\"]*)(\":.*?,)", "").replaceAll("\\{", "{\n");
			// json = json.replaceAll("(?i)(\")([^\"]*password[^\"]*)(\":.*?,)", "****,").replaceAll("\\{", "{\n");
			return json;
		} catch (Exception e) {
			log.error("com.knet.imore.entities.model.BasicModel: Caught Exception--- " + e.getMessage());
		}
		return super.toString();
	}

	public String json() throws IOException, JsonGenerationException, JsonMappingException {
		StringWriter writer = new StringWriter();
		new ObjectMapper().writeValue(writer, this);
		String json = writer.toString();
		return json;
	}

}
