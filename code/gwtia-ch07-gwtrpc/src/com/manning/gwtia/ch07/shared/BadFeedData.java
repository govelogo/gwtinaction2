package com.manning.gwtia.ch07.shared;

import java.util.Date;

/**
 * An example of a feed data class that GWT will be unable to
 * automatically generate a serializer for due to the fact that
 * it does not have a no-arg constructor.
 * 
 * We use this as the basis to discuss custom serializers.  See
 * the BadFeedData_CustomFieldSerializer class for the custom
 * serializer code that we would write in order to use this DTO
 * with GWT-RPC.
 */
public class BadFeedData
{
  
  /** The created at date/time. */
  private Date createdAt;
  
  /** The text data. */
  private String text;

  
  /**
   * Instantiates a new bad feed data.
   *
   * @param createdAt the created at date/time
   */
  public BadFeedData(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the created at date/time.
   *
   * @return the created at
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Gets the text data.
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text data.
   *
   * @param text the new text
   */
  public void setText(String text) {
    this.text = text;
  }
}
