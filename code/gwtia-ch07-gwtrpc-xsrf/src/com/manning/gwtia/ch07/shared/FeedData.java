package com.manning.gwtia.ch07.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A DTO that would store a single tweet from Twitter,
 * or a single item from an RSS feed, or a single post
 * on Facebook, etc.
 */
public class FeedData implements IsSerializable
{
  
  /** The created at date/time. */
  private Date createdAt;
  
  /** The text data. */
  private String text;

  /**
   * Instantiates a new feed data.
   */
  public FeedData() {
    /*
     * A no-arg constructor is required for GWT to be able to auto-generate
     * a serializer for this class.
     */
  }

  /**
   * Instantiates a new feed data using the provided inputs.
   *
   * @param createdAt the created at date/time
   * @param text the text data
   */
  public FeedData(Date createdAt, String text) {
    this.createdAt = createdAt;
    this.text = text;
  }

  /**
   * Gets the created at date/time.
   *
   * @return the created at date/time
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Gets the text data.
   *
   * @return the text data
   */
  public String getText() {
    return text;
  }
}
