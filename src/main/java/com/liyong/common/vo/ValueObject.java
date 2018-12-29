package com.liyong.common.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class ValueObject implements Serializable, Cloneable{
    /**
     * 默认的序列化ID
     */
    private static final long serialVersionUID = 1L;
    
    public String jmsType;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this,  ToStringStyle.DEFAULT_STYLE);
    }

    public String getJmsType() {
        return jmsType;
    }

    public void setJmsType(String jmsType) {
        this.jmsType = jmsType;
    }
}
